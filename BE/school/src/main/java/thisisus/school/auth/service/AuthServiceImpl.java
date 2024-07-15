package thisisus.school.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;
import thisisus.school.auth.dto.response.MemberInfoFromIdToken;
import thisisus.school.auth.exception.AlreadyRegisteredEmailException;
import thisisus.school.auth.exception.NotFoundEmailException;
import thisisus.school.auth.infrastructure.AuthTokenGenerator;
import thisisus.school.auth.infrastructure.JwtTokenProvider;
import thisisus.school.auth.infrastructure.kakao.KakaoProperties;
import thisisus.school.auth.infrastructure.kakao.KakaoTokenInfoResponse;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoOauthClient;
import thisisus.school.auth.processor.LoginByIdTokenProcessor;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

	private final MemberRepository memberRepository;
	private final RequestKakaoOauthClient requestKakaoOauthClient;
	private final LoginByIdTokenProcessor loginByIdTokenProcessor;
	private final KakaoProperties kakaoProperties;
	private final AuthTokenGenerator authTokenGenerator;
	private final RefreshTokenValidator refreshTokenValidator;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public IdTokenResponse getIdToken(final String code) {
		final KakaoTokenInfoResponse kakaoTokenInfoResponse = requestKakaoOauthClient.getToken(
			kakaoProperties.getClientId(),
			kakaoProperties.getRedirectUrl(),
			code);
		return new IdTokenResponse(kakaoTokenInfoResponse.getIdToken());
	}

	@Override
	@Transactional
	public AuthResponse signUp(final String idToken, final SignUpRequest signUpRequest) {
		final MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
		if (!validateEmail(memberInfoFromIdToken.getEmail())) {
			Member member = signUpRequest.toEntity(memberInfoFromIdToken.getEmail());
			memberRepository.save(member);
			AuthResponse authResponse = authTokenGenerator.generate(member);
			member.setRefreshToken(authResponse.getRefreshToken());
			return authResponse;
		} else {
			throw new AlreadyRegisteredEmailException();
		}
	}

	@Override
	@Transactional
	public AuthResponse login(final String idToken) {
		final MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
		if (validateEmail(memberInfoFromIdToken.getEmail())) {
			Member member = memberRepository.findByEmail(memberInfoFromIdToken.getEmail());
			AuthResponse authResponse = authTokenGenerator.generate(member);
			member.setRefreshToken(authResponse.getRefreshToken());
			return authResponse;
		} else {
			throw new NotFoundEmailException();
		}
	}

	@Override
	@Transactional
	public void logout(final Long memberId) {
		final Member member = memberRepository.findById(memberId).
			orElseThrow(NotFoundMemberException::new);
		member.setRefreshToken(null);
	}

	@Override
	@Transactional
	public AuthResponse reissueToken(final String refreshToken) {
		refreshTokenValidator.validateToken(refreshToken);
		final Long memberId = Long.valueOf(jwtTokenProvider.getMemberId(refreshToken));
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		refreshTokenValidator.validateLogoutToken(member);
		refreshTokenValidator.validateTokenOwner(refreshToken, member);
		AuthResponse authResponse = authTokenGenerator.generate(member);
		member.setRefreshToken(authResponse.getRefreshToken());
		return authResponse;
	}

	private Boolean validateEmail(final String email) {
		return memberRepository.existsByEmail(email);
	}
}
package thisisus.school.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthTokenResponse;
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
import thisisus.school.redis.Auth.service.RefreshTokenRedisService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

	private final MemberRepository memberRepository;
	private final RefreshTokenRedisService refreshTokenRedisService;
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
	public void signUp(final String idToken, final SignUpRequest signUpRequest) {
		final MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
		if (!validateEmail(memberInfoFromIdToken.getEmail())) {
			Member member = signUpRequest.toEntity(memberInfoFromIdToken.getEmail(),
				signUpRequest.name(), signUpRequest.role(), signUpRequest.nickname());
			memberRepository.save(member);
		} else {
			Member member = memberRepository.findByEmail(memberInfoFromIdToken.getEmail());
			if (member.getMemberStatus().equals("DELETED")) {
				member.reRegistration();
			} else {
				throw new AlreadyRegisteredEmailException();
			}
		}
	}

	@Override
	@Transactional
	public AuthTokenResponse login(final String idToken) {
		final MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
		if (validateEmail(memberInfoFromIdToken.getEmail())) {
			Member member = memberRepository.findByEmail(memberInfoFromIdToken.getEmail());
			AuthTokenResponse authTokenResponse = authTokenGenerator.generate(member);
			refreshTokenRedisService.save(authTokenResponse.getRefreshToken(), member.getId());
			return authTokenResponse;
		} else {
			throw new NotFoundEmailException();
		}
	}

	@Override
	@Transactional
	public void logout(final String token) {
		refreshTokenValidator.validateLogoutToken(token);
		refreshTokenRedisService.delete(token);
	}

	@Override
	@Transactional
	public AuthTokenResponse reissueToken(final String refreshToken) {
		refreshTokenValidator.validateToken(refreshToken);
		final Long memberId = Long.valueOf(jwtTokenProvider.getMemberId(refreshToken));
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		refreshTokenValidator.validateLogoutToken(refreshToken);
		refreshTokenValidator.validateTokenOwner(refreshToken, member.getId());
		AuthTokenResponse authTokenResponse = authTokenGenerator.generate(member);
		refreshTokenRedisService.save(authTokenResponse.getRefreshToken(), member.getId());
		return authTokenResponse;
	}

	private Boolean validateEmail(final String email) {
		return memberRepository.existsByEmail(email);
	}

}
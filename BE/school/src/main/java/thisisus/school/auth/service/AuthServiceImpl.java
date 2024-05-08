package thisisus.school.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;
import thisisus.school.auth.dto.response.MemberInfoFromIdToken;
import thisisus.school.auth.exception.AlreadyRegisteredEmailException;
import thisisus.school.auth.exception.NotFoundEmailException;
import thisisus.school.auth.infrastructure.CreateToken;
import thisisus.school.auth.infrastructure.kakao.KakaoProperties;
import thisisus.school.auth.infrastructure.kakao.KakaoTokenInfoResponse;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoMemberInfoClient;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoOauthClient;
import thisisus.school.auth.processor.LoginByIdTokenProcessor;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final RequestKakaoOauthClient requestKakaoOauthClient;
    private final RequestKakaoMemberInfoClient requestKakaoMemberInfoClient;
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final KakaoProperties kakaoProperties;
    private final CreateToken createToken;

    @Override
    public IdTokenResponse getIdToken(String code) {
        KakaoTokenInfoResponse kakaoTokenInfoResponse = requestKakaoOauthClient.getToken(
            kakaoProperties.getClientId(),
            kakaoProperties.getRedirectUrl(),
            code);
        return new IdTokenResponse(kakaoTokenInfoResponse.getIdToken());
    }

    @Override
    public AuthResponse login(String idToken) {
        MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
        if (validateEmail(memberInfoFromIdToken.getEmail())) {
            Member member = memberRepository.findByEmail(memberInfoFromIdToken.getEmail());
            return createToken.createAccessToken(member);
        } else {
            throw new NotFoundEmailException();
        }
    }

    @Override
    @Transactional
    public AuthResponse signUp(String idToken, SignUpRequest signUpRequest) {
        MemberInfoFromIdToken memberInfoFromIdToken = loginByIdTokenProcessor.getMemberInfoFromIdToken(idToken);
        if (!validateEmail(memberInfoFromIdToken.getEmail())) {
            Member member = signUpRequest.toEntity(memberInfoFromIdToken.getEmail());
            memberRepository.save(member);
            AuthResponse authResponse = createToken.createAccessToken(member);
            member.setRefreshToken(authResponse.getRefreshToken());
            return authResponse;
        } else {
            throw new AlreadyRegisteredEmailException();
        }
    }

    private Boolean validateEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
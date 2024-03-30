package thisisus.school.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.KakaoTokenResponse;
import thisisus.school.auth.exception.AlreadyRegisteredEmail;
import thisisus.school.auth.exception.NotFoundEmail;
import thisisus.school.auth.infrastructure.CreateToken;
import thisisus.school.auth.infrastructure.kakao.KakaoMemberInfoResponse;
import thisisus.school.auth.infrastructure.kakao.KakaoProperties;
import thisisus.school.auth.infrastructure.kakao.KakaoTokenInfoResponse;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoMemberInfoClient;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoOauthClient;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final MemberRepository memberRepository;
  private final RequestKakaoOauthClient requestKakaoOauthClient;
  private final RequestKakaoMemberInfoClient requestKakaoMemberInfoClient;
  private final KakaoProperties kakaoProperties;
  private final CreateToken createToken;

  public KakaoTokenResponse getAccessTokenFromKakao(String code) {
    KakaoTokenInfoResponse kakaoTokenInfoResponse = requestKakaoOauthClient.getToken(
        kakaoProperties.getClientId(),
        kakaoProperties.getRedirectUrl(),
        code);
    return new KakaoTokenResponse(kakaoTokenInfoResponse.getAccessToken());
  }

  public AuthResponse login(String kakaoAccessToken) {
    KakaoMemberInfoResponse kakaoMemberInfoResponse = requestKakaoMemberInfoClient.getId("Bearer " + kakaoAccessToken);
    if (validateKakaoId(kakaoMemberInfoResponse.getId())) {
      Member member = memberRepository.findByKakaoId(kakaoMemberInfoResponse.getId());
      return createToken.createAccessToken(member);
    } else throw new NotFoundEmail();
  }

  @Transactional
  public AuthResponse signUp(String kakaoAccessToken, SignUpRequest signUpRequest) {
    KakaoMemberInfoResponse kakaoMemberInfoResponse = requestKakaoMemberInfoClient.getId("Bearer " + kakaoAccessToken);
    if (!validateKakaoId(kakaoMemberInfoResponse.getId())) {
      Member member = signUpRequest.toEntity(kakaoMemberInfoResponse.getKakaoAccount().getEmail(),
          kakaoMemberInfoResponse.getId());
      memberRepository.save(member);
      AuthResponse authResponse = createToken.createAccessToken(member);
      member.setRefreshToken(authResponse.getRefreshToken());
      return authResponse;
    } else throw new AlreadyRegisteredEmail();
  }

  private Boolean validateKakaoId(Long kakaoId) {
    return memberRepository.existsByKakaoId(kakaoId);
  }
}

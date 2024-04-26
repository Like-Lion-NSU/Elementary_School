package thisisus.school.auth.infrastructure.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thisisus.school.auth.infrastructure.oidc.PublicKeysDto;

@FeignClient(name = "kakao", url = "https://kauth.kakao.com")
public interface RequestKakaoOauthClient {

  @PostMapping("/oauth/token?grant_type=authorization_code")
  KakaoTokenInfoResponse getToken(
      @RequestParam("client_id") String clientId,
      @RequestParam("redirect_uri") String redirectUri,
      @RequestParam("code") String code
  );

  @GetMapping("/.well-known/jwks.json")
  PublicKeysDto getPublicKeys();
}
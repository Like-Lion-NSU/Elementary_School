package thisisus.school.auth.infrastructure.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "https://kauth.kakao.com")
public interface RequestKakaoOauthClient {
  @PostMapping("/oauth/token?grant_type=authorization_code")
  KakaoTokenInfoResponse getToken(
      @RequestParam("client_id") String clientId,
      @RequestParam("redirect_uri") String redirectUri,
      @RequestParam("code") String code
  );
}

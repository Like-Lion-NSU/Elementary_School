package thisisus.school.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.KakaoIdResponse;
import thisisus.school.auth.dto.response.KakaoTokenResponse;
import thisisus.school.auth.service.AuthService;
import thisisus.school.common.response.SuccessResonse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

  private final AuthService authService;

  @GetMapping("/oauth/authorize")
  public SuccessResonse getKakaoAccessToken(@RequestParam("code") String code) {
    KakaoTokenResponse kakaoTokenResponse = authService.getAccessTokenFromKakao(code);
    return SuccessResonse.of(kakaoTokenResponse);
  }

  @PostMapping("/login")
  public SuccessResonse login(@RequestParam("kakaoAccessToken") String kakaoAccessToken) {
    AuthResponse authResponse = authService.login(kakaoAccessToken);
    return SuccessResonse.of(authResponse);
  }

  @PostMapping("/sign-up")
  public SuccessResonse signUp(@RequestParam("kakaoAccessToken") String kakaoAccessToken, @RequestBody SignUpRequest signUpRequest) {
    AuthResponse authResponse = authService.signUp(kakaoAccessToken, signUpRequest);
    return SuccessResonse.of(authResponse);
  }

  @PostMapping("/refresh")
  public SuccessResonse refreshToken() {
    return SuccessResonse.of();
  }
}

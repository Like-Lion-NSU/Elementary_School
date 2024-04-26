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
import thisisus.school.auth.dto.response.IdTokenResponse;
import thisisus.school.auth.service.AuthService;
import thisisus.school.common.response.SuccessResonse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/idToken")
    public SuccessResonse getIdToken(
        @RequestParam("code") String code
    ) {
        IdTokenResponse idTokenResponse = authService.getIdToken(code);
        return SuccessResonse.of(idTokenResponse);
    }

    @PostMapping("/login")
    public SuccessResonse login(
        @RequestParam("idToken") String idToken
    ) {
        AuthResponse authResponse = authService.login(idToken);
        return SuccessResonse.of(authResponse);
    }

    @PostMapping("/sign-up")
    public SuccessResonse signUp(
        @RequestParam("idToken") String idToken,
        @RequestBody SignUpRequest signUpRequest
    ) {
        AuthResponse authResponse = authService.signUp(idToken, signUpRequest);
        return SuccessResonse.of(authResponse);
    }

    @PostMapping("/refresh")
    public SuccessResonse refreshToken() {
        return SuccessResonse.of();
    }
}
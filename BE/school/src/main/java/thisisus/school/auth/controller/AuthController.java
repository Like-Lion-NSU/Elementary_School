package thisisus.school.auth.controller;

import static org.springframework.boot.web.server.Cookie.SameSite.*;
import static org.springframework.http.HttpHeaders.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
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

	@PostMapping("/sign-up")
	public SuccessResonse signUp(
		@RequestParam("idToken") String idToken,
		@RequestBody SignUpRequest signUpRequest,
		HttpServletResponse response
	) {
		AuthResponse authResponse = authService.signUp(idToken, signUpRequest);
		ResponseCookie cookie = getCookie(authResponse.getRefreshToken());
		response.addHeader(SET_COOKIE, cookie.toString());
		return SuccessResonse.of(authResponse);
	}

	@PostMapping("/login")
	public SuccessResonse login(
		@RequestParam("idToken") String idToken,
		HttpServletResponse response
	) {
		AuthResponse authResponse = authService.login(idToken);
		ResponseCookie cookie = getCookie(authResponse.getRefreshToken());
		response.addHeader(SET_COOKIE, cookie.toString());
		return SuccessResonse.of(authResponse);
	}

	@DeleteMapping("/logout")
	public SuccessResonse logout(
		@RequestHeader("refresh") String refreshToken
	) {
		authService.logout(refreshToken);
		return SuccessResonse.of();
	}

	@PostMapping("/token/reissue")
	public SuccessResonse reissueToken(
		@RequestHeader("refresh") String refreshToken,
		HttpServletResponse response
	) {
		AuthResponse authResponse = authService.reissueToken(refreshToken);
		ResponseCookie cookie = getCookie(authResponse.getRefreshToken());
		response.addHeader(SET_COOKIE, cookie.toString());
		return SuccessResonse.of(authResponse);
	}

	private ResponseCookie getCookie(
		final String refreshToken
	) {
		ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", refreshToken)
			.httpOnly(true)
			.secure(true)
			.path("/api/admin/auth/reissue-token")
			.sameSite(NONE.attributeValue())
			.build();
		return cookie;
	}
}
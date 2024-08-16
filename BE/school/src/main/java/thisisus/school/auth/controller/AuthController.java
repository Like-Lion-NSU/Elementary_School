package thisisus.school.auth.controller;

import static org.springframework.boot.web.server.Cookie.SameSite.*;
import static org.springframework.http.HttpHeaders.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthTokenResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;
import thisisus.school.auth.service.AuthService;
import thisisus.school.common.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@GetMapping("/idToken")
	public SuccessResponse getIdToken(
		@RequestParam("code") String code
	) {
		IdTokenResponse idTokenResponse = authService.getIdToken(code);
		return SuccessResponse.of(idTokenResponse);
	}

	@PostMapping("/sign-up")
	public SuccessResponse signUp(
		@RequestParam("idToken") String idToken,
		@RequestBody @Valid SignUpRequest signUpRequest
	) {
		authService.signUp(idToken, signUpRequest);
		return SuccessResponse.of();
	}

	@PostMapping("/login")
	public SuccessResponse login(
		@RequestParam("idToken") String idToken,
		HttpServletResponse response
	) {
		AuthTokenResponse authTokenResponse = authService.login(idToken);
		ResponseCookie cookie = getCookie(authTokenResponse.getRefreshToken());
		response.addHeader(SET_COOKIE, cookie.toString());
		return SuccessResponse.of(authTokenResponse);
	}

	@DeleteMapping("/logout")
	public SuccessResponse logout(
		@RequestHeader("refresh") String refreshToken
	) {
		authService.logout(refreshToken);
		return SuccessResponse.of();
	}

	@PostMapping("/token/reissue")
	public SuccessResponse reissueToken(
		@RequestHeader("refresh") String refreshToken,
		HttpServletResponse response
	) {
		AuthTokenResponse authTokenResponse = authService.reissueToken(refreshToken);
		ResponseCookie cookie = getCookie(authTokenResponse.getRefreshToken());
		response.addHeader(SET_COOKIE, cookie.toString());
		return SuccessResponse.of(authTokenResponse);
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
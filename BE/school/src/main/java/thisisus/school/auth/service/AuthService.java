package thisisus.school.auth.service;

import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;

public interface AuthService {

	IdTokenResponse getIdToken(String code);

	AuthResponse signUp(String idToken, SignUpRequest signUpRequest);

	AuthResponse login(String idToken);

	void logout(Long memberId);

	AuthResponse reissueToken(String refreshToken);
}

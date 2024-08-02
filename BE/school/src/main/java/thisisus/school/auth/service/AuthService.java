package thisisus.school.auth.service;

import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthTokenResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;

public interface AuthService {

	IdTokenResponse getIdToken(String code);

	void signUp(String idToken, SignUpRequest signUpRequest);

	AuthTokenResponse login(String idToken);

	void logout(String refreshToken);

	AuthTokenResponse reissueToken(String refreshToken);
}

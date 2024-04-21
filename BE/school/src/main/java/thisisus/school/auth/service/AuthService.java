package thisisus.school.auth.service;

import thisisus.school.auth.dto.request.SignUpRequest;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.auth.dto.response.IdTokenResponse;

public interface AuthService {

    IdTokenResponse getIdToken(String code);

    AuthResponse login(String idToken);

    AuthResponse signUp(String idToken, SignUpRequest signUpRequest);

}

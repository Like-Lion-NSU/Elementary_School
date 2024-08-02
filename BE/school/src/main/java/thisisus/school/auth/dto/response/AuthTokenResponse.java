package thisisus.school.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthTokenResponse {

    private String accessToken;
    private String refreshToken;
}
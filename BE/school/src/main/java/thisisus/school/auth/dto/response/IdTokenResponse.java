package thisisus.school.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdTokenResponse {

    private String idToken;
}
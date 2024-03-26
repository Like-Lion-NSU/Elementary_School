package thisisus.school.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoTokenResponse {
  private String accessToken;
}

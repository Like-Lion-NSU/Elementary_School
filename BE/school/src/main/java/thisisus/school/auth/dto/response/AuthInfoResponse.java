package thisisus.school.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfoResponse {
	Long memberId;
	String role;
}

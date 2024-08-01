package thisisus.school.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	GUEST, USER;

	public static boolean isGuest(String role) {
		return GUEST.equals(Role.valueOf(role));
	}
}
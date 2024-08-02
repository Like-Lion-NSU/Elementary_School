package thisisus.school.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	GUEST, STUDENT, TEACHER;

	public static boolean isGuest(String role) {
		return GUEST.equals(Role.valueOf(role));
	}

	public static boolean isNotTeacher(String role){
		return !TEACHER.equals(Role.valueOf(role));
	}
}
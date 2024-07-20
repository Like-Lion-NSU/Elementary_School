package thisisus.school.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import thisisus.school.member.domain.Role;

public record UpdateMemberRequest(
	@NotNull(message = "닉네임은 빈칸일 수가 없습니다.")
	@NotBlank(message = "닉네임은 공백일 수가 없습니다.")
	@Size(min = 1, max = 15, message = "닉네임은 1 ~ 15자 사이어야 합니다.")
	String nickname,
	@NotNull(message = "역할은 빈칸일 수가 없습니다.")
	@NotBlank(message = "역할은 공백일 수가 없습니다.")
	Role role
) {

}
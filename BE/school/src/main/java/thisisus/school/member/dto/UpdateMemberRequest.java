package thisisus.school.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import thisisus.school.member.domain.Role;

public record UpdateMemberRequest(
	@NotNull
	@NotBlank
	String nickname,
	@NotNull
	@NotBlank
	Role role
) {

}
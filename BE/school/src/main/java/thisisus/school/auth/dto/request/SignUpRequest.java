package thisisus.school.auth.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.Role;

public record SignUpRequest(
    @NotNull(message = "사용자 이름은 빈칸일 수가 없습니다.")
    @NotBlank(message = "사용자 이름은 공백일 수가 없습니다.")
    @Size(min = 2, max = 10, message = "사용자 이름은 2 ~ 10 사이어야 합니다.")
    String name,
    @NotNull(message = "역할은 빈칸일 수가 없습니다.")
    Role role,
    @NotNull(message = "닉네임은 빈칸일 수가 없습니다.")
    @NotBlank(message = "닉네임은 공백일 수가 없습니다.")
    @Size(min = 1, max = 15, message = "닉네임은 1 ~ 15자 사이어야 합니다.")
    String nickname
) {

    public Member toEntity(String email, String name, Role role, String nickname) {
        return Member.builder()
            .email(email)
            .name(name)
            .role(role)
            .nickname(nickname)
            .build();
    }
}
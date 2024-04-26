package thisisus.school.auth.dto.request;

import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.Role;

public record SignUpRequest(
    String name,
    Role role,
    String nickname
) {

    public Member toEntity(String email) {
        return Member.builder()
            .email(email)
            .name(name)
            .role(role)
            .nickname(nickname)
            .build();
    }
}
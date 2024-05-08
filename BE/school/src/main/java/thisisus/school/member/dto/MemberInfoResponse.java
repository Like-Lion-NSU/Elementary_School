package thisisus.school.member.dto;

import thisisus.school.member.domain.Role;

public record MemberInfoResponse(
    String emai,
    Role role
) {

}
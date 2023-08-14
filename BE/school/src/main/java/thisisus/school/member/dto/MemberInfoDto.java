package thisisus.school.member.dto;

import lombok.Builder;

@Builder
public class MemberInfoDto {

    private String email;
    private String role;
    private Long point;

}

package thisisus.school.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

    private String email;
    private String role;
    private Long point;

}

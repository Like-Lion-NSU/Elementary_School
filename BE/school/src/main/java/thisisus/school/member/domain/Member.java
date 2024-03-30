package thisisus.school.member.domain;

import lombok.*;
import thisisus.school.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long kakaoId;
    private String name;
    private String email;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    private String refreshToken;

    @Builder
    public Member(Long kakaoId, String name, String email, String nickname, Role role){
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.memberStatus = MemberStatus.ACTIVE;
    }

    public void delete(){
        this.memberStatus = MemberStatus.DELETED;
    }
    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
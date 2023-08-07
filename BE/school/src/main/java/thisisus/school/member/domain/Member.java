package thisisus.school.member.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // PK 시스템 상의 id 값

    /**
     * 회원 이름, 이메일, 생년월일은 소셜 로그인(구글, 카카오톡)을 통해 가져온다.
     */
    private String name;        // 회원 이름

    private String email;       // 회원 이메일

    /**
     * 회원 가입 후 선택에 따라 role 정해짐
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 기본 point 값 얼마?
     */
    private Long point;

    private LocalDateTime lastLogin;

    private String provider;

    public Member update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Builder
    public Member(Long id, String name, String email, Role role, Long point, LocalDateTime lastLogin, String provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.point = point;
        this.lastLogin = lastLogin;
        this.provider = provider;
    }


}
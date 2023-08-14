package thisisus.school.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import thisisus.school.member.security.util.AuthProvider;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // PK 시스템 상의 id 값

    private String name;        // 회원 이름

    private String email;       // 회원 이메일

    private String role;

    private Long point;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String refreshToken;


    public Member update(String name) {
        this.name = name;
        return this;
    }

    public Member setRole(String role) {
        this.role = role;
        return this;
    }

    @Builder
    public Member(Long id, String name, String email, String role, Long point, LocalDateTime lastLogin, AuthProvider provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.point = point;
        this.lastLogin = lastLogin;
        this.provider = provider;
    }


//    @JsonIgnore     // 양뱡향 관계에서는 한쪽에 JsonIgnor를 하지 않으면 무한루프에 걸림!
//    @OneToMany(mappedBy = "member")
//    private List<Role> roles = new ArrayList<>(); // List로 역할(Role)을 담을 수 있도록 추가

//    @Enumerated(EnumType.STRING)
//    private Role role;

    //    public List<Role> getRoles() {
//        return roles;
//    }


    //    public String getRoleKey() {
////        return this.role.getKey();
//        return this.role != null ? this.role.getKey() : null;
//    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        if (this.role != null) {
//            authorities.add(new SimpleGrantedAuthority(this.role.getKey()));
//        }
//        return authorities;
////        return null;
//    }


}
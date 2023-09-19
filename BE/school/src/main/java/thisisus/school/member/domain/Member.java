package thisisus.school.member.domain;

import lombok.*;
import thisisus.school.common.BaseEntity;
import thisisus.school.member.security.util.AuthProvider;
import thisisus.school.post.domain.Comment;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostLiked;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Member extends BaseEntity{

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

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PostLiked> likes = new ArrayList<>();


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
        this.setDeleted(false);
    }

    public void delete(){
        this.name=null;
        this.email=null;
        this.role=null;
        this.point=null;
        this.lastLogin=null;
        this.provider=null;
        this.refreshToken=null;
        this.setUpdateAt(null);
        this.setDeleted(true);
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
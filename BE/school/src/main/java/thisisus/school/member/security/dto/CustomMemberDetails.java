package thisisus.school.member.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import thisisus.school.member.domain.Member;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomMemberDetails implements UserDetails, OAuth2User{

    private Member member;
    private Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomMemberDetails(Member member, Collection<GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }

    public static CustomMemberDetails create(Member member) {
        return new CustomMemberDetails(
                member,
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey()))
        );
    }

    public static CustomMemberDetails create(Member member, Map<String, Object> attributes) {
        CustomMemberDetails cusztomMemberDetails = create(member);
        cusztomMemberDetails.setAttributes(attributes);

        return cusztomMemberDetails;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();

        collections.add(() -> {
            return "ROLE_" + member.getRole();
        });

        return collections;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return member.getName();
    }
}

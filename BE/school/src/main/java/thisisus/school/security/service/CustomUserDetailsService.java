package thisisus.school.security.service;

import thisisus.school.security.common.converters.ProviderUserRequest;
import thisisus.school.security.model.users.PrincipalUser;
import thisisus.school.security.model.users.ProviderUser;
import thisisus.school.security.model.users.User;
import thisisus.school.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService extends AbstractOAuth2UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            user = User.builder()
                    .id("1")
                    .username("onjsdnjs")
                    .password("{noop}1234")
                    .authorities(AuthorityUtils.createAuthorityList("ROLE_USER"))
                    .email("onjsdnjs@gmail.com")
                    .build();
        }

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(user);
        ProviderUser providerUser = providerUser(providerUserRequest);

        selfCertificate(providerUser);

        return new PrincipalUser(providerUser);
    }
}


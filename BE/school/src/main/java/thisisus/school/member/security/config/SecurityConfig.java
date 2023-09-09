package thisisus.school.member.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtAuthenticationFilter;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.repository.CookieAuthorizationRequestRepository;
import thisisus.school.member.security.service.CustomOAuth2UserServiceTest;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserServiceTest customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final MemberRepository memberRepository;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
//                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/login", "/social/**", "/oauth2/**", "/role").permitAll()
//                .antMatchers("/v1/**").hasAnyRole("ROLE_STUDENT","ROLE_TEACHER")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("http://115.85.183.239/v1/home")
                .authorizationEndpoint()
                .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
        ;
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        ;
        http.logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "accessToken", "refreshToken")
                .logoutSuccessUrl("http://27.96.131.94/") // 로그아웃 성공시
                .permitAll()
        ;
        http.
                addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
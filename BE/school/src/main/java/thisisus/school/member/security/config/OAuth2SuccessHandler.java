//package thisisus.school.member.security.config;
////
////import lombok.RequiredArgsConstructor;
////import lombok.extern.slf4j.Slf4j;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.token.Token;
////import org.springframework.security.core.token.TokenService;
////import org.springframework.security.oauth2.core.user.OAuth2User;
////import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
////import org.springframework.stereotype.Component;
////import org.springframework.web.util.UriComponentsBuilder;
////import thisisus.school.member.security.dto.CustomMemberDetails;
////
////import javax.servlet.ServletException;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////
////@Slf4j
////@RequiredArgsConstructor
////@Component
////public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
////    private final TokenService tokenService;
////
////    @Override
////    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
////            throws IOException, ServletException {
////        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
////        CustomMemberDetails customMemberDetails = userRequestMapper.toDto(oAuth2User);
////
////        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
////        // 최초 로그인이라면 회원가입 처리를 한다.
////        String targetUrl;
////        log.info("토큰 발행 시작");
////
////        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
////        log.info("{}", token);
////        targetUrl = UriComponentsBuilder.fromUriString("/home")
////                .queryParam("token", "token")
////                .build().toUriString();
////        getRedirectStrategy().sendRedirect(request, response, targetUrl);
////    }
////}
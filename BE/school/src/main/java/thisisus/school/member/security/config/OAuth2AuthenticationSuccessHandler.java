package thisisus.school.member.security.config;



import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import thisisus.school.member.security.repository.CookieAuthorizationRequestRepository;
import thisisus.school.member.security.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;

import static thisisus.school.member.security.repository.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${oauth.authorizedRedirectUri}")
    private String redirectUri;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler.class);
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("redirect URIs are not matched.");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        //JWT 생성
        LOGGER.info("[JWT 생성]");
//        String accessToken = jwtTokenProvider.createAccessToken(authentication);
//        jwtTokenProvider.createRefreshToken(authentication, response);

        Map<String, String> tokens = jwtTokenProvider.generateToken(authentication);
        try {
            String accessToken = URLEncoder.encode(tokens.get("accessToken"), "utf-8");
            String refreshToken = URLEncoder.encode(tokens.get("refreshToken"), "utf-8");

            Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(60 * 60 * 24 * 1);
            accessTokenCookie.setHttpOnly(false);
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(60 * 60 * 24 * 7); // Set the appropriate expiration for the refresh token
            refreshTokenCookie.setHttpOnly(false);
            response.addCookie(refreshTokenCookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();
        Member findMember = memberRepository.getByEmail(member.getEmail());

        if (findMember.getRole().equals("NOT_ROLE")) {
            return UriComponentsBuilder.fromUriString("http://27.96.131.94/role")
                    .build().toString();
        }

//         본 코드
//        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, response);
        LOGGER.info("[JWT 생성 완료]");
        return UriComponentsBuilder.fromUriString("http://27.96.131.94/home")
//                .queryParam("TOKEN", tokenInfo.getAccessToken())
//                .queryParam("REFRESH_TOKEN", tokenInfo.getRefreshToken())
                .build().toString();


        /* 08-10 본 코드
        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        log.info("JWT Token 생성 완료");

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", tokenInfo.getAccessToken())
                .build().toUriString();

         */
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(redirectUri);

        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }
}
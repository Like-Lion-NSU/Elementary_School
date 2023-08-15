package thisisus.school.member.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${oauth.refresh-cookie-key}")
    private String cookieKey;

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

        public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
            // 1. validation Refresh Token
            String oldRefreshToken = CookieUtils.getCookie(request, cookieKey)
                    .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("No Refresh Token Cookie"));

            if (!jwtTokenProvider.validateToken(oldAccessToken)) {
                throw new RuntimeException("Not Validated Refresh Token");
            }

            // 2. 유저 정보 얻기
            Authentication authentication = jwtTokenProvider.getAuthentication(oldAccessToken);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            Long id = Long.valueOf(customUserDetails.getName());

            // 3. Match Refresh Token
            String savedToken = memberRepository.getRefreshTokenById(id);

            if (!savedToken.equals(oldAccessToken)) {
                throw new RuntimeException("Not Matched Refresh Token");
            }

            // 4. Jwt 갱신
            String accessToken = jwtTokenProvider.createAccessToken(authentication);
            jwtTokenProvider.createRefreshToken(authentication/*, response*/);

            return accessToken;
        }
}

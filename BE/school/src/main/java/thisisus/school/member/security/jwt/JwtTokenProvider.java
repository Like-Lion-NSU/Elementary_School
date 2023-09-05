package thisisus.school.member.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
//@NoArgsConstructor
//@RequiredArgsConstructor
public class JwtTokenProvider {

    private final String SECRET_KEY;
    private final String COOKIE_REFRESH_TOKEN_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60;
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7;
    private final String AUTHORITIES_KEY = "role";

    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private MemberRepository memberRepository;


    public JwtTokenProvider(@Value("${oauth.secret-key}") String secretKey, @Value("${oauth.refresh-cookie-key}") String cookieKey) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.COOKIE_REFRESH_TOKEN_KEY = cookieKey;
    }

    public Map<String, String> generateToken(Authentication authentication) {
        Map<String, String> tokens = new HashMap<String, String>();
        LOGGER.info("[JWT] JWT 토큰 생성 시작");
        LOGGER.info("Authentication Principal: {}", authentication.getPrincipal());
        LOGGER.info("[AccessToken] Access 토큰 생성 시작");
        String accessToken = createAccessToken(authentication);
        LOGGER.info("[AccessToken] Access 토큰 생성 완료");

        LOGGER.info("[AccessToken] refresh 토큰 생성 시작");
        String refreshToken = createRefreshToken(authentication);
        LOGGER.info("[AccessToken] refresh 토큰 생성 완료");

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;

//        return MemberResponseDto.TokenInfo.builder()
//                .grantType("Bearer")
//                .accessToken(accessToken)
//                .accessTokenExpirationTime(ACCESS_TOKEN_EXPIRE_LENGTH)
//                .refreshToken(refreshToken)
//                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_LENGTH)
//                .build();
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        Date accessValidity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);
        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();

        String memberEmail = member.getEmail();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        LOGGER.info("[role] JWT 생성 시 role : {}", role);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(memberEmail)
                .claim(AUTHORITIES_KEY, role)
                .setIssuer("debrains")
                .setIssuedAt(now)
                .setExpiration(accessValidity)
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        Date now = new Date();
        Date refreshValidity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH);

        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer("debrains")
                .setIssuedAt(now)
                .setExpiration(refreshValidity)
                .compact();

        saveRefreshToken(authentication, refreshToken);

//        ResponseCookie cookie = ResponseCookie.from(COOKIE_REFRESH_TOKEN_KEY, refreshToken)
//                .httpOnly(true)
//                .secure(true)
//                .sameSite("Lax")
//                .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH/1000)
//                .path("/")
//                .build();
//        response.addHeader("Set-Cookie", cookie.toString());

        return refreshToken;

    }

    private void saveRefreshToken(Authentication authentication, String refreshToken) {
        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();
        Long id = Long.valueOf(member.getName());

        memberRepository.updateRefreshToken(id, refreshToken);
    }

    // AccessToken을 검사하고 얻은 정보로 Authentication 객체 생성
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        LOGGER.info("[JWT] Creating Authentication from AccessToken");

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        CustomUserDetails principal = new CustomUserDetails(claims.getSubject(), authorities);
        LOGGER.info("[principal] principal : {}", principal);

//        CustomUserDetails principal2 = userDetailsService.loadMemberbyEmail(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "Bearer", authorities);
    }

    public Boolean validateToken(String token) {
        try {
//            byte[] decodedSecretKey = Base64.getDecoder().decode(SECRET_KEY);
//            System.out.println("Decoded SECRET_KEY: " + new String(decodedSecretKey, StandardCharsets.UTF_8));
//            Jwts.parserBuilder().setSigningKey(new SecretKeySpec(decodedSecretKey, SignatureAlgorithm.HS512.getJcaName())).build().parseClaimsJws(token).getBody();
//            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalStateException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
//            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(accessToken).getBody();
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
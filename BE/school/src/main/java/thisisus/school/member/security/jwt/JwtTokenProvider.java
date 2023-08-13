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

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
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
                .collect(Collectors.joining());

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
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        CustomUserDetails principal = new CustomUserDetails(Long.valueOf(claims.getSubject()), "", authorities);

//        CustomUserDetails principal2 = userDetailsService.loadMemberbyEmail(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "Bearer", authorities);
    }

    public Boolean validateToken(String token) {
        try {
            byte[] decodedSecretKey = Base64.getDecoder().decode(SECRET_KEY);
//            System.out.println("Decoded SECRET_KEY: " + new String(decodedSecretKey, StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(new SecretKeySpec(decodedSecretKey, SignatureAlgorithm.HS512.getJcaName())).build().parseClaimsJws(token).getBody();
//            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
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
            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    /*private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsServiceImpl userDetailsService;

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";
    private final long tokenValidMillisecond = 1000L * 60 * 60;

    @PostConstruct      // 빈 객체로 주입 된 이후 수행되는 메서드
    protected void init() {
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    public String createToken(String email, List<String> roles) {
        LOGGER.info("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }

    *//**
     * 필터에서 인증 성공 시 SecurityContextHolder에 저장할 Authenticaiton을 생성하는 역할
     * @param token
     * @return
     *//*
    public Authentication getAuthentication(String token) {
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByEmail(this.getUseremail(token));
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserEmail : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUseremail(String token) {
        LOGGER.info("[getUseremail] 토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        LOGGER.info("[getUseremail] 토큰 기반 회원 구별 정보 완료, info : {}", info);
        return info;
    }

    public String resolveToken(HttpServletRequest request) {
        LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token) {
        LOGGER.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            LOGGER.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }*/

//    private UserDetailsServiceImpl userDetailsService;
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String AUTHORITIES_KEY = "auth";
//    private static final String BEARER_TYPE = "Bearer";
//    private static final String TYPE_ACCESS = "access";
//    private static final String TYPE_REFRESH = "refresh";
//
//    private final Key key;
//
//    //The specified key byte array is 248 bits which is not secure enough for any JWT HMAC-SHA algorithm.
//    // The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with HMAC-SHA algorithms MUST have a size >= 256 bits (the key size must be greater than or equal to the hash output size).
//    // Consider using the io.jsonwebtoken.security.Keys#secretKeyFor(SignatureAlgorithm) method to create a key guaranteed to be secure enough for your preferred HMAC-SHA algorithm.
//    public JwtTokenProvider(@Value("${oauth.jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    //Authentication 을 가지고 AccessToken, RefreshToken 을 생성하는 메서드
//    public MemberResponseDto.TokenInfo generateToken(Authentication authentication) {
//        return generateToken(authentication.getName(), authentication.getAuthorities());
//    }
//
//    //name, authorities 를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
//    public MemberResponseDto.TokenInfo generateToken(String name, Collection<? extends GrantedAuthority> inputAuthorities) {
//        //권한 가져오기
//        String authorities = inputAuthorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        Date now = new Date();
//
//        //Generate AccessToken
//        String accessToken = Jwts.builder()
//                .setSubject(name)
//                .claim(AUTHORITIES_KEY, authorities)
//                .claim("type", TYPE_ACCESS)
//                .setIssuedAt(now)   //토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + ExpireTime.ACCESS_TOKEN_EXPIRE_TIME))  //토큰 만료 시간 설정
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//
//        //Generate RefreshToken
//        String refreshToken = Jwts.builder()
//                .claim("type", TYPE_REFRESH)
//                .setIssuedAt(now)   //토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + ExpireTime.REFRESH_TOKEN_EXPIRE_TIME)) //토큰 만료 시간 설정
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//
//        return MemberResponseDto.TokenInfo.builder()
//                .grantType(BEARER_TYPE)
//                .accessToken(accessToken)
//                .accessTokenExpirationTime(ExpireTime.ACCESS_TOKEN_EXPIRE_TIME)
//                .refreshToken(refreshToken)
//                .refreshTokenExpirationTime(ExpireTime.REFRESH_TOKEN_EXPIRE_TIME)
//                .build();
//    }
//
//    //JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
//    public Authentication getAuthentication(String accessToken) {
//        //토큰 복호화
//        Claims claims = parseClaims(accessToken);
//
//        if (claims.get(AUTHORITIES_KEY) == null) {
//            //TODO:: Change Custom Exception
//            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
//        }
//
//        //클레임에서 권한 정보 가져오기
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        //UserDetails 객체를 만들어서 Authentication 리턴
//        UserDetails userDetails = userDetailsService.loadMemberbyEmail(claims.getSubject());
////        UserDetails userDetails = new User(claims.getSubject(), "", authorities);
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//
//    //토큰 정보를 검증하는 메서드
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (SecurityException | MalformedJwtException e) {
//            log.info("Invalid JWT Token", e);
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT Token", e);
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT Token", e);
//        } catch (IllegalArgumentException e) {
//            log.info("JWT claims string is empty.", e);
//        }
//        return false;
//    }
//
//    private Claims parseClaims(String accessToken) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e) {
//            // ???
//            return e.getClaims();
//        }
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }

}

package thisisus.school.auth.infrastructure;

import static thisisus.school.common.exception.ExceptionCode.*;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thisisus.school.auth.dto.response.MemberInfoFromIdToken;
import thisisus.school.auth.exception.InvalidTokenException;
import thisisus.school.common.exception.CustomException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

	@Value("${secret}")
	private String secretKey;
	private Long time = 1209600000L;

	private SecretKey key;

	@PostConstruct
	protected void init() {
		byte[] decodedKey = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(decodedKey);
	}

	public String createToken(Long memberId, String role, String type, Long time) {

		long now = (new Date()).getTime();
		Date validity = new Date(now + time);

		return Jwts.builder()
			.setSubject(memberId.toString())
			.claim("role", role)
			.claim("type", type)
			.signWith(key, SignatureAlgorithm.HS256)
			.setExpiration(validity)
			.compact();
	}

	public String createAccessToken(Long memberId, String role) {
		return createToken(memberId, role, "ACEESS_TOKEN", time);
	}

	public String createRefreshToken(Long memberId, String role) {
		return createToken(memberId, role, "REFRESH_TOKEN", time);
	}

	public String getMemberId(String token) {
		String memberIdStr = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();

		return memberIdStr;
	}

	public String getKid(String idToken) {
		try {
			String[] idTokenParts = idToken.split("\\.");
			String encodedHeader = idTokenParts[0];
			String decodedHeader = new String(Base64.getDecoder().decode(encodedHeader), StandardCharsets.UTF_8);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> map = objectMapper.readValue(decodedHeader, Map.class);
			return map.get("kid");
		} catch (Exception e) {
			throw new InvalidTokenException();
		}
	}

	public MemberInfoFromIdToken getMemberInfoFromIdToken(String idToken, RSAPublicKey publicKey, String iss,
		String aud) {
		try {
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(publicKey)
				.requireIssuer(iss)
				.requireAudience(aud)
				.build()
				.parseClaimsJws(idToken)
				.getBody();
			return MemberInfoFromIdToken.builder()
				.email(claims.get("email", String.class))
				.build();
		} catch (SignatureException e) {
			throw new CustomException(INVALID_JWT_CHARACTER);
		} catch (ExpiredJwtException e) {
			throw new CustomException(EXPIRED_TOKEN);
		} catch (UnsupportedJwtException e) {
			throw new CustomException(INVALID_TOKEN);
		} catch (IllegalArgumentException e) {
			throw new CustomException(INCORRECT_TOKEN);
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new CustomException(INVALID_JWT_CHARACTER);
		} catch (ExpiredJwtException e) {
			throw new CustomException(EXPIRED_TOKEN);
		} catch (UnsupportedJwtException e) {
			throw new CustomException(INVALID_TOKEN);
		} catch (IllegalArgumentException e) {
			throw new CustomException(INCORRECT_TOKEN);
		}
	}
}
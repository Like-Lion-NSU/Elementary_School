package thisisus.school.auth.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.exception.AlreadyLoggedOutException;
import thisisus.school.auth.exception.InvalidTokenException;
import thisisus.school.auth.exception.MemberRefreshTokenMismatchException;
import thisisus.school.auth.infrastructure.AuthTokenGenerator;
import thisisus.school.redis.Auth.domain.RefreshToken;
import thisisus.school.redis.Auth.service.RefreshTokenRedisService;

@RequiredArgsConstructor
@Component
public class RefreshTokenValidator {

	private final AuthTokenGenerator authTokenGenerator;
	private final RefreshTokenRedisService refreshTokenRedisService;

	protected void validateToken(final String refreshToken) {
		refreshTokenRedisService.validateRefreshToken(refreshToken);
		if (!authTokenGenerator.isValidToken(refreshToken)) {
			throw new InvalidTokenException();
		}
	}

	protected void validateTokenOwner(final String refreshToken, final Long memberId) {
		RefreshToken token = refreshTokenRedisService.findByKey(refreshToken);
		if (!token.getMemberId().equals(memberId)) {
			throw new MemberRefreshTokenMismatchException();
		}
	}

	protected void validateLogoutToken(final String refreshToken) {
		if (refreshTokenRedisService.findByKey(refreshToken).equals(null)) {
			throw new AlreadyLoggedOutException();
		}
	}
}
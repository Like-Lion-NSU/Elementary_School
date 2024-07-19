package thisisus.school.redis.Auth.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import thisisus.school.redis.Auth.domain.RefreshToken;
import thisisus.school.redis.Auth.exception.ExpiredTokenException;
import thisisus.school.redis.Auth.repository.RefreshTokenRedisRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenRedisServiceImpl implements RefreshTokenRedisService {
	private final RefreshTokenRedisRepository refreshTokenRedisRepository;

	private final long REFRESH_TOKEN_TIME_TO_LIVE = 604800000L;

	public void save(final String token, final Long memberId) {
		RefreshToken refreshToken = RefreshToken.builder()
			.refreshToken(token)
			.memberId(memberId)
			.build();
		refreshTokenRedisRepository.save(token, refreshToken, REFRESH_TOKEN_TIME_TO_LIVE);
	}

	@Override
	public RefreshToken findByKey(final String key) {
		return refreshTokenRedisRepository.findByKey(key).get();
	}


	public void delete(final String key) {
		refreshTokenRedisRepository.delete(key);
	}

	public void validateRefreshToken(final String key) {
		refreshTokenRedisRepository.findByKey(key).orElseThrow(ExpiredTokenException::new);
	}
}
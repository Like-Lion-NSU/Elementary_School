package thisisus.school.redis.Auth.service;

import thisisus.school.redis.Auth.domain.RefreshToken;

public interface RefreshTokenRedisService {
	void save(String token, Long memberId);
	RefreshToken findByKey(String token);
	void delete(String key);
	void validateRefreshToken(String token);
}
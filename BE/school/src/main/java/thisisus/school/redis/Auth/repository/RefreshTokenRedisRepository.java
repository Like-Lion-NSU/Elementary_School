package thisisus.school.redis.Auth.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import thisisus.school.redis.Auth.domain.RefreshToken;
import thisisus.school.redis.common.CommonRedisRepository;

@Repository
public class RefreshTokenRedisRepository extends CommonRedisRepository<RefreshToken> {

	public RefreshTokenRedisRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
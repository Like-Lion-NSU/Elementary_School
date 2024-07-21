package thisisus.school.redis.common;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public abstract class CommonRedisRepository<T> {
	protected RedisTemplate<String, T> redisTemplate;

	public void save(String key, T value, Long expiredTime) {
		redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
	}

	public Optional<T> findByKey(String key) {
		try {
			return Optional.of(redisTemplate.opsForValue().get(key));
		} catch (NullPointerException e) {
			return Optional.empty();
		}
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}
}
package thisisus.school.redis.Auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class ExpiredTokenException extends CustomException {
	public ExpiredTokenException() {
		super(ExceptionCode.EXPIRED_TOKEN);
	}
}

package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class AccessDeniedResourceException extends CustomException {
	public AccessDeniedResourceException() {
		super(ExceptionCode.ACCESS_DENIED_RESOURCE);
	}
}
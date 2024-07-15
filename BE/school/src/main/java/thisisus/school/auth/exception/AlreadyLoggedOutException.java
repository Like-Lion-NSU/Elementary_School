package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class AlreadyLoggedOutException extends CustomException {
	public AlreadyLoggedOutException() {
		super(ExceptionCode.ALREADY_LOGGED_OUT);
	}
}

package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class TokenTypeMismatchException extends CustomException {
	public TokenTypeMismatchException() {
		super(ExceptionCode.TOKEN_TYPE_MISMATCH);
	}
}
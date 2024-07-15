package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class MemberRefreshTokenMismatchException extends CustomException {
	public MemberRefreshTokenMismatchException() {
		super(ExceptionCode.MEMBER_REFRESH_TOKEN_MISMATCH);
	}
}
package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class InvalidTokenException extends CustomException {

    public InvalidTokenException() {
        super(ExceptionCode.INVALID_TOKEN);
    }
}

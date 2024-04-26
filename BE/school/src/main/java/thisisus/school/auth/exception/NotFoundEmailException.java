package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundEmailException extends CustomException {

    public NotFoundEmailException() {
        super(ExceptionCode.NOT_FOUND_EMAIL);
    }
}

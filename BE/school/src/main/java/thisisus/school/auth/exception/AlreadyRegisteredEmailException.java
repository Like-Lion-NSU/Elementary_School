package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class AlreadyRegisteredEmailException extends CustomException {

    public AlreadyRegisteredEmailException() {
        super(ExceptionCode.ALREADY_REGISTERED_EMAIL);
    }
}

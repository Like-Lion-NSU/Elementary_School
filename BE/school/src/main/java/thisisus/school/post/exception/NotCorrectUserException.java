package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotCorrectUserException extends CustomException {
    public NotCorrectUserException() {
        super(ExceptionCode.NOT_CORRECT_USER);
    }
}

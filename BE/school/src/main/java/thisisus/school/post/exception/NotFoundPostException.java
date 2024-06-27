package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundPostException extends CustomException {
    public NotFoundPostException() {
        super(ExceptionCode.NOT_FOUND_POST);
    }
}

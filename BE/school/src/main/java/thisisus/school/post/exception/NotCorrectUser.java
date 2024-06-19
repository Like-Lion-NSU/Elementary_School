package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotCorrectUser extends CustomException {
    public NotCorrectUser() {
        super(ExceptionCode.NOT_FOUND_POST);
    }
}

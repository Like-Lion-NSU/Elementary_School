package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundPost extends CustomException {
    public NotFoundPost() {
        super(ExceptionCode.NOT_FOUND_POST);
    }
}

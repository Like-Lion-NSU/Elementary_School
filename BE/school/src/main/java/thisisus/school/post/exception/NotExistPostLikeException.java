package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotExistPostLikeException extends CustomException {
    public NotExistPostLikeException() {
        super(ExceptionCode.NOT_EXIST_POSTLIKE);
    }
}

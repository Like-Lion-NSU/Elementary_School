package thisisus.school.post.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class AlreadyExistPostLikeException extends CustomException {
    public AlreadyExistPostLikeException() {
        super(ExceptionCode.ALREADY_EXIST_POSTLIKE);
    }
}

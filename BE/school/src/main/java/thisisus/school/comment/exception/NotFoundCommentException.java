package thisisus.school.comment.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundCommentException extends CustomException {
	public NotFoundCommentException() {
		super(ExceptionCode.NOT_FOUND_COMMENT);
	}
}

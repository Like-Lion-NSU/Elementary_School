package thisisus.school.chatting.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class CannotRequestChatToStudentException extends CustomException {
	public CannotRequestChatToStudentException() {
		super(ExceptionCode.CANNOT_REQUEST_CHAT_TO_STUDENT);
	}
}
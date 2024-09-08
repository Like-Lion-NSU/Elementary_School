package thisisus.school.chatting.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundChatRoomException extends CustomException {
	public NotFoundChatRoomException() {
		super(ExceptionCode.NOT_FOUND_CHATROOM);
	}
}
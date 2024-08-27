package thisisus.school.common.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

	BAD_REQUEST_ERROR(BAD_REQUEST, "잘못된 요청입니다."),
	INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST, "잘못된 HTTP 요청입니다."),
	INVALID_JWT_CHARACTER(BAD_REQUEST, "JWT 형식이 유효하지 않습니다."),
	INVALID_TOKEN(UNAUTHORIZED, "유효하지 않는 토큰입니다."),
	UNSUPPORTED_HTTP_METHOD(METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
	NOT_FOUND_MEMBER(NOT_FOUND, "사용자를 찾을 수 없습니다."),
	NOT_FOUND_EMAIL(NOT_FOUND, "이메일이 존재하지 않습니다."),
	NOT_FOUND_CHATROOM(NOT_FOUND, "채팅방이 존재하지 않습니다."),
	EXPIRED_TOKEN(UNAUTHORIZED, "토큰이 만료되었습니다."),
	INCORRECT_TOKEN(UNAUTHORIZED, "올바르지 않는 토큰입니다."),
	ALREADY_REGISTERED_EMAIL(CONFLICT, "이미 등록된 이메일입니다."),
	ALREADY_LOGGED_OUT(CONFLICT, "이미 로그아웃된 사용자입니다."),
	NOT_FOUND_POST(NOT_FOUND, "게시물을 찾을 수 없습니다."),
	NOT_CORRECT_USER(UNAUTHORIZED, "작성자가 일치하지 않습니다."),
	PUBLIC_KEY_GENERATION_FAILED(INTERNAL_SERVER_ERROR, "공개 키 생성에 실패했습니다."),
	TOKEN_TYPE_MISMATCH(FORBIDDEN, "토큰 타입이 잘못되었습니다."),
	ACCESS_DENIED_RESOURCE(FORBIDDEN, "손님/학생 등급은 서비스에 접근할 수 없습니다."),
	MEMBER_REFRESH_TOKEN_MISMATCH(FORBIDDEN, "로그인한 사용자의 RefreshToken이 아닙니다."),
	CANNOT_REQUEST_CHAT_TO_STUDENT(FORBIDDEN, "학생에게는 채팅을 요청할 수 없습니다."),
  	ALREADY_EXIST_POSTLIKE(CONFLICT, "이미 좋아요를 누른 게시물입니다."),
  	NOT_EXIST_POSTLIKE(NOT_FOUND,"해당 게시물에 좋아요 기록이 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}

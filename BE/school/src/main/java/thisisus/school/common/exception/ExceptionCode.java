package thisisus.school.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
  BAD_REQUEST_ERROR(BAD_REQUEST, "잘못된 요청입니다."),
  INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST, "잘못된 HTTP 요청입니다."),
  UNSUPPORTED_HTTP_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
  INVALID_INPUT(BAD_REQUEST,"잘못된 형식을 입력했습니다."),

  /* 게시물 관련 */
  NOT_FOUND_POST(NOT_FOUND,"게시글이 존재하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String message;
}

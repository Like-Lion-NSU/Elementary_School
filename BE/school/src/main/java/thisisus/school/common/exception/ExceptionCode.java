package thisisus.school.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
  BAD_REQUEST_ERROR(BAD_REQUEST, "잘못된 요청입니다."),
  INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST, "잘못된 HTTP 요청입니다."),
  UNSUPPORTED_HTTP_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.");

  private final HttpStatus httpStatus;
  private final String message;
}

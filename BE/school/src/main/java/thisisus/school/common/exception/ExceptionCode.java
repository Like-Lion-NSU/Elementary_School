package thisisus.school.common.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
  BAD_REQUEST_ERROR(BAD_REQUEST, "잘못된 요청입니다."),
  INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST, "잘못된 HTTP 요청입니다."),
  INVALID_JWT_CHARACTER(BAD_REQUEST, "JWT 형식이 유효하지 않습니다."),
  UNSUPPORTED_HTTP_METHOD(METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
  UNSUPPORTED_TOKEN(UNAUTHORIZED, "유효하지 않는 토큰입니다."),
  NOT_FOUND_MEMBER(NOT_FOUND, "사용자를 찾을 수 없습니다."),
  NOT_FOUND_EMAIL(NOT_FOUND, "이메일이 존재하지 않습니다."),
  EXPIRED_TOKEN(UNAUTHORIZED, "토큰이 만료되었습니다."),
  INCORRECT_TOKEN(UNAUTHORIZED, "올바르지 않는 토큰입니다."),
  ALREADY_REGISTERED_EMAIL(HttpStatus.CONFLICT, "이미 등록된 이메일입니다."),

  /* post관련 */
  NOT_FOUND_POST(NOT_FOUND, "게시물을 찾을 수 없습니다.")
  ;

  private final HttpStatus httpStatus;
  private final String message;
}

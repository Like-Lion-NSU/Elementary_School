package thisisus.school.common.exception;

import java.net.BindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import thisisus.school.common.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    ErrorResponse error = ErrorResponse.from(e.getExceptionCode());
    return ResponseEntity.status(e.getExceptionCode().getHttpStatus()).body(error);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    ErrorResponse error = ErrorResponse.from(ExceptionCode.BAD_REQUEST_ERROR);
    return ResponseEntity.status(error.getHttpStatus()).body(error);
  }

  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
    ErrorResponse error = ErrorResponse.from(ExceptionCode.INVALID_HTTP_MESSAGE_BODY);
    return ResponseEntity.status(error.getHttpStatus()).body(error);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponse> handleBindException(MethodArgumentTypeMismatchException e) {
    ErrorResponse error = ErrorResponse.from(ExceptionCode.INVALID_HTTP_MESSAGE_BODY);
    return ResponseEntity.status(error.getHttpStatus()).body(error);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleBindException(HttpRequestMethodNotSupportedException e) {
    ErrorResponse error = ErrorResponse.from(ExceptionCode.UNSUPPORTED_HTTP_METHOD);
    return ResponseEntity.status(error.getHttpStatus()).body(error);
  }
}

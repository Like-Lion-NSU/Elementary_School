package thisisus.school.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import thisisus.school.common.exception.ExceptionCode;

@Getter
public class ErrorResponse {

  private final HttpStatus httpStatus;
  private final String message;

  @Builder
  private ErrorResponse(ExceptionCode exceptionCode){
    this.httpStatus = exceptionCode.getHttpStatus();
    this.message = exceptionCode.getMessage();
  }

  public static ErrorResponse from(ExceptionCode code) {
    return new ErrorResponse(code);
  }

}

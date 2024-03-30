package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundEmail extends CustomException {

  public NotFoundEmail(){
    super(ExceptionCode.NOT_FOUND_EMAIL);
  }
}

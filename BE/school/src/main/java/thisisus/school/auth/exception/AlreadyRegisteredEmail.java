package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class AlreadyRegisteredEmail extends CustomException {
  public AlreadyRegisteredEmail(){
    super(ExceptionCode.ALREADY_REGISTERED_EMAIL);
  }
}

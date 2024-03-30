package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class UnSupportedToken extends CustomException {

  public UnSupportedToken(){
    super(ExceptionCode.UNSUPPORTED_TOKEN);
  }
}

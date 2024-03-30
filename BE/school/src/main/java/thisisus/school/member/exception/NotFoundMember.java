package thisisus.school.member.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundMember extends CustomException {
  public NotFoundMember(){
    super(ExceptionCode.NOT_FOUND_MEMBER);
  }
}

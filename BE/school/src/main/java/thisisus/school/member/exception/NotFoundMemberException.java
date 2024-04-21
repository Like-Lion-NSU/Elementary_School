package thisisus.school.member.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class NotFoundMemberException extends CustomException {

    public NotFoundMemberException() {
        super(ExceptionCode.NOT_FOUND_MEMBER);
    }
}
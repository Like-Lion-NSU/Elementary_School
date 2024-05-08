package thisisus.school.auth.exception;

import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;

public class PublicKeyGenerationFailedException extends CustomException {

    public PublicKeyGenerationFailedException() {
        super(ExceptionCode.PUBLIC_KEY_GENERATION_FAILED);
    }
}

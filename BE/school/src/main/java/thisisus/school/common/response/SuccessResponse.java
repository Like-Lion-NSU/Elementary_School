package thisisus.school.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResponse<T> {

    private final boolean isSuccess;
    private final HttpStatus httpStatus;
    private final T data;

    private SuccessResponse(T data) {
        this.isSuccess = true;
        this.httpStatus = HttpStatus.OK;
        this.data = data;
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse(data);
    }

    public static <T> SuccessResponse<T> of() {
        return new SuccessResponse(null);
    }
}

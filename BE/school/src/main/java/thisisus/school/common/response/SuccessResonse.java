package thisisus.school.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResonse<T> {

    private final boolean isSuccess;
    private final HttpStatus httpStatus;
    private final T data;


    private SuccessResonse(T data) {
        this.isSuccess = true;
        this.httpStatus = HttpStatus.OK;
        this.data = data;
    }


    public static <T> SuccessResonse<T> of(T data) {
        return new SuccessResonse(data);
    }

    public static <T> SuccessResonse<T> of() {
        return new SuccessResonse(null);
    }
}

package thisisus.school.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@ApiModel(value = "기본 응답")
public class DefaultResponseDto<T> {

    private String responseCode;
    private String responseMessage;
    private T data;

    public DefaultResponseDto(final String responseCode, final String responseMessage){
        this.responseCode=responseCode;
        this.responseMessage=responseMessage;
        this.data=null;
    }

    public static <T> DefaultResponseDto<T> responseDto(final String responseCode, final String responseMessage) {
        return  response(responseCode, responseMessage, null);
    }

    public static <T> DefaultResponseDto<T> response(final String responseCode, final String responseMessage, final T data) {
        return DefaultResponseDto.<T>builder()
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .data(data)
                .build();
    }
}

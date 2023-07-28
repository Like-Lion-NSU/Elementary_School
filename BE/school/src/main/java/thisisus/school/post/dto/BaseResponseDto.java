package thisisus.school.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseResponseDto {
    private boolean success;

    private int code;

    private String msg;
}

package thisisus.school.post.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thisisus.school.config.BaseResponse;

@Setter
@NoArgsConstructor
public class PostUpResponseDto extends BaseResponseDto {
    private String title;
    private String content;

    @Builder
    public PostUpResponseDto(Boolean success, int code, String msg, String title, String content){
        super(success,code,msg);
        this.title=title;
        this.content=content;
    }
}

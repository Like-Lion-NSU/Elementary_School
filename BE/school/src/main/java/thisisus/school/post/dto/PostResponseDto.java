package thisisus.school.post.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class PostResponseDto extends BaseResponseDto {
    private String title;
    private String content;

    @Builder
    public PostResponseDto(Boolean success, int code, String msg, String title, String content){
        super(success,code,msg);
        this.title=title;
        this.content=content;
    }
}

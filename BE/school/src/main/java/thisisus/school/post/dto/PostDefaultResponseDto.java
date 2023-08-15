package thisisus.school.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.post.domain.Post;

@ApiModel(value = "Post 기본 응답")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostDefaultResponseDto {

    @ApiModelProperty(position = 1, required = true, value ="Post 식별자", example = "1")
    private Long postId;

    public PostDefaultResponseDto(Post post){
        this.postId=post.getId();
    }
}

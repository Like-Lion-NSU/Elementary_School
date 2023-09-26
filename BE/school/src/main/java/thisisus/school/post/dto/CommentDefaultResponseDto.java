package thisisus.school.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.post.domain.Comment;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;

import java.time.LocalDateTime;

@ApiModel(value = "Comment 기본 응답")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentDefaultResponseDto {

    @ApiModelProperty(position = 1, required = true, value ="Post 식별자", example = "1")
    private Long commentId;
    private Long postId;
    private String email;
    private String body;
    private PostCategory category;
    private boolean CommentDeleted;
    private LocalDateTime updateAt;

    public CommentDefaultResponseDto(Comment comment){
        this.commentId=comment.getId();
        this.postId=comment.getPost().getId();
        this.CommentDeleted=comment.isDeleted();
        this.category=comment.getPost().getCategory();
        this.email=comment.getMember().getEmail();
        this.updateAt=comment.getUpdateAt();
        this.body=comment.getContent();
    }
}

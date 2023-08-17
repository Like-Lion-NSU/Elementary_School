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
import thisisus.school.post.domain.PostPhoto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Post 기본 응답")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostDefaultResponseDto {

    @ApiModelProperty(position = 1, required = true, value ="Post 식별자", example = "1")
    private Long postId;
    private String title;
    private String content;
    private PostCategory category;
    private String email;
    private LocalDateTime updateAt;
    private int viewCount;
    private List<PostPhoto> photos = new ArrayList<>();
    private List<CommentDefaultResponseDto> comments = new ArrayList<>();



    public PostDefaultResponseDto(Post post){
        this.postId=post.getId();
        this.title=post.getTitle();
        this.content=post.getContent();
        this.category=post.getCategory();
        this.email=post.getMember().getEmail();
        this.updateAt=post.getUpdateAt();
        this.viewCount=post.getViewCount();
        if(post.getComments().size()!=0){
            List<Comment> comments = post.getComments();
            for(Comment comment : comments){
                this.comments.add(new CommentDefaultResponseDto(comment));
            }
        }
        if(post.getPostPhotos().size()!=0){
            List<PostPhoto> photos = post.getPostPhotos();
            for(PostPhoto photo : photos){
                this.photos.add(photo);
            }
        }

    }
}

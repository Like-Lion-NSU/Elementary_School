package thisisus.school.post.dto.request;

import lombok.*;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private PostCategory category;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .likeCount(0)
                .viewCount(0)
                .build();
    }
}

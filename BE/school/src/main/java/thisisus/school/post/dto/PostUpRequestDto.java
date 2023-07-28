package thisisus.school.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import thisisus.school.post.category.PostCategory;
import thisisus.school.post.domain.Post;

import java.time.LocalDateTime;

@Data
public class PostUpRequestDto {

    private String title;
    private String content;
    private PostCategory category;
    private LocalDateTime createAt;

    @Builder
    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .createAt(createAt)
                .build();
    }

}

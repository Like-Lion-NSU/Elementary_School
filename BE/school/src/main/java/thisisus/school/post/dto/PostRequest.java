package thisisus.school.post.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.member.domain.Member;
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

    public Post toEntity(Member member){
        return Post.builder().
                title(title)
                .content(content)
                .category(category)
                .likeCount(0)
                .viewCount(0)
                .member(member)
                .build();

    }
}

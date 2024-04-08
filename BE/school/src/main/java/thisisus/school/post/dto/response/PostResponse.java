package thisisus.school.post.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private PostCategory category;
    private int viewCount;
    private int likeCount;

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
    }
}

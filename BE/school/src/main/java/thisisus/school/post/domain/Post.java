package thisisus.school.post.domain;

import lombok.Data;
import thisisus.school.domain.Member;
import thisisus.school.post.category.PostCategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private PostCategory category;
    private Long likeCount;
    private Long viewCount;
    private LocalDateTime createAt;

    private Member member;
    private PostLike postLike;
}

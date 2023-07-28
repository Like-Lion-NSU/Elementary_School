package thisisus.school.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thisisus.school.domain.Member;
import thisisus.school.post.category.PostCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
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

    @ManyToOne
    private Member member;

    @Builder
    public Post(String title, String content, PostCategory category, LocalDateTime createAt){
        this.title=title;
        this.content=content;
        this.category=category;
        this.createAt=createAt;
    }
}

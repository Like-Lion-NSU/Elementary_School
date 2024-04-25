package thisisus.school.post.domain;

import lombok.*;
import thisisus.school.common.BaseTimeEntity;
import thisisus.school.post.dto.PostUpdateRequest;

import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @Column
    private int likeCount;

    @Column
    private int viewCount;

    @Column
    private boolean isDelete;

    public void delete() {
        this.isDelete = true;
    }

    public void update(String title, String content, PostCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}

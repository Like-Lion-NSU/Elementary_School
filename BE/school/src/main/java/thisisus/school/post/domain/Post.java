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

    //삭제 수행 메소드
    public void delete() {
        this.isDelete = true;
    }

    public void update(PostUpdateRequest postRequest) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }
}

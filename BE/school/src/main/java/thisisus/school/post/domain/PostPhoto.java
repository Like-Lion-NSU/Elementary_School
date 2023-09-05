package thisisus.school.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import thisisus.school.common.BaseEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="post_id")
    private Post post;

    //파일 경로 저장
    private String photoUrl;

    @Builder
    public PostPhoto(Post post, String photoUrl){
        this.post=post;
        this.photoUrl=photoUrl;
        this.setDeleted(false);

        post.getPostPhotos().add(this);
    }

    public void delete(){
        this.setDeleted(true);
    }
}

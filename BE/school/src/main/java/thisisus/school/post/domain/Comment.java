package thisisus.school.post.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import thisisus.school.common.BaseEntity;
import thisisus.school.post.dto.CommentRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class Comment extends BaseEntity {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<CommentPhoto> commentPhoto = new ArrayList<>();

    private Long memberId;

    @Builder
    public Comment(String content){
        this.content=content;
        this.setDeleted(false);
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content=commentRequestDto.getContent();
    }

    public void delete(){
        this.content=null;
        this.setDeleted(true);
    }

}

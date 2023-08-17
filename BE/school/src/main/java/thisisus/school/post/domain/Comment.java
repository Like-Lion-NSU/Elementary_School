package thisisus.school.post.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import thisisus.school.common.BaseEntity;
import thisisus.school.member.domain.Member;
import thisisus.school.post.dto.CommentRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Comment(String content, Member member){
        this.content=content;
        this.setDeleted(false);
        this.member = member;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content=commentRequestDto.getContent();
    }

    public void delete(){
    /*     this.content=null;
       this.setMember(null);
        this.setPost(null);
        this.setUpdateAt(null);
        this.setCreateAt(null);*/
        this.setDeleted(true);
    }

}
package thisisus.school.post.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import thisisus.school.common.BaseEntity;
import thisisus.school.member.domain.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostLiked extends BaseEntity {

   @Id
   @Column(name="like_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id")
   private Post post;

  @Builder
   public PostLiked(Post post, Member member){
      this.post=post;
      this.member=member;
  }

  public void delete(){
      this.setDeleted(true);
  }
}

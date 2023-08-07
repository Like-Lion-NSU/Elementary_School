package thisisus.school.post.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import thisisus.school.common.BaseEntity;

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

   private Long memberId;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id")
   private Post post;

  @Builder
   public PostLiked(Post post, Long memberId){
      this.post=post;
      this.memberId=memberId;
  }

  public void delete(){
      this.setDeleted(true);
  }
}

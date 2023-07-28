package thisisus.school.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import thisisus.school.domain.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PostLike {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private boolean like;

   @ManyToOne
   private Member member;

   @ManyToOne
   private Post post;
}

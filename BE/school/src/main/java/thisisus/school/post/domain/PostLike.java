package thisisus.school.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PostLike {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private boolean like;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id")
   private Post post;
}

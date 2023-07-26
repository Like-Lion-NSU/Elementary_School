package thisisus.school.post.domain;

import lombok.Data;
import thisisus.school.domain.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PostLike {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private boolean like;

   private Member member;

   private Post post;
}

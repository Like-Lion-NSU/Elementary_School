package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMemberIdAndIsDeletedIsFalse(Long id);
}

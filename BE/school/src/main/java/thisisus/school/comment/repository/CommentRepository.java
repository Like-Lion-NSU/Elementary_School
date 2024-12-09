package thisisus.school.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import thisisus.school.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}

package thisisus.school.comment.repository;

import java.util.List;

import thisisus.school.comment.domain.Comment;

public interface CommentCustomRepository {
	List<Comment> findAllByPostId(Long postId);
	List<Comment> findAllByMemberId(Long memberId);
}

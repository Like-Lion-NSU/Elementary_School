package thisisus.school.comment.service;

import java.util.List;

import thisisus.school.comment.dto.CommentRequest;
import thisisus.school.comment.dto.CommentResponse;

public interface CommentService {
	CommentResponse saveComment(CommentRequest commentRequest, Long memberId);
	CommentResponse findComment(Long commentId);
	List<CommentResponse> findCommentsByPost(Long postId);
	List<CommentResponse> findCommentsByMember(Long memberId);
	void delete(Long commentId, Long memberId);
}

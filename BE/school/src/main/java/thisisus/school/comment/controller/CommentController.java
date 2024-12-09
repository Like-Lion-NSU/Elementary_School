package thisisus.school.comment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.comment.dto.CommentRequest;
import thisisus.school.comment.dto.CommentResponse;
import thisisus.school.comment.service.CommentService;
import thisisus.school.common.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
	private final CommentService commentService;

	@PostMapping
	public SuccessResponse<CommentResponse> saveComment(@Valid @RequestBody CommentRequest commentRequest,
		@AuthenticatedMemberId Long memberId) {
		CommentResponse response = commentService.saveComment(commentRequest, memberId);
		return SuccessResponse.of(response);
	}

	@GetMapping("/{commentId}")
	public SuccessResponse<CommentResponse> findComment(@PathVariable("commentId") final Long commentId) {
		CommentResponse responses = commentService.findComment(commentId);
		return SuccessResponse.of(responses);
	}

	@GetMapping("/{postId}")
	public SuccessResponse<List<CommentResponse>> findCommentsByPost(@PathVariable("postId") final Long postId) {
		List<CommentResponse> responses = commentService.findCommentsByPost(postId);
		return SuccessResponse.of(responses);
	}

	@GetMapping("/myComments")
	public SuccessResponse<List<CommentResponse>> findCommentsByMember(@AuthenticatedMemberId Long memberId) {
		List<CommentResponse> response = commentService.findCommentsByMember(memberId);
		return SuccessResponse.of(response);
	}

	@DeleteMapping("/{commentId}")
	public SuccessResponse deleteComment(@PathVariable("commentId") final Long commentId,
		@AuthenticatedMemberId Long memberId) {
		commentService.delete(commentId, memberId);
		return SuccessResponse.of();
	}
}

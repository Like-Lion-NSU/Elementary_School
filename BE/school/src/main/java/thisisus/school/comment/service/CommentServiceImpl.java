package thisisus.school.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import thisisus.school.comment.domain.Comment;
import thisisus.school.comment.dto.CommentRequest;
import thisisus.school.comment.dto.CommentResponse;
import thisisus.school.comment.exception.NotFoundCommentException;
import thisisus.school.comment.repository.CommentRepository;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	@Override
	public CommentResponse saveComment(final CommentRequest commentRequest, final Long memberId) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		final Post post = postRepository.findById(commentRequest.getPostId())
			.orElseThrow(NotFoundPostException::new);
		Comment comment = commentRequest.toEntity(member, post);
		commentRepository.save(comment);

		CommentResponse response = new CommentResponse(comment);
		return response;
	}

	@Override
	public CommentResponse findComment(final Long commentId) {
		final Comment comment = commentRepository.findById(commentId)
			.orElseThrow(NotFoundCommentException::new);

		CommentResponse response = new CommentResponse(comment);
		return response;
	}

	@Override
	public List<CommentResponse> findCommentsByPost(final Long postId) {
		final List<Comment> comments = commentRepository.findAllByPostId(postId);

		List<CommentResponse> responses = comments.stream()
			.map(post -> new CommentResponse(post))
			.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<CommentResponse> findCommentsByMember(final Long memberId) {
		final List<Comment> comments = commentRepository.findAllByMemberId(memberId);

		List<CommentResponse> responses = comments.stream()
			.map(post -> new CommentResponse(post))
			.collect(Collectors.toList());
		return responses;
	}

	@Override
	public void delete(Long commentId, Long memberId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(NotFoundCommentException::new);

		comment.checkWriter(memberId);
		comment.delete();
	}
}
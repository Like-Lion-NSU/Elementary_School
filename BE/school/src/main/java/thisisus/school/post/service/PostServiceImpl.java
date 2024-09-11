package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;

	@Override
	public PostResponse savePost(final PostRequest postRequest, final Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		Post post = postRequest.toEntity(member);
		postRepository.save(post);

		PostResponse response = new PostResponse(post);
		return response;
	}

	@Override
	public PostResponse findPost(final Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(NotFoundPostException::new);

		PostResponse response = new PostResponse(post);
		return response;
	}

	@Override
	public List<PostResponse> findPostByMemberId(final Long memberId) {
		List<Post> posts = postRepository.findAllByMemberId(memberId);
		List<PostResponse> responses = posts.stream()
			.map(post -> new PostResponse(post))
			.collect(Collectors.toList());
		return responses;
	}

	@Override
	public void findPosts(PostCategory postCategory) {

	}

	@Override
	public PostResponse update(final Long postId, final PostUpdateRequest postRequest, final Long memberId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(NotFoundPostException::new);
		post.checkWriter(post, memberId);
		post.update(postRequest.getTitle(), post.getContent(), postRequest.getCategory());

		PostResponse response = new PostResponse(post);
		return response;
	}

	@Override
	public void delete(final Long postId, final Long memberId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(NotFoundPostException::new);
		post.checkWriter(post, memberId);
		post.delete();
	}
}

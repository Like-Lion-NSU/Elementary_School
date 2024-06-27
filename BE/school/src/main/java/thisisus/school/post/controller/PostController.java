package thisisus.school.post.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;

	@PostMapping
	public SuccessResonse<PostResponse> savePost(@Valid @RequestBody PostRequest postRequest,
		@AuthenticatedMemberId Long memberId) {
		PostResponse response = postService.savePost(postRequest, memberId);
		return SuccessResonse.of(response);
	}

	@GetMapping("/{postId}")
	public SuccessResonse<PostResponse> findPost(@PathVariable("postId") final Long postId) {
		PostResponse response = postService.findPost(postId);
		return SuccessResonse.of(response);
	}

	@PatchMapping("/{postId}")
	public SuccessResonse<PostResponse> updatePost(@PathVariable("postId") final Long postId,
		@Valid @RequestBody PostUpdateRequest postRequest,
		@AuthenticatedMemberId Long memberId) {
		PostResponse response = postService.update(postId, postRequest, memberId);
		return SuccessResonse.of(response);
	}

	@DeleteMapping("/{postId}")
	public SuccessResonse deletePost(@PathVariable("postId") final Long postId,
		@AuthenticatedMemberId Long memberId) {
		postService.delete(postId, memberId);
		return SuccessResonse.of();
	}

	@GetMapping("/myPost")
	public SuccessResonse<List<PostResponse>> findUserPost(@AuthenticatedMemberId final Long memberId) {
		List<PostResponse> response = postService.findPostByMemberId(memberId);
		return SuccessResonse.of(response);
	}
}

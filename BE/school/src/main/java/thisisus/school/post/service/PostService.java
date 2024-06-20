package thisisus.school.post.service;

import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {
	PostResponse savePost(PostRequest postRequest, Long memberId);

	PostResponse update(Long postId, PostUpdateRequest postRequest, Long memberId);

	void delete(Long postId);

	PostResponse findPost(Long postId);

	List<PostResponse> findPostByMemberId(Long memberId);

	void findPosts(PostCategory postCategory);

}

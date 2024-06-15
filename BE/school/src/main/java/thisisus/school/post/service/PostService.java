package thisisus.school.post.service;

import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {
    PostResponse savePost(PostRequest postRequest, Long memberId);

    PostResponse update(PostUpdateRequest postRequest);

    void delete(long postId);

    PostResponse findPost(long postId);

    List<PostResponse> findPostByMemberId(long memberId);

    void findPosts(PostCategory postCategory);

}

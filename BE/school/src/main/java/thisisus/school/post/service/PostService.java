package thisisus.school.post.service;

import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;

public interface PostService {
    PostResponse savePost(PostRequest postRequest);

    PostResponse update(PostUpdateRequest postRequest);

    void delete(long postId);

    PostResponse findPost(long postId);

    void findPosts(PostCategory postCategory);

}

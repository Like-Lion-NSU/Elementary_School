package thisisus.school.post.service;

import thisisus.school.post.dto.request.PostRequest;
import thisisus.school.post.dto.request.PostUpdateRequest;
import thisisus.school.post.dto.response.PostResponse;

public interface PostService {
    PostResponse savePost(PostRequest postRequest);
    PostResponse findOnePost(long postId);

    PostResponse update(PostUpdateRequest postRequest);

    void delete(long postId);

}

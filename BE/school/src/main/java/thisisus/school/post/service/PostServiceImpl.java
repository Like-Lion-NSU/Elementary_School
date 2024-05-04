package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.exception.NotFoundPost;
import thisisus.school.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostResponse savePost(PostRequest postRequest) {
        Post post = postRequest.toEntity();
        postRepository.save(post);

        PostResponse response = new PostResponse(post);
        return response;
    }

    @Override
    public PostResponse findPost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPost::new);

        PostResponse response = new PostResponse(post);
        return response;
    }

    @Override
    public void findPosts(PostCategory postCategory) {

    }


    @Override
    public PostResponse update(PostUpdateRequest postRequest) {
        Post post = postRepository.findById(postRequest.getId())
                .orElseThrow(NotFoundPost::new);
        post.update(postRequest.getTitle(), post.getContent(), postRequest.getCategory());

        PostResponse response = new PostResponse(post);
        return response;
    }

    @Override
    public void delete(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPost::new);
        post.delete();
    }
}

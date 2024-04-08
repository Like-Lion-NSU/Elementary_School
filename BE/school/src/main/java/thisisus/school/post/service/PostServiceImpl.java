package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.request.PostRequest;
import thisisus.school.post.dto.request.PostUpdateRequest;
import thisisus.school.post.dto.response.PostResponse;
import thisisus.school.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    /**
     * 게시물 등록
     *
     * @param postRequest
     * @return
     */
    @Override
    public PostResponse savePost(PostRequest postRequest) {
        Post post = postRequest.toEntity();
        postRepository.save(post);

        PostResponse response = new PostResponse(post);
        return response;
    }

    /**
     * 게시물 한개 등록
     *
     * @param postId
     * @return
     */
    @Override
    public PostResponse findOnePost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_POST));

        PostResponse response = new PostResponse(post);
        return response;
    }

    /**
     * 게시물 수정
     *
     * @param postRequest
     * @return
     */
    @Override
    public PostResponse update(PostUpdateRequest postRequest) {
        Post post = postRepository.findById(postRequest.getId())
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_POST));
        post.update(postRequest);
        postRepository.save(post);
        PostResponse response = new PostResponse(post);
        return response;
    }

    /**
     * 게시물 삭제
     *
     * @param postId
     */
    @Override
    public void delete(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_POST));
        post.delete();
        postRepository.save(post);
    }
}

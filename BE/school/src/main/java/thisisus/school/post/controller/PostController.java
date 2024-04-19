package thisisus.school.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.service.PostServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {
    private final PostServiceImpl postService;

    /**
     * 게시글 등록
     * TODO - memeber관련 작업
     *
     * @param postRequest
     * @return
     */
    @PostMapping
    public SuccessResonse<PostResponse> savePost(@Valid PostRequest postRequest) {
        PostResponse response = postService.savePost(postRequest);
        return SuccessResonse.of(response);
    }

    /**
     * 게시물 상세 조회
     *
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public SuccessResonse<PostResponse> findPost(@PathVariable("postId") long postId) {
        PostResponse response = postService.findPost(postId);
        return SuccessResonse.of(response);
    }

    /**
     * 게시글 수정
     *
     * @param postRequest
     * @return
     */
    @PatchMapping
    public SuccessResonse<PostResponse> updatePost(@Valid PostUpdateRequest postRequest) {
        PostResponse response = postService.update(postRequest);
        return SuccessResonse.of(response);
    }


    @DeleteMapping("{postId}")
    public SuccessResonse deletePost(@PathVariable("postId") long postId) {
        postService.delete(postId);
        return SuccessResonse.of();
    }
}

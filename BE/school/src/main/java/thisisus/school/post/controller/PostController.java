package thisisus.school.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.service.PostServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
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
    public SuccessResonse<PostResponse> savePost(@Valid @RequestBody PostRequest postRequest) {
        PostResponse response = postService.savePost(postRequest);
        return SuccessResonse.of(response);
    }

    @GetMapping("/{postId}")
    public SuccessResonse<PostResponse> findPost(@PathVariable("postId") long postId) {
        PostResponse response = postService.findPost(postId);
        return SuccessResonse.of(response);
    }

    @PatchMapping
    public SuccessResonse<PostResponse> updatePost(@Valid @RequestBody PostUpdateRequest postRequest) {
        PostResponse response = postService.update(postRequest);
        return SuccessResonse.of(response);
    }


    @DeleteMapping("{postId}")
    public SuccessResonse deletePost(@PathVariable("postId") long postId) {
        postService.delete(postId);
        return SuccessResonse.of();
    }
}

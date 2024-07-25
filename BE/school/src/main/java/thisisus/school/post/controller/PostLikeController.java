package thisisus.school.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.service.PostLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}/like")
    public SuccessResonse likePost(@PathVariable("postId") final Long postId,
                                   @AuthenticatedMemberId final Long memberId) {
        postLikeService.likePost(postId, memberId);
        return SuccessResonse.of();
    }

    @DeleteMapping("{postId}/like")
    public SuccessResonse disLikePost(@PathVariable("postId") final Long postId,
                                      @AuthenticatedMemberId final Long memberId) {
        postLikeService.disLikePost(postId, memberId);
        return SuccessResonse.of();
    }
}

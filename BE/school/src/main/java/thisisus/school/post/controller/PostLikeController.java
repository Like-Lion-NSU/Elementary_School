package thisisus.school.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.service.PostLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public SuccessResonse insertLike(@PathVariable("postId") final Long postId,
                                     @AuthenticatedMemberId final Long memberId) {
        postLikeService.insertLike(postId, memberId);
        return SuccessResonse.of();
    }
}

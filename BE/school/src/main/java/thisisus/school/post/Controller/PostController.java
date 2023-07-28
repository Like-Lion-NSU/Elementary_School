package thisisus.school.post.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import thisisus.school.post.dto.PostUpRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.domain.Member;
import thisisus.school.post.dto.PostUpResponseDto;
import thisisus.school.post.service.PostService;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public PostUpResponseDto postWrite(PostUpRequestDto postUpRequestDto) throws Exception{
        log.info("[postWrite] 게시물 작성 동작");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Member member = (Member) principal;
        log.info("User uid : {}", member.getEmail());

        PostUpResponseDto postUpResponseDto = postService.savePost(member, postUpRequestDto);

        return postUpResponseDto;
    }

}

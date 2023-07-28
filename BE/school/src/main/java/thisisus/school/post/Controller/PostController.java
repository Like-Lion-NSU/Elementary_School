package thisisus.school.post.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thisisus.school.post.category.PostCategory;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.DetailPostDto;
import thisisus.school.post.dto.PostUpRequestDto;
import thisisus.school.domain.Member;
import thisisus.school.post.dto.PostResponseDto;
import thisisus.school.post.service.PostService;

@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 작성
    @PostMapping("/write")
    public PostResponseDto postWrite(PostUpRequestDto postUpRequestDto) throws Exception{
        log.info("[postWrite] 게시물 작성 동작");

        PostResponseDto postResponseDto = postService.savePost(postUpRequestDto);

        log.info("[postWrite] 게시물 작성 성공");

        return postResponseDto;
    }

    //게시물 수정
    @PostMapping("/{postId}/update")
    public PostResponseDto updatePost(@PathVariable("postId")Long postId, PostUpRequestDto postUpRequestDto){
        log.info("[updatePost] 게시물 수정 동작");

        PostResponseDto postResponseDto = postService.updatePost(postId, postUpRequestDto);

        return postResponseDto;
    }

    //게시물 삭제
    @DeleteMapping("/post/{postId}/delete")
    public PostResponseDto deletePost(@PathVariable("postId")Long postId){
        log.info("[deletePost] 게시물 삭제 동작.");

        return postService.deletePost(postId);
    }

    //카테고리별로 게시물 조회
    @GetMapping("/post/{category}")
    public String findPostsByCategory(@PathVariable("category") String category, Model model){
        log.info("[findPostsByCategory] 게시물 카테고리 동작.");
        Page<Post> postList =  postService.findPostsByCategory(PostCategory.valueOf(category));

        model.addAttribute("postList", postList);

        return "/";
    }

    //특정 게시글 1개 조회
    @GetMapping("/post/{category}/{postId}")
    public String detailPost(@PathVariable("category")String category, @PathVariable("postId")Long postId, Model model){
        log.info("[detailPost] 특정 게시물 조회 동작.");
        DetailPostDto detailPost = postService.detailPost(postId,category);
        return "/";
    }

}

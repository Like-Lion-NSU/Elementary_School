package thisisus.school.post.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thisisus.school.common.DefaultResponseDto;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.PostDefaultResponseDto;
import thisisus.school.post.dto.PostRequestDto;
import thisisus.school.post.service.PostService;


import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "Post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation(value="게시글 등록")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "POST_SAVE",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "POST_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
})
    @GetMapping("/post")
    public ResponseEntity<DefaultResponseDto> savePost(/*HttpServletRequest request,*/ PostRequestDto postRequestDto){

        Post post = postService.savePost(postRequestDto);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_REGISTERED")
                        .responseMessage("게시글 등록 완료")
                        .data(response)
                        .build());
    }


    @ApiOperation(value="내가 쓴 게시글 다건 조회")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "POSTS_FOUND",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "POSTS_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("/user/posts")
    public ResponseEntity<DefaultResponseDto> findAllPostsByUser(/*HttpServletRequest request,*/@RequestParam("memberId")Long memberId){
        List<Post> posts = postService.findAllPostsByUser(memberId);

        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POSTS_FOUND")
                        .responseMessage("내가 쓴 게시글 다건 조회 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value="게시물 단건 조회")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "POST_FOUND",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "POST_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> findOnePost(@RequestParam("postId") Long postId){

        Post post = postService.findOnePost(postId);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_FOUND")
                        .responseMessage("게시물 단건 조회 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value="게시글 수정")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "POST_UPDATEED",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "POST_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PutMapping("/user/{memberId}/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> updatePost(@RequestParam("postId") Long postId,@RequestParam("memberId") Long memberId, PostRequestDto postRequestDto ){

        Post post = postService.updatePost(postId, postRequestDto);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_UPDATEED")
                        .responseMessage("게시글 수정 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value="게시글 삭제")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "POST_DELETED",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "POST_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PostMapping("/user/{memberId}/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> deletePost(@RequestParam("postId") Long postId, @RequestParam("memberId") Long memberId ){

        Post post = postService.deletePost(postId);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_DELETED")
                        .responseMessage("게시글 삭제 완료")
                        .data(response)
                        .build());
    }




}

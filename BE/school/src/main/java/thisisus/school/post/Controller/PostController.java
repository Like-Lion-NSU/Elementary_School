package thisisus.school.post.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.post.dto.request.PostRequest;
import thisisus.school.post.dto.request.PostUpdateRequest;
import thisisus.school.post.dto.response.PostResponse;
import thisisus.school.post.service.PostServiceImpl;

import javax.validation.Valid;

@RestController
@Slf4j
@Api(tags = "게시글")
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostServiceImpl postService;

    /**
     * 게시글 등록
     * TODO - memeber관련 작업
     *
     * @param postRequest
     * @return
     */
    @ApiOperation(value = "게시글 등록")
    @PostMapping
    public SuccessResonse<PostResponse> savePost(@Valid PostRequest postRequest) {
        PostResponse response = postService.savePost(postRequest);
        return SuccessResonse.of(response);
    }

    /**
     * 게시물 상세 조회
     * @param postId
     * @return
     */
    @ApiOperation(value = "게시물 한개 조회")
    @GetMapping("/{postId}")
    public SuccessResonse<PostResponse> findOnePost(@PathVariable("postId") long postId) {
        PostResponse response = postService.findOnePost(postId);
        return SuccessResonse.of(response);
    }

    /**
     * 게시글 수정
     * @param postRequest
     * @return
     */
    @ApiOperation(value = "게시글 수정")
    @PatchMapping
    public SuccessResonse<PostResponse> updatePost(@Valid PostUpdateRequest postRequest) {
        PostResponse response = postService.update(postRequest);
        return SuccessResonse.of(response);
    }


    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("{postId}")
    public SuccessResonse deletePost(@PathVariable("postId") long postId) {
        postService.delete(postId);
        return SuccessResonse.of();
    }



//
//
//
//    @ApiOperation(value = "내가 쓴 게시글 다건 조회")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "POSTS_FOUND",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "POSTS_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
///*    @GetMapping("/user/{memberId}/{category}/posts")
//    public ResponseEntity<DefaultResponseDto> findAllPostsByUser(*//*HttpServletRequest request,*//*@PathVariable("memberId")Long memberId){
//        List<Post> posts = postService.findAllPostsByUser(memberId);
//
//        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());
//
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("POSTS_FOUND")
//                        .responseMessage("내가 쓴 게시글 다건 조회 완료")
//                        .data(response)
//                        .build());
//    }*/
//    @GetMapping("/user/posts")
//    public /*List<Post>*/ResponseEntity<DefaultResponseDto> findAllPostsByUser(/*HttpServletRequest request,*/@AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        List<Post> posts = postServiceImpl2.findAllPostsByUser(customUserDetails);
//
//        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());
//
//        /*return posts;*/
//
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("POSTS_FOUND")
//                        .responseMessage("내가 쓴 게시글 다건 조회 완료")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value = "카테고리별 게시글 조회")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "POSTS_FOUND",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "POSTS_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @GetMapping("/{category}/posts")
//    public ResponseEntity<DefaultResponseDto> findAllPostsByCategory(@PathVariable("category") String category) {
//        List<Post> posts = postServiceImpl2.findAllPostsByCategory(category);
//
//        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());
//
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("POSTS_FOUND")
//                        .responseMessage("카테고리별 게시글 조회")
//                        .data(response)
//                        .build());
//    }
//

//
//
//
//    @ApiOperation(value = "좋아요한 게시글 다건 조회")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "LIKE_FOUND",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @GetMapping("/user/posts/likes")
//    public ResponseEntity<DefaultResponseDto> findAllLikedPostsByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
//
//        List<Post> posts = postServiceImpl2.findAllLikedPostsByUser(customUserDetails);
//
//        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("LIKE_FOUND")
//                        .responseMessage("내가 쓴 댓글 조회 완료")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value = "좋아요 등록")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "LIKE_WORK",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @PutMapping("/post/{category}/{postId}/like")
//    public ResponseEntity<DefaultResponseDto> saveLike(@PathVariable("postId") Long postId,
//                                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//
//        PostLiked postLiked = postServiceImpl2.saveLike(postId, customUserDetails);
//
//        LikedDefaultResponseDto response = new LikedDefaultResponseDto(postLiked);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("LIKE_WORK")
//                        .responseMessage("좋아요 등록")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value = "좋아요 취소")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "LIKE_WORK",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @DeleteMapping("/post/{category}/{postId}/like/{likeId}")
//    public ResponseEntity<DefaultResponseDto> deletedLike(@PathVariable("postId") Long postId,
//                                                          @PathVariable("likeId") Long likeId,
//                                                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        if (postServiceImpl2.likeMatchMember(likeId, customUserDetails)) {
//            PostLiked postLiked = postServiceImpl2.deletedLike(likeId);
//
//            LikedDefaultResponseDto response = new LikedDefaultResponseDto(postLiked);
//            return ResponseEntity.status(200)
//                    .body(DefaultResponseDto.builder()
//                            .responseCode("LIKE_WORK")
//                            .responseMessage("좋아요 등록/취소")
//                            .data(response)
//                            .build());
//        }
//        return ResponseEntity.status(400)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("like_NOT_FOUND")
//                        .responseMessage("좋아요를 찾을수 없습니다.")
//                        .data(null)
//                        .build());
//    }


}
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import thisisus.school.common.DefaultResponseDto;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostLiked;
import thisisus.school.post.dto.LikedDefaultResponseDto;
import thisisus.school.post.dto.PostDefaultResponseDto;
import thisisus.school.post.dto.PostRequestDto;
import thisisus.school.post.repository.PostLikeRepository;
import thisisus.school.post.repository.PostRepository;
import thisisus.school.post.service.PostService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "게시글")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostLikeRepository postLikeRepository;
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
    @PostMapping(value="/writePost",consumes = "multipart/form-data")
    public ResponseEntity<DefaultResponseDto> savePost(PostRequestDto postRequestDto,
                                                       @AuthenticationPrincipal CustomUserDetails customUserDetails)
            throws Exception{

        if(customUserDetails != null) {
            Post post = postService.savePost(postRequestDto, customUserDetails);
            PostDefaultResponseDto response = new PostDefaultResponseDto(post);
            return ResponseEntity.status(200)
                    .body(DefaultResponseDto.builder()
                            .responseCode("POST_REGISTERED")
                            .responseMessage("게시글 등록 완료")
                            .data(response)
                            .build());
        }
        return ResponseEntity.status(401)
                .body(DefaultResponseDto.builder()
                        .responseMessage("해당 유저를 찾을 수 없습니다.")
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
/*    @GetMapping("/user/{memberId}/{category}/posts")
    public ResponseEntity<DefaultResponseDto> findAllPostsByUser(*//*HttpServletRequest request,*//*@PathVariable("memberId")Long memberId){
        List<Post> posts = postService.findAllPostsByUser(memberId);

        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POSTS_FOUND")
                        .responseMessage("내가 쓴 게시글 다건 조회 완료")
                        .data(response)
                        .build());
    }*/
    @GetMapping("/user/posts")
    public /*List<Post>*/ResponseEntity<DefaultResponseDto> findAllPostsByUser(/*HttpServletRequest request,*/@AuthenticationPrincipal CustomUserDetails customUserDetails){
        List<Post> posts = postService.findAllPostsByUser(customUserDetails);

        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());

        /*return posts;*/

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POSTS_FOUND")
                        .responseMessage("내가 쓴 게시글 다건 조회 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value="카테고리별 게시글 조회")
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
    @GetMapping("/{category}/posts")
    public ResponseEntity<DefaultResponseDto> findAllPostsByCategory(@PathVariable("category")String category){
        List<Post> posts = postService.findAllPostsByCategory(category);

        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());

        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POSTS_FOUND")
                        .responseMessage("카테고리별 게시글 조회")
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
    public ResponseEntity<DefaultResponseDto> findOnePost(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("postId") Long postId){

        Post post = postService.findOnePost(postId);
        /*PostDefaultResponseDto response = new PostDefaultResponseDto(post);*/
        PostLiked isLiked = postService.isLikedPost(customUserDetails, post);
        PostDefaultResponseDto response = new PostDefaultResponseDto(post,isLiked);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_FOUND")
                        .responseMessage("게시물 단건 조회")
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
/*    @PutMapping("/user/{memberId}/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> updatePost(@PathVariable("postId") Long postId,@PathVariable("memberId") Long memberId, @RequestBody PostRequestDto postRequestDto ){

        Post post = postService.updatePost(postId, postRequestDto);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_UPDATEED")
                        .responseMessage("게시글 수정 완료")
                        .data(response)
                        .build());
    }*/
    @PutMapping(value="/post/{category}/{postId}",consumes = "multipart/form-data")
    public ResponseEntity<DefaultResponseDto> updatePost(@PathVariable("postId") Long postId,
                                                         @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                         PostRequestDto postRequestDto )throws Exception{

        if (postService.postMatchMember(postId, customUserDetails)) {

            Post post = postService.updatePost(postId, postRequestDto);

            PostDefaultResponseDto response = new PostDefaultResponseDto(post);
            return ResponseEntity.status(200)
                    .body(DefaultResponseDto.builder()
                            .responseCode("POST_UPDATEED")
                            .responseMessage("게시글 수정 완료")
                            .data(response)
                            .build());
        }
        return ResponseEntity.status(400)
                .body(DefaultResponseDto.builder()
                        .responseCode("USER_NOT_FOUND")
                        .responseMessage("유저를 찾을 수 없습니다.")
                        .data(null)
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
/*    @DeleteMapping("/user/{memberId}/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> deletePost(@PathVariable("postId") Long postId, @PathVariable("memberId") Long memberId ){

        Post post = postService.deletePost(postId);

        PostDefaultResponseDto response = new PostDefaultResponseDto(post);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_DELETED")
                        .responseMessage("게시글 삭제 완료")
                        .data(response)
                        .build());
    }*/
    @DeleteMapping("/post/{category}/{postId}")
    public ResponseEntity<DefaultResponseDto> deletePost(@PathVariable("postId") Long postId, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (postService.postMatchMember(postId, customUserDetails)) {
            Post post = postService.deletePost(postId);

            PostDefaultResponseDto response = new PostDefaultResponseDto(post);
            return ResponseEntity.status(200)
                    .body(DefaultResponseDto.builder()
                            .responseCode("POST_DELETED")
                            .responseMessage("게시글 삭제 완료")
                            .data(response)
                            .build());
        }
        return ResponseEntity.status(400)
                .body(DefaultResponseDto.builder()
                        .responseCode("USER_NOT_FOUND")
                        .responseMessage("유저를 찾을 수 없습니다.")
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "좋아요한 게시글 다건 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "LIKE_FOUND",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("/user/posts/likes")
    public ResponseEntity<DefaultResponseDto> findAllLikedPostsByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        List<Post> posts = postService.findAllLikedPostsByUser(customUserDetails);

        List<PostDefaultResponseDto> response = posts.stream().map(post -> new PostDefaultResponseDto(post)).collect(Collectors.toList());
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("LIKE_FOUND")
                        .responseMessage("내가 쓴 댓글 조회 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value = "좋아요 등록")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "LIKE_WORK",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PutMapping("/post/{category}/{postId}/like")
    public ResponseEntity<DefaultResponseDto> saveLike(@PathVariable("postId") Long postId,
                                                       @AuthenticationPrincipal CustomUserDetails customUserDetails ) {

        PostLiked postLiked = postService.saveLike(postId,customUserDetails);

        LikedDefaultResponseDto response = new LikedDefaultResponseDto(postLiked);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("LIKE_WORK")
                        .responseMessage("좋아요 등록")
                        .data(response)
                        .build());
    }

    @ApiOperation(value = "좋아요 취소")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "LIKE_WORK",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @DeleteMapping("/post/{category}/{postId}/like/{likeId}")
    public ResponseEntity<DefaultResponseDto> deletedLike(@PathVariable("postId") Long postId,
                                                          @PathVariable("likeId") Long likeId,
                                                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (postService.likeMatchMember(likeId, customUserDetails)) {
            PostLiked postLiked = postService.deletedLike(likeId);

            LikedDefaultResponseDto response = new LikedDefaultResponseDto(postLiked);
            return ResponseEntity.status(200)
                    .body(DefaultResponseDto.builder()
                            .responseCode("LIKE_WORK")
                            .responseMessage("좋아요 등록/취소")
                            .data(response)
                            .build());
        }
        return ResponseEntity.status(400)
                .body(DefaultResponseDto.builder()
                        .responseCode("like_NOT_FOUND")
                        .responseMessage("좋아요를 찾을수 없습니다.")
                        .data(null)
                        .build());
    }




}
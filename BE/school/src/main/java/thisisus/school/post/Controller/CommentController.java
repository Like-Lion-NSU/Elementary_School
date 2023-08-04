package thisisus.school.post.Controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import thisisus.school.common.DefaultResponseDto;
import thisisus.school.post.dto.PostDefaultResponseDto;
import thisisus.school.post.dto.PostRequestDto;

import javax.validation.Valid;

public class CommentController {
//    @ApiOperation(value="댓글 등록")
//    @ApiResponses(value ={
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "COMMENT_SAVE",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @GetMapping("/post/{category}/{postId}/comment")
//    public ResponseEntity<DefaultResponseDto> saveComment(@RequestBody @Valid PostRequestDto PostRequestDto){
//
//        PostDefaultResponseDto response = new PostDefaultResponseDto(comment);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("POST_REGISTERED")
//                        .responseMessage("댓글 등록 완료")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value="댓글 수정")
//    @ApiResponses(value ={
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "COMMENT_UPDATE",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @PostMapping("/post/{category}/{postId}/comment/{commentId}")
//    public ResponseEntity<DefaultResponseDto> updateComment(@RequestBody @Valid ){
//
//        PostDefaultResponseDto response = new PostDefaultResponseDto(comment);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("COMMENT_UPDATE")
//                        .responseMessage("댓글 수정 완료")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value="내가 쓴 댓글 다건 조회")
//    @ApiResponses(value ={
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "COMMENT_FOUND",
//                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
//            @ApiResponse(responseCode = "401",
//                    description = "UNAUTHORIZED_MEMBER"),
//            @ApiResponse(responseCode = "404",
//                    description = "COMMENT_NOT_FOUND"),
//            @ApiResponse(responseCode = "500",
//                    description = "SERVER_ERROR"),
//    })
//    @PostMapping("/post/{category}/{postId}/comments")
//    public ResponseEntity<DefaultResponseDto> findAllCommentsByUser(@RequestBody @Valid ){
//
//        PostDefaultResponseDto response = new PostDefaultResponseDto(comment);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("COMMENT_FOUND")
//                        .responseMessage("내가 쓴 댓글 다건 조회 완료")
//                        .data(response)
//                        .build());
//    }
//
//    @ApiOperation(value="내가 좋아요한 게시글 조회")
//    @ApiResponses(value ={
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
//    @PostMapping("/user/posts/likes")
//    public ResponseEntity<DefaultResponseDto> findAllPostByLikes(@RequestBody @Valid ){
//
//        PostDefaultResponseDto response = new PostDefaultResponseDto(like);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("LIKE_FOUND")
//                        .responseMessage("내가 쓴 댓글 조회 완료")
//                        .data(response)
//                        .build());
//    }
//
//
//    @ApiOperation(value="좋아요 등록/취소")
//    @ApiResponses(value ={
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
//    @PostMapping("/post/{category}/{postId}")
//    public ResponseEntity<DefaultResponseDto> saveLike(@RequestBody @Valid ){
//
//        PostDefaultResponseDto response = new PostDefaultResponseDto(like);
//        return ResponseEntity.status(200)
//                .body(DefaultResponseDto.builder()
//                        .responseCode("LIKE_WORK")
//                        .responseMessage("좋아요 등록/취소")
//                        .data(response)
//                        .build());
//    }
}

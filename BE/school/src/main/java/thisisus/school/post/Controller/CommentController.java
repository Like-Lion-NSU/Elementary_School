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
import thisisus.school.post.domain.Comment;
import thisisus.school.post.dto.CommentDefaultResponseDto;
import thisisus.school.post.dto.PostDefaultResponseDto;
import thisisus.school.post.dto.CommentRequestDto;
import thisisus.school.post.repository.CommentRepository;
import thisisus.school.post.service.CommentService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "댓글")
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;

    private final CommentService commentService;

    @ApiOperation(value = "댓글 등록")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "COMMENT_SAVE",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PostMapping("/post/{category}/{postId}/comment")
    public ResponseEntity<DefaultResponseDto> saveComment(@RequestParam("category") String category, @RequestParam("postId") Long postId, CommentRequestDto commentRequestDto) {

        Comment comment = commentService.saveComment(postId, commentRequestDto);

        CommentDefaultResponseDto response = new CommentDefaultResponseDto(comment);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("POST_REGISTERED")
                        .responseMessage("댓글 등록 완료")
                        .data(response)
                        .build());
    }

    @ApiOperation(value = "댓글 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "COMMENT_UPDATE",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @PutMapping("/post/{category}/{postId}/comment/{commentId}")
    public ResponseEntity<DefaultResponseDto> updateComment(@RequestParam("category") String category, @RequestParam("postId") Long postId,
                                                            CommentRequestDto commentRequestDto, @RequestParam("commentId") Long commentId) {
        Comment comment = commentService.updateComment(commentId, commentRequestDto);

        CommentDefaultResponseDto response = new CommentDefaultResponseDto(comment);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("COMMENT_UPDATE")
                        .responseMessage("댓글 수정")
                        .data(response)
                        .build());
    }

    @ApiOperation(value = "내가 쓴 댓글 다건 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "COMMENTS_FOUND",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @GetMapping("/user/{userId}/post/comments")
    public ResponseEntity<DefaultResponseDto> findAllCommentsByUser(@RequestParam("userId") Long userId) {

        List<Comment> comments = commentService.findAllCommentByUser(userId);

        List<CommentDefaultResponseDto> response = comments.stream().map(comment -> new CommentDefaultResponseDto(comment)).collect(Collectors.toList());
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("COMMENTS_FOUND")
                        .responseMessage("내가 쓴 댓글 다건 조회 완료")
                        .data(response)
                        .build());
    }


    @ApiOperation(value = "내가 쓴 댓글 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "COMMENT_DELETED",
                    content = @Content(schema = @Schema(implementation = PostDefaultResponseDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED_MEMBER"),
            @ApiResponse(responseCode = "404",
                    description = "COMMENT_NOT_FOUND"),
            @ApiResponse(responseCode = "500",
                    description = "SERVER_ERROR"),
    })
    @DeleteMapping("/post/{category}/{postId}/comment/{commentId}")
    public ResponseEntity<DefaultResponseDto> deleteComment(@RequestParam("category") String category,@RequestParam("commentId") Long commentId,@RequestParam("postId") Long postId) {

        Comment comment = commentService.deleteComment(commentId);


        CommentDefaultResponseDto response = new CommentDefaultResponseDto(comment);
        return ResponseEntity.status(200)
                .body(DefaultResponseDto.builder()
                        .responseCode("COMMENT_DELETED")
                        .responseMessage("내가 쓴 댓글 삭제")
                        .data(response)
                        .build());
    }


}

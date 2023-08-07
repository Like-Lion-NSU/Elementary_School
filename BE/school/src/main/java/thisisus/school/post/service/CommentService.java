package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.exception.CustomException;
import thisisus.school.post.domain.Comment;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.CommentRequestDto;
import thisisus.school.post.repository.CommentRepository;
import thisisus.school.post.repository.PostRepository;

import java.util.List;

import static thisisus.school.exception.ExceptionCode.SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class CommentService {
   private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /*
   * 댓글 등록
   * 500(SERVER_ERROR)
    */
    @Transactional
    public Comment saveComment(Long postId, CommentRequestDto commentRequestDto){
        try{
            Post post = postRepository.findById(postId).get();
            Comment comment = Comment.builder()
                    .content(commentRequestDto.getContent())
                    .build();
            comment.setPost(post);
            commentRepository.save(comment);
            return comment;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }

    /*
    * 댓글 수정
    * 500(SERVER_ERROR)
    * */
    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto commentRequestDto){
        try{
            Comment comment = commentRepository.findById(commentId).get();
            comment.update(commentRequestDto);

            return comment;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }

    /*
     * 내가 쓴 댓글 다건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public List<Comment> findAllCommentByUser(Long userId){
        try{
            List<Comment> comments = commentRepository.findByMemberIdAndIsDeletedIsFalse(userId);
            return comments;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }

    /*
     * 내가 쓴 댓글 삭제
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Comment deleteComment(Long commentId){
        try{
            Comment comment = commentRepository.findById(commentId).get();
            comment.delete();
            return comment;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }
}

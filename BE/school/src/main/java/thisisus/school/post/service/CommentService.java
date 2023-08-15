package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import thisisus.school.exception.CustomException;
import thisisus.school.post.domain.Comment;
import thisisus.school.post.domain.CommentPhoto;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.CommentRequestDto;
import thisisus.school.post.repository.CommentPhotoRepository;
import thisisus.school.post.repository.CommentRepository;
import thisisus.school.post.repository.PostPhotoRepository;
import thisisus.school.post.repository.PostRepository;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static thisisus.school.exception.ExceptionCode.SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentPhotoRepository commentPhotoRepository;
   private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    /*
   * 댓글 등록
   * 500(SERVER_ERROR)
    */
    @Transactional
    public Comment saveComment(Long postId, CommentRequestDto commentRequestDto) throws Exception{
        try{
            Post post = postRepository.findById(postId).get();
            Comment comment = Comment.builder()
                    .content(commentRequestDto.getContent())
                    .build();
            comment.setPost(post);
            if(commentRequestDto.getFiles().size()!=0) {
                savePhoto(comment, commentRequestDto.getFiles());
            }
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

    @Transactional
    public void savePhoto(Comment comment, List<MultipartFile> files) throws Exception {
        if (files.size() != 0) {
            String projectPath = System.getProperty("user.dir") + "\\\\src\\\\main\\\\resources\\\\static\\\\comments";
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                String extension = filename.substring(filename.lastIndexOf("."));
                String saveFilename = UUID.randomUUID()+extension;

                File saveFile = new File(projectPath, saveFilename);
                file.transferTo(saveFile);

               CommentPhoto commentPhoto = CommentPhoto.builder()
                        .photoUrl(saveFilename)
                        .comment(comment)
                        .build();
                commentPhotoRepository.save(commentPhoto);
            }

        }
    }
}

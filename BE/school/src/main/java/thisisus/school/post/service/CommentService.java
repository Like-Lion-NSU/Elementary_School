package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import thisisus.school.exception.CustomException;
import thisisus.school.member.domain.Member;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;
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
    private final MemberService memberService;


    /*
   * 댓글 등록
   * 500(SERVER_ERROR)
    */
    @Transactional
    public Comment saveComment(Long postId, CommentRequestDto commentRequestDto,
    CustomUserDetails customUserDetails) throws Exception{
        try{
            Member member = memberService.findMember(customUserDetails);
            Post post = postRepository.findById(postId).get();
            Comment comment = Comment.builder()
                    .content(commentRequestDto.getContent())
                    .build();
            comment.setPost(post);
            comment.setMember(member);
          
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
    public List<Comment> findAllCommentByUser(CustomUserDetails customUserDetails){
        try{
            Member member = memberService.findMember(customUserDetails);
            List<Comment> comments = commentRepository.findByMemberIdAndIsDeletedIsFalse(member.getId());
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
            String projectPath = System.getProperty("user.dir") + "\\\\BE\\\\school\\\\src\\\\main\\\\resources\\\\static\\\\comments";
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

    public boolean commentMatchMember(Long commentId, CustomUserDetails customUserDetails) {
        Comment comment = commentRepository.findById(commentId).get();
        Member member = memberService.findMember(customUserDetails);

        return comment.getMember().getId().equals(member.getId());
    }
}

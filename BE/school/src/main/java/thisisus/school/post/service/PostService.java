package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import thisisus.school.exception.CustomException;
import thisisus.school.member.domain.Member;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;
import thisisus.school.post.domain.*;
import thisisus.school.post.dto.PostRequestDto;
import thisisus.school.post.repository.PostPhotoRepository;
import thisisus.school.post.repository.PostLikeRepository;
import thisisus.school.post.repository.PostRepository;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static thisisus.school.exception.ExceptionCode.SERVER_ERROR;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final MemberService memberService;
    private final PostPhotoRepository postPhotoRepository;

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    /*
     * 게시글 등록
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post savePost(PostRequestDto postRequestDto, CustomUserDetails customUserDetails) throws Exception {
        try {
            Member member =  memberService.findMember(customUserDetails);

            Post post = Post.builder()
                    .title(postRequestDto.getTitle())
                    .content(postRequestDto.getContent())
                    .category(PostCategory.valueOf(postRequestDto.getCategory()))
                    .member(member)
                    .build();
            if(postRequestDto.getFiles()!=null) {
                savePhoto(post, postRequestDto.getFiles());
            }
            postRepository.save(post);
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }


    public List<Post> findAllPostsByCategory(String category){
        try{
            List<Post> posts = postRepository.findByCategory(PostCategory.valueOf(category));

            return posts;
        }catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }

    /*
     * 내가 쓴 게시글 다건 조회
     * 500(SERVER_ERROR)
     * */
/*    @Transactional(readOnly = true)
    public List<Post> findAllPostsByUser(Long memberId) {
        try {
            List<Post> posts = postRepository.findAllByMemberIdAndIsDeletedIsFalse(memberId);
            return posts;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }*/
    @Transactional(readOnly = true)
    public List<Post> findAllPostsByUser(CustomUserDetails customUserDetails) {
        try {
            Member member = memberService.findMember(customUserDetails);
            List<Post> posts = postRepository.findAllByMemberIdAndIsDeletedIsFalse(member.getId());
            return posts;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 게시글 단건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public Post findOnePost(Long postId) {
        try {
            Post post = postRepository.findById(postId).get();
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }
    @Transactional
    public PostLiked isLikedPost(CustomUserDetails customUserDetails, Post post){
        try{
            Member member = memberService.findMember(customUserDetails);
            return postLikeRepository.findByMemberIdAndPostIdAndIsDeletedIsFalse(member.getId(),post.getId());
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }
    }

    /*
     * 게시글 수정
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post updatePost(Long postId, PostRequestDto postRequestDto) throws Exception{
        try {
            Post post = postRepository.findById(postId).get();
            if(postRequestDto.getFiles()!=null) {
                savePhoto(post, postRequestDto.getFiles());
            }
            post.updatePost(postRequestDto);
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 게시글 삭제
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post deletePost(Long postId) {
        try {
            Post post = postRepository.findById(postId).get();
            post.delete();
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 좋아요한 게시글 다건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public List<Post> findAllLikedPostsByUser(CustomUserDetails customUserDetails) {
        List<Post> posts = new ArrayList<>();
        try {
            Member member= memberService.findMember(customUserDetails);
            List<PostLiked> postLikes = postLikeRepository.findByMemberIdAndIsDeletedIsFalse(member.getId());

            postLikes.stream().map(postLike -> posts.add(postLike.getPost()));

            return posts;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    @Transactional
    public PostLiked saveLike(Long postId,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {
            Member member = memberService.findMember(customUserDetails);
            Post post = postRepository.findById(postId).get();
            PostLiked postLiked = PostLiked.builder()
                    .post(post)
                    .member(member)
                    .build();

            postLikeRepository.save(postLiked);
            return postLiked;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }


    @Transactional
    public PostLiked deletedLike(Long likeId) {
        try {
            PostLiked postLiked = postLikeRepository.findById(likeId).get();
            postLiked.delete();

            return postLiked;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }
    @Transactional
    public void savePhoto(Post post, List<MultipartFile> files) throws Exception {
        if (files.size() != 0) {
            String projectPath = System.getProperty("user.dir") + "\\\\BE\\\\school\\\\src\\\\main\\\\resources\\\\static\\\\files";
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                String extension = filename.substring(filename.lastIndexOf("."));
                String saveFilename = UUID.randomUUID()+extension;

                File saveFile = new File(projectPath, saveFilename);
                file.transferTo(saveFile);

                PostPhoto postPhoto = PostPhoto.builder()
                        .photoUrl(saveFilename)
                        .post(post)
                        .build();
                postPhotoRepository.save(postPhoto);
            }

        }
    }

    public boolean postMatchMember(Long postId, CustomUserDetails customUserDetails) {
        Post post = postRepository.findById(postId).get();
        Member member = memberService.findMember(customUserDetails);

        return post.getMember().getId().equals(member.getId());
    }

    public boolean likeMatchMember(Long likeId, CustomUserDetails customUserDetails) {
        PostLiked like = postLikeRepository.findById(likeId).get();
        Member member = memberService.findMember(customUserDetails);

        return like.getMember().getId().equals(member.getId());
    }
}
package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.exception.CustomException;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequestDto;
import thisisus.school.post.repository.PostRepository;


import java.util.List;

import static thisisus.school.exception.ExceptionCode.SERVER_ERROR;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /*
    * 게시글 등록
    * 500(SERVER_ERROR)
    * */
    @Transactional
    public Post savePost(PostRequestDto postRequestDto){
        try{
            Post post = Post.builder()
                    .title(postRequestDto.getTitle())
                    .content(postRequestDto.getContent())
                    .category(PostCategory.valueOf(postRequestDto.getCategory()))
                    .build();
            postRepository.save(post);
            return post;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 내가 쓴 게시글 다건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public List<Post> findAllPostsByUser(Long memberId){
        try{
           List<Post> posts = postRepository.findAllByMemberIdAndIsDeletedIsFalse(memberId);
            return posts;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 게시글 단건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public Post findOnePost(Long postId){
        try{
            Post post = postRepository.findById(postId).get();
            return post;
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
    public Post updatePost(Long postId, PostRequestDto postRequestDto){
        try{
            Post post = postRepository.findById(postId).get();
            post.updatePost(postRequestDto);
            return post;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 게시글 삭제
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post deletePost(Long postId){
        try{
            Post post = postRepository.findById(postId).get();
            post.delete();
            return post;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }
    @Transactional
    public Boolean validatedMemberAndPost(Long postId, Long memberId){
        Post post = postRepository.findById(postId).get();
        Post user = postRepository.findByMemberId(memberId);
        if(post.getMemberId().equals(user.getMemberId())){
            return true;
        }else{
            return false;
        }
    }

}

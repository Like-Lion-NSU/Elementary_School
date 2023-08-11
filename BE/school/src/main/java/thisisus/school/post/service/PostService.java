package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import thisisus.school.exception.CustomException;
import thisisus.school.post.domain.PostPhoto;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.domain.PostLiked;
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
    private final PostPhotoRepository postPhotoRepository;

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    /*
     * 게시글 등록
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post savePost(PostRequestDto postRequestDto) throws Exception {
        try {
            Post post = Post.builder()
                    .title(postRequestDto.getTitle())
                    .content(postRequestDto.getContent())
                    .category(PostCategory.valueOf(postRequestDto.getCategory()))
                    .build();
            if(postRequestDto.getFiles().size()!=0) {
                savePhoto(post, postRequestDto.getFiles());
            }
            postRepository.save(post);
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    /*
     * 내가 쓴 게시글 다건 조회
     * 500(SERVER_ERROR)
     * */
    @Transactional(readOnly = true)
    public List<Post> findAllPostsByUser(Long memberId) {
        try {
            List<Post> posts = postRepository.findAllByMemberIdAndIsDeletedIsFalse(memberId);
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

    /*
     * 게시글 수정
     * 500(SERVER_ERROR)
     * */
    @Transactional
    public Post updatePost(Long postId, PostRequestDto postRequestDto) {
        try {
            Post post = postRepository.findById(postId).get();
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
    public List<Post> findAllLikedPostsByUser(Long memberId) {
        List<Post> posts = new ArrayList<>();
        try {
            List<PostLiked> postLikes = postLikeRepository.findByMemberIdAndIsDeletedIsFalse(memberId);

            postLikes.stream().map(postLike -> posts.add(postLike.getPost()));

            return posts;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }

    @Transactional
    public Post saveLike(Long postId, Long memberId) {
        try {
            Post post = postRepository.findById(postId).get();
            PostLiked postLiked = PostLiked.builder()
                    .post(post)
                    .memberId(memberId)
                    .build();
            postLikeRepository.save(postLiked);
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }


    @Transactional
    public Post deletedLike(Long likeId) {
        try {
            PostLiked postLiked = postLikeRepository.findById(likeId).get();
            postLiked.delete();
            Post post = postRepository.findById(postLiked.getPost().getId()).get();
            return post;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CustomException(SERVER_ERROR);
        }

    }
    @Transactional
    public void savePhoto(Post post, List<MultipartFile> files) throws Exception {
        if (files.size() != 0) {
            String projectPath = System.getProperty("user.dir") + "\\\\src\\\\main\\\\resources\\\\static\\\\files";
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
}
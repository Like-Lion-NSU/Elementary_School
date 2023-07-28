package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.domain.Member;
import thisisus.school.post.category.PostCategory;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.DetailPostDto;
import thisisus.school.post.dto.PostUpRequestDto;
import thisisus.school.post.dto.PostResponseDto;
import thisisus.school.post.repository.PostRepository;
import thisisus.school.repository.MemberRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    // 게시글 작성
    public PostResponseDto savePost(PostUpRequestDto postUpRequestDto) throws Exception{
        log.info("[savePost] 현재 사용자 정보 조회");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Member member = (Member) principal;
        log.info("User uid : {}", member.getEmail());

        Member user = memberRepository.findByEmail(member.getEmail()).orElse(null);
        log.info("[savePost] 게시글 저장 로직 시작합니다. id : {}", user.getEmail());

        Post post = postUpRequestDto.toEntity();
        post.setMember(user);

        postRepository.save(post);

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .title(post.getTitle())
                .content("게시글 올리기 성공")
                .build();

        return postResponseDto;
    }

    //게시글 수정
    public PostResponseDto updatePost(Long postId, PostUpRequestDto postUpRequestDto){
        log.info("[savePost] 현재 사용자 정보 조회");

        Post post = postRepository.findById(postId).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Member member = (Member) principal;
        log.info("User uid : {}", member.getEmail());
        log.info("[savePost] 게시글 수정 로직 시작합니다. id : {}", member.getEmail());

        if(member.getEmail().equals(post.getMember().getEmail())){
            post.update(postUpRequestDto);
            postRepository.save(post);

            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .title(post.getTitle())
                    .content("게시글 수정 성공")
                    .build();
            return postResponseDto;
        }else {

            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .title(post.getTitle())
                    .content("게시글 수정 실패, 본인의 게시글이 아닙니다.")
                    .build();

            return postResponseDto;
        }
    }


    public PostResponseDto deletePost(Long postId){
        log.info("[savePost] 현재 사용자 정보 조회");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Member member = (Member) principal;

        Post post = postRepository.findById(postId).get();
        if(post.getMember().getEmail().equals(member.getEmail())){
            postRepository.delete(post);

            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .title(post.getTitle())
                    .content("게시글 삭제 성공")
                    .build();

            return postResponseDto;
        }else{
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .title(post.getTitle())
                    .content("게시글 삭제 실패, 본인의 게시글이 아닙니다.")
                    .build();

            return postResponseDto;
        }

    }

    public Page<Post> findPostsByCategory(PostCategory category){
        Page<Post> posts = postRepository.findPostByCategory(category);

        return posts;

    }


    public DetailPostDto detailPost(Long postId, String category){
        Post post = postRepository.findById(postId).get();

        Member member = post.getMember();
        log.info("[detailPost] 게시물 세부사항 조회 로직 시작 PostId : {}, Member : {}", postId, member.getEmail());

        DetailPostDto detailPostDto = DetailPostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .category(category)
                .email(member.getEmail())
                .postId(post.getId())
                .build();
        return detailPostDto;
    }

}

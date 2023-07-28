package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.domain.Member;
import thisisus.school.post.domain.Post;
import thisisus.school.post.dto.PostUpRequestDto;
import thisisus.school.post.dto.PostUpResponseDto;
import thisisus.school.post.repository.PostRepository;
import thisisus.school.repository.MemberRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @Transactional
    public PostUpResponseDto savePost(Member member, PostUpRequestDto postUpRequestDto) throws Exception{
        Member user = memberRepository.findByEmail(member.getEmail()).orElse(null);
        log.info("[savePost] 게시글 저장 로직 시작합니다. id : {}", user.getEmail());

        Post post = postUpRequestDto.toEntity();
        post.setMember(user);

        postRepository.save(post);

        PostUpResponseDto postUpResponseDto = PostUpResponseDto.builder()
                .title(post.getTitle())
                .build();

        return postUpResponseDto;
    }

}

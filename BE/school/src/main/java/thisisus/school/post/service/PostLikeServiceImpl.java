package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostLike;
import thisisus.school.post.exception.AlreadyExistPostLikeException;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostLikeRepository;
import thisisus.school.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public synchronized void likePost(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);
        Post post = postRepository.findWithLockById(postId)
                .orElseThrow(NotFoundPostException::new);
        if (postLikeRepository.existsByMemberAndPost(member, post)) {
            throw new AlreadyExistPostLikeException();
        }
        post.increaseLikeCount();
        postRepository.save(post);

        PostLike postLike = PostLike.builder()
                .post(post)
                .member(member)
                .build();

        postLikeRepository.save(postLike);
    }
}

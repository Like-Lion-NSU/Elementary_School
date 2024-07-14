package thisisus.school.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.MemberStatus;
import thisisus.school.member.domain.Role;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.domain.PostLike;
import thisisus.school.post.exception.AlreadyExistPostLikeException;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostLikeRepository;
import thisisus.school.post.repository.PostRepository;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostLikeServiceImplTest {

    @Mock
    PostLikeRepository postLikeRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    PostLikeServiceImpl postLikeService;

    private Member member;
    private Post post;
    private PostLike postLike;

    @BeforeEach
    void setUp() {
        member = new Member(1L, "장창현",
                "abcdef@naver.com", "장첸", Role.STUDENT, MemberStatus.ACTIVE,
                "tempRefresh");
        post = new Post(1L, "테스트용 제목", "테스트용 내용",
                PostCategory.소통해요, 0, 0, false, member);
        postLike = PostLike.builder().member(member).post(post).build();
    }

    @Test
    @DisplayName("좋아요 insert - 성공")
    void insertPostLike() {
        //given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(false);
        when(postLikeRepository.save(any(PostLike.class))).thenReturn(postLike);

        //when
        postLikeService.insertLike(1L, 1L);

        //then
        verify(memberRepository).findById(1L);
        verify(postRepository).findById(1L);
        verify(postLikeRepository).existsByMemberAndPost(member, post);
        verify(postLikeRepository).save(any(PostLike.class));
        Assertions.assertEquals(1, post.getLikeCount());
    }

    @Test
    @DisplayName("좋아요 insert - 잘못된 postId로 인한 실패")
    void failedInsertPostLikeByWrongPostId() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundPostException.class, () -> postLikeService.insertLike(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 insert - 잘못된 memberId로 인한 실패")
    void failedInsertPostLikeByWrongMemberId() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundMemberException.class, () -> postLikeService.insertLike(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 insert - 이미 좋아요를 누른 post인 경우")
    void failedInsertPostLikeByAlreadyExistPostLike() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(true);

        // then
        assertThrows(AlreadyExistPostLikeException.class, () -> postLikeService.insertLike(1L, 1L));
    }

    // 차이가 나긴 하지만 메모리상에 위치하는 Mock객체를 이용해서 그런지 차이가 매우 극소량 났다.
    // 또한 실제 DB에 접근하여 물리적인 행위가 안나오니 크게 오차가 없는것 같다.
    // 그래서 DB에 실제 접근하는 테스트코드를 작성하기로 했다.
    @Test
    @DisplayName("좋아요 기능에 대한 동시성 테스트")
    void synchronousTestAboutPostLike() throws InterruptedException {
        int numberOfThreads = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(false);
        when(postLikeRepository.save(any(PostLike.class))).thenReturn(postLike);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    postLikeService.insertLike(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });

        }
        latch.await();

        assertEquals(numberOfThreads, post.getLikeCount());
    }

}
package thisisus.school.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.MemberStatus;
import thisisus.school.member.domain.Role;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.repository.PostRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostLikeSynchronousTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeServiceImpl postLikeService;

    private Member member;
    private Member member2;
    private Post post;

    @BeforeEach
    void setUp() {
        member = new Member(1L, "장창현",
                "abcdef@naver.com", "장첸", Role.STUDENT, MemberStatus.ACTIVE,
                "tempRefresh");
        member2 = new Member(2L, "윤후중",
                "sdfsdf@naver.com", "윤후", Role.STUDENT, MemberStatus.ACTIVE,
                "tempRefresh2");
        memberRepository.save(member);
        memberRepository.save(member2);
        post = new Post(1L, "테스트용 제목", "테스트용 내용",
                PostCategory.소통해요, 0, 0, false, member);
        postRepository.save(post);
    }

    @Test
    @DisplayName("좋아요 기능에 대한 동시성 테스트2")
    void db를_이용한_동시성_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);
        executorService.execute(() -> {
            postLikeService.insertLike(1L, member.getId());
            latch.countDown();
        });

        executorService.execute(() -> {
            postLikeService.insertLike(1L, member2.getId());
            latch.countDown();
        });
        latch.await();
        Post post1 = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        Assertions.assertEquals(2, post1.getLikeCount());
    }

    // PostLike에 insert를 안하고 post의 likecount에 대해서만 update를 수행할때
//    @Test
//    @DisplayName("좋아요 기능에 대한 동시성 테스트3")
//    void db를_이용한_동시성_테스트2() throws InterruptedException{
//        int numberOfThreads = 100;
//        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            executorService.submit(() -> {
//                try {
//                    postLikeService.insertLike(1L, 1L);
//                } finally {
//                    latch.countDown();
//                }
//            });
//
//        }
//        latch.await();
//        Post post1 = postRepository.findById(1L).orElseThrow(RuntimeException::new);
//        assertEquals(numberOfThreads, post1.getLikeCount());
//    }
}

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
import thisisus.school.post.exception.NotExistPostLikeException;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostLikeRepository;
import thisisus.school.post.repository.PostRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        postLikeService.likePost(1L, 1L);

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
        assertThrows(NotFoundPostException.class, () -> postLikeService.likePost(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 insert - 잘못된 memberId로 인한 실패")
    void failedInsertPostLikeByWrongMemberId() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundMemberException.class, () -> postLikeService.likePost(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 insert - 이미 좋아요를 누른 post인 경우")
    void failedInsertPostLikeByAlreadyExistPostLike() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(true);

        // then
        assertThrows(AlreadyExistPostLikeException.class, () -> postLikeService.likePost(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 delete - 성공")
    void deletePostLike() {
        //given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findWithLockById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(true);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(postLikeRepository.findByMemberAndPost(member, post)).thenReturn(postLike);
        doNothing().when(postLikeRepository).delete(any(PostLike.class));

        //when
        postLikeService.disLikePost(1L, 1L);

        //then
        verify(memberRepository).findById(1L);
        verify(postRepository).findWithLockById(1L);
        verify(postLikeRepository).existsByMemberAndPost(member, post);
        verify(postRepository).save(any(Post.class));
        verify(postLikeRepository).delete(any(PostLike.class));
    }

    @Test
    @DisplayName("좋아요 delete - 잘못된 postId로 인한 실패")
    void failedDeletePostLikeByWrongPostId() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // when
        assertThrows(NotFoundPostException.class, () -> postLikeService.disLikePost(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 delete - 잘못된 memberId로 인한 실패")
    void failedDeletePostLikeByWrongMemberId() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        assertThrows(NotFoundMemberException.class, () -> postLikeService.disLikePost(1L, 1L));
    }

    @Test
    @DisplayName("좋아요 delete - 좋아요를 누르지 않은 경우")
    void failedDeletePostLikeByNotExistPostLike() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(postRepository.findWithLockById(1L)).thenReturn(Optional.of(post));
        when(postLikeRepository.existsByMemberAndPost(member, post)).thenReturn(false);

        // then
        assertThrows(NotExistPostLikeException.class, () -> postLikeService.disLikePost(1L, 1L));
    }

}
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
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.exception.NotCorrectUserException;
import thisisus.school.post.exception.NotFoundPostException;
import thisisus.school.post.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
	private Member member;
	private Member member2;
	@Mock
	private PostRepository postRepository;
	@InjectMocks
	private PostServiceImpl postService;

	@BeforeEach
	void setUp() {
		member = new Member(1L, "장창현",
			"abcdef@naver.com", "장첸", Role.STUDENT, MemberStatus.ACTIVE,
			"tempRefresh");
		member2 = new Member(2L, "테스트용유저",
			"ghijk@naver.com", "테스트용", Role.STUDENT, MemberStatus.ACTIVE,
			"tempRefresh2");
	}

	@Test
	@DisplayName("post 조회 성공")
	void findPost() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();
		given(postRepository.findById(fakePostId)).willReturn(Optional.ofNullable(post));

		//when
		PostResponse findPost = postService.findPost(fakePostId);

		//then
		Assertions.assertEquals(fakePostId, findPost.getPostId());
		Assertions.assertEquals(member.getNickname(), findPost.getWriter());
	}

	@Test
	@DisplayName("MemberId를 통한 Post조회")
	void findPostByMemberId() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();

		List<PostResponse> arr = new ArrayList<>();
		PostResponse postResponse = new PostResponse(post);
		arr.add(postResponse);

		List<Post> givenArr = new ArrayList<>();
		givenArr.add(post);

		given(postRepository.findAllByMemberId(member.getId())).willReturn(givenArr);

		//when
		List<PostResponse> postByMemberId = postService.findPostByMemberId(member.getId());

		//then
		Assertions.assertEquals(1, postByMemberId.size());
		Assertions.assertEquals(arr.get(0).getPostId(), postByMemberId.get(0).getPostId());
	}

	@Test
	@DisplayName("post 수정 성공")
	void update() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();
		given(postRepository.findById(fakePostId)).willReturn(Optional.ofNullable(post));

		PostUpdateRequest postUpdateRequest = new PostUpdateRequest("수정 테스트 제목", "수정 테스트 내용"
			, PostCategory.소통해요);
		//when
		PostResponse update = postService.update(post.getId(), postUpdateRequest, member.getId());
		//then
		Assertions.assertEquals("수정 테스트 제목", update.getTitle());
	}

	@Test
	@DisplayName("post 수정 실패 - 작성자 불일치")
	void failedUpdateByUnCorrectUser() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();
		given(postRepository.findById(fakePostId)).willReturn(Optional.ofNullable(post));

		PostUpdateRequest postUpdateRequest = new PostUpdateRequest("수정 테스트 제목", "수정 테스트 내용"
			, PostCategory.소통해요);
		//when
		Assertions.assertThrows(
			NotCorrectUserException.class, () -> postService.update(post.getId(), postUpdateRequest, member2.getId()));

		//then

	}

	@Test
	@DisplayName("post 삭제 성공")
	void delete() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();
		given(postRepository.findById(fakePostId)).willReturn(Optional.ofNullable(post));
		//when
		postService.delete(fakePostId, member.getId());
		Post findPost = postRepository.findById(fakePostId).orElseThrow(NotFoundPostException::new);
		//then
		Assertions.assertEquals(true, findPost.isDelete());
	}

	@Test
	@DisplayName("post 삭제 실패 - 작성자 불일치")
	void failedDeleteByUnCorrectUser() {
		//given
		Long fakePostId = 1L;
		Post post = Post.builder()
			.id(fakePostId)
			.title("테스트 제목")
			.content("테스트 내용")
			.likeCount(0)
			.viewCount(0)
			.member(member)
			.build();
		given(postRepository.findById(fakePostId)).willReturn(Optional.ofNullable(post));
		//when
		Assertions.assertThrows(
			NotCorrectUserException.class, () -> postService.delete(fakePostId, member2.getId()));

		//then
	}
}
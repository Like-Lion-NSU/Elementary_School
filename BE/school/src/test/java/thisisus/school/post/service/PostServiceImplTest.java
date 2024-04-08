package thisisus.school.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.request.PostRequest;
import thisisus.school.post.dto.response.PostResponse;
import thisisus.school.post.repository.PostRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceImplTest {

    @Autowired
    PostServiceImpl postService;
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시글 등록")
    void savePost() {
        //given
        PostRequest postRequest = new PostRequest("test 제목", "test 내용" , PostCategory.소통해요);

        //when
        PostResponse response = postService.savePost(postRequest);

        //then
        Post post = postRepository.findById(response.getPostId())
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_POST));

        assertEquals(response.getPostId(), post.getId());
        assertEquals("test 제목", post.getTitle());
        assertEquals("test 내용", post.getContent());
    }
}
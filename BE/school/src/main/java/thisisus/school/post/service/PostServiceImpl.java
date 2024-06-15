package thisisus.school.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.common.exception.CustomException;
import thisisus.school.common.exception.ExceptionCode;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.post.dto.PostRequest;
import thisisus.school.post.dto.PostResponse;
import thisisus.school.post.dto.PostUpdateRequest;
import thisisus.school.post.exception.NotFoundPost;
import thisisus.school.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    public PostResponse savePost(PostRequest postRequest, Long memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Post post = createPost(postRequest,member);
        postRepository.save(post);

        PostResponse response = new PostResponse(post);
        return response;
    }

    private Post createPost(PostRequest postRequest, Member member) {
        return Post.builder().
                title(postRequest.getTitle())
                .content(postRequest.getContent())
                .category(postRequest.getCategory())
                .likeCount(0)
                .viewCount(0)
                .member(member)
                .build();
    }

    @Override
    public PostResponse findPost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPost::new);

        PostResponse response = new PostResponse(post);
        return response;
    }

    @Override
    public List<PostResponse> findPostByMemberId(long memberId) {
        List<Post> posts = postRepository.findByMemberId(memberId);
        List<PostResponse> result = posts.stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void findPosts(PostCategory postCategory) {

    }


    @Override
    public PostResponse update(PostUpdateRequest postRequest) {
        Post post = postRepository.findById(postRequest.getId())
                .orElseThrow(NotFoundPost::new);
        post.update(postRequest.getTitle(), post.getContent(), postRequest.getCategory());

        PostResponse response = new PostResponse(post);
        return response;
    }

    @Override
    public void delete(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPost::new);
        post.delete();
    }
}

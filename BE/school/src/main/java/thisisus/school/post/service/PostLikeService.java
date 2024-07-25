package thisisus.school.post.service;

public interface PostLikeService {
    void likePost(Long postId, Long memberId);
    void disLikePost(Long postId, Long memberId);
}

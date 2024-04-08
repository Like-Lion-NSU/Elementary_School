//package thisisus.school.post.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import thisisus.school.post.domain.PostLiked;
//
//import java.util.List;
//
//public interface PostLikeRepository extends JpaRepository<PostLiked, Long> {
//    List<PostLiked> findByMemberIdAndIsDeletedIsFalse(Long memberId);
//    PostLiked findByMemberIdAndPostIdAndIsDeletedIsFalse(Long memberId, Long postId);
//}

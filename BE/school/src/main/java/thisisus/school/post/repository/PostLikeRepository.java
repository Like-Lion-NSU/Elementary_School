package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisisus.school.member.domain.Member;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostLike;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByMemberAndPost(Member member, Post post);

    PostLike findByMemberAndPost(Member member, Post post);
}

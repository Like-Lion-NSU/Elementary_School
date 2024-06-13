package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberId(Long memberId);

}

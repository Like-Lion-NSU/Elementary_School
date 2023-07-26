package thisisus.school.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.domain.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}

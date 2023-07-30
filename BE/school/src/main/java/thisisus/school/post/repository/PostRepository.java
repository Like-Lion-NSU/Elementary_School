package thisisus.school.post.repository;

import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.category.PostCategory;
import thisisus.school.post.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByCategory(PostCategory category);
    List<Post> findPostsByUserId(Long UserId);
}

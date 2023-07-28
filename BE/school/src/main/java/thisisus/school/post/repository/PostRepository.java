package thisisus.school.post.repository;

import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.post.category.PostCategory;
import thisisus.school.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findPostByCategory(PostCategory category);
}

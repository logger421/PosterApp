package org.logger421.poster.repositiories;

import org.logger421.poster.models.Post;
import org.logger421.poster.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post getPostById(long id);
    List<Post> getByAuthor(User author);
}

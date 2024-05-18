package org.logger421.poster.repositiories;

import org.logger421.poster.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

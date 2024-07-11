package org.logger421.poster.repositiories;

import org.logger421.poster.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);

    Comment findCommentById(Long commentId);
}

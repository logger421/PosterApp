package org.logger421.poster.services;

import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.Post;
import org.logger421.poster.repositiories.PostRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public record PostService(PostRepository postRepository) {

    public void createPost(PostDTO post) {
        postRepository.save(
                Post
                        .builder()
                        .author(post.author())
                        .title(post.title())
                        .content(post.content())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build());
    }
}

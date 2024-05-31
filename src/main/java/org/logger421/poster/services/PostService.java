package org.logger421.poster.services;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.Post;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void createPost(PostDTO post) {
        User byUsername = userRepository.findByEmail(post.getAuthor());
        postRepository.save(
                Post
                        .builder()
                        .author(byUsername)
                        .content(post.getContent())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build());
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
}

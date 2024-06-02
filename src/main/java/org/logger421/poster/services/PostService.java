package org.logger421.poster.services;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.Comment;
import org.logger421.poster.models.Post;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.CommentRepository;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.logger421.poster.requests.PostRequestAction;
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
    private final CommentRepository commentRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
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

    @Transactional
    public boolean editPost(long postId, String userEmail, PostRequestAction action) {
        Post post = postRepository.getPostById(postId);
        User user = userRepository.findByEmail(userEmail);
        boolean result = false;
        switch (action) {
            case ADD_LIKE -> {
                Set<User> likes = post.getLikes();
                result = likes.add(user);
                post.setLikes(likes);
                postRepository.save(post);
            }
            case REMOVE_LIKE -> {
                Set<User> likes = post.getLikes();
                result = likes.removeIf(u -> u.getId() == user.getId());
                post.setLikes(likes);
                postRepository.save(post);
            }
        }
        return result;
    }

    @Transactional
    public Comment addComment(long postId, String comment, String userEmail) {
        Post post = postRepository.getPostById(postId);
        User user = userRepository.findByEmail(userEmail);
        Comment newComment = Comment
                .builder()
                .comment(comment)
                .postId(postId)
                .userId(user.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        Set<Comment> comments = post.getComments();
        comments.add(newComment);
        post.setComments(comments);
        postRepository.save(post);
        Comment saved = commentRepository.save(newComment);
        return saved;
    }
}
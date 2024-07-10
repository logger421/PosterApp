package org.logger421.poster.services;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.Comment;
import org.logger421.poster.models.Post;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.CommentRepository;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.logger421.poster.actions.PostRequestAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
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
        User byUsername = userRepository.findByUsername(post.getAuthor());
        postRepository.save(
                Post
                        .builder()
                        .author(byUsername)
                        .content(post.getContent())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build());
    }

    @Transactional
    public boolean editPost(long postId, String userName, PostRequestAction action) {
        Post post = postRepository.getPostById(postId);
        User user = userRepository.findByUsername(userName);

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
                result = likes.removeIf(u -> Objects.equals(u.getId(), user.getId()));
                post.setLikes(likes);
                postRepository.save(post);
            }
        }
        return result;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getUserPosts(String userName) {
        User user = userRepository.findByUsername(userName);
        return postRepository.getByAuthor(user);
    }

    @Transactional
    public Comment addComment(long postId, String comment, String userName) {
        Post post = postRepository.getPostById(postId);
        User user = userRepository.findByUsername(userName);

        Comment newComment = Comment.builder()
                .comment(comment)
                .post(post)
                .author(user)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        List<Comment> comments = post.getComments();
        comments.add(newComment);
        post.setComments(comments);
        postRepository.save(post);

        return commentRepository.save(newComment);
    }

    @Transactional
    public Comment deleteComment(long commentId) {
        Comment newComment = commentRepository.findCommentById(commentId);
        newComment.setComment("Deleted");
        return commentRepository.save(newComment);
    }

    public List<Comment> getComments(long id) {
        return commentRepository.findCommentsByPostId(id);
    }
}
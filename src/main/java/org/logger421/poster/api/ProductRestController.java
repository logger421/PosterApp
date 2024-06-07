package org.logger421.poster.api;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.actions.PostRequestAction;
import org.logger421.poster.dto.CommentDTO;
import org.logger421.poster.models.Comment;
import org.logger421.poster.requests.AddCommentRequest;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public record ProductRestController(PostService postService, UserService userService) {

    @PostMapping("/like/{postId}")
    public ResponseEntity<HttpStatus> likePost(@PathVariable @NonNull String postId, Authentication auth) {
        log.info("Adding like for postId={}, by user={}", postId, auth.getName());

        boolean successful = postService.editPost(Long.parseLong(postId), auth.getName(), PostRequestAction.ADD_LIKE);

        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/dislike/{postId}")
    public ResponseEntity<HttpStatus> dislikePost(@PathVariable @NonNull String postId, Authentication auth) {
        log.info("Removing like for postId={}, by user={}", postId, auth.getName());

        boolean successful = postService.editPost(Long.parseLong(postId), auth.getName(), PostRequestAction.REMOVE_LIKE);

        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody AddCommentRequest request, Authentication auth) {
        log.info("Adding comment {}, by user={}", request, auth.getName());

        Comment added = postService.addComment(request.postId(), request.comment(), auth.getName());

        return added != null ? ResponseEntity.ok(added) : ResponseEntity.internalServerError().build();
    }

    @GetMapping("/view/comments/{postId}")
    public List<CommentDTO> getComments(@PathVariable @NonNull String postId, Authentication auth) {
        log.info("Viewing comments for postId={}, by user={}", postId, auth.getName());

        List<Comment> comments = postService.getComments(Long.parseLong(postId));

        return comments
                .stream()
                .map(
                        comment ->
                                CommentDTO
                                        .builder()
                                        .userName(comment.getAuthor().getUsername())
                                        .postId(comment.getPost().getId())
                                        .comment(comment.getComment())
                                        .createdAt(comment.getCreatedAt())
                                        .build()
                )
                .toList();
    }
}

package org.logger421.poster.api;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.models.Comment;
import org.logger421.poster.requests.AddCommentRequest;
import org.logger421.poster.requests.PostRequestAction;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public record ProductRestController(PostService postService, UserService userService) {

    @PostMapping("/like/{postId}")
    public ResponseEntity<HttpStatus> likePost(@PathVariable @NonNull String postId, Authentication auth) {
        log.info("Adding like for postId={}", postId);
        boolean successful = postService.editPost(Long.parseLong(postId), auth.getName(), PostRequestAction.ADD_LIKE);
        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/dislike/{postId}")
    public ResponseEntity<HttpStatus> dislikePost(@PathVariable @NonNull String postId, Authentication auth) {
        boolean successful = postService.editPost(Long.parseLong(postId), auth.getName(), PostRequestAction.REMOVE_LIKE);
        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/addComment")
    public ResponseEntity<HttpStatus> addComment(@RequestBody AddCommentRequest request, Authentication auth) {
        Comment added = postService.addComment(request.postId(), request.comment(), auth.getName());
        return added == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

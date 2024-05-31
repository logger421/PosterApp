package org.logger421.poster.api;

import org.logger421.poster.requests.AddCommentRequest;
import org.logger421.poster.requests.PostRequestAction;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository(value = "/api/posts")
public record ProductRestController(PostService postService, UserService userService) {

    @PostMapping(value = "like")
    public ResponseEntity<HttpStatus> likePost(@RequestParam String postId, Authentication auth) {
        boolean successful = postService.editPost(Long.valueOf(postId), auth.getName(), PostRequestAction.ADD_LIKE);
        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "dislike")
    public ResponseEntity<HttpStatus> dislikePost(@RequestParam String postId, Authentication auth) {
        boolean successful = postService.editPost(Long.valueOf(postId), auth.getName(), PostRequestAction.REMOVE_LIKE);
        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/addComment")
    public ResponseEntity<HttpStatus> addComment(@RequestBody AddCommentRequest request, Authentication auth) {
        boolean successful = postService.addComment(request.postId(), request.comment(), auth.getName());
        return successful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

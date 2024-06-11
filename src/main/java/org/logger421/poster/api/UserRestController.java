package org.logger421.poster.api;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.models.User;
import org.logger421.poster.requests.EditUserRequest;
import org.logger421.poster.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/profile")
public record UserRestController(UserService userService) {

    @GetMapping("/get")
    public EditUserRequest getUser(Authentication authentication) {
        log.info("Getting user with userName {}", authentication.getName());
        User user = userService.findByUsername(authentication.getName());
        return new EditUserRequest(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    @PatchMapping("/edit")
    public ResponseEntity editProfile(@RequestBody EditUserRequest request, Authentication authentication) {
        log.info("Editing profile data for {}", authentication.getName());

        userService.editUserData(request, authentication.getName());
        return ResponseEntity.ok("");
    }
}

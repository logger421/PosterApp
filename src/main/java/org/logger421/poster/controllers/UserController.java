package org.logger421.poster.controllers;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.UserDTO;
import org.logger421.poster.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public record UserController(UserService userService) {

    @GetMapping("/search")
    public String searchPage(Model model, @RequestParam(name = "search") String searchPrompt) {
        log.debug("Searing by prompt: {}", searchPrompt);

        final List<UserDTO> searchedUsers = userService
                .getSearchUsers(searchPrompt)
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getProfilePictureUrl())
                )
                .toList();
        log.debug("Found {} sellers", searchedUsers.size());

        model.addAttribute("foundUsers", searchedUsers);
        model.addAttribute("searchPrompt", searchPrompt);

        if (searchedUsers.isEmpty()) {
            model.addAttribute("footerClass", "fixed-bottom");
        }

        return "search-page";
    }

    @GetMapping("/friends")
    String getFriends(Model model, Authentication auth) {
        log.info("Getting friends of user {}", auth.getName());

        List<UserDTO> friends = userService
                .getFriends(auth.getName())
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getProfilePictureUrl())
                )
                .toList();

        model.addAttribute("friends", friends);

        return "/friends";
    }

    @PostMapping("/friends/add")
    ResponseEntity<ResponseStatus> addFriend(@RequestParam(name = "userName") @NonNull String userName, Authentication auth) {
        log.info("{} added to friends: {}", auth.getName(), userName);
        boolean result = userService.addFriend(auth.getName(), userName);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/friends/delete")
    ResponseEntity<ResponseStatus> deleteFriend(@RequestParam(name = "userName") @NonNull String userName, Authentication auth) {
        log.info("{} deleted from friends: {}", auth.getName(), userName);
        boolean result = userService.deleteFriend(auth.getName(), userName);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

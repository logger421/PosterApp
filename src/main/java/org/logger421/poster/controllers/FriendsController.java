package org.logger421.poster.controllers;

import lombok.NonNull;
import org.logger421.poster.dto.UserDTO;
import org.logger421.poster.models.User;
import org.logger421.poster.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/friends")
public record FriendsController(UserService userService) {

    private static final Logger log = LoggerFactory.getLogger(FriendsController.class);

    @GetMapping
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

        return "friends";
    }

    @PostMapping("/add")
    String addFriend(@RequestParam(name = "userName") @NonNull String userName, Authentication auth) {
        log.info("{} added to friends: {}", auth.getName(), userName);
        userService.addFriend(auth.getName(), userName);
        return "redirect:friends?requestSend";
    }
}

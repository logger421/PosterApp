package org.logger421.poster.controllers;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.UserDTO;
import org.logger421.poster.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public record UserController(UserService userService) {

    @RequestMapping("/search")
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
}

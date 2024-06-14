package org.logger421.poster.controllers;

import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.User;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record HomeController(PostService postService, UserService userService) {

    @GetMapping(value = {"/", "/home", "/index"})
    public String homePage(Model model, Authentication auth) {
        User user = userService.findByUsername(auth.getName());

        model.addAttribute("userId", user.getId());
        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("post", new PostDTO());
        return "home";
    }
}

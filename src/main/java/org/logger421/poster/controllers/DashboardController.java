package org.logger421.poster.controllers;

import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public record DashboardController(PostService postService, UserService userService) {

    @GetMapping
    public String dashboard(Model model, Authentication auth) {
        model.addAttribute("userPosts", postService.getUserPosts(auth.getName()));
        return "dashboard";
    }
}

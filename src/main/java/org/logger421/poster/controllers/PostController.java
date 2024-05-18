package org.logger421.poster.controllers;

import org.logger421.poster.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public record PostController(PostService postService) {

    @GetMapping("/dashboard")
    public String getUserPosts(Model model) {
        return "/dashboard";
    }

    @PostMapping("/post/new")
    public String addPost(Model model) {
        return "redirect:/dashboard?created";
    }
}

package org.logger421.poster.controllers;

import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record HomeController(PostService postService) {

    @GetMapping(value = {"/", "/home", "/index"})
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("post", new PostDTO());
        return "home";
    }
}

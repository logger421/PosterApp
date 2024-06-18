package org.logger421.poster.controllers;

import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.models.Post;
import org.logger421.poster.models.User;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public record HomeController(PostService postService, UserService userService) {

    @GetMapping(value = {"/", "/index", "/poster"})
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model, Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        List<Post> posts = postService.getPosts();
        Collections.reverse(posts);
        model.addAttribute("userId", user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("post", new PostDTO());
        return "home";
    }
}

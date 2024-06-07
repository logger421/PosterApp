package org.logger421.poster.controllers;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.dto.PostDTO;
import org.logger421.poster.services.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public record PostController(PostService postService) {

    @PostMapping("/post/new")
    public String addPost(PostDTO postDTO, Authentication authentication) {
        postDTO.setAuthor(authentication.getName());
        postService.createPost(postDTO);
        return "redirect:/home?postCreated";
    }
}

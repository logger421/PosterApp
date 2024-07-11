package org.logger421.poster.controllers;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.models.User;
import org.logger421.poster.requests.UploadImagePayload;
import org.logger421.poster.services.PostService;
import org.logger421.poster.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public record DashboardController(PostService postService, UserService userService) {

    @GetMapping()
    public String dashboard(Model model, Authentication auth) {
        User user = userService.findByUsername(auth.getName());

        model.addAttribute("userId", user.getId());
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("userProfilePictureUrl", user.getProfilePictureUrl());
        model.addAttribute("userPosts", postService.getUserPosts(auth.getName()));
        model.addAttribute("uploadImagePayload", new UploadImagePayload());

        return "dashboard";
    }

    @GetMapping("/{userId}")
    public String userDashboard(@PathVariable Long userId, Model model, Authentication auth) {
        User user = userService.findById(userId);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("userProfilePictureUrl", user.getProfilePictureUrl());
        model.addAttribute("userPosts", postService.getUserPosts(user.getUsername()));
        model.addAttribute("areFriends", userService.checkIfFriendExists(auth.getName(), user.getUsername()));

        return "user-profile";
    }

    @PostMapping(path = "/profileFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateProfilePicture(@ModelAttribute UploadImagePayload payload, Authentication auth) {
        log.info("Image file {}", payload);
        userService.uploadProfilePhoto(payload.getImageFile(), auth.getName());
        return "redirect:/dashboard?fileUploaded";
    }
}

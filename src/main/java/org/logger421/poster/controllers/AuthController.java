package org.logger421.poster.controllers;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.models.User;
import org.logger421.poster.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public record AuthController(UserService userService) {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

//    @PostMapping("/registerNew")
//    public String register(@RequestBody UserRegistrationRequest request) {
//        userService.registerCustomer(request);
//        return "redirect:/login?registered";
//    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") User user) {
        userService.registerCustomer(user);
        return "redirect:/login?registered";
    }
}

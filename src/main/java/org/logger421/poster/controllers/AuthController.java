package org.logger421.poster.controllers;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.requests.UserRegistrationRequest;
import org.logger421.poster.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public record AuthController(UserService userService) {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping
    public void register(@RequestBody UserRegistrationRequest request) {
        log.info("Registering new user: {} {}", request.username(), request.email());
        userService.registerCustomer(request);
    }
}

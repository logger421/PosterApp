package org.logger421.poster.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping(value = {"/", "/home", "/index"})
    public String homePage(Model model) {
        return "home";
    }
}

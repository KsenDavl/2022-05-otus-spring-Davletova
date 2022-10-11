package ru.otus.spring.davlks.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.davlks.security.entity.User;
import ru.otus.spring.davlks.security.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getStartPage() {
        return "start";
    }

    @GetMapping("/success")
    public String authenticatedPage() {
        return "redirect:/book/all";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}

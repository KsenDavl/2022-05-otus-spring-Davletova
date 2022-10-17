package ru.otus.spring.davlks.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.davlks.controller.BookController;
import ru.otus.spring.davlks.security.dto.UserDto;
import ru.otus.spring.davlks.security.enums.RoleNames;
import ru.otus.spring.davlks.security.service.UserService;

@Controller
public class UserController {

    Logger LOGGER = LoggerFactory.getLogger(BookController.class);

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
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", RoleNames.CUSTOMER.getDeclaringClass().getEnumConstants());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDto userDto) {
        userService.saveUser(userService.mapUserDtoToUser(userDto));
        LOGGER.info("New user logged up: " + userDto.toString());
        return "redirect:/";
    }
}

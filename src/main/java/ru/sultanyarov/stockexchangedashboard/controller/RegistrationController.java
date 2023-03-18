package ru.sultanyarov.stockexchangedashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sultanyarov.stockexchangedashboard.service.UserService;

@Validated
@Controller
@RequestMapping("/api/v1")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user/registration")
    public String registration(String username, String password) {
        userService.registrationUser(username, password);
        return "redirect:/index.html";
    }
}

package com.workflex.controller;

import com.workflex.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String test() {
        return "Welcome to WorkFlex Backend";
    }

}

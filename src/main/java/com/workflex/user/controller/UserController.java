package com.workflex.user.controller;

import com.workflex.user.dto.request.RegisterRequest;
import com.workflex.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {

        userService.register(request);

        return "User Registered Successfully";
    }
}
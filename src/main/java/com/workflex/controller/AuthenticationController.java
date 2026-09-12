package com.workflex.controller;

import com.workflex.dto.request.LoginRequest;
import com.workflex.dto.response.LoginResponse;
import com.workflex.entity.User;
import com.workflex.security.CustomUserDetails;
import com.workflex.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        User user = userDetails.getUser();

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                token,
                "Login Successful"
        );
    }
}
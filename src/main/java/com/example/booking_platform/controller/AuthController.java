package com.example.booking_platform.controller;

import com.example.booking_platform.dto.request.UserSignUpRequestDTO;
import com.example.booking_platform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String getSignInPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String getSignUpPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute UserSignUpRequestDTO dto) {
        authService.register(dto);
        return "auth/login";
    }
}

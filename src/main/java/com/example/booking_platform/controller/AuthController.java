package com.example.booking_platform.controller;

import com.example.booking_platform.dto.request.UserSignUpDTO;
import com.example.booking_platform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
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
    public String signUp(@ModelAttribute @Valid UserSignUpDTO dto) {

        log.warn("DTO info {} -> {} -> {}", dto.getPassword(), dto.getPassword(), dto.getUsername());
        authService.register(dto);
        return "auth/login";
    }
}

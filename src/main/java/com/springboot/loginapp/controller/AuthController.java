package com.springboot.loginapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.springboot.loginapp.dto.AuthRequest;
import com.springboot.loginapp.dto.AuthResponse;
import com.springboot.loginapp.dto.RegisterRequest;
import com.springboot.loginapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService = new AuthService();

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
}
@PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {
    return authService.authenticate(request);
}
}

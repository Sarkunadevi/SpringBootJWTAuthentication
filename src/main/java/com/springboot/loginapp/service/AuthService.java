package com.springboot.loginapp.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.loginapp.dto.AuthRequest;
import com.springboot.loginapp.dto.AuthResponse;
import com.springboot.loginapp.dto.RegisterRequest;
import com.springboot.loginapp.entity.UserData;
import com.springboot.loginapp.repository.UserRepository;
import com.springboot.loginapp.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository = null;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtService jwtService = new JwtService();
    private final AuthenticationManager authenticationManager = null;

    public AuthResponse register(RegisterRequest request) {
        UserData user = UserData.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}

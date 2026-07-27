package com.tourism.itda.auth.controller;

import com.tourism.itda.auth.dto.LoginRequest;
import com.tourism.itda.auth.dto.LoginResponse;
import com.tourism.itda.auth.dto.SignupRequest;
import com.tourism.itda.auth.dto.UserResponse;
import com.tourism.itda.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignupRequest request){
        return authService.signup(
                request.loginId(),
                request.password(),
                request.name(),
                request.nickname(),
                request.email(),
                request.birthDate(),
                request.agreedToTerms()
        );
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(
                request.loginId(),
                request.password()
        );
    }
}

package com.tourism.itda.auth.controller;

import com.tourism.itda.auth.dto.*;
import com.tourism.itda.auth.service.AuthService;
import com.tourism.itda.user.dto.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public LoginResponse signup(@RequestBody SignupRequest request){
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

    @PostMapping("/kakao")
    public LoginResponse kakaoLogin(@RequestBody KakaoLoginRequest request){
        return authService.kakaoLogin(request.code());
    }

    @GetMapping("/session")
    public SessionResponse getSession(Authentication authentication) {
        if (authentication == null) {
            return SessionResponse.empty();
        }
        Long userId = (Long) authentication.getPrincipal();
        return authService.getSession(userId);
    }

    @PostMapping("/logout")
    public SuccessResponse logout() {
        return SuccessResponse.ok();
    }

    @PostMapping("/password/reset-request")
    public SuccessResponse passwordResetRequest(@RequestBody PasswordResetRequestDto request) {
        authService.sendPasswordResetCode(request.loginId(), request.email());
        return SuccessResponse.ok();
    }

    @PostMapping("/password/verify-code")
    public VerifyCodeResponse verifyCode(@RequestBody VerifyCodeRequest request) {
        return authService.verifyCode(request.loginId(), request.code());
    }

    @PatchMapping("/password/reset")
    public SuccessResponse resetPassword(@RequestBody PasswordResetDto request) {
        authService.resetPassword(request.resetToken(), request.newPassword());
        return SuccessResponse.ok();
    }
}

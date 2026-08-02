package com.tourism.itda.user.controller;

import com.tourism.itda.user.dto.CheckAvailableResponse;
import com.tourism.itda.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check-login-id")
    public CheckAvailableResponse checkLoginId(@RequestParam String loginId) {
        return new CheckAvailableResponse(userService.isLoginIdAvailable(loginId));
    }

    @GetMapping("/check-nickname")
    public CheckAvailableResponse checkNickname(@RequestParam String nickname) {
        return new CheckAvailableResponse(userService.isNicknameAvailable(nickname));
    }
}

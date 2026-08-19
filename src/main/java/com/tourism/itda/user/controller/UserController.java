package com.tourism.itda.user.controller;

import com.tourism.itda.user.dto.CheckAvailableResponse;
import com.tourism.itda.user.dto.SuccessResponse;
import com.tourism.itda.user.dto.UpdateProfileRequest;
import com.tourism.itda.user.dto.UpdateProfileResponse;
import com.tourism.itda.user.dto.UserProfileResponse;
import com.tourism.itda.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/me")
    public UserProfileResponse getMyProfile(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return userService.getMyProfile(userId);
    }

    @PatchMapping("/me")
    public UpdateProfileResponse updateMyProfile(Authentication authentication,
                                                 @RequestBody UpdateProfileRequest request) {
        Long userId = (Long) authentication.getPrincipal();
        return new UpdateProfileResponse(userService.updateMyProfile(userId, request));
    }

    @DeleteMapping("/me")
    public SuccessResponse deleteMyAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.deleteMyAccount(userId);
        return SuccessResponse.ok();
    }


}

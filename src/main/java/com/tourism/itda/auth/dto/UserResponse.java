package com.tourism.itda.auth.dto;

import com.tourism.itda.user.entity.User;

public record UserResponse(
        Long userId,
        String loginId,
        String nickname,
        String email,
        String profileUrl
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getLoginId(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileUrl()
        );
    }
}

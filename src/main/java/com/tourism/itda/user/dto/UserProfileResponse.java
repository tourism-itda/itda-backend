package com.tourism.itda.user.dto;

import com.tourism.itda.user.entity.User;

public record UserProfileResponse(
        Long userId,
        String loginId,
        String name,
        String nickname,
        String email,
        String profileUrl,
        Boolean darkMode,
        String language,
        Boolean notificationEnabled
) {
    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getUserId(),
                user.getLoginId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileUrl(),
                user.getDarkMode(),
                user.getLanguage(),
                user.getNotificationEnabled()
        );
    }
}

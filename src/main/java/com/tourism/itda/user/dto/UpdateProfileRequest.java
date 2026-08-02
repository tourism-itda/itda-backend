package com.tourism.itda.user.dto;

public record UpdateProfileRequest(
        String nickname,
        String profileUrl,
        Boolean darkMode,
        String language,
        Boolean notificationEnabled
) {
}

package com.tourism.itda.auth.dto;

import com.tourism.itda.user.entity.User;

public record SessionResponse(SessionUser user) {

    public record SessionUser(Long userId, String loginId, String nickname, String email, String profileUrl) {
        public static SessionUser from(User user) {
            return new SessionUser(
                    user.getUserId(),
                    user.getLoginId(),
                    user.getNickname(),
                    user.getEmail(),
                    user.getProfileUrl()
            );
        }
    }

    public static SessionResponse of(User user) {
        return new SessionResponse(SessionUser.from(user));
    }

    public static SessionResponse empty() {
        return new SessionResponse(null);
    }
}

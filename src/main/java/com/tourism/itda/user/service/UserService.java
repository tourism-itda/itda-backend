package com.tourism.itda.user.service;

import com.tourism.itda.user.dto.UpdateProfileRequest;
import com.tourism.itda.user.dto.UserProfileResponse;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginIdAvailable(String loginId) {
        return !userRepository.existsByLoginId(loginId);
    }

    public boolean isNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    public UserProfileResponse getMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (user.getDeletedAt() != null) {
            throw new IllegalArgumentException("탈퇴한 유저입니다.");
        }
        return UserProfileResponse.from(user);
    }

    @Transactional
    public void deleteMyAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (user.getDeletedAt() != null) {
            throw new IllegalArgumentException("이미 탈퇴한 유저입니다.");
        }
        user.delete();
    }

    @Transactional
    public UserProfileResponse updateMyProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if (request.name() != null) {
            if (request.name().isBlank()) throw new IllegalArgumentException("이름은 공백일 수 없어요.");
            user.changeName(request.name());
        }
        if (request.email() != null) {
            if (request.email().isBlank()) throw new IllegalArgumentException("이메일은 공백일 수 없어요.");
            user.changeEmail(request.email());
        }
        if (request.nickname() != null) {
            if (request.nickname().isBlank()) throw new IllegalArgumentException("닉네임은 공백일 수 없어요.");
            user.changeNickname(request.nickname());
        }
        if (request.profileUrl() != null) user.changeProfileUrl(request.profileUrl());
        if (request.darkMode() != null) user.changeDarkMode(request.darkMode());
        if (request.language() != null) user.changeLanguage(request.language());
        if (request.notificationEnabled() != null) user.changeNotificationEnabled(request.notificationEnabled());

        return UserProfileResponse.from(user);
    }
}

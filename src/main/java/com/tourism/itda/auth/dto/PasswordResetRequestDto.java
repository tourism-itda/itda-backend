package com.tourism.itda.auth.dto;

public record PasswordResetRequestDto(String loginId, String email) {
}

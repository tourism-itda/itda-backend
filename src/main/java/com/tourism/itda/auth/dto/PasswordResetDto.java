package com.tourism.itda.auth.dto;

public record PasswordResetDto(String resetToken, String newPassword) {
}

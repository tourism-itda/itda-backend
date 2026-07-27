package com.tourism.itda.auth.dto;

public record LoginResponse(String accessToken, UserResponse user) {
}

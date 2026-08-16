package com.tourism.itda.auth.dto;

public record VerifyCodeRequest(String loginId, String code) {
}

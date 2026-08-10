package com.tourism.itda.user.dto;

public record SuccessResponse(boolean success) {
    public static SuccessResponse ok() {
        return new SuccessResponse(true);
    }
}

package com.itda.itinerary.dto;

/** No.29 DELETE 응답: { "success": true } (v2). */
public record SuccessResponse(boolean success) {
    public static SuccessResponse ok() {
        return new SuccessResponse(true);
    }
}

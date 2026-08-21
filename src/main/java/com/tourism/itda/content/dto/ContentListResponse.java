package com.tourism.itda.content.dto;

import java.util.List;

public record ContentListResponse(
        List<ContentListItemResponse> data,
        long total
) {
}

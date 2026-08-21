package com.itda.itinerary.route;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 촬영지 큐레이션에 쓰는 Claude 설정.
 *
 * <p>키가 없거나 {@code enabled=false} 면 {@link SpotCurator} 가 점수 1등으로 폴백한다.
 * LLM 없이도 일정 생성은 정상 동작한다.
 *
 * @param model Claude 모델 ID. 후보 5개 중 1개 고르고 한 줄 쓰는 작업이라 Haiku 로 충분하다.
 */
@ConfigurationProperties(prefix = "itda.llm")
public record LlmProperties(
        boolean enabled,
        String model,
        String apiKey,
        long maxTokens) {

    public LlmProperties {
        if (model == null || model.isBlank()) {
            model = "claude-haiku-4-5";
        }
        if (maxTokens <= 0) {
            maxTokens = 1024L;
        }
    }

    public boolean isUsable() {
        return enabled && apiKey != null && !apiKey.isBlank();
    }
}

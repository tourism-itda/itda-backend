package com.tourism.itda.content.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import com.tourism.itda.planner.route.LlmProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Component
public class ContentClassifier {

    private static final String SYSTEM_PROMPT = """
            너는 한국 드라마·영화의 시대적 배경과 주요 인물을 분류하는 전문가다.
            제목, 줄거리, 키워드, 태그라인을 보고 아래 규칙에 따라 분류해라.

            [Kingdom — 작품의 시대적 배경 왕조]
            GOGURYEO: 고구려
            BAEKJE: 백제
            SILLA: 신라
            GAYA: 가야
            UNIFIED_SILLA: 통일신라
            BALHAE: 발해
            LATER_GOGURYEO: 후고구려
            LATER_BAEKJE: 후백제
            GORYEO: 고려
            JOSEON: 조선
            KOREAN_EMPIRE: 대한제국
            JAPANESE_COLONY: 일제강점기
            FIRST_REPUBLIC_OF_KOREA: 대한민국 제1공화국
            현대극이거나 시대 배경이 불분명하면 kingdom은 null로 반환해라.

            [PersonType — 작품에서 핵심적으로 다루는 역사적 인물 유형]
            KING: 왕·군주
            GENERAL: 장군·무인
            SCHOLAR: 학자·문인
            MONK: 승려
            POLITICIAN: 정치가·관료
            INVENTOR: 과학자·발명가
            INDEPENDENCE_ACTIVIST: 독립운동가
            역사적 인물 유형이 두드러지지 않으면 personType은 null로 반환해라.

            [personName — 작품의 핵심 역사적 실존 인물 이름]
            작품이 특정 역사적 실존 인물을 중심으로 다룬다면 그 인물의 실제 이름을 한국어로 반환해라.
            예: "세종", "이순신", "단종", "광개토대왕", "안중근"
            특정 실존 인물이 중심이 아니거나 불분명하면 null로 반환해라.
            """;

    public record ContentClassification(
            @JsonPropertyDescription("작품의 시대적 배경 왕조. Kingdom enum 값 또는 null.")
            String kingdom,

            @JsonPropertyDescription("작품의 핵심 역사적 인물 유형. PersonType enum 값 또는 null.")
            String personType,

            @JsonPropertyDescription("작품의 핵심 역사적 실존 인물 이름(한국어). 없으면 null.")
            String personName
    ) {}

    private final LlmProperties properties;
    private final AnthropicClient client;

    public ContentClassifier(LlmProperties properties) {
        this.properties = properties;
        this.client = createClient(properties);
    }

    public Optional<ContentClassification> classify(String title, String overview, String keywords, String tagline) {
        if (client == null) {
            return Optional.empty();
        }
        try {
            StructuredMessageCreateParams<ContentClassification> params = MessageCreateParams.builder()
                    .model(properties.model())
                    .maxTokens(properties.maxTokens())
                    .system(SYSTEM_PROMPT)
                    .outputConfig(ContentClassification.class)
                    .addUserMessage(buildPrompt(title, overview, keywords, tagline))
                    .build();

            ContentClassification result = client.messages().create(params).content().stream()
                    .flatMap(block -> block.text().stream())
                    .map(block -> block.text())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Claude 응답에 구조화 결과가 없습니다."));

            log.info(
                    "콘텐츠 분류 결과 - title={}, kingdom={}, personType={}, personName={}",
                    title,
                    result.kingdom(),
                    result.personType(),
                    result.personName()
            );

            return Optional.of(result);

        } catch (Exception e) {
            log.warn("콘텐츠 분류 실패 (title={}): {}", title, e.toString());
            return Optional.empty();
        }
    }

    public Kingdom parseKingdom(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Kingdom.valueOf(value);
        } catch (IllegalArgumentException e) {
            log.warn("알 수 없는 Kingdom 값: {}", value);
            return null;
        }
    }

    public PersonType parsePersonType(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return PersonType.valueOf(value);
        } catch (IllegalArgumentException e) {
            log.warn("알 수 없는 PersonType 값: {}", value);
            return null;
        }
    }

    private static String buildPrompt(String title, String overview, String keywords, String tagline) {
        return "제목: " + orEmpty(title) + "\n"
                + "줄거리: " + orEmpty(overview) + "\n"
                + "키워드: " + orEmpty(keywords) + "\n"
                + "태그라인: " + orEmpty(tagline);
    }

    private static String orEmpty(String s) {
        return s == null ? "(없음)" : s;
    }

    private static AnthropicClient createClient(LlmProperties properties) {
        if (!properties.isUsable()) {
            log.info("Claude 콘텐츠 분류가 비활성 상태입니다 (itda.llm.api-key 미설정 또는 enabled=false).");
            return null;
        }
        try {
            return AnthropicOkHttpClient.builder()
                    .apiKey(properties.apiKey())
                    .timeout(Duration.ofSeconds(15))
                    .build();
        } catch (Exception e) {
            log.warn("Claude 클라이언트 생성 실패: {}", e.toString());
            return null;
        }
    }
}

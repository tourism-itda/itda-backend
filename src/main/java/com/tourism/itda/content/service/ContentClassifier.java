package com.tourism.itda.content.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.tourism.itda.explore.data.HistoricalEventData;
import com.tourism.itda.explore.entity.HistoricalEvent;
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

    // 검수된 사건 목록은 HistoricalEventData 가 단일 출처다. 프롬프트에 그대로 주입해
    // Claude 가 자유롭게 연도를 지어내지 않고 이 목록 중 하나만 '선택'하게 한다.
    private static final String SYSTEM_PROMPT = buildSystemPrompt();

    private static String buildSystemPrompt() {
        StringBuilder eventList = new StringBuilder();
        for (HistoricalEvent e : HistoricalEventData.EVENTS) {
            eventList.append("- ").append(e.getName())
                    .append(" (").append(e.getStartYear()).append("~").append(e.getEndYear()).append("): ")
                    .append(e.getSummary()).append("\n");
        }

        return """
            너는 한국 드라마·영화의 시대적 배경과 주요 인물을 분류하는 전문가다.
            제목, 줄거리, 키워드, 태그라인을 보고 아래 규칙에 따라 분류해라.

            [Kingdom — 작품의 시대적 배경 왕조/시대]
            GORYEO: 고려 (918~1392)
            JOSEON: 조선 (1392~1897)
            KOREAN_EMPIRE: 대한제국 (1897~1910)
            JAPANESE_COLONY: 일제강점기 (1910~1945)
            FIRST_REPUBLIC_OF_KOREA: 대한민국 제1공화국 (1948~1960)
            THIRD_FOURTH_REPUBLIC: 제3·4공화국 (1961~1979, 박정희·유신)
            FIFTH_REPUBLIC: 제5공화국 (1980~1987, 전두환·5·18·6월항쟁)
            SIXTH_REPUBLIC: 제6공화국 이후 (1987~2008, 민주화·서울올림픽·IMF)
            시대 배경이 불분명하면 kingdom은 null로 반환해라.
            918년(고려 건국) 이전 시대(고구려·백제·신라·가야·통일신라·발해·후삼국)가
            배경이면 서비스 대상이 아니므로 kingdom은 null로 반환해라.
            2008년 이후가 배경이거나 역사적 사실과 무관한 허구(SF·판타지 등)이면 kingdom은 null로 반환해라.
            [액자식 구성 주의] 현재 시점에서 과거를 회상하거나, 도입/결말에만 현대 장면이
            나오는 액자식 구성이면 그 현대 장면이 아니라 '작품이 실제로 다루는 핵심 사건'의
            시대로 분류해라. (예: 노병이 현대에서 회상하지만 본편이 한국전쟁이면 제1공화국)

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
            예: "세종", "이순신", "단종", "안중근"
            주인공이 가상 인물이더라도, 작품이 특정 실존 인물의 생애·시대를 다루거나
            실제 역사적 사건을 다루면 그 사건·인물의 실존 인물을 반환해라.
            예: "봉오동 전투"를 다루면 "홍범도", 화가 장승업의 일대기를 다루면 "장승업".

            [eventName — 작품이 다루는 역사적 시대/사건]
            아래 목록 중 작품의 배경에 가장 잘 맞는 사건 이름을 '있는 그대로' 하나 반환해라.
            주인공이 허구 인물이어도, 작품이 실제 역사적 시대·사건을 배경으로 하면 해당 사건을 반환해라.
            (예: 가상 인물이 임진왜란을 배경으로 활약하면 "임진왜란")
            목록에 없는 시대이거나 배경이 불분명하면 반드시 null을 반환해라.
            목록에 없는 사건 이름을 새로 지어내지 마라 — 반드시 아래 목록의 이름만 사용해라.
            선택 가능한 사건 목록:
            %s
            [반드시 지켜야 할 근거 규칙 — 억지 매칭 방지]
            - 줄거리나 키워드에 그 인물·사건을 가리키는 명백한 근거가 있을 때만 인물·사건을 반환해라.
            - 제목만 보고 추측하거나, 줄거리가 없거나 부실해 근거를 찾을 수 없으면 반드시 null을 반환해라.
            - 조금이라도 확신이 없으면 억지로 지어내지 말고 null을 반환해라.
            특정 실존 인물이 중심이 아니거나 불분명하면 null로 반환해라.
            """.formatted(eventList.toString());
    }

    public record ContentClassification(
            @JsonPropertyDescription("작품의 시대적 배경 왕조. Kingdom enum 값 또는 null.")
            String kingdom,

            @JsonPropertyDescription("작품의 핵심 역사적 인물 유형. PersonType enum 값 또는 null.")
            String personType,

            @JsonPropertyDescription("작품의 핵심 역사적 실존 인물 이름(한국어). 없으면 null.")
            String personName,

            @JsonPropertyDescription("작품이 다루는 역사적 시대/사건 이름. 제공된 목록의 이름과 정확히 일치해야 하며, 없으면 null.")
            String eventName
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
                    "콘텐츠 분류 결과 - title={}, kingdom={}, personType={}, personName={}, eventName={}",
                    title,
                    result.kingdom(),
                    result.personType(),
                    result.personName(),
                    result.eventName()
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

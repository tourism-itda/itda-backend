package com.tourism.itda.content.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.tourism.itda.content.service.HistoryChronologyLoader.ChronologyEvent;
import com.tourism.itda.planner.route.LlmProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class StorytellingGenerator {

    private static final String SYSTEM_PROMPT = """
            너는 한국 역사 해설가다.
            아래에는 영화 정보와, 국사편찬위원회 연표에서 발췌한 '각색 없는 실제 역사 사건' 목록이 주어진다.
            영화가 실제로 다루는 사건에 해당하는 역사 기록을 독자에게 소개하는 것이 목적이다.

            [작업 순서 — 반드시 이 순서로 사고해라]
            1. 먼저 영화의 제목·줄거리·키워드를 읽고, 이 영화가 다루는 핵심 사건이 무엇인지 파악한다.
            2. 주어진 연표 사건 목록 중에서, 영화가 실제로 다루는 사건과 '직접 관련된' 것만 선별한다.
               (표현이 달라도 의미가 같으면 관련된 것으로 본다. 예: 영화 키워드 '한글' ↔ 연표 '훈민정음 창제')
               (영화 내용과 무관한 사건은 재위 기간에 있더라도 절대 사용하지 마라.)
            3. 선별한 사건들만으로 아래 세 필드를 작성한다.

            [출력 필드]
            - summary: 이 작품이 어떤 역사적 시기와 인물, 어떤 사건을 다루는지 2~3문장으로 요약.
            - storyIntro: 해당 시대와 인물의 역사적 배경을 동화를 들려주듯 따뜻한 말투로 2~3문장 도입부로 서술.
            - storyBody: 2단계에서 선별한 실제 사건들을 중심으로 500자 내외로 서술.

            [말투 — 매우 중요]
            - 어린이에게 동화책을 읽어주듯, 역사에 거부감이 없도록 다정하고 쉬운 말투로 써라.
            - 모든 문장의 종결어미는 반드시 '~했습니다', '~있었습니다', '~되었습니다' 처럼 '~습니다'로 끝내라.
            - '~했지요', '~였답니다', '~랍니다' 같은 다른 종결어미는 절대 쓰지 마라.
            - 어려운 한자어나 딱딱한 설명체는 피하고, 이야기를 들려주듯 자연스럽게 풀어써라.

            [엄격한 규칙]
            - 연표에 없는 사건이나 인물의 대사·심리 묘사 등 창작·각색은 절대 하지 마라.
            - 영화와 관련 없는 사건은 연표에 있어도 쓰지 마라.
            - 연표에 관련 사건이 없거나 연표 정보 자체가 없으면, storyBody는 영화 줄거리 수준의 일반적 배경만 간단히 서술해라.
            - 말투는 부드럽게 하되, 사건의 내용(누가·언제·무엇을) 자체는 연표 사실에서 벗어나면 안 된다.
            """;

    public record StorytellingResult(
            @JsonPropertyDescription("작품 한 줄 요약 (2~3문장)")
            String summary,

            @JsonPropertyDescription("시대·인물 배경 스토리텔링 도입부 (2~3문장)")
            String storyIntro,

            @JsonPropertyDescription("핵심 사건 중심 스토리텔링 본문 (400자 내외)")
            String storyBody
    ) {}

    private final LlmProperties properties;
    private final AnthropicClient client;
    private final HistoryChronologyLoader chronologyLoader;

    public StorytellingGenerator(LlmProperties properties, HistoryChronologyLoader chronologyLoader) {
        this.properties = properties;
        this.chronologyLoader = chronologyLoader;
        this.client = createClient(properties);
    }

    public Optional<StorytellingResult> generate(
            String title,
            String overview,
            String keywords,
            String tagline,
            String personName,
            List<ChronologyEvent> events
    ) {
        if (client == null) return Optional.empty();

        try {
            String prompt = buildPrompt(title, overview, keywords, tagline, personName, events);

            StructuredMessageCreateParams<StorytellingResult> params = MessageCreateParams.builder()
                    .model(properties.model())
                    .maxTokens(2048)
                    .system(SYSTEM_PROMPT)
                    .outputConfig(StorytellingResult.class)
                    .addUserMessage(prompt)
                    .build();

            StorytellingResult result = client.messages().create(params).content().stream()
                    .flatMap(block -> block.text().stream())
                    .map(block -> block.text())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Claude 응답에 구조화 결과가 없습니다."));

            return Optional.of(result);

        } catch (Exception e) {
            log.warn("스토리텔링 생성 실패 (title={}): {}", title, e.toString());
            return Optional.empty();
        }
    }

    private String buildPrompt(
            String title, String overview, String keywords, String tagline,
            String personName, List<ChronologyEvent> events
    ) {
        return "제목: " + orEmpty(title) + "\n"
                + "줄거리: " + orEmpty(overview) + "\n"
                + "키워드: " + orEmpty(keywords) + "\n"
                + "태그라인: " + orEmpty(tagline) + "\n"
                + "핵심 인물: " + (personName != null ? personName : "(없음)") + "\n"
                + "실제 역사 연표(각색 없음):\n" + chronologyLoader.toPromptText(events);
    }

    private static String orEmpty(String s) {
        return s == null ? "(없음)" : s;
    }

    private static AnthropicClient createClient(LlmProperties properties) {
        if (!properties.isUsable()) {
            log.info("StorytellingGenerator 비활성 상태 (itda.llm.api-key 미설정 또는 enabled=false).");
            return null;
        }
        try {
            return AnthropicOkHttpClient.builder()
                    .apiKey(properties.apiKey())
                    .timeout(Duration.ofSeconds(30))
                    .build();
        } catch (Exception e) {
            log.warn("Claude 클라이언트 생성 실패: {}", e.toString());
            return null;
        }
    }
}

package com.itda.itinerary.route;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.itda.place.domain.Place;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * 코드가 좁혀 놓은 촬영지 후보들 중 하나를 Claude 가 고르고 이유를 붙인다.
 *
 * <p>역할 분담이 이 클래스의 핵심이다:
 * <ul>
 *   <li><b>코드</b>({@link SpotScorer}) — 동선상 말이 되는가. 거리·순서·운영시간.
 *       LLM 은 좌표 계산을 못 하고 거리를 지어내므로 이 판정을 맡길 수 없다.</li>
 *   <li><b>LLM</b>(이 클래스) — 같이 보면 좋은 조합인가. 장면 맥락, 성격 중복.
 *       {@code category} 문자열만으로는 "둘 다 카페인데 하나는 고백씬, 하나는 배경"을
 *       구분할 수 없다.</li>
 * </ul>
 *
 * <p>후보가 이미 전부 "동선상 괜찮은 곳"이므로 LLM 이 무엇을 고르든 일정이 망가지지 않는다.
 * 호출 실패·타임아웃·엉뚱한 place_id 응답은 모두 점수 1등으로 폴백한다.
 */
@Slf4j
@Component
public class SpotCurator {

    private static final String SYSTEM_PROMPT = """
            너는 드라마·영화 촬영지 여행 코스를 짜는 큐레이터다.
            하루 일정에 추가할 촬영지 후보 목록이 주어진다.

            후보들은 이미 동선(거리·이동시간·운영시간) 검증을 통과한 곳들이다.
            거리나 소요시간을 다시 계산하거나 추정하지 마라. 그건 이미 끝난 판단이다.

            네가 볼 것은 하나다: 이미 고른 촬영지들과 함께 보기 좋은 조합인가.
            성격이 겹치는 곳보다 서로 다른 경험을 주는 곳을 우선한다.

            place_id 는 반드시 후보 목록에 제시된 값 중 하나를 그대로 써라.
            """;

    private final LlmProperties properties;
    private final AnthropicClient client;

    public SpotCurator(LlmProperties properties) {
        this.properties = properties;
        this.client = createClient(properties);
    }

    /**
     * 후보 중 하나를 고른다.
     *
     * @param shortlist    {@link SpotScorer} 가 점수순으로 정렬한 후보 (앞쪽이 높은 점수)
     * @param contentTitle 콘텐츠 제목 — LLM 에게 맥락을 준다
     * @param selected     이미 확정된 촬영지들
     * @return 항상 결과가 있다. LLM 을 못 쓰면 shortlist 1등으로 폴백한다.
     */
    public Optional<CuratedSpot> choose(List<ScoredSpot> shortlist,
                                        String contentTitle,
                                        List<ContentSpot> selected) {
        if (shortlist.isEmpty()) {
            return Optional.empty();
        }

        ContentSpot topScored = shortlist.get(0).spot();
        if (client == null || shortlist.size() == 1) {
            return Optional.of(CuratedSpot.fallback(topScored));
        }

        try {
            SpotPick pick = ask(shortlist, contentTitle, selected);
            ContentSpot chosen = findById(shortlist, pick.placeId());
            if (chosen == null) {
                log.warn("Claude 가 후보에 없는 place_id({})를 골라 점수 1등으로 폴백합니다.", pick.placeId());
                return Optional.of(CuratedSpot.fallback(topScored));
            }
            return Optional.of(new CuratedSpot(chosen, trimReason(pick.reason()), true));

        } catch (Exception e) {
            log.warn("Claude 촬영지 큐레이션 실패 — 점수 1등으로 폴백합니다: {}", e.toString());
            return Optional.of(CuratedSpot.fallback(topScored));
        }
    }

    private SpotPick ask(List<ScoredSpot> shortlist, String contentTitle, List<ContentSpot> selected) {
        StructuredMessageCreateParams<SpotPick> params = MessageCreateParams.builder()
                .model(properties.model())
                .maxTokens(properties.maxTokens())
                .system(SYSTEM_PROMPT)
                .outputConfig(SpotPick.class)
                .addUserMessage(userPrompt(shortlist, contentTitle, selected))
                .build();

        return client.messages().create(params).content().stream()
                .flatMap(block -> block.text().stream())
                .map(block -> block.text())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Claude 응답에 구조화 결과가 없습니다."));
    }

    private String userPrompt(List<ScoredSpot> shortlist, String contentTitle, List<ContentSpot> selected) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("콘텐츠: ").append(contentTitle == null ? "(제목 없음)" : contentTitle).append('\n');

        prompt.append("\n이미 고른 촬영지:\n");
        if (selected.isEmpty()) {
            prompt.append("- (아직 없음. 이번이 첫 촬영지다)\n");
        } else {
            for (ContentSpot spot : selected) {
                prompt.append("- ").append(describe(spot.place())).append('\n');
            }
        }

        prompt.append("\n후보:\n");
        for (ScoredSpot scored : shortlist) {
            Place place = scored.spot().place();
            StringJoiner notes = new StringJoiner(", ");
            if (scored.wouldBeLast()) {
                notes.add("하루 마지막 자리, 도착 예상 " + scored.estimatedLastArrival());
                if (scored.nightBonusApplied()) {
                    notes.add("야간 운영 가능");
                } else if (!place.isNightOpen()) {
                    notes.add("야간 운영 여부 불명");
                }
            }
            prompt.append("- place_id=").append(scored.spot().placeId())
                    .append(" | ").append(describe(place));
            if (notes.length() > 0) {
                prompt.append(" | ").append(notes);
            }
            prompt.append('\n');
        }

        prompt.append("\n위 후보 중 하나를 골라라.");
        return prompt.toString();
    }

    private static String describe(Place place) {
        StringBuilder sb = new StringBuilder(place.getName());
        if (place.getCategory() != null) {
            sb.append(" (").append(place.getCategory()).append(')');
        }
        String description = place.getDescription();
        if (description != null && !description.isBlank()) {
            sb.append(" — ").append(abbreviate(description, 120));
        }
        return sb.toString();
    }

    private static ContentSpot findById(List<ScoredSpot> shortlist, long placeId) {
        for (ScoredSpot scored : shortlist) {
            Long id = scored.spot().placeId();
            if (id != null && id == placeId) {
                return scored.spot();
            }
        }
        return null;
    }

    private static AnthropicClient createClient(LlmProperties properties) {
        if (!properties.isUsable()) {
            log.info("Claude 큐레이션이 비활성 상태입니다 (itda.llm.api-key 미설정 또는 enabled=false). "
                    + "촬영지는 점수 1등으로 선택됩니다.");
            return null;
        }
        try {
            return AnthropicOkHttpClient.builder()
                    .apiKey(properties.apiKey())
                    .timeout(Duration.ofSeconds(15))
                    .build();
        } catch (Exception e) {
            log.warn("Claude 클라이언트 생성 실패 — 큐레이션 없이 동작합니다: {}", e.toString());
            return null;
        }
    }

    private static String trimReason(String reason) {
        if (reason == null || reason.isBlank()) {
            return null;
        }
        return abbreviate(reason.trim(), 200);
    }

    private static String abbreviate(String s, int max) {
        return s.length() <= max ? s : s.substring(0, max) + "...";
    }
}

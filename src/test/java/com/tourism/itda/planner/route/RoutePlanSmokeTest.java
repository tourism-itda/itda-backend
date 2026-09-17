package com.tourism.itda.planner.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.explore.config.PersonDataInitializer;
import com.tourism.itda.explore.config.PlaceDataInitializer;
import com.tourism.itda.explore.entity.ContentPerson;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.repository.ContentPersonRepository;
import com.tourism.itda.explore.repository.PersonRepository;
import com.tourism.itda.planner.dto.RoutePlanRequest;
import com.tourism.itda.planner.dto.RoutePlanResponse;
import com.tourism.itda.planner.dto.RouteSlotView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 루트 생성 전체 경로를 한 번 굴려 보는 스모크 테스트.
 *
 * <p>단위 테스트가 아니다. <b>관광공사 TourAPI 와 카카오 API 를 실제로 호출</b>하므로
 * 네트워크와 키가 없으면 결과가 달라진다. 그래서 {@code smoke} 태그를 붙여 기본 빌드에서 제외한다.
 *
 * <pre>
 *   .\gradlew.bat test --tests "*RoutePlanSmokeTest" -Psmoke
 * </pre>
 *
 * <p>확인하려는 것:
 * <ol>
 *   <li>인물 체인으로 앵커가 잡히는가 (content_place 가 비어 있어도)</li>
 *   <li>흩어진 앵커 중 한 지역만 남기는가 — 정조는 서울 창덕궁과 수원권이 섞여 있다</li>
 *   <li>관련 2 + 일반 1 로 구성되는가</li>
 *   <li>방문 순서가 동선 기준으로 정해지는가</li>
 * </ol>
 *
 * <p>키가 없으면 일반 명소가 0곳이라 관련 명소만으로 루트가 나온다. 그 경우도 실패가 아니다 —
 * 로그에 구성을 찍어 두었으니 눈으로 확인하면 된다.
 */
@Tag("smoke")
@SpringBootTest
@ActiveProfiles("h2")
@DisplayName("루트 생성 스모크")
class RoutePlanSmokeTest {

    private static final long TEST_CONTENT_ID = 999_001L;

    @Autowired private PersonDataInitializer personDataInitializer;
    @Autowired private PlaceDataInitializer placeDataInitializer;
    @Autowired private ContentRepository contentRepository;
    @Autowired private ContentPersonRepository contentPersonRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private RoutePlanner routePlanner;
    @Autowired private ObjectMapper objectMapper;

    /**
     * {@code @SpringBootTest} 는 CommandLineRunner 를 실행하지 않는다.
     * 시드가 있어야 인물 체인이 동작하므로 직접 부른다.
     */
    @BeforeEach
    void seed() throws Exception {
        if (personRepository.count() == 0) {
            personDataInitializer.run();
        }
        placeDataInitializer.run();

        if (!contentRepository.existsById(TEST_CONTENT_ID)) {
            Content content = new Content(
                    TEST_CONTENT_ID, null, "사도 (스모크 테스트)", null,
                    "영조와 사도세자의 비극을 다룬 작품", 2015, "movie", null, null);
            content.publish();
            contentRepository.save(content);

            // 정조는 수원 화성·화성행궁·융릉(경기)과 창덕궁(서울)에 걸쳐 있다.
            // 지역 클러스터링이 동작하는지 보기 좋은 인물이다.
            Person jeongjo = personRepository.findByName("정조").orElseThrow();
            contentPersonRepository.save(new ContentPerson(content, jeongjo));
        }
    }

    @Test
    @DisplayName("인물 체인으로 앵커를 잡고 관련 2 + 일반 1 로 구성한다")
    void 루트를_생성한다() throws Exception {
        RoutePlanResponse response = routePlanner.plan(
                new RoutePlanRequest(TEST_CONTENT_ID, null, null, null));

        System.out.println("=== 루트 응답 ===");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

        System.out.println("=== 방문 순서 ===");
        int order = 0;
        for (RouteSlotView slot : response.slots()) {
            String filled = slot.place() == null
                    ? "(빈칸 — 사용자가 고름)"
                    : slot.place().name() + "  [" + slot.filledBy() + "]";
            System.out.printf("칸 %d  %-6s %-8s %s%n",
                    order++, slot.label(), slot.estimatedTime(), filled);
        }

        assertThat(response.slots()).isNotEmpty();
        assertThat(response.spotCount())
                .as("명소는 1곳 이상 3곳 이하여야 한다")
                .isBetween(1, DayTemplate.MAX_SPOTS);

        long relatedCount = response.slots().stream()
                .filter(slot -> slot.place() != null)
                .filter(slot -> slot.filledBy() != com.tourism.itda.planner.dto.SlotFilledBy.GENERAL)
                .count();

        assertThat(relatedCount)
                .as("작품 관련 명소는 최소 1곳, 최대 %d곳", RoutePlanner.MAX_RELATED_SPOTS)
                .isBetween(1L, (long) RoutePlanner.MAX_RELATED_SPOTS);
    }
}

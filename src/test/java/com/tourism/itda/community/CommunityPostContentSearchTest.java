package com.tourism.itda.community;

import com.tourism.itda.community.dto.CommunityPostDetailResponse;
import com.tourism.itda.community.dto.CommunityPostSummaryResponse;
import com.tourism.itda.community.service.CommunityService;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.planner.entity.Itinerary;
import com.tourism.itda.planner.repository.ItineraryRepository;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 커뮤니티 목록에서 "어떤 작품으로 만든 루트인지" 보여주고, 작품 이름으로 검색/필터할 수 있는지 확인한다.
 * - 목록·상세 응답에 content_id / content_title / content_thumbnail_url 이 실린다
 * - q 는 일정 제목뿐 아니라 작품 제목에도 매칭된다 (작품 이름으로 검색하면 그 작품의 루트가 나온다)
 * - q 는 지역명, 작성자 닉네임에도 매칭된다
 * - content_id 로 특정 작품의 루트만 걸러 볼 수 있다
 * - 작품 없이 만든 일정(content_id null)도 목록에서 깨지지 않는다
 */
@SpringBootTest
@ActiveProfiles("h2")
@TestPropertySource(properties = {
        "aws.region=ap-northeast-2",
        "aws.access-key=dummy",
        "aws.secret-key=dummy"
})
@Transactional
class CommunityPostContentSearchTest {

    private static final long CONTENT_A_ID = 9_000_001L;
    private static final long CONTENT_B_ID = 9_000_002L;
    private static final String CONTENT_A_TITLE = "검색테스트작품에이";
    private static final String CONTENT_B_TITLE = "검색테스트작품비";

    @Autowired
    private CommunityService communityService;
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private UserRepository userRepository;

    private Long routeOfAId;
    private Long routeOfBId;
    private Long routeWithoutContentId;
    private Long routeInBusanId;
    private Long routeByNicknamedAuthorId;

    @BeforeEach
    void setUp() {
        contentRepository.save(content(CONTENT_A_ID, CONTENT_A_TITLE, "https://img.test/a-thumb.jpg", null));
        // thumbnail 이 없으면 poster 로 대체되는지도 함께 본다.
        contentRepository.save(content(CONTENT_B_ID, CONTENT_B_TITLE, null, "https://img.test/b-poster.jpg"));

        // 일정 제목에는 작품 이름이 들어있지 않다 — 제목 매칭만으로는 절대 안 잡힌다.
        routeOfAId = sharedItinerary(CONTENT_A_ID, "경복궁 하루 코스");
        routeOfBId = sharedItinerary(CONTENT_B_ID, "수원화성 하루 코스");
        routeWithoutContentId = sharedItinerary(null, "작품 없이 만든 코스");

        // 제목·작품명 어디에도 없는 지역명으로만 검색해서 매칭되는지 확인.
        routeInBusanId = sharedItinerary(null, "바다 보러 가는 코스", "부산광역시", 1L);

        // 제목에 없는 작성자 닉네임으로 검색해서 매칭되는지 확인.
        User author = userRepository.save(
                new User("nickname-search-tester", "pw", "테스터", "검색테스트닉네임",
                        null, null, true));
        routeByNicknamedAuthorId = sharedItinerary(null, "닉네임과 무관한 제목", "서울", author.getUserId());
    }

    @Test
    void q_matchesContentTitle_notOnlyItineraryTitle() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts(CONTENT_A_TITLE, null, "recent", 0, 20);

        assertThat(result).extracting(CommunityPostSummaryResponse::itineraryId).containsExactly(routeOfAId);
    }

    @Test
    void q_stillMatchesItineraryTitle() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts("작품 없이", null, "recent", 0, 20);

        assertThat(result).extracting(CommunityPostSummaryResponse::itineraryId).containsExactly(routeWithoutContentId);
    }

    @Test
    void listResponse_carriesContentInfo_andFallsBackToPosterWhenNoThumbnail() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts(null, null, "recent", 0, 50);

        CommunityPostSummaryResponse a = find(result, routeOfAId);
        assertThat(a.contentId()).isEqualTo(CONTENT_A_ID);
        assertThat(a.contentTitle()).isEqualTo(CONTENT_A_TITLE);
        assertThat(a.contentThumbnailUrl()).isEqualTo("https://img.test/a-thumb.jpg");

        CommunityPostSummaryResponse b = find(result, routeOfBId);
        assertThat(b.contentThumbnailUrl()).isEqualTo("https://img.test/b-poster.jpg");
    }

    @Test
    void routeWithoutContent_hasNullContentFields_andDoesNotBreakTheList() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts(null, null, "recent", 0, 50);

        CommunityPostSummaryResponse none = find(result, routeWithoutContentId);
        assertThat(none.contentId()).isNull();
        assertThat(none.contentTitle()).isNull();
        assertThat(none.contentThumbnailUrl()).isNull();
    }

    @Test
    void q_matchesRegion_notOnlyItineraryTitle() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts("부산광역시", null, "recent", 0, 20);

        assertThat(result).extracting(CommunityPostSummaryResponse::itineraryId).containsExactly(routeInBusanId);
    }

    @Test
    void q_matchesAuthorNickname_notOnlyItineraryTitle() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts("검색테스트닉네임", null, "recent", 0, 20);

        assertThat(result).extracting(CommunityPostSummaryResponse::itineraryId)
                .containsExactly(routeByNicknamedAuthorId);
    }

    @Test
    void contentIdFilter_returnsOnlyThatContentsRoutes() {
        List<CommunityPostSummaryResponse> result = communityService.getPosts(null, CONTENT_B_ID, "recent", 0, 50);

        assertThat(result).extracting(CommunityPostSummaryResponse::itineraryId).containsExactly(routeOfBId);
    }

    @Test
    void detailResponse_carriesContentInfo() {
        CommunityPostDetailResponse detail = communityService.getPostDetail(routeOfAId);

        assertThat(detail.contentId()).isEqualTo(CONTENT_A_ID);
        assertThat(detail.contentTitle()).isEqualTo(CONTENT_A_TITLE);
        assertThat(detail.contentThumbnailUrl()).isEqualTo("https://img.test/a-thumb.jpg");

        CommunityPostDetailResponse none = communityService.getPostDetail(routeWithoutContentId);
        assertThat(none.contentId()).isNull();
        assertThat(none.contentTitle()).isNull();
    }

    private CommunityPostSummaryResponse find(List<CommunityPostSummaryResponse> list, Long itineraryId) {
        return list.stream().filter(p -> p.itineraryId().equals(itineraryId)).findFirst().orElseThrow();
    }

    private Long sharedItinerary(Long contentId, String title) {
        return sharedItinerary(contentId, title, "서울", 1L);
    }

    private Long sharedItinerary(Long contentId, String title, String region, Long userId) {
        Itinerary it = Itinerary.builder()
                .userId(userId)
                .contentId(contentId)
                .title(title)
                .region(region)
                .durationLabel("당일치기")
                .build();
        it.changeIsShared(true);
        return itineraryRepository.save(it).getId();
    }

    /** Content 는 setter/빌더가 없어(TMDB 동기화로만 생성) 테스트에서는 리플렉션으로 채운다. */
    private Content content(long id, String title, String thumbnailUrl, String posterUrl) {
        Content c = new Content();
        ReflectionTestUtils.setField(c, "id", id);
        ReflectionTestUtils.setField(c, "title", title);
        ReflectionTestUtils.setField(c, "thumbnailUrl", thumbnailUrl);
        ReflectionTestUtils.setField(c, "posterUrl", posterUrl);
        return c;
    }
}

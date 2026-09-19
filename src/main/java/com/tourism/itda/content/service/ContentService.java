package com.tourism.itda.content.service;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.*;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentStatus;
import com.tourism.itda.content.entity.ContentMedia;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.exception.ContentNotFoundException;
import com.tourism.itda.content.repository.BookmarkRepository;
import com.tourism.itda.content.repository.ContentCategoryRepository;
import com.tourism.itda.content.repository.ContentCharacterRepository;
import com.tourism.itda.content.repository.ContentFactCheckRepository;
import com.tourism.itda.content.repository.ContentMediaRepository;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.content.repository.ContentStorySectionRepository;
import com.tourism.itda.content.service.HistoryChronologyLoader.ChronologyEvent;
import com.tourism.itda.explore.data.HistoricalPersonData;
import com.tourism.itda.explore.entity.ContentKingdom;
import com.tourism.itda.explore.entity.HistoricalEvent;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import com.tourism.itda.explore.repository.ContentKingdomRepository;
import com.tourism.itda.explore.repository.HistoricalEventRepository;
import com.tourism.itda.explore.repository.PersonRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.tourism.itda.explore.entity.ContentPerson;
import com.tourism.itda.explore.repository.ContentPersonRepository;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContentService {

    private final TmdbClient tmdbClient;
    private final ContentRepository contentRepository;
    private final ContentMediaRepository contentMediaRepository;
    private final ContentCategoryRepository contentCategoryRepository;
    private final ContentCharacterRepository contentCharacterRepository;
    private final ContentStorySectionRepository contentStorySectionRepository;
    private final ContentFactCheckRepository contentFactCheckRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final BookmarkRepository bookmarkRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final ContentClassifier contentClassifier;
    private final StorytellingGenerator storytellingGenerator;
    private final HistoryChronologyLoader chronologyLoader;
    private final ContentKingdomRepository contentKingdomRepository;
    private final ContentPersonRepository contentPersonRepository;
    private final PersonRepository personRepository;
    private final HistoricalEventRepository historicalEventRepository;

    public ContentService(
            TmdbClient tmdbClient,
            ContentRepository contentRepository,
            ContentMediaRepository contentMediaRepository,
            ContentCategoryRepository contentCategoryRepository,
            ContentCharacterRepository contentCharacterRepository,
            ContentStorySectionRepository contentStorySectionRepository,
            ContentFactCheckRepository contentFactCheckRepository,
            ContentPlaceRepository contentPlaceRepository,
            BookmarkRepository bookmarkRepository,
            PlaceRepository placeRepository,
            PlaceImageRepository placeImageRepository,
            ContentClassifier contentClassifier,
            StorytellingGenerator storytellingGenerator,
            HistoryChronologyLoader chronologyLoader,
            ContentKingdomRepository contentKingdomRepository,
            ContentPersonRepository contentPersonRepository,
            PersonRepository personRepository,
            HistoricalEventRepository historicalEventRepository
    ) {
        this.tmdbClient = tmdbClient;
        this.contentRepository = contentRepository;
        this.contentMediaRepository = contentMediaRepository;
        this.contentCategoryRepository = contentCategoryRepository;
        this.contentCharacterRepository = contentCharacterRepository;
        this.contentStorySectionRepository = contentStorySectionRepository;
        this.contentFactCheckRepository = contentFactCheckRepository;
        this.contentPlaceRepository = contentPlaceRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.placeRepository = placeRepository;
        this.placeImageRepository = placeImageRepository;
        this.contentClassifier = contentClassifier;
        this.storytellingGenerator = storytellingGenerator;
        this.chronologyLoader = chronologyLoader;
        this.contentKingdomRepository = contentKingdomRepository;
        this.contentPersonRepository = contentPersonRepository;
        this.personRepository = personRepository;
        this.historicalEventRepository = historicalEventRepository;
    }

    /**
     * TMDB에서 콘텐츠를 가져와 DB에 저장한다.
     *
     * Claude가 콘텐츠의 시대적 배경과 역사적 인물을 분류하고,
     * 분류된 kingdom을 content와 content_kingdom에 저장한다.
     */
    private Content saveContent(Long contentId) {

        TmdbResponse fetchedContent;
        String fetchedMediaType;

        try {
            fetchedContent = tmdbClient.getMovie(contentId);
            fetchedMediaType = "MOVIE";

        } catch (HttpClientErrorException.NotFound e) {
            fetchedContent = tmdbClient.getTv(contentId);
            fetchedMediaType = "TV";
        }

        final TmdbResponse contentData = fetchedContent;
        final String mediaType = fetchedMediaType;

        // 줄거리(overview)가 없으면 분류의 근거가 없어 오분류(억지 인물 매칭)를 유발하므로
        // 아예 저장하지 않는다. (예: 정보 없는 신작·기획 단계 항목)
        if (contentData.getOverview() == null || contentData.getOverview().isBlank()) {
            throw new ContentNotFoundException(contentId);
        }

        // 3. 영화/TV에 따라 제목과 날짜 결정
        final String title = "MOVIE".equals(mediaType)
                ? contentData.getTitle()
                : contentData.getName();

        final String date = "MOVIE".equals(mediaType)
                ? contentData.getReleaseDate()
                : contentData.getFirstAirDate();

        final int year;

        if (date != null && date.length() >= 4) {
            year = Integer.parseInt(date.substring(0, 4));
        } else {
            year = 0;
        }

        // 4. 영화/TV에 따라 키워드 조회
        final TmdbKeywordResponse keyword;

        if ("MOVIE".equals(mediaType)) {
            keyword = tmdbClient.getKeywords(contentId);
        } else {
            keyword = tmdbClient.getTvKeywords(contentId);
        }

        final String keywords = keyword != null
                && keyword.getKeywords() != null
                ? keyword.getKeywords()
                .stream()
                .map(TmdbKeywordResponse.KeywordDto::getName)
                .collect(Collectors.joining(","))
                : "";

        // 5. Content 생성
        Content content = new Content(
                contentData.getId(),
                contentData.getId(),
                title,
                "https://image.tmdb.org/t/p/w500" + contentData.getPosterPath(),
                contentData.getOverview(),
                year,
                mediaType,
                keywords,
                contentData.getTagline()
        );

        // 6. Claude 분류 + 연표 기반 스토리 생성 (재처리 배치와 공유하는 로직)
        StoryReprocessResult classificationResult = classifyAndGenerateStory(
                content,
                title,
                contentData.getOverview(),
                keywords,
                contentData.getTagline()
        );
        Person classifiedPerson = classificationResult.matchedPerson();

        // 7. 썸네일
        content.changeThumbnailUrl(
                "https://image.tmdb.org/t/p/w500"
                        + contentData.getPosterPath()
        );

        // 8. 노출 상태는 '장소(content_place) 유무'로만 결정한다.
        // 적재 시점에는 아직 관련 장소가 발굴되지 않았으므로 기본값 PENDING(비노출 보류)으로 둔다.
        // 이후 장소 발굴 배치(AnchorDiscoveryBatch)가 관련 장소를 확보하면 PUBLISHED 로 승격한다.
        // (인물/사건 매칭은 연표 기반 줄거리 생성에만 쓰이고, 노출 여부와는 무관하다.)

        // 9. Content 저장
        Content savedContent =
                contentRepository.save(content);

// 나라-콘텐츠 연결
        if (savedContent.getKingdom() != null
                && !contentKingdomRepository.existsByContentIdAndKingdom(
                savedContent.getId(),
                savedContent.getKingdom()
        )) {

            contentKingdomRepository.save(
                    new ContentKingdom(
                            savedContent,
                            savedContent.getKingdom()
                    )
            );
        }

// 인물-콘텐츠 연결
        if (classifiedPerson != null) {

            contentPersonRepository.save(
                    new ContentPerson(
                            savedContent,
                            classifiedPerson
                    )
            );
        }

        return savedContent;
    }

    /**
     * 분류→연표→스토리 재생성 결과. 재처리 배치의 리포트와 saveContent 의 인물 매핑에 쓰인다.
     *
     * @param chronologySource 연표 구간을 무엇에서 뽑았는지: "PERSON", "EVENT", "NONE"
     */
    public record StoryReprocessResult(
            Kingdom kingdom,
            Person matchedPerson,
            String usedEventName,
            String chronologySource,
            boolean storyRegenerated
    ) {}

    /**
     * Claude 분류 결과로 (인물/사건) 연표 구간을 정하고, 그 구간의 실제 연표를 근거로 스토리를 재생성해
     * {@code content} 의 kingdom/personType/personName 및 summary/storyIntro/storyBody 를 갱신한다.
     *
     * <p>연표 구간 우선순위: 인물(가장 정밀) > 검수된 사건 > 없음. 분류가 비면 아무것도 바꾸지 않는다.
     * 인물-콘텐츠 매핑 저장은 반환된 {@link StoryReprocessResult#matchedPerson()} 을 보고 호출측이 처리한다.
     */
    public StoryReprocessResult classifyAndGenerateStory(
            Content content, String title, String overview, String keywords, String tagline) {

        ResolvedClassification r = resolveClassification(title, overview, keywords, tagline);
        if (r == null) {
            return new StoryReprocessResult(null, null, null, "NONE", false);
        }

        content.classify(r.kingdom(), r.personType(),
                r.matchedPerson() != null ? r.matchedPerson().getName() : null);

        String personName = r.matchedPerson() != null ? r.matchedPerson().getName() : null;
        boolean storyRegenerated = storytellingGenerator.generate(
                        title, overview, keywords, tagline, personName, r.events())
                .map(s -> {
                    content.changeSummary(s.summary());
                    content.changeStoryIntro(s.storyIntro());
                    content.changeStoryBody(s.storyBody());
                    return true;
                })
                .orElse(false);

        return new StoryReprocessResult(
                r.kingdom(), r.matchedPerson(),
                r.matchedEvent() != null ? r.matchedEvent().getName() : null,
                r.chronologySource(), storyRegenerated);
    }

    /**
     * 스토리를 재생성하지 않고 분류/연표 매칭 결과만 미리 본다(재처리 배치의 dryRun 용).
     * {@code content} 를 변경하지 않으므로 트랜잭션 커밋 시에도 아무것도 저장되지 않는다.
     */
    public StoryReprocessResult previewClassification(
            String title, String overview, String keywords, String tagline) {

        ResolvedClassification r = resolveClassification(title, overview, keywords, tagline);
        if (r == null) {
            return new StoryReprocessResult(null, null, null, "NONE", false);
        }
        return new StoryReprocessResult(
                r.kingdom(), r.matchedPerson(),
                r.matchedEvent() != null ? r.matchedEvent().getName() : null,
                r.chronologySource(), false);
    }

    private record ResolvedClassification(
            Kingdom kingdom, PersonType personType,
            Person matchedPerson, HistoricalEvent matchedEvent,
            String chronologySource, List<ChronologyEvent> events) {}

    /**
     * Claude 분류 → 인물/사건 매칭 → 연표 구간 선정까지의 (스토리 생성 이전) 해석 단계.
     * 분류가 비면 null 을 반환한다. content 를 변경하지 않는 순수 조회 로직이다.
     */
    private ResolvedClassification resolveClassification(
            String title, String overview, String keywords, String tagline) {

        var classification = contentClassifier.classify(title, overview, keywords, tagline);
        if (classification.isEmpty()) {
            return null;
        }

        var c = classification.get();
        Kingdom kingdom = contentClassifier.parseKingdom(c.kingdom());

        // 같은 이름이 서로 다른 kingdom으로 존재할 수 있어(예: 고종 = JOSEON/KOREAN_EMPIRE)
        // kingdom을 모르면 이름만으로는 어느 행인지 확정할 수 없다. 잘못 연결하느니 매칭하지 않는다.
        Person classifiedPerson = kingdom != null
                ? personRepository.findByNameAndKingdom(c.personName(), kingdom).orElse(null)
                : null;

        PersonType personType = contentClassifier.parsePersonType(c.personType());

        // 연표 구간 결정 — 인물 매칭이 실패해도, 허구 인물이 실제 역사 사건을 배경으로 하면
        // 검수된 사건의 연도 구간으로 연표를 뽑는다. 연도는 항상 사람이 검수한 값이라 오추론 오염이 없다.
        HistoricalEvent matchedEvent =
                (classifiedPerson == null && c.eventName() != null)
                        ? historicalEventRepository.findByName(c.eventName()).orElse(null)
                        : null;

        // 안전장치: 사건의 왕조와 분류된 왕조가 어긋나면(오분류) 연표를 쓰지 않는다.
        if (matchedEvent != null && kingdom != null && matchedEvent.getKingdom() != kingdom) {
            log.warn("사건-왕조 불일치로 연표 미적용 - title={}, event={}({}), kingdom={}",
                    title, matchedEvent.getName(), matchedEvent.getKingdom(), kingdom);
            matchedEvent = null;
        }

        final List<ChronologyEvent> events;
        final String chronologySource;
        if (classifiedPerson != null) {
            events = chronologyLoader.getEventsBetween(
                    classifiedPerson.getStartYear(), classifiedPerson.getEndYear());
            chronologySource = "PERSON";
        } else if (matchedEvent != null) {
            events = chronologyLoader.getEventsBetween(
                    matchedEvent.getStartYear(), matchedEvent.getEndYear());
            chronologySource = "EVENT";
        } else {
            events = List.of();
            chronologySource = "NONE";
        }

        return new ResolvedClassification(
                kingdom, personType, classifiedPerson, matchedEvent, chronologySource, events);
    }

    /**
     * 콘텐츠 저장 API
     *
     * 현재 Controller와의 호환을 위해 kingdom 파라미터는 유지한다.
     * 실제 kingdom 분류는 Claude가 담당한다.
     */
    public ContentResponse saveMovie(Long movieId) {

        Content content = contentRepository.findById(movieId)
                .orElseGet(() -> saveContent(movieId));

        /*
         * 이미 저장된 콘텐츠인데
         * content_kingdom 관계가 없는 경우 관계를 보완한다.
         */
        if (content.getKingdom() != null
                && !contentKingdomRepository.existsByContentIdAndKingdom(
                content.getId(),
                content.getKingdom()
        )) {

            contentKingdomRepository.save(
                    new ContentKingdom(
                            content,
                            content.getKingdom()
                    )
            );
        }

        return ContentResponse.from(content);
    }

    /**
     * 한국 역사·전쟁 영화를 TMDB에서 자동 수집한다.
     *
     * 아직 DB에 저장되지 않은 영화만 최대 limit편 처리한다.
     * 각 콘텐츠의 kingdom은 Claude가 자동으로 분류한다.
     */
    public int collectKoreanHistoryMovies(int limit) {

        int saved = 0;
        int page = 1;
        int totalPages = 1;

        /*
         * limit편을 채우거나 마지막 페이지에 도달할 때까지 페이지를 순회한다.
         * 이미 저장된 콘텐츠가 많으면 한 페이지로는 신규 limit편을 못 채우므로
         * 다음 페이지까지 이어서 조회한다.
         */
        while (saved < limit && page <= totalPages) {

            TmdbSearchResponse discovered =
                    tmdbClient.discoverKoreanHistory(page);

            if (discovered == null
                    || discovered.getResults() == null
                    || discovered.getResults().isEmpty()) {
                break;
            }

            totalPages = discovered.getTotalPages();

            for (TmdbSearchResponse.Result result : discovered.getResults()) {

                if (saved >= limit) {
                    break;
                }

                Long movieId = result.getId();

                /*
                 * 이미 저장된 콘텐츠는 건너뛴다.
                 */
                if (contentRepository.existsById(movieId)) {
                    continue;
                }

                /*
                 * TMDB 조회 → Claude 분류 → DB 저장
                 * → content_kingdom 저장
                 * 줄거리 없는 항목은 saveContent가 ContentNotFoundException을 던지므로
                 * 건너뛰고 다음 후보로 넘어간다(배치가 중단되지 않도록).
                 */
                try {
                    saveContent(movieId);
                    saved++;
                } catch (ContentNotFoundException e) {
                    // 줄거리 없음 등으로 저장 대상이 아님 → skip
                }
            }

            page++;
        }

        return saved;
    }

    /**
     * 콘텐츠 조회 API
     *
     * DB에 없으면 TMDB에서 가져와 저장한다.
     */
    public ContentDetailResponse findContent(Long id) {

        Content content = contentRepository.findById(id)
                .orElseGet(() -> saveContent(id));

        // 매칭 실패로 보류(PENDING)된 콘텐츠는 사용자에게 노출하지 않는다.
        if (content.getStatus() != ContentStatus.PUBLISHED) {
            throw new ContentNotFoundException(id);
        }

        return buildDetailResponse(content);
    }

    private ContentDetailResponse buildDetailResponse(Content content) {

        MediaSummaryResponse media =
                contentMediaRepository.findByContent(content)
                        .stream()
                        .findFirst()
                        .map(ContentMedia::getMedia)
                        .map(MediaSummaryResponse::from)
                        .orElse(null);

        List<CategorySummaryResponse> categories =
                contentCategoryRepository.findByContent(content)
                        .stream()
                        .map(CategorySummaryResponse::from)
                        .toList();

        List<CharacterResponse> characters =
                contentCharacterRepository
                        .findByContentIdOrderBySortOrderAsc(content.getId())
                        .stream()
                        .map(CharacterResponse::from)
                        .toList();

        List<StorySectionResponse> storySections =
                contentStorySectionRepository
                        .findByContentOrderBySortOrderAsc(content)
                        .stream()
                        .map(StorySectionResponse::from)
                        .toList();

        List<FactCheckResponse> factChecks =
                contentFactCheckRepository
                        .findByContentOrderBySortOrderAsc(content)
                        .stream()
                        .map(FactCheckResponse::from)
                        .toList();

        List<ContentPlace> contentPlacesForDetail =
                contentPlaceRepository
                        .findByContentOrderByRecommendOrderAsc(content);

        Map<Long, Place> placesForDetail =
                findPlacesByIds(
                        contentPlacesForDetail.stream()
                                .map(cp -> cp.getId().getPlaceId())
                                .toList()
                );

        List<RelatedPlaceResponse> relatedPlaces =
                contentPlacesForDetail.stream()
                        .map(cp ->
                                RelatedPlaceResponse.from(
                                        cp,
                                        placesForDetail.get(
                                                cp.getId().getPlaceId()
                                        )
                                )
                        )
                        .toList();

        return ContentDetailResponse.of(
                content,
                media,
                categories,
                characters,
                storySections,
                factChecks,
                relatedPlaces
        );
    }

    public TmdbCreditResponse getCredits(Long movieId) {
        return tmdbClient.getCredits(movieId);
    }

    public List<ContentPlaceListItemResponse> getRelatedPlaces(
            Long contentId,
            Long userId
    ) {

        Content content = contentRepository.findById(contentId)
                .orElseThrow(
                        () -> new ContentNotFoundException(contentId)
                );

        List<ContentPlace> contentPlaces =
                contentPlaceRepository
                        .findByContentOrderByRecommendOrderAsc(content);

        List<Long> placeIds =
                contentPlaces.stream()
                        .map(cp -> cp.getId().getPlaceId())
                        .toList();

        Map<Long, Place> places =
                findPlacesByIds(placeIds);

        Map<Long, String> primaryImageUrls =
                findPrimaryImageUrlsByPlaceIds(placeIds);

        return contentPlaces.stream()
                .map(contentPlace -> {

                    Long placeId =
                            contentPlace.getId().getPlaceId();

                    boolean isBookmarked =
                            userId != null
                                    && bookmarkRepository
                                    .existsByUserIdAndPlaceId(
                                            userId,
                                            placeId
                                    );

                    return ContentPlaceListItemResponse.of(
                            contentPlace,
                            places.get(placeId),
                            primaryImageUrls.get(placeId),
                            isBookmarked
                    );
                })
                .toList();
    }

    private Map<Long, Place> findPlacesByIds(
            List<Long> placeIds
    ) {

        return placeRepository.findAllById(placeIds)
                .stream()
                .collect(
                        Collectors.toMap(
                                Place::getId,
                                place -> place
                        )
                );
    }

    private Map<Long, String> findPrimaryImageUrlsByPlaceIds(
            List<Long> placeIds
    ) {

        return placeImageRepository
                .findByPlaceIdInAndPrimaryIsTrue(placeIds)
                .stream()
                .collect(
                        Collectors.toMap(
                                PlaceImage::getPlaceId,
                                PlaceImage::getImageUrl,
                                (a, b) -> a
                        )
                );
    }
    public ContentListResponse searchContents(
            String q,
            String mediaType,
            Long categoryId,
            String kingdom,
            Long personId,
            String sort,
            int page,
            int limit
    ) {

        String likePattern =
                (q != null && !q.isBlank())
                        ? "%" + q + "%"
                        : null;

        String type =
                (mediaType != null && !mediaType.isBlank())
                        ? mediaType
                        : null;

        Kingdom kingdomEnum = null;

        if (kingdom != null && !kingdom.isBlank()) {
            try {
                kingdomEnum = Kingdom.valueOf(kingdom.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "존재하지 않는 kingdom입니다: " + kingdom
                );
            }
        }

        Sort sortOrder =
                "popular".equalsIgnoreCase(sort)
                        ? Sort.by(
                        Sort.Direction.DESC,
                        "viewCount"
                )
                        : Sort.by(
                        Sort.Direction.DESC,
                        "createdAt"
                );

        Pageable pageable =
                PageRequest.of(
                        page,
                        limit,
                        sortOrder
                );

        Page<Content> result =
                contentRepository.search(
                        likePattern,
                        type,
                        categoryId,
                        kingdomEnum,
                        personId,
                        pageable
                );

        List<ContentListItemResponse> data =
                result.getContent()
                        .stream()
                        .map(this::toListItem)
                        .toList();

        return new ContentListResponse(
                data,
                result.getTotalElements()
        );
    }


    private ContentListItemResponse toListItem(
            Content content
    ) {

        MediaSummaryResponse media =
                contentMediaRepository.findByContent(content)
                        .stream()
                        .findFirst()
                        .map(ContentMedia::getMedia)
                        .map(MediaSummaryResponse::from)
                        .orElse(null);

        ContentCategoryBriefResponse category =
                contentCategoryRepository.findByContent(content)
                        .stream()
                        .findFirst()
                        .map(ContentCategoryBriefResponse::from)
                        .orElse(null);

        return ContentListItemResponse.of(
                content,
                media,
                category
        );
    }
}
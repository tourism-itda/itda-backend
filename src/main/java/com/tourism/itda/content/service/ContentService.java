package com.tourism.itda.content.service;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.*;
import com.tourism.itda.content.entity.Content;
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
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;
import com.tourism.itda.explore.repository.ContentKingdomRepository;
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
            PersonRepository personRepository
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

        // 6. Claude를 이용한 역사적 시대/인물 분류
        Person classifiedPerson = null;

        var classification = contentClassifier.classify(
                title,
                contentData.getOverview(),
                keywords,
                contentData.getTagline()
        );

        if (classification.isPresent()) {

            var c = classification.get();

            Kingdom kingdom =
                    contentClassifier.parseKingdom(c.kingdom());

            // 같은 이름이 서로 다른 kingdom으로 존재할 수 있다(예: 고종 = JOSEON/KOREAN_EMPIRE
            // 두 행). kingdom을 모르면 이름만으로는 어느 행인지 확정할 수 없고(findByName은
            // 동명이인이 있으면 IncorrectResultSizeDataAccessException), 잘못 아무 행이나
            // 골라 연결하는 것보다는 아예 매칭하지 않는 게 안전하다. person 연결은 부가 정보라
            // classifiedPerson이 null이어도 콘텐츠 저장 자체는 정상 진행된다(아래 전부 null-safe).
            classifiedPerson = kingdom != null
                    ? personRepository.findByNameAndKingdom(c.personName(), kingdom).orElse(null)
                    : null;

            System.out.println("=== PERSON DEBUG ===");
            System.out.println("Claude personName = [" + c.personName() + "]");
            System.out.println("classifiedPerson = " + classifiedPerson);

            PersonType personType =
                    contentClassifier.parsePersonType(c.personType());

            content.classify(
                    kingdom,
                    personType,
                    classifiedPerson != null
                            ? classifiedPerson.getName()
                            : null
            );

            final List<ChronologyEvent> events =
                    classifiedPerson != null
                            ? chronologyLoader.getEventsBetween(
                            classifiedPerson.getStartYear(),
                            classifiedPerson.getEndYear()
                    )
                            : List.of();

            final String personName =
                    classifiedPerson != null
                            ? classifiedPerson.getName()
                            : null;

            storytellingGenerator.generate(
                    title,
                    contentData.getOverview(),
                    keywords,
                    contentData.getTagline(),
                    personName,
                    events
            ).ifPresent(s -> {
                content.changeSummary(s.summary());
                content.changeStoryIntro(s.storyIntro());
                content.changeStoryBody(s.storyBody());
            });
        }

        // 7. 썸네일
        content.changeThumbnailUrl(
                "https://image.tmdb.org/t/p/w500"
                        + contentData.getPosterPath()
        );

        // 8. Content 저장
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

            System.out.println("=== CONTENT PERSON SAVE ===");
            System.out.println("contentId = " + savedContent.getId());
            System.out.println("personId = " + classifiedPerson.getPersonId());
            System.out.println("personName = " + classifiedPerson.getName());

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

        TmdbSearchResponse discovered =
                tmdbClient.discoverKoreanHistory(1);

        if (discovered == null
                || discovered.getResults() == null) {
            return 0;
        }

        int saved = 0;

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
             */
            saveContent(movieId);

            saved++;
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
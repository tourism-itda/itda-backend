package com.tourism.itda.content.service;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.*;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentMedia;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.exception.ContentNotFoundException;
import com.tourism.itda.content.repository.ContentCategoryRepository;
import com.tourism.itda.content.repository.ContentCharacterRepository;
import com.tourism.itda.content.repository.ContentFactCheckRepository;
import com.tourism.itda.content.repository.ContentMediaRepository;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.content.repository.ContentStorySectionRepository;
import com.tourism.itda.content.repository.BookmarkRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
            PlaceImageRepository placeImageRepository
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
    }

    /**
     * TMDB에서 영화를 가져와 DB에 저장
     */
    private Content saveContent(Long movieId) {

        TmdbResponse movie = tmdbClient.getMovie(movieId);

        // 필요하면 나중에 배우 정보 사용
        TmdbCreditResponse credits = tmdbClient.getCredits(movieId);

        TmdbKeywordResponse keyword = tmdbClient.getKeywords(movieId);

        int year = Integer.parseInt(
                movie.getReleaseDate().substring(0, 4)
        );

        String keywords = keyword.getKeywords()
                .stream()
                .map(TmdbKeywordResponse.KeywordDto::getName)
                .collect(Collectors.joining(","));

        Content content = new Content(
                movie.getId(),
                movie.getId(),
                movie.getTitle(),
                "https://image.tmdb.org/t/p/w500" + movie.getPosterPath(),
                movie.getOverview(),
                year,
                "MOVIE",    
                keywords,
                movie.getTagline()
        );

        return contentRepository.save(content);
    }

    /**
     * 영화 저장 API
     */
    public ContentResponse saveMovie(Long movieId) {

        Content content = saveContent(movieId);

        return ContentResponse.from(content);
    }

    /**
     * 영화 조회 API
     * DB에 없으면 TMDB에서 가져와 저장 후 반환
     */
    public ContentDetailResponse findContent(Long id) {

        Content content = contentRepository.findById(id)
                .orElseGet(() -> saveContent(id));

        return buildDetailResponse(content);
    }

    private ContentDetailResponse buildDetailResponse(Content content) {

        MediaSummaryResponse media = contentMediaRepository.findByContent(content).stream()
                .findFirst()
                .map(ContentMedia::getMedia)
                .map(MediaSummaryResponse::from)
                .orElse(null);

        List<CategorySummaryResponse> categories = contentCategoryRepository.findByContent(content).stream()
                .map(CategorySummaryResponse::from)
                .toList();

        List<CharacterResponse> characters = contentCharacterRepository.findByContentIdOrderBySortOrderAsc(content.getId()).stream()
                .map(CharacterResponse::from)
                .toList();

        List<StorySectionResponse> storySections = contentStorySectionRepository.findByContentOrderBySortOrderAsc(content).stream()
                .map(StorySectionResponse::from)
                .toList();

        List<FactCheckResponse> factChecks = contentFactCheckRepository.findByContentOrderBySortOrderAsc(content).stream()
                .map(FactCheckResponse::from)
                .toList();

        List<ContentPlace> contentPlacesForDetail = contentPlaceRepository.findByContentOrderByRecommendOrderAsc(content);
        Map<Long, Place> placesForDetail = findPlacesByIds(contentPlacesForDetail.stream()
                .map(cp -> cp.getId().getPlaceId())
                .toList());

        List<RelatedPlaceResponse> relatedPlaces = contentPlacesForDetail.stream()
                .map(cp -> RelatedPlaceResponse.from(cp, placesForDetail.get(cp.getId().getPlaceId())))
                .toList();

        return ContentDetailResponse.of(content, media, categories, characters, storySections, factChecks, relatedPlaces);
    }

    public TmdbCreditResponse getCredits(Long movieId) {
        return tmdbClient.getCredits(movieId);
    }

    public List<ContentPlaceListItemResponse> getRelatedPlaces(Long contentId, Long userId) {

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ContentNotFoundException(contentId));

        List<ContentPlace> contentPlaces = contentPlaceRepository.findByContentOrderByRecommendOrderAsc(content);
        List<Long> placeIds = contentPlaces.stream().map(cp -> cp.getId().getPlaceId()).toList();

        Map<Long, Place> places = findPlacesByIds(placeIds);
        Map<Long, String> primaryImageUrls = findPrimaryImageUrlsByPlaceIds(placeIds);

        return contentPlaces.stream()
                .map(contentPlace -> {
                    Long placeId = contentPlace.getId().getPlaceId();
                    boolean isBookmarked = userId != null
                            && bookmarkRepository.existsByUserIdAndPlaceId(userId, placeId);
                    return ContentPlaceListItemResponse.of(
                            contentPlace, places.get(placeId), primaryImageUrls.get(placeId), isBookmarked);
                })
                .toList();
    }

    private Map<Long, Place> findPlacesByIds(List<Long> placeIds) {
        return placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, place -> place));
    }

    private Map<Long, String> findPrimaryImageUrlsByPlaceIds(List<Long> placeIds) {
        return placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(placeIds).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));
    }

    public ContentListResponse searchContents(
            String q,
            String mediaType,
            Long categoryId,
            String sort,
            int page,
            int limit
    ) {
        String likePattern = (q != null && !q.isBlank()) ? "%" + q + "%" : null;
        String type = (mediaType != null && !mediaType.isBlank()) ? mediaType : null;

        Sort sortOrder = "popular".equalsIgnoreCase(sort)
                ? Sort.by(Sort.Direction.DESC, "viewCount")
                : Sort.by(Sort.Direction.DESC, "createdAt");

        Pageable pageable = PageRequest.of(page, limit, sortOrder);

        Page<Content> result = contentRepository.search(likePattern, type, categoryId, pageable);

        List<ContentListItemResponse> data = result.getContent().stream()
                .map(this::toListItem)
                .toList();

        return new ContentListResponse(data, result.getTotalElements());
    }

    private ContentListItemResponse toListItem(Content content) {

        MediaSummaryResponse media = contentMediaRepository.findByContent(content).stream()
                .findFirst()
                .map(ContentMedia::getMedia)
                .map(MediaSummaryResponse::from)
                .orElse(null);

        ContentCategoryBriefResponse category = contentCategoryRepository.findByContent(content).stream()
                .findFirst()
                .map(ContentCategoryBriefResponse::from)
                .orElse(null);

        return ContentListItemResponse.of(content, media, category);
    }

}
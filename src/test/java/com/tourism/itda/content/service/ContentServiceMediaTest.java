package com.tourism.itda.content.service;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.TmdbResponse;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentMedia;
import com.tourism.itda.content.entity.Media;
import com.tourism.itda.content.repository.BookmarkRepository;
import com.tourism.itda.content.repository.ContentCategoryRepository;
import com.tourism.itda.content.repository.ContentCharacterRepository;
import com.tourism.itda.content.repository.ContentFactCheckRepository;
import com.tourism.itda.content.repository.ContentMediaRepository;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.content.repository.ContentStorySectionRepository;
import com.tourism.itda.content.repository.MediaRepository;
import com.tourism.itda.explore.repository.ContentKingdomRepository;
import com.tourism.itda.explore.repository.ContentPersonRepository;
import com.tourism.itda.explore.repository.HistoricalEventRepository;
import com.tourism.itda.explore.repository.PersonRepository;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * media(타입/개봉연도) 적재 로직 검증.
 * - saveContent() 로 새로 저장되는 콘텐츠는 Content.mediaType/releaseYear 를 그대로 복사한 media 가 붙는다
 * - 기존에 저장돼 있던(media 없는) 콘텐츠는 TMDB 재호출 없이 백필로 채워진다
 * - 이미 다 채워졌으면(findAllWithoutMedia 가 빈 목록) 아무 것도 건드리지 않는다
 *
 * TmdbClient/ContentClassifier 등은 전부 Mockito 목이라 실제 TMDB·Claude 호출도, 실제 DB도 타지 않는다
 * (앱 전체 컨텍스트를 띄우지 않아 시작 시 데이터 시더와의 충돌도 없다). ContentClassifier.classify() 를
 * 빈 결과로 만들면 인물/사건/스토리 재생성 로직이 전부 조기 반환되므로(ContentService.resolveClassification),
 * media 연결 로직만 순수하게 검증할 수 있다.
 */
@ExtendWith(MockitoExtension.class)
class ContentServiceMediaTest {

    private static final long MOVIE_ID = 900_001L;
    private static final long LEGACY_TV_ID = 900_002L;

    @Mock private TmdbClient tmdbClient;
    @Mock private ContentRepository contentRepository;
    @Mock private ContentMediaRepository contentMediaRepository;
    @Mock private MediaRepository mediaRepository;
    @Mock private ContentCategoryRepository contentCategoryRepository;
    @Mock private ContentCharacterRepository contentCharacterRepository;
    @Mock private ContentStorySectionRepository contentStorySectionRepository;
    @Mock private ContentFactCheckRepository contentFactCheckRepository;
    @Mock private ContentPlaceRepository contentPlaceRepository;
    @Mock private BookmarkRepository bookmarkRepository;
    @Mock private PlaceRepository placeRepository;
    @Mock private PlaceImageRepository placeImageRepository;
    @Mock private ContentClassifier contentClassifier;
    @Mock private StorytellingGenerator storytellingGenerator;
    @Mock private HistoryChronologyLoader chronologyLoader;
    @Mock private ContentKingdomRepository contentKingdomRepository;
    @Mock private ContentPersonRepository contentPersonRepository;
    @Mock private PersonRepository personRepository;
    @Mock private HistoricalEventRepository historicalEventRepository;

    @InjectMocks
    private ContentService contentService;

    @Test
    void saveMovie_createsMedia_copyingTypeAndReleaseYearFromContent() {
        when(contentClassifier.classify(any(), any(), any(), any())).thenReturn(Optional.empty());
        when(contentRepository.findById(MOVIE_ID)).thenReturn(Optional.empty());
        when(tmdbClient.getMovie(MOVIE_ID)).thenReturn(
                tmdbResponse(MOVIE_ID, "새콘텐츠", "테스트 개요", "2019-05-01", "/poster.jpg"));
        when(tmdbClient.getKeywords(MOVIE_ID)).thenReturn(null);
        when(contentRepository.save(any(Content.class))).thenAnswer(inv -> inv.getArgument(0));
        when(mediaRepository.save(any(Media.class))).thenAnswer(inv -> inv.getArgument(0));

        contentService.saveMovie(MOVIE_ID);

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);
        org.mockito.Mockito.verify(mediaRepository).save(mediaCaptor.capture());
        assertThat(mediaCaptor.getValue().getType()).isEqualTo("MOVIE");
        assertThat(mediaCaptor.getValue().getReleaseYear()).isEqualTo(2019);

        ArgumentCaptor<ContentMedia> linkCaptor = ArgumentCaptor.forClass(ContentMedia.class);
        org.mockito.Mockito.verify(contentMediaRepository).save(linkCaptor.capture());
        assertThat(linkCaptor.getValue().getMedia()).isEqualTo(mediaCaptor.getValue());
        assertThat(linkCaptor.getValue().getContent().getId()).isEqualTo(MOVIE_ID);
    }

    @Test
    void backfill_fillsExistingContentWithoutMedia_withoutCallingTmdb() {
        Content legacy = new Content(LEGACY_TV_ID, LEGACY_TV_ID, "레거시작품", "https://img.test/p.jpg",
                "개요", 2015, "TV", "", null);
        when(contentRepository.findAllWithoutMedia()).thenReturn(List.of(legacy));
        when(mediaRepository.save(any(Media.class))).thenAnswer(inv -> inv.getArgument(0));

        int filled = contentService.backfillMediaForExistingContents();

        assertThat(filled).isEqualTo(1);
        verifyNoInteractions(tmdbClient, contentClassifier);

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);
        org.mockito.Mockito.verify(mediaRepository).save(mediaCaptor.capture());
        assertThat(mediaCaptor.getValue().getType()).isEqualTo("TV");
        assertThat(mediaCaptor.getValue().getReleaseYear()).isEqualTo(2015);

        org.mockito.Mockito.verify(contentMediaRepository).save(any(ContentMedia.class));
    }

    @Test
    void backfill_doesNothing_whenNoContentIsMissingMedia() {
        when(contentRepository.findAllWithoutMedia()).thenReturn(List.of());

        int filled = contentService.backfillMediaForExistingContents();

        assertThat(filled).isZero();
        verifyNoInteractions(mediaRepository, contentMediaRepository, tmdbClient);
    }

    private TmdbResponse tmdbResponse(long id, String title, String overview, String releaseDate, String posterPath) {
        TmdbResponse response = new TmdbResponse();
        ReflectionTestUtils.setField(response, "id", id);
        ReflectionTestUtils.setField(response, "title", title);
        ReflectionTestUtils.setField(response, "overview", overview);
        ReflectionTestUtils.setField(response, "releaseDate", releaseDate);
        ReflectionTestUtils.setField(response, "posterPath", posterPath);
        return response;
    }
}

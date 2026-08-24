package com.tourism.itda.content.client;

import com.tourism.itda.content.dto.TmdbCreditResponse;
import com.tourism.itda.content.dto.TmdbKeywordResponse;
import com.tourism.itda.content.dto.TmdbResponse;
import com.tourism.itda.content.dto.TmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TmdbClient {

    @Value("${tmdb.api.access-token}")
    private String accessToken;

    // TMDB API 공통 베이스
    private static final String API_BASE = "https://api.themoviedb.org/3";

    // 영화
    private static final String MOVIE_BASE_URL = API_BASE + "/movie/";
    private static final String MOVIE_SEARCH_URL = API_BASE + "/search/movie";
    private static final String MOVIE_DISCOVER_URL = API_BASE + "/discover/movie";

    // TV
    private static final String TV_BASE_URL = API_BASE + "/tv/";

    // TMDB 장르 ID
    // 36 = 역사, 10752 = 전쟁
    @Value("${itda.movie-collect.genres}")
    private String historyGenres;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        return headers;
    }

    /**
     * TMDB 영화 상세 조회
     */
    public TmdbResponse getMovie(Long movieId) {

        String url =
                MOVIE_BASE_URL
                        + movieId
                        + "?language=ko-KR";

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbResponse.class
                );

        return response.getBody();
    }

    /**
     * TMDB TV 드라마 상세 조회
     */
    public TmdbResponse getTv(Long tvId) {

        String url =
                TV_BASE_URL
                        + tvId
                        + "?language=ko-KR";

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbResponse.class
                );

        return response.getBody();
    }

    /**
     * 영화 크레딧 조회
     */
    public TmdbCreditResponse getCredits(Long movieId) {

        String url =
                MOVIE_BASE_URL
                        + movieId
                        + "/credits";

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbCreditResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbCreditResponse.class
                );

        return response.getBody();
    }

    /**
     * 영화 키워드 조회
     */
    public TmdbKeywordResponse getKeywords(Long movieId) {

        String url =
                MOVIE_BASE_URL
                        + movieId
                        + "/keywords";

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbKeywordResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbKeywordResponse.class
                );

        return response.getBody();
    }

    /**
     * TV 프로그램 키워드 조회
     */
    public TmdbKeywordResponse getTvKeywords(Long tvId) {

        String url =
                TV_BASE_URL
                        + tvId
                        + "/keywords";

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbKeywordResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbKeywordResponse.class
                );

        return response.getBody();
    }

    /**
     * 한국 역사·전쟁 장르 영화를 인기순으로 조회한다.
     * 자동 수집용
     *
     * with_origin_country=KR
     * with_genres=36,10752
     */
    public TmdbSearchResponse discoverKoreanHistory(int page) {

        String url = UriComponentsBuilder
                .fromHttpUrl(MOVIE_DISCOVER_URL)
                .queryParam("with_origin_country", "KR")
                .queryParam("with_genres", historyGenres)
                .queryParam("language", "ko-KR")
                .queryParam("sort_by", "popularity.desc")
                .queryParam("include_adult", false)
                .queryParam("page", page)
                .build()
                .toUriString();

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbSearchResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbSearchResponse.class
                );

        return response.getBody();
    }

    /**
     * 영화 검색
     */
    public TmdbSearchResponse searchMovies(String query, int page) {

        String url = UriComponentsBuilder
                .fromHttpUrl(MOVIE_SEARCH_URL)
                .queryParam("query", query)
                .queryParam("language", "ko-KR")
                .queryParam("page", page)
                .queryParam("include_adult", false)
                .build()
                .toUriString();

        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbSearchResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbSearchResponse.class
                );

        return response.getBody();
    }
}
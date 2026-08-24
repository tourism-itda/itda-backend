package com.tourism.itda.content.client;

import com.tourism.itda.content.dto.TmdbCreditResponse;
import com.tourism.itda.content.dto.TmdbKeywordResponse;
import com.tourism.itda.content.dto.TmdbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.tourism.itda.content.dto.TmdbSearchResponse;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class TmdbClient {

    @Value("${tmdb.api.access-token}")
    private String accessToken;

    // TMDB API 공통 베이스. 도메인·버전 변경 시 이 한 곳만 수정하면 된다.
    private static final String API_BASE = "https://api.themoviedb.org/3";
    private static final String BASE_URL = API_BASE + "/movie/";
    private static final String SEARCH_URL = API_BASE + "/search/movie";
    private static final String DISCOVER_URL = API_BASE + "/discover/movie";

    // TMDB 장르 ID (36=역사, 10752=전쟁). application.yml 에서 조정 가능.
    @Value("${itda.movie-collect.genres}")
    private String historyGenres;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeaders(){

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(accessToken);

        return headers;
    }

    public TmdbResponse getMovie(Long movieId){

        String url =
                BASE_URL
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

    public TmdbCreditResponse getCredits(Long movieId){

        String url =
                BASE_URL
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

    public TmdbKeywordResponse getKeywords(Long movieId){

        String url =
                BASE_URL
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
     * 한국 역사·전쟁 장르 영화를 인기순으로 조회한다. (자동 수집용)
     * with_origin_country=KR + with_genres=36,10752
     */
    public TmdbSearchResponse discoverKoreanHistory(int page) {

        String url = UriComponentsBuilder
                .fromHttpUrl(DISCOVER_URL)
                .queryParam("with_origin_country", "KR")
                .queryParam("with_genres", historyGenres)
                .queryParam("language", "ko-KR")
                .queryParam("sort_by", "popularity.desc")
                .queryParam("include_adult", false)
                .queryParam("page", page)
                .build()
                .toUriString();

        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<TmdbSearchResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbSearchResponse.class
                );

        return response.getBody();
    }

    public TmdbSearchResponse searchMovies(String query, int page) {

        String url = UriComponentsBuilder
                .fromHttpUrl(SEARCH_URL)
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


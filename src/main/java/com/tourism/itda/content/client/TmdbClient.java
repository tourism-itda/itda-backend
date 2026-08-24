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

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String SEARCH_URL =
            "https://api.themoviedb.org/3/search/movie";

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


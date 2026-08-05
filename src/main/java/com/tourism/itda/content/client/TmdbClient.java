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


@Component
public class TmdbClient {

    @Value("${tmdb.api.access-token}")
    private String accessToken;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";


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


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);


        HttpEntity<Void> entity = new HttpEntity<>(headers);


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


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);


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
}
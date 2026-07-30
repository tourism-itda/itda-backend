package com.tourism.itda.content.client;

import com.tourism.itda.content.dto.TmdbCreditResponseDto;
import com.tourism.itda.content.dto.TmdbKeywordResponseDto;
import com.tourism.itda.content.dto.TmdbResponseDto;
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


    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeaders(){

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(accessToken);

        return headers;
    }

    public TmdbResponseDto getMovie(Long movieId){

        String url =
                "https://api.themoviedb.org/3/movie/"
                        + movieId
                        + "?language=ko-KR";


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);


        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<TmdbResponseDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbResponseDto.class
                );


        return response.getBody();
    }

    public TmdbCreditResponseDto getCredits(Long movieId){

        String url =
                "https://api.themoviedb.org/3/movie/"
                        + movieId
                        + "/credits";


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<TmdbCreditResponseDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbCreditResponseDto.class
                );


        return response.getBody();
    }

    public TmdbKeywordResponseDto getKeywords(Long movieId){

        String url =
                "https://api.themoviedb.org/3/movie/"
                        + movieId
                        + "/keywords";


        HttpEntity<Void> entity =
                new HttpEntity<>(getHeaders());


        ResponseEntity<TmdbKeywordResponseDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        TmdbKeywordResponseDto.class
                );


        return response.getBody();
    }
}
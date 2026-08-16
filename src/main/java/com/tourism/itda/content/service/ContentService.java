package com.tourism.itda.content.service;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.*;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ContentService {

    private final TmdbClient tmdbClient;
    private final ContentRepository contentRepository;

    public ContentService(
            TmdbClient tmdbClient,
            ContentRepository contentRepository
    ) {
        this.tmdbClient = tmdbClient;
        this.contentRepository = contentRepository;
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
    public ContentResponse findContent(Long id) {

        Content content = contentRepository.findById(id)
                .orElseGet(() -> saveContent(id));

        return ContentResponse.from(content);
    }

    public TmdbCreditResponse getCredits(Long movieId) {
        return tmdbClient.getCredits(movieId);
    }

}
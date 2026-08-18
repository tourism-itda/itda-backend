package com.tourism.itda.explore.service;

import com.tourism.itda.explore.dto.KingdomDetailResponse;
import com.tourism.itda.explore.dto.KingdomResponse;
import com.tourism.itda.explore.dto.PersonResponse;
import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KingdomService {

    private final PersonRepository personRepository;

    // 나라 목록
    public List<KingdomResponse> getKingdoms() {

        return Arrays.stream(Kingdom.values())
                .map(kingdom ->
                        new KingdomResponse(
                                kingdom,
                                getKingdomName(kingdom)
                        )
                )
                .toList();
    }

    // 나라 상세
    public KingdomDetailResponse getKingdom(Kingdom kingdom) {

        return new KingdomDetailResponse(
                kingdom,
                getKingdomName(kingdom)
        );
    }

    // 나라별 인물
    public List<PersonResponse> getPersonsByKingdom(
            Kingdom kingdom
    ) {

        return personRepository.findByKingdom(kingdom)
                .stream()
                .map(PersonResponse::new)
                .toList();
    }

    // enum → 실제 표시 이름
    private String getKingdomName(Kingdom kingdom) {

        return switch (kingdom) {

            case GOGURYEO -> "고구려";
            case BAEKJE -> "백제";
            case SILLA -> "신라";
            case GAYA -> "가야";

            case UNIFIED_SILLA -> "통일신라";
            case BALHAE -> "발해";

            case LATER_GOGURYEO -> "후고구려";
            case LATER_BAEKJE -> "후백제";

            case GORYEO -> "고려";
            case JOSEON -> "조선";

            case KOREAN_EMPIRE -> "대한제국";
            case JAPANESE_COLONY -> "일제강점기";
            case FIRST_REPUBLIC_OF_KOREA -> "대한민국 제1공화국";
        };
    }
}
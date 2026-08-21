package com.tourism.itda.global.tourapi;

import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.place.entity.PlaceType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 관광API 분류 코드 매핑.
 *
 * <p>주의: {@code cat3} 만 단독으로 넘기면 필터가 먹지 않는다. {@code cat1}, {@code cat2} 를
 * 계층으로 함께 보내야 한다. 이 클래스가 그 세 개를 한 묶음으로 만들어 준다.
 *
 * <p>식당은 카페를 제외한 나머지를 한 번에 뽑을 방법이 없어서(cat3 는 단일 값만 받는다)
 * contentTypeId=39 로 전체를 받아온 뒤 카페 코드를 코드에서 걸러낸다.
 */
public final class TourApiCategory {

    /** 음식점 (식당 + 카페가 모두 여기 들어있다). */
    public static final String CONTENT_TYPE_FOOD = "39";

    /** 관광지 — 촬영지 보강용. */
    public static final String CONTENT_TYPE_ATTRACTION = "12";

    private static final String CAT1_FOOD = "A05";
    private static final String CAT2_FOOD = "A0502";

    /** 카페/전통찻집. 식당과 카페를 가르는 유일한 기준. */
    public static final String CAT3_CAFE = "A05020900";

    private TourApiCategory() {
    }

    /** 슬롯 타입에 맞는 locationBasedList2 조회 파라미터. */
    public static Map<String, String> queryParamsFor(PlaceType type) {
        Map<String, String> params = new LinkedHashMap<>();
        switch (type) {
            case RESTAURANT -> {
                // 카페까지 같이 딸려온다 — isCafe() 로 코드에서 제외한다.
                params.put("contentTypeId", CONTENT_TYPE_FOOD);
                params.put("cat1", CAT1_FOOD);
                params.put("cat2", CAT2_FOOD);
            }
            case CAFE -> {
                params.put("contentTypeId", CONTENT_TYPE_FOOD);
                params.put("cat1", CAT1_FOOD);
                params.put("cat2", CAT2_FOOD);
                params.put("cat3", CAT3_CAFE);
            }
            case SPOT -> params.put("contentTypeId", CONTENT_TYPE_ATTRACTION);
        }
        return params;
    }

    /** 이 항목이 카페인가. */
    public static boolean isCafe(String cat3) {
        return CAT3_CAFE.equals(cat3);
    }

    /** 응답 항목이 요청한 슬롯 타입에 맞는지 최종 확인. */
    public static boolean matches(PlaceType type, String cat3) {
        return switch (type) {
            case CAFE -> isCafe(cat3);
            case RESTAURANT -> !isCafe(cat3);
            case SPOT -> true;
        };
    }

    /** 관광API contentTypeId → 우리 PlaceType. */
    public static PlaceType toPlaceType(String contentTypeId, String cat3) {
        if (CONTENT_TYPE_FOOD.equals(contentTypeId)) {
            return isCafe(cat3) ? PlaceType.CAFE : PlaceType.RESTAURANT;
        }
        if (CONTENT_TYPE_ATTRACTION.equals(contentTypeId)) {
            return PlaceType.SPOT;
        }
        throw new InvalidRequestException("지원하지 않는 관광API 콘텐츠 타입입니다: " + contentTypeId);
    }
}

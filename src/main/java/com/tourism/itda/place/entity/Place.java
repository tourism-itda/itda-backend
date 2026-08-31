package com.tourism.itda.place.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "text")
    private String description;

    private double latitude;
    private double longitude;

    private String address;
    private String region;

    /** 명세서 응답용 원문. 관광API detailIntro2 의 usetime/opentimefood 를 그대로 담는다. */
    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "kakao_place_id")
    private String kakaoPlaceId;

    // ── 아래는 하루 일정 템플릿을 위해 추가된 컬럼 ────────────────────────────

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type", nullable = false)
    private PlaceType placeType = PlaceType.SPOT;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private PlaceSource source = PlaceSource.SEED;

    /** 관광API contentid. source=TOUR_API 일 때만 채워진다. */
    @Column(name = "external_id")
    private String externalId;

    /** openingHours 파싱 성공 시에만 채워진다. 실패하면 null. */
    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    /**
     * 저녁 이후(기본 20시)에도 방문 가능한가.
     * 파싱에 실패하면 false — 애매하면 밤에 안 넣는 쪽이 안전하다.
     */
    @Column(name = "night_open", nullable = false)
    private boolean nightOpen = false;

    /** 관광API 에서 가져온 식당/카페를 place 에 저장하기 위한 팩토리. */
    public static Place ofTourApi(String externalId,
                                  PlaceType placeType,
                                  String name,
                                  String category,
                                  double latitude,
                                  double longitude,
                                  String address,
                                  String region) {
        Place place = new Place();
        place.externalId = externalId;
        place.source = PlaceSource.TOUR_API;
        place.placeType = placeType;
        place.name = name;
        place.category = category;
        place.latitude = latitude;
        place.longitude = longitude;
        place.address = address;
        place.region = region;
        return place;
    }

    /**
     * detailIntro2 로 뒤늦게 받아온 영업시간을 채운다.
     * 최종 선택된 장소에만 호출한다 — 후보 전부에 부르면 API 호출이 폭발한다.
     */
    public void applyOpeningHours(String raw, LocalTime openTime, LocalTime closeTime, boolean nightOpen) {
        this.openingHours = raw;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.nightOpen = nightOpen;
    }

    /** 관광API 재조회 시 변한 값만 갱신한다 (좌표/주소는 바뀔 수 있다). */
    public void refreshFromTourApi(String name, String category, double latitude, double longitude,
                                   String address, String region) {
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.region = region;
    }

    public static Place ofSeed(
            String name,
            String category,
            String description,
            double latitude,
            double longitude,
            String address,
            String region
    ) {
        Place place = new Place();
        place.source = PlaceSource.SEED;
        place.placeType = PlaceType.SPOT;
        place.name = name;
        place.category = category;
        place.description = description;
        place.latitude = latitude;
        place.longitude = longitude;
        place.address = address;
        place.region = region;
        return place;
    }
}

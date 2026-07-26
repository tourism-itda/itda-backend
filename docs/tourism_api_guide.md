# 관광 API 가이드

## 공통 코드

### arrange (정렬 기준)

| 값 | 설명 |
|----|------|
| A  | 제목순 |
| B  | 조회순 |
| C  | 수정일순 |
| D  | 생성일순 |
| E  | 거리순 (locationBased에서만 유효) |

### contentTypeId (콘텐츠 타입)

| 값  | 설명 |
|-----|------|
| 12  | 관광지 |
| 14  | 문화시설 |
| 15  | 행사/축제 |
| 28  | 레포츠 |
| 32  | 숙박 |
| 38  | 쇼핑 |
| 39  | 음식점 |

---

## API 목록

### 1. 위치기반 관광정보 조회

**공공데이터 API:** `locationBasedList2`
**엔드포인트:** `GET /api/places/location`

**예시 요청 (서울시청 기준 1km 반경)**

```
GET /api/places/location?mapX=126.9783882&mapY=37.5666103&radius=1000&contentTypeId=12&arrange=E&numOfRows=5&pageNo=1
```

**파라미터**

| 파라미터      | 필수 | 예시          | 설명 |
|--------------|------|---------------|------|
| mapX         | O    | 126.9783882   | 경도 |
| mapY         | O    | 37.5666103    | 위도 |
| radius       | O    | 1000          | 반경 (단위: m, 최대 20000) |
| contentTypeId | X   | 12            | 콘텐츠 타입 (생략 시 전체) |
| areaCode     | X    | 1             | 지역코드 |
| sigunguCode  | X    | 24            | 시군구코드 |
| cat1         | X    | A02           | 대분류 |
| cat2         | X    | A0201         | 중분류 |
| cat3         | X    | A02010700     | 소분류 |
| arrange      | X    | E             | 정렬 기준 |
| listYN       | X    | Y             | 목록 구분 (Y: 목록, N: 개수) |
| pageNo       | X    | 1             | 페이지 번호 (기본값: 1) |
| numOfRows    | X    | 10            | 한 페이지 결과 수 (기본값: 10) |

**응답 필드**

| 필드                    | 설명 |
|------------------------|------|
| contentId              | 콘텐츠 ID |
| contentTypeId          | 콘텐츠 타입 ID |
| title                  | 장소명 |
| addr1                  | 주소 |
| addr2                  | 상세 주소 |
| areaCode               | 지역코드 |
| sigunguCode            | 시군구코드 |
| cat1 / cat2 / cat3     | 대/중/소 분류코드 |
| firstImage             | 대표이미지 원본 |
| firstImage2            | 대표이미지 썸네일 |
| cpyrhtDivCd            | 저작권 유형 |
| mapX                   | 경도 |
| mapY                   | 위도 |
| dist                   | 요청 좌표로부터 거리 (m) |
| tel                    | 전화번호 |
| bookTour               | 교과서 속 여행지 여부 |
| createdTime            | 등록일 |
| modifiedTime           | 수정일 |

**예시 응답**

```json
[
  {
    "contentid": "2373204",
    "contenttypeid": "12",
    "title": "경성 부민관 폭탄 의거지",
    "addr1": "서울특별시 중구 세종대로 125 (태평로1가)",
    "addr2": "",
    "areacode": "1",
    "sigungucode": "24",
    "cat1": "A02",
    "cat2": "A0201",
    "cat3": "A02010700",
    "firstimage": "http://tong.visitkorea.or.kr/cms/resource/30/3077530_image2_1.JPG",
    "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/30/3077530_image3_1.JPG",
    "cpyrhtDivCd": "Type3",
    "mapx": "126.9768017026",
    "mapy": "37.5675596526",
    "mlevel": "6",
    "dist": "176.0588713555648",
    "tel": "",
    "booktour": null,
    "createdtime": "20160317011015",
    "modifiedtime": "20250820142318"
  }
]
```

---

### 2. 키워드 검색 조회

**공공데이터 API:** `searchKeyword2`
**엔드포인트:** `GET /api/places/keyword`

**예시 요청**

```
GET /api/places/keyword?keyword=덕수궁&areaCode=1&contentTypeId=12&arrange=A&numOfRows=10&pageNo=1
```

> 한글 키워드를 그대로 입력해도 동작함.

**파라미터**

| 파라미터      | 필수 | 예시      | 설명 |
|--------------|------|-----------|------|
| keyword      | O    | 덕수궁    | 검색 키워드 |
| contentTypeId | X   | 12        | 콘텐츠 타입 (생략 시 전체) |
| areaCode     | X    | 1         | 지역코드 |
| sigunguCode  | X    | 24        | 시군구코드 |
| cat1         | X    | A02       | 대분류 |
| cat2         | X    | A0201     | 중분류 |
| cat3         | X    | A02010700 | 소분류 |
| arrange      | X    | A         | 정렬 기준 |
| listYN       | X    | Y         | 목록 구분 |
| pageNo       | X    | 1         | 페이지 번호 (기본값: 1) |
| numOfRows    | X    | 10        | 한 페이지 결과 수 (기본값: 10) |

**응답 필드**

| 필드                | 설명 |
|--------------------|------|
| contentId          | 콘텐츠 ID |
| contentTypeId      | 콘텐츠 타입 ID |
| title              | 장소명 |
| addr1              | 주소 |
| addr2              | 상세 주소 |
| areaCode           | 지역코드 |
| sigunguCode        | 시군구코드 |
| cat1 / cat2 / cat3 | 대/중/소 분류코드 |
| firstImage         | 대표이미지 원본 |
| firstImage2        | 대표이미지 썸네일 |
| cpyrhtDivCd        | 저작권 유형 |
| mapX               | 경도 |
| mapY               | 위도 |
| tel                | 전화번호 |
| bookTour           | 교과서 속 여행지 여부 |
| createdTime        | 등록일 |
| modifiedTime       | 수정일 |

**예시 응답**

```json
[
  {
    "contentid": "1605981",
    "contenttypeid": "12",
    "title": "덕수궁 대한문",
    "addr1": "서울특별시 중구 세종대로 99 (정동)",
    "addr2": "",
    "areacode": "1",
    "sigungucode": "24",
    "cat1": "A02",
    "cat2": "A0201",
    "cat3": "A02010300",
    "firstimage": "http://tong.visitkorea.or.kr/cms/resource/91/3384991_image2_1.JPG",
    "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/91/3384991_image3_1.JPG",
    "cpyrhtDivCd": "Type3",
    "mapx": "126.9765906796",
    "mapy": "37.5651071556",
    "mlevel": "6",
    "tel": "",
    "booktour": null,
    "createdtime": "20120418205524",
    "modifiedtime": "20250730154607"
  },
  {
    "contentid": "129186",
    "contenttypeid": "12",
    "title": "덕수궁 돌담길",
    "addr1": "서울특별시 중구 세종대로 지하 101",
    "addr2": "",
    "areacode": "1",
    "sigungucode": "24",
    "cat1": "A02",
    "cat2": "A0203",
    "cat3": "A02030600",
    "firstimage": "http://tong.visitkorea.or.kr/cms/resource/50/2658350_image2_1.jpg",
    "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/50/2658350_image3_1.jpg",
    "cpyrhtDivCd": "Type3",
    "mapx": "126.9748935853",
    "mapy": "37.5660820124",
    "mlevel": "6",
    "tel": "",
    "booktour": null,
    "createdtime": "20060727090000",
    "modifiedtime": "20250109134517"
  }
]
```

---

### 3. 행사정보 조회

**공공데이터 API:** `searchFestival2`
**엔드포인트:** `GET /api/places/festivals`

**예시 요청**

```
GET /api/places/festivals?eventStartDate=20250101&areaCode=1&numOfRows=10&pageNo=1
```

**파라미터**

| 파라미터       | 필수 | 예시       | 설명 |
|---------------|------|------------|------|
| eventStartDate | O   | 20250101   | 행사 시작일 (YYYYMMDD) |
| eventEndDate   | X   | 20251231   | 행사 종료일 (YYYYMMDD) |
| areaCode       | X   | 1          | 지역코드 |
| sigunguCode    | X   | 24         | 시군구코드 |
| arrange        | X   | A          | 정렬 기준 |
| listYN         | X   | Y          | 목록 구분 |
| pageNo         | X   | 1          | 페이지 번호 (기본값: 1) |
| numOfRows      | X   | 10         | 한 페이지 결과 수 (기본값: 10) |

**응답 필드**

| 필드              | 설명 |
|------------------|------|
| contentId        | 콘텐츠 ID |
| contentTypeId    | 콘텐츠 타입 ID (항상 15) |
| title            | 행사명 |
| addr1            | 주소 |
| addr2            | 상세 주소 |
| areaCode         | 지역코드 |
| sigunguCode      | 시군구코드 |
| firstImage       | 대표이미지 원본 |
| firstImage2      | 대표이미지 썸네일 |
| mapX             | 경도 |
| mapY             | 위도 |
| eventStartDate   | 행사 시작일 |
| eventEndDate     | 행사 종료일 |
| sponsor1         | 주최자 |
| sponsor1Tel      | 주최자 연락처 |
| sponsor2         | 주관사 |
| sponsor2Tel      | 주관사 연락처 |
| tel              | 전화번호 |
| createdTime      | 등록일 |
| modifiedTime     | 수정일 |

**예시 응답**

```json
[
  {
    "contentid": "3439947",
    "contenttypeid": "15",
    "title": "강남 미디어 윈터페스타",
    "addr1": "서울특별시 강남구 영동대로 511 (삼성동)",
    "addr2": "",
    "areacode": "1",
    "sigungucode": "1",
    "cat1": "A02",
    "cat2": "A0207",
    "cat3": "A02070200",
    "firstimage": "http://tong.visitkorea.or.kr/cms/resource/54/3579654_image2_1.jpg",
    "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/54/3579654_image3_1.jpg",
    "cpyrhtDivCd": "Type3",
    "mapx": "127.0610512042",
    "mapy": "37.5103955843",
    "mlevel": "6",
    "tel": "02-6000-0114",
    "booktour": null,
    "createdtime": "20241209134305",
    "modifiedtime": "20251208174342",
    "eventstartdate": "20251219",
    "eventenddate": "20260103",
    "sponsor1": null,
    "sponsor1tel": null,
    "sponsor2": null,
    "sponsor2tel": null
  }
]
```

---

### 4. 공통정보 조회

**공공데이터 API:** `detailCommon2`
**엔드포인트:** `GET /api/places/common`

**예시 요청**

```
GET /api/places/common?contentId=1605981
```

**파라미터**

| 파라미터      | 필수 | 예시    | 설명 |
|--------------|------|---------|------|
| contentId    | O    | 1605981 | 콘텐츠 ID |
| contentTypeId | X   | 12      | 콘텐츠 타입 |
| defaultYN    | X    | Y       | 기본정보 조회 여부 |
| firstImageYN | X    | Y       | 대표이미지 조회 여부 |
| areaInfoYN   | X    | Y       | 지역정보 조회 여부 |
| addrInfoYN   | X    | Y       | 주소정보 조회 여부 |
| mapInfoYN    | X    | Y       | 좌표정보 조회 여부 |
| overviewYN   | X    | Y       | 개요정보 조회 여부 |

**응답 필드**

| 필드              | 설명 |
|------------------|------|
| contentId        | 콘텐츠 ID |
| contentTypeId    | 콘텐츠 타입 ID |
| title            | 장소명 |
| tel              | 전화번호 |
| telName          | 전화번호 명칭 |
| homepage         | 홈페이지 URL |
| firstImage       | 대표이미지 원본 |
| firstImage2      | 대표이미지 썸네일 |
| cpyrhtDivCd      | 저작권 유형 |
| areaCode         | 지역코드 |
| sigunguCode      | 시군구코드 |
| cat1 / cat2 / cat3 | 대/중/소 분류코드 |
| addr1            | 주소 |
| addr2            | 상세 주소 |
| zipCode          | 우편번호 |
| mapX             | 경도 |
| mapY             | 위도 |
| mLevel           | 지도 레벨 |
| overview         | 장소 개요 (상세 설명) |
| bookTour         | 교과서 속 여행지 여부 |
| createdTime      | 등록일 |
| modifiedTime     | 수정일 |

**예시 응답**

```json
{
  "contentid": "2373204",
  "contenttypeid": "12",
  "title": "경성 부민관 폭탄 의거지",
  "tel": "",
  "telname": "",
  "homepage": "<a href=\"http://mfis.mpva.go.kr\" target=\"_blank\">http://mfis.mpva.go.kr</a>",
  "booktour": null,
  "firstimage": "http://tong.visitkorea.or.kr/cms/resource/30/3077530_image2_1.JPG",
  "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/30/3077530_image3_1.JPG",
  "cpyrhtDivCd": "Type3",
  "areacode": "1",
  "sigungucode": "24",
  "cat1": "A02",
  "cat2": "A0201",
  "cat3": "A02010700",
  "addr1": "서울특별시 중구 세종대로 125 (태평로1가)",
  "addr2": "",
  "zipcode": "04519",
  "mapx": "126.9768017026",
  "mapy": "37.5675596526",
  "mlevel": "6",
  "overview": "경성 부민관 폭탄 의거지는 경성부민관에서 친일파가 주도하는 대회를 방해하기 위해 폭탄을 터트린 곳이다...",
  "createdtime": "20160317011015",
  "modifiedtime": "20250820142318"
}
```

---

### 5. 소개정보 조회

**공공데이터 API:** `detailIntro2`
**엔드포인트:** `GET /api/places/intro`

> contentTypeId에 따라 응답 필드가 달라짐. 해당하지 않는 타입의 필드는 null로 반환.

**예시 요청**

```
GET /api/places/intro?contentId=1605981&contentTypeId=12
```

**파라미터**

| 파라미터      | 필수 | 예시    | 설명 |
|--------------|------|---------|------|
| contentId    | O    | 1605981 | 콘텐츠 ID |
| contentTypeId | O   | 12      | 콘텐츠 타입 |

**관광지 (12)**

| 필드               | 설명 |
|-------------------|------|
| infoCenter        | 문의 및 안내 |
| restDate          | 쉬는 날 |
| useTime           | 이용 시간 |
| parking           | 주차 정보 |
| openDate          | 개장일 |
| expGuide          | 체험 안내 |
| expAgeRange       | 체험 가능 연령 |
| accomCount        | 수용 인원 |
| accomCountParking | 주차 수용 인원 |
| parkingFee        | 주차 요금 |
| chkBabyCarriage   | 유모차 대여 여부 |
| chkPet            | 애완동물 동반 여부 |
| chkCreditCard     | 신용카드 가능 여부 |
| heritage1         | 세계문화유산 여부 |
| heritage2         | 세계자연유산 여부 |
| heritage3         | 세계기록유산 여부 |
| useSeason         | 이용 계절 |

**문화시설 (14)**

| 필드                   | 설명 |
|-----------------------|------|
| infoCenterCulture     | 문의 및 안내 |
| restDateCulture       | 쉬는 날 |
| useTimeCulture        | 이용 시간 |
| parkingCulture        | 주차 정보 |
| parkingFeeCulture     | 주차 요금 |
| chkBabyCarriageCulture | 유모차 대여 여부 |
| chkPetCulture         | 애완동물 동반 여부 |
| chkCreditCardCulture  | 신용카드 가능 여부 |
| spendTime             | 관람 소요 시간 |
| discount              | 할인 정보 |
| scale                 | 규모 |
| museum                | 박물관 여부 |
| gallery               | 미술관 여부 |
| accomCountCulture     | 수용 인원 |
| openDate              | 개장일 |

**행사/공연/축제 (15)**

| 필드                  | 설명 |
|----------------------|------|
| eventStartDate       | 행사 시작일 |
| eventEndDate         | 행사 종료일 |
| sponsor1             | 주최자 |
| sponsor1Tel          | 주최자 연락처 |
| sponsor2             | 주관사 |
| sponsor2Tel          | 주관사 연락처 |
| eventPlace           | 행사 장소 |
| playTime             | 공연 시간 |
| useTimeFestival      | 이용 요금 및 시간 |
| spendTimeFestival    | 관람 소요 시간 |
| discountInfoFestival | 할인 정보 |
| bookingPlace         | 예매처 |
| subEvent             | 부대행사 |
| program              | 행사 프로그램 |
| ageLimit             | 관람 가능 연령 |
| eventHomepage        | 행사 홈페이지 |

**레포츠 (28)**

| 필드                    | 설명 |
|------------------------|------|
| infoCenterLeports      | 문의 및 안내 |
| restDateLeports        | 쉬는 날 |
| useAgeLeports          | 이용 가능 연령 |
| useFeeLeports          | 입장료 |
| openPeriod             | 개장 기간 |
| parkingLeports         | 주차 정보 |
| parkingFeeLeports      | 주차 요금 |
| chkBabyCarriageLeports | 유모차 대여 여부 |
| chkPetLeports          | 애완동물 동반 여부 |
| chkCreditCardLeports   | 신용카드 가능 여부 |
| reservation            | 예약 안내 |
| scale                  | 규모 |
| accomCountLeports      | 수용 인원 |
| expAgeRangeLeports     | 체험 가능 연령 |

**숙박 (32)**

| 필드               | 설명 |
|-------------------|------|
| checkIn           | 체크인 시간 |
| checkOut          | 체크아웃 시간 |
| infoCenterLodging | 문의 및 안내 |
| parkingLodging    | 주차 정보 |
| pickupService     | 픽업 서비스 여부 |
| foodPlace         | 식음료장 |
| reservationLodging | 예약 안내 |
| reservationUrl    | 예약 URL |
| roomType          | 객실 유형 |
| scaleLodging      | 규모 |
| subFacility       | 부대시설 |
| accomCountLodging | 수용 인원 |
| chkCooking        | 취사 가능 여부 |
| refundRegulation  | 환불 규정 |
| benikia           | 베니키아 여부 |
| goodStay          | 굿스테이 여부 |
| hanok             | 한옥 여부 |
| theme             | 테마 |
| barbecue          | 바베큐 시설 여부 |
| beauty            | 뷰티 시설 여부 |
| beverage          | 음료 시설 여부 |
| bicycle           | 자전거 대여 여부 |
| campfire          | 캠프파이어 여부 |
| fitness           | 피트니스 여부 |
| karaoke           | 노래방 여부 |
| publicBath        | 공용 욕실 여부 |
| publicPc          | 공용 PC 여부 |
| sauna             | 사우나 여부 |
| seminar           | 세미나실 여부 |
| sports            | 스포츠 시설 여부 |

**쇼핑 (38)**

| 필드                    | 설명 |
|------------------------|------|
| restDateShopping       | 쉬는 날 |
| openTime               | 영업 시간 |
| parkingShopping        | 주차 정보 |
| chkBabyCarriageShopping | 유모차 대여 여부 |
| chkPetShopping         | 애완동물 동반 여부 |
| chkCreditCardShopping  | 신용카드 가능 여부 |
| sellItem               | 판매 품목 |
| saleItem               | 주요 품목 |
| fairDay                | 장날 |
| shopGuide              | 매장 안내 |
| scale                  | 규모 |

**음식점 (39)**

| 필드                | 설명 |
|--------------------|------|
| openTimeFood       | 영업 시간 |
| restDateFood       | 쉬는 날 |
| parkingFood        | 주차 정보 |
| chkBabyCarriageFood | 유모차 대여 여부 |
| chkPetFood         | 애완동물 동반 여부 |
| chkCreditCardFood  | 신용카드 가능 여부 |
| firstMenu          | 대표 메뉴 |
| treatMenu          | 취급 메뉴 |
| lcnsNo             | 인허가번호 |
| smoking            | 흡연 여부 |
| seat               | 좌석 수 |
| kidsFacility       | 어린이 놀이방 여부 |
| packing            | 포장 가능 여부 |
| infoCenterFood     | 문의 및 안내 |
| reservationFood    | 예약 안내 |

**예시 응답 (contentTypeId=12, 관광지)**

```json
{
  "contentid": "2373204",
  "contenttypeid": "12",
  "infocenter": "중구청 문화재관리팀 02-3396-5842",
  "restdate": "매주 토요일~일요일 / 법정공휴일",
  "usetime": "상시 개방",
  "parking": "불가능",
  "expguide": "",
  "heritage1": "0",
  "heritage2": "0",
  "heritage3": "0",
  "accomcount": "",
  "useseason": "",
  "opendate": "",
  "expagerange": "",
  "accomcountparking": null,
  "parkingfee": null,
  "chkbabycarriage": "",
  "chkpet": "",
  "chkcreditcard": ""
}
```

> 해당하지 않는 타입의 필드(문화시설, 행사, 숙박 등)는 모두 null로 반환됨.

---

### 6. 이미지정보 조회

**공공데이터 API:** `detailImage2`
**엔드포인트:** `GET /api/places/images`

**예시 요청**

```
GET /api/places/images?contentId=1605981&imageYN=Y&subImageYN=Y&numOfRows=10&pageNo=1
```

**파라미터**

| 파라미터    | 필수 | 예시    | 설명 |
|------------|------|---------|------|
| contentId  | O    | 1605981 | 콘텐츠 ID |
| imageYN    | X    | Y       | 이미지 조회 여부 |
| subImageYN | X    | Y       | 서브 이미지 조회 여부 |
| pageNo     | X    | 1       | 페이지 번호 (기본값: 1) |
| numOfRows  | X    | 10      | 한 페이지 결과 수 (기본값: 10) |

**응답 필드**

| 필드          | 설명 |
|--------------|------|
| contentId    | 콘텐츠 ID |
| originImgUrl | 원본 이미지 URL |
| imgName      | 이미지명 |
| smallImageUrl | 썸네일 이미지 URL |
| cpyrhtDivCd  | 저작권 유형 |
| serialNum    | 이미지 일련번호 |

**예시 응답**

```json
[
  {
    "contentid": "2373204",
    "originimgurl": "http://tong.visitkorea.or.kr/cms/resource/31/3077531_image2_1.JPG",
    "imgname": "경성 부민관 폭탄 의거지 (2)",
    "smallimageurl": "http://tong.visitkorea.or.kr/cms/resource/31/3077531_image3_1.JPG",
    "cpyrhtDivCd": "Type3",
    "serialnum": "3077531_1"
  },
  {
    "contentid": "2373204",
    "originimgurl": "http://tong.visitkorea.or.kr/cms/resource/32/3077532_image2_1.JPG",
    "imgname": "경성 부민관 폭탄 의거지 (3)",
    "smallimageurl": "http://tong.visitkorea.or.kr/cms/resource/32/3077532_image3_1.JPG",
    "cpyrhtDivCd": "Type3",
    "serialnum": "3077532_4"
  }
]
```

---

### 7. 관광정보 동기화 목록 조회

**공공데이터 API:** `areaBasedSyncList2`
**엔드포인트:** `GET /api/places/sync`

**예시 요청 (서울 전체)**

```
GET /api/places/sync?areaCode=1&contentTypeId=12&arrange=C&numOfRows=10&pageNo=1
```

**파라미터**

| 파라미터      | 필수 | 예시           | 설명 |
|--------------|------|----------------|------|
| areaCode     | X    | 1              | 지역코드 |
| sigunguCode  | X    | 24             | 시군구코드 |
| contentTypeId | X   | 12             | 콘텐츠 타입 (생략 시 전체) |
| cat1         | X    | A02            | 대분류 |
| cat2         | X    | A0201          | 중분류 |
| cat3         | X    | A02010700      | 소분류 |
| arrange      | X    | C              | 정렬 기준 |
| listYN       | X    | Y              | 목록 구분 |
| modifiedTime | X    | 20250101000000 | 해당 일시 이후 수정된 데이터만 조회 (YYYYMMDDHHmmss) |
| pageNo       | X    | 1              | 페이지 번호 (기본값: 1) |
| numOfRows    | X    | 10             | 한 페이지 결과 수 (기본값: 10) |

**응답 필드**

| 필드              | 설명 |
|------------------|------|
| contentId        | 콘텐츠 ID |
| contentTypeId    | 콘텐츠 타입 ID |
| title            | 장소명 |
| addr1            | 주소 |
| addr2            | 상세 주소 |
| areaCode         | 지역코드 |
| sigunguCode      | 시군구코드 |
| cat1 / cat2 / cat3 | 대/중/소 분류코드 |
| firstImage       | 대표이미지 원본 |
| firstImage2      | 대표이미지 썸네일 |
| cpyrhtDivCd      | 저작권 유형 |
| mapX             | 경도 |
| mapY             | 위도 |
| tel              | 전화번호 |
| bookTour         | 교과서 속 여행지 여부 |
| showFlag         | 게시 여부 |
| createdTime      | 등록일 |
| modifiedTime     | 수정일 |

---

### 8. 키워드 검색 연관 관광지 조회

**공공데이터 API:** `TarRlteTarService1 / searchKeyword1`
**엔드포인트:** `GET /api/places/related/keyword`

> KorService2와 별개 서비스. `areaCd`는 **법정동 시도코드** 기준 (KorService2의 areaCode와 다름).

**예시 요청**

```
GET /api/places/related/keyword?keyword=뮤지엄산&areaCd=51&signguCd=51130&baseYm=202503&numOfRows=10&pageNo=1
```

**파라미터**

| 파라미터  | 필수 | 예시    | 설명 |
|----------|------|---------|------|
| keyword  | O    | 뮤지엄산 | 관광지명 키워드 |
| areaCd   | O    | 51      | 법정동 시도코드 (아래 코드표 참고) |
| signguCd | X    | 51130   | 시군구코드 |
| baseYm   | X    | 202503  | 기준 날짜 (YYYYMM, 생략 시 최신) |
| pageNo   | X    | 1       | 페이지 번호 (기본값: 1) |
| numOfRows | X   | 10      | 한 페이지 결과 수 (기본값: 10) |

**areaCd (법정동 시도코드)**

| areaCd | 지역 |
|--------|------|
| 11     | 서울특별시 |
| 26     | 부산광역시 |
| 27     | 대구광역시 |
| 28     | 인천광역시 |
| 29     | 광주광역시 |
| 30     | 대전광역시 |
| 31     | 울산광역시 |
| 36     | 세종특별자치시 |
| 41     | 경기도 |
| 43     | 충청북도 |
| 44     | 충청남도 |
| 45     | 전라북도 |
| 46     | 전라남도 |
| 47     | 경상북도 |
| 48     | 경상남도 |
| 50     | 제주특별자치도 |
| 51     | 강원특별자치도 |

**응답 필드**

| 필드              | 설명 |
|------------------|------|
| baseYm           | 기준 날짜 (YYYYMM) |
| tAtsCd           | 검색 관광지 코드 |
| tAtsNm           | 검색 관광지명 |
| areaCd           | 검색 관광지 지역코드 |
| areaNm           | 검색 관광지 지역명 |
| signguCd         | 검색 관광지 시군구코드 |
| signguNm         | 검색 관광지 시군구명 |
| rlteTatsCd       | 연관 관광지 코드 |
| rlteTatsNm       | 연관 관광지명 |
| rlteRegnCd       | 연관 관광지 지역코드 |
| rlteRegnNm       | 연관 관광지 지역명 |
| rlteSignguCd     | 연관 관광지 시군구코드 |
| rlteSignguNm     | 연관 관광지 시군구명 |
| rlteCtgryLclsNm  | 연관 관광지 대분류 |
| rlteCtgryMclsNm  | 연관 관광지 중분류 |
| rlteCtgrySclsNm  | 연관 관광지 소분류 |
| rlteRank         | 연관 순위 |

**예시 응답**

```json
[
  {
    "baseYm": "202503",
    "tAtsCd": "0bfeca2105aa7bf8d83e4622e5da19ec",
    "tAtsNm": "뮤지엄산",
    "areaCd": "51",
    "areaNm": "강원특별자치도",
    "signguCd": "51130",
    "signguNm": "원주시",
    "rlteTatsCd": "85f72636fc5aa3de9bcabe7b39daa546",
    "rlteTatsNm": "스톤크릭",
    "rlteRegnCd": "51",
    "rlteRegnNm": "강원특별자치도",
    "rlteSignguCd": "51130",
    "rlteSignguNm": "원주시",
    "rlteCtgryLclsNm": "음식",
    "rlteCtgryMclsNm": "음식",
    "rlteCtgrySclsNm": "카페/찻집",
    "rlteRank": "1"
  },
  {
    "baseYm": "202503",
    "tAtsCd": "0bfeca2105aa7bf8d83e4622e5da19ec",
    "tAtsNm": "뮤지엄산",
    "areaCd": "51",
    "areaNm": "강원특별자치도",
    "signguCd": "51130",
    "signguNm": "원주시",
    "rlteTatsCd": "31c13fce36918d9c6bab361f0fd20cc7",
    "rlteTatsNm": "황금들밥/오크밸리월송점",
    "rlteRegnCd": "51",
    "rlteRegnNm": "강원특별자치도",
    "rlteSignguCd": "51130",
    "rlteSignguNm": "원주시",
    "rlteCtgryLclsNm": "음식",
    "rlteCtgryMclsNm": "음식",
    "rlteCtgrySclsNm": "한식",
    "rlteRank": "2"
  }
]
```

---

## 일반적인 호출 플로우

**위치 기반 탐색**

```
GET /api/places/location?mapX=...&mapY=...&radius=...
  └─ contentId 획득
       ├─ GET /api/places/common?contentId={contentId}
       ├─ GET /api/places/intro?contentId={contentId}&contentTypeId={contentTypeId}
       └─ GET /api/places/images?contentId={contentId}
```

**키워드 탐색**

```
GET /api/places/keyword?keyword=덕수궁&areaCode=1
  └─ contentId 획득
       ├─ GET /api/places/common?contentId={contentId}
       ├─ GET /api/places/intro?contentId={contentId}&contentTypeId={contentTypeId}
       └─ GET /api/places/images?contentId={contentId}
```

# 잇다(Itda) — 장소·일정 파트 API 변경사항 (프론트 전달용)

> 작성: 권승훈 (No.25~32 담당) / 브랜치 `feat/place-itinerary`
> 기준: 명세서 `잇다_API명세서_v4.xlsx`
> 최종 갱신: 2026-08-20

---

## 0. 한 줄 요약

**기존 8개 API(No.25~32)의 요청/응답 스펙은 하나도 바뀌지 않았습니다.**
대신 "하루 루트 만들기" 화면을 위해 **신규 엔드포인트 4개**가 추가됐고, 이 4개가 기존 `POST /itineraries`(No.28) 저장으로 연결되는 **새 흐름**을 만듭니다.

⚠️ 신규 4개는 **명세서 v4에 없는 것**입니다. 팀·프론트 합의가 필요합니다.

---

## 1. 무엇이 바뀌었나 (변경 요약표)

| 구분 | 항목 | 상태 |
|---|---|---|
| 기존 API | No.25~32 요청/응답 필드 | **변경 없음** ✅ |
| 신규 API | `GET /contents/{content_id}/places` | 추가 |
| 신규 API | `POST /itineraries/route` | 추가 |
| 신규 API | `GET /itineraries/route/candidates` | 추가 |
| 신규 API | `POST /places/import` | 추가 |
| DB | `place` 테이블에 컬럼 6개 추가 | 추가 (기존 응답에는 **노출 안 함**) |
| 공통 | 에러 포맷 `{code, message}` | 변경 없음 |
| 공통 | JSON snake_case | 변경 없음 |

### `place` 테이블 추가 컬럼 (참고용, 기존 응답에는 안 나감)
`place_type`, `source`, `external_id`, `open_time`, `close_time`, `night_open`

> `GET /places/{id}`(No.25) 응답은 **예전 그대로**입니다. 위 컬럼들은 신규 API 응답에서만 일부(`place_type`, `night_open`) 나갑니다.

---

## 2. 새 흐름 (화면 순서대로)

```
① 콘텐츠 상세
   └─ GET /contents/{content_id}/places      ← 촬영지 목록 (직접선택 화면). 건너뛰어도 됨
             │
② 루트 생성 (미리보기, DB 저장 X)
   └─ POST /itineraries/route                ← 슬롯 6칸 + 지도 타원(segments) 반환
             │                                  촬영지 칸만 채워짐 / 식당·카페 칸은 비어 있음
③ 빈 칸 채우기 (칸마다 반복)
   └─ GET /itineraries/route/candidates      ← 그 구간의 식당/카페 후보 (지도 핀)
             │
④ 사용자가 후보 하나 선택
   └─ POST /places/import                    ← place_id 발급 (⚠️ 저장 전 필수 단계)
             │
⑤ 저장
   └─ POST /itineraries                      ← 기존 No.28 그대로. place_id 들을 넘김
```

**핵심**: ②에서 식당/카페는 **서버가 안 채웁니다.** 취향 편차가 커서 사용자가 지도에서 직접 고르는 게 팀 결정이었습니다.

---

## 3. 신규 API 상세

공통: 인증 **불필요** (현재 기준), 응답 JSON은 snake_case, 에러는 `{ "code": "...", "message": "..." }`.

### 3-1. `GET /contents/{content_id}/places` — 콘텐츠의 촬영지 전체 목록

직접선택 화면에 뿌릴 목록입니다. `recommend_order` 오름차순.

**응답** `200 OK` — 배열

```json
[
  {
    "place_id": 8,
    "name": "경복궁",
    "category": "고궁",
    "description": "조선의 법궁, 수문장 교대식",
    "image_url": "https://.../gyeongbokgung.jpg",
    "opening_hours": "09:00~18:00(화휴무)",
    "night_open": false,
    "latitude": 37.579617,
    "longitude": 126.977041,
    "recommend_order": 1
  }
]
```

| 필드 | 설명 |
|---|---|
| `night_open` | 저녁(20시) 이후에도 방문 가능한가. 하루 마지막 촬영지 배치에 쓰임 |
| `recommend_order` | 콘텐츠 안에서의 추천 순위 |

**에러**: `404 NOT_FOUND` — 이 콘텐츠에 등록된 촬영지가 없음

---

### 3-2. `POST /itineraries/route` — 하루 루트 생성 (미리보기, **DB 저장 안 함**)

**요청 바디**

```json
{
  "content_id": 2,
  "spot_place_ids": [8, 12],
  "allowance_meters": 2000
}
```

| 필드 | 필수 | 설명 |
|---|:---:|---|
| `content_id` | ✅ | 콘텐츠 ID |
| `spot_place_ids` | ❌ | 사용자가 "꼭 가고 싶다"고 고른 촬영지. **최대 3개** |
| `allowance_meters` | ❌ | 동선이 몇 m 늘어나도 되는지 (타원 두께). 생략 시 **3000** |

**두 모드가 한 API로 처리됩니다:**
- `spot_place_ids` 를 **비우면** → 자동추천 (서버가 3곳 선택)
- `spot_place_ids` 를 **채우면** → 직접선택. 3개보다 적으면 나머지를 서버가 채움

**서버의 방어 동작 (프론트에서도 막아주세요)**
- 3개를 넘겨 보내면 → **앞에서부터 3개만** 사용
- 이 콘텐츠의 촬영지가 아닌 `place_id` → **조용히 무시**
- 중복 id → 하나로 취급

**응답** `200 OK`

```json
{
  "content_id": 2,
  "content_title": "왕이 된 남자",
  "region": "서울",
  "spot_count": 3,
  "allowance_meters": 2000,
  "slots": [
    {
      "visit_order": 0,
      "slot_type": "SPOT",
      "label": "촬영지",
      "estimated_time": "10:00",
      "filled_by": "USER",
      "place": {
        "place_id": 8,
        "place_type": "SPOT",
        "name": "경복궁",
        "category": "고궁",
        "address": "서울 종로구 사직로 161",
        "image_url": "https://.../gyeongbokgung.jpg",
        "opening_hours": "09:00~18:00(화휴무)",
        "night_open": false,
        "latitude": 37.579617,
        "longitude": 126.977041
      }
    },
    {
      "visit_order": 1,
      "slot_type": "RESTAURANT",
      "label": "점심",
      "estimated_time": "12:05",
      "filled_by": "EMPTY",
      "segment_index": 0
    }
  ],
  "segments": [
    {
      "segment_index": 0,
      "start_place_id": 8,
      "end_place_id": 12,
      "start_latitude": 37.579617,
      "start_longitude": 126.977041,
      "end_latitude": 37.574040,
      "end_longitude": 126.985620,
      "direct_distance_m": 940,
      "allowance_meters": 2000,
      "partial_coverage": false,
      "slot_orders": [1, 2]
    }
  ]
}
```

#### `slots[]` 필드

| 필드 | 설명 |
|---|---|
| `visit_order` | **0부터** 시작하는 방문 순서 |
| `slot_type` | `SPOT` / `RESTAURANT` / `CAFE` |
| `label` | 표시용 한글: `"촬영지"` / `"점심"` / `"카페"` / `"저녁"` |
| `estimated_time` | 예상 도착 시각. **표시용 추정치** (순서를 정하는 근거 아님) |
| `place` | 채워진 칸만 존재. **빈 칸이면 필드 자체가 없음** ⚠️ |
| `filled_by` | `USER` / `CURATED` / `SCORED` / `EMPTY` |
| `reason` | 자동 선택된 촬영지에만 존재. 없으면 **필드 자체가 없음** ⚠️ |
| `segment_index` | **빈 칸에만 존재.** 후보 조회 API에 그대로 넘기면 됨 ⚠️ |

`filled_by` 값 의미 (뱃지로 구분 가능):

| 값 | 의미 |
|---|---|
| `USER` | 사용자가 직접 고른 촬영지 |
| `CURATED` | 점수 + Claude 큐레이션으로 채움 |
| `SCORED` | 점수만으로 채움 (Claude 미사용/폴백) |
| `EMPTY` | 아직 비어 있음 → 사용자가 후보에서 고를 자리 |

#### `segments[]` 필드 — 지도에 그릴 **타원 하나**

| 필드 | 설명 |
|---|---|
| `segment_index` | 구간 번호 |
| `start_place_id` / `end_place_id` | 앞·뒤 앵커 촬영지. **`end_place_id`는 하루 마지막 구간이면 없음** ⚠️ |
| `start_latitude/longitude` | 타원의 초점 1 |
| `end_latitude/longitude` | 타원의 초점 2. 마지막 구간이면 **없음** ⚠️ |
| `direct_distance_m` | 두 앵커 사이 직선거리(m) = 초점 간 거리 |
| `allowance_meters` | 허용거리(m) = 타원의 '두께' |
| `partial_coverage` | `true`면 두 촬영지가 너무 멀어(합계 40km 초과) **앵커 주변만 검색**했다는 뜻. 구간 중앙부 가게는 후보에 없음 → UI 안내 권장 |
| `slot_orders` | 이 구간에서 채워야 할 슬롯들의 `visit_order` |

**에러**: `404 NOT_FOUND` (콘텐츠 없음 / 촬영지 없음)

---

### 3-3. `GET /itineraries/route/candidates` — 구간별 식당/카페 후보

**쿼리 파라미터**

| 이름 | 필수 | 설명 |
|---|:---:|---|
| `start_place_id` | ✅ | 앞쪽 앵커 (`segments[].start_place_id`) |
| `end_place_id` | ❌ | 뒤쪽 앵커. **없으면** 앵커 주변을 가까운 순으로 반환 |
| `slot_type` | ✅ | `RESTAURANT` 또는 `CAFE` (`SPOT` 넣으면 400) |
| `allowance_meters` | ❌ | 허용거리(m). 생략 시 3000, 상한 20000 (넘으면 잘림) |
| `exclude_external_ids` | ❌ | 이미 보여준 후보들. **"다른 곳 추천" 재조회용**. 반복 지정 가능 |

```
GET /itineraries/route/candidates?start_place_id=8&end_place_id=12&slot_type=RESTAURANT&allowance_meters=2000
GET /itineraries/route/candidates?start_place_id=8&slot_type=RESTAURANT&exclude_external_ids=126508&exclude_external_ids=2649619
```

> **서버가 루트 상태를 안 들고 있습니다.** 사용자가 허용거리 슬라이더를 움직이면 `allowance_meters`만 바꿔 같은 요청을 다시 부르면 됩니다.

**응답** `200 OK`

```json
{
  "slot_type": "RESTAURANT",
  "allowance_meters": 2000,
  "partial_coverage": false,
  "candidates": [
    {
      "external_id": "126508",
      "place_type": "RESTAURANT",
      "name": "토속촌삼계탕",
      "category": "한식",
      "address": "서울 종로구 자하문로5길 5",
      "image_url": "https://.../img.jpg",
      "latitude": 37.578400,
      "longitude": 126.971900,
      "detour_meters": 320,
      "detour_known": true
    }
  ]
}
```

| 필드 | 설명 |
|---|---|
| `external_id` | ⚠️ **`place_id`가 아닙니다.** 관광API contentid. 아직 DB에 저장 전 |
| `detour_meters` | 이곳을 들르면 동선이 늘어나는 거리(m). **오름차순 정렬** |
| `detour_known` | `false`면 `detour_meters`는 우회거리가 아니라 **앵커로부터의 거리**입니다. 이때 "동선 +N m"로 표시하면 안 됩니다 (마지막 구간엔 뒤 앵커가 없어 우회거리를 정의할 수 없음) |
| `allowance_meters` | **실제 적용된** 값. 요청이 상한을 넘으면 잘려서 반영됨 |

**⚠️ `candidates`는 빈 배열일 수 있습니다 — 에러가 아닙니다.**
관광공사에 등록된 업소가 반경 안에 없는 지역이 실제로 있고, 서버에 `TOUR_API_KEY`가 없어도 빈 배열이 옵니다.

**에러**
- `400 INVALID_REQUEST` — `slot_type=SPOT`
- `404 NOT_FOUND` — 앵커 place_id 없음

---

### 3-4. `POST /places/import` — 고른 후보를 place로 확정 (⚠️ 저장 전 필수)

후보는 `place_id`가 없기 때문에, 사용자가 하나를 고른 시점에 이 API로 **place_id를 발급**받아야 `POST /itineraries`(No.28)에 넣을 수 있습니다.

**요청 바디**

```json
{ "external_id": "126508", "place_type": "RESTAURANT" }
```

> 좌표·이름을 클라이언트에서 받지 않는 것은 **의도적**입니다. 서버가 `external_id`로 관광API를 다시 조회해 저장합니다.

**응답** `200 OK` — `slots[].place`와 **똑같은 모양**

```json
{
  "place_id": 101,
  "place_type": "RESTAURANT",
  "name": "토속촌삼계탕",
  "category": "한식",
  "address": "서울 종로구 자하문로5길 5",
  "opening_hours": "10:00~22:00",
  "night_open": true,
  "latitude": 37.578400,
  "longitude": 126.971900
}
```

- 이미 저장된 곳이면 **기존 행을 재사용**합니다 (중복 저장 안 됨).
- `image_url`은 여기선 `null` → **필드가 나오지 않습니다.** 후보 응답의 `image_url`을 프론트가 들고 있다가 쓰세요.
- 영업시간은 이 시점에 한 번만 채워집니다.

**에러**: `404 NOT_FOUND` — 관광API에 해당 contentid 없음

---

## 4. 프론트가 특히 조심할 것 ⚠️

### 4-0. 경로에 `/api` 접두사가 **없습니다**

| | |
|---|---|
| Base URL | `http://localhost:8080` |
| 올바른 호출 | `GET /places/8` → `200` |
| 잘못된 호출 | `GET /api/places/8` → `404` |

서버에 `context-path` 설정이 없습니다. 명세서 v4에 적힌 경로(`/places/:place_id`, `/itineraries`, …) **그대로** 호출하세요. 팀원 4명의 엔드포인트가 전부 접두사 없이 구현돼 있습니다.

⚠️ 이때 나오는 404는 `{"code":"NOT_FOUND","message":"요청하신 경로를 찾을 수 없습니다."}` 라서 **"엔드포인트가 미구현"처럼 보입니다.** 메시지를 구분해 주세요:
- `"요청하신 경로를 찾을 수 없습니다."` → **경로가 틀림** (오타 / `/api` 접두사)
- `"장소를 찾을 수 없습니다."` → 경로는 맞고 **데이터가 없음**

### 4-1. `null` 필드는 응답에서 **아예 빠집니다**
서버 설정이 `default-property-inclusion: non_null` 입니다. 즉:

| 상황 | 결과 |
|---|---|
| 빈 슬롯 | `"place": null`이 아니라 **`place` 키 자체가 없음** |
| 채워진 슬롯 | **`segment_index` 키가 없음** |
| Claude 미사용 | **`reason` 키가 없음** |
| 마지막 구간 | **`end_place_id`, `end_latitude`, `end_longitude` 키가 없음** |

→ `data.place?.name` 처럼 optional 접근으로 처리해 주세요. 빈 칸 판별은 **`filled_by === "EMPTY"`** 로 하는 게 가장 안전합니다.

### 4-2. `external_id` ≠ `place_id`
후보 목록에서 오는 건 `external_id`(문자열)입니다. 일정 저장에는 못 씁니다. 반드시 `POST /places/import`를 거쳐 `place_id`(숫자)를 받으세요.

### 4-3. `detour_known: false` 면 "동선 +N m" 표시 금지
그 값은 우회거리가 아니라 앵커로부터의 단순 거리입니다. "가까운 순" 정도로만 표기해 주세요.

### 4-4. `partial_coverage: true` 는 UI 안내 대상
"두 장소가 멀어서 중간 지역 가게는 못 찾았어요" 같은 안내가 필요합니다.

### 4-5. `estimated_time` 은 추정치
`"10:00"` 형태(ISO local time). 이동시간을 직선거리 ÷ 평균속도 25km/h로 계산한 값이고, 빈 칸은 좌표를 몰라 구간 이동시간을 균등 분배한 **근사**입니다. 사용자가 실제 장소를 고르면 값이 달라집니다.

### 4-6. `visit_order` 는 0부터
기존 No.28/No.31의 `visit_order`와 같은 체계인지 저장 시 확인이 필요합니다.

---

## 5. 최종 결정사항 (합의된 것 / 확인 필요한 것)

### ✅ 확정 — 하루 일정 템플릿

```
장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소
       └───── 구간 0 ─────┘        └─ 구간 1 ─┘
```

- **촬영지는 최대 3곳** (하루에 소화 가능한 양)
- **마지막이 촬영지인 것은 의도된 설계** — 저녁 이후 시간대라 야간 운영 가능한 촬영지(야경 명소, 야시장 등)를 배치. `night_open`이 점수에 반영됨
- 촬영지를 3곳 미만으로 고르면 **템플릿을 뒤에서부터 줄입니다**:

| 촬영지 수 | 슬롯 배치 |
|:---:|---|
| 1 | 장소 → 식당(점심) |
| 2 | 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) |
| 3 | 장소 → 식당(점심) → 카페 → 장소 → 식당(저녁) → 장소 |

- **숙소는 제외** — 하루 일정이므로 (팀 결정)

### ✅ 확정 — 그 외

| 항목 | 결정 |
|---|---|
| 식당·카페 자동 선택 | **안 함.** 취향 편차가 커서 사용자가 지도에서 직접 선택 |
| 후보 정렬 | **우회거리(동선 증가량) 오름차순** |
| 후보 개수 | 구간당 **8개** |
| 허용거리 기본/상한 | **3000m / 20000m** |
| 루트 생성 시 DB 저장 | **안 함.** `POST /itineraries`(No.28)를 눌러야 저장 |
| 후보 place 저장 시점 | **사용자가 고른 시점에만** (수십 건 후보를 매번 저장하면 테이블이 쓰레기로 참) |
| 거리/시간 | 하버사인 직선거리 + 평균속도 25km/h (추후 카카오 길찾기로 교체 가능하게 인터페이스 분리) |
| 식당/카페 데이터 출처 | 한국관광공사 TourAPI (KorService2) 온디맨드 |
| Claude 큐레이션 | **기본 OFF.** 동선·거리·순서·운영시간 판정은 전부 코드가 하고, LLM은 코드가 좁힌 후보 중 **선택 + 이유 작성만** 담당. 꺼두면 점수 1등이 그대로 선택됨 |

### ⚠️ 프론트·팀 확인 필요

1. **신규 4개 엔드포인트가 명세서 v4에 없습니다.** 명세서에 반영할지, 경로를 바꿀지 결정 필요.
2. **`GET /itineraries/recommend`(No.27)가 그대로 남아 있습니다.**
   - No.27 = 콘텐츠 기반 **단순 추천** (촬영지를 순서대로 나열)
   - `POST /itineraries/route` = 촬영지 직접선택 + 식당/카페 직접선택 **새 흐름**
   - **두 개가 병존 중**입니다. 프론트가 어느 화면에서 어느 걸 쓸지 정해 주세요. (둘 중 하나를 버릴지도 결정 필요)
3. **`GET /contents/{id}/places` 경로가 콘텐츠 도메인(박세현 파트)과 겹칩니다.** 나중에 합칠 때 이동하거나 경로 조정이 필요할 수 있습니다.
4. **신규 4개 전부 인증 불필요**로 열려 있습니다. `POST /places/import`는 쓰기 작업이라 인증을 걸지 논의 필요.
5. **체류시간 값** — 촬영지 120분 / 식당 90분 / 카페 60분. 처음 60/60/40으로 잡았더니 마지막 촬영지가 오후 3시에 끝나 '저녁 이후' 전제가 깨져서 상향했습니다. 실사용 후 재조정 가능.

---

## 6. 테스트

`api.http` 파일에 신규 4개 API 요청이 전부 들어 있습니다 (IntelliJ HTTP Client). 시드 데이터 기준 `content_id=2`(서울)로 바로 실행 가능합니다.

- 서버 실행: `.\gradlew.bat bootRun "--args=--spring.profiles.active=h2"`
- ⚠️ `TOUR_API_KEY` 환경변수가 없으면 **후보 목록이 빈 배열**로 옵니다 (에러 아님). 촬영지 배치·구간 타원은 키 없이도 정상 동작합니다.

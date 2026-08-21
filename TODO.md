# 잇다(Itda) 백엔드 — 권승훈 파트 TODO

> 출처 명세서: `~/Downloads/잇다_API명세서_v4.xlsx - API 전체.pdf`
> 최종 갱신: 2026-08-21

---

## 현재 상태 요약

**담당 8개 API(No.25~32) 구현 완료.** 여기에 '하루 루트 만들기' 신규 4개 API가 추가됐다
(상세: `API_변경사항_프론트전달.md`).

| 구분 | 상태 |
|---|---|
| No.25~32 (8개) | ✅ 구현 완료 |
| 신규 4개 (`/contents/{id}/places`, `/itineraries/route`, `/route/candidates`, `/places/import`) | ✅ 구현 완료 — ⚠️ 명세서 v4에 없음, 팀 합의 필요 |
| 빌드 | ✅ `compileJava` / `compileTestJava` 통과 |
| 테스트 | ⚠️ 47개 중 46개 통과. `contextLoads()` 1개는 Postgres 미기동으로 실패 (코드 문제 아님) |
| H2 프로파일 동작 확인 | ✅ `--spring.profiles.active=h2` |
| Postgres(Docker) 동작 확인 | ❌ 미검증 — Docker Desktop 미기동 |

### dev 이식 완료 (2026-08-21)

`feat/place-itinerary` 는 `origin/dev` 와 공통 조상이 없는 orphan 히스토리였다.
그대로 PR 하면 팀원 코드를 삭제하는 diff 가 되므로, **dev 위에 새 브랜치를 파서 파일만 이식**했다.

- 작업 브랜치: **`feat/place-itinerary-v2`** (`origin/dev` 기준)
- 이전 브랜치 `feat/place-itinerary` 는 참고용으로만 남긴다. **PR 은 v2 로 올린다.**

이식하면서 dev 규격에 맞춘 것:

| 항목 | 이전 | 이식 후 (dev 기준) |
|---|---|---|
| 패키지 | `com.itda.*` | `com.tourism.itda.*` |
| 경로 접두사 | `/places/8` | `/api/places/8` |
| 일정 패키지 | `itinerary` | `planner` (dev 가 비워둔 자리) |
| 엔티티 패키지 | `domain/` | `entity/` |
| 공통 패키지 | `common/` | `global/` |
| 인증 | 내 임시 JWT 필터 | **안시현 `global.jwt.JwtFilter` 사용.** 내 필터·JwtProvider·DevTokenController 삭제. `@LoginUser` 는 SecurityContext 에서 userId 를 읽도록 재작성 |
| Content / ContentPlace / Bookmark | 내 사본 | **박세현 `content.entity.*` 사용.** 내 사본 삭제 |
| `GET /contents/{id}/places` | 내 컨트롤러 | **박세현 것이 이미 dev 에 있음.** 내 중복 컨트롤러 삭제 |
| 에러 포맷 | `{code, message}` | **`{message}`** (dev `ErrorResponse` 에 code 없음) |
| snake_case / non_null | 전역 설정 | **내 DTO 에만 어노테이션.** 전역으로 켜면 팀원 응답이 바뀌므로 |
| QueryDSL | 의존성 있음 | **삭제** (실사용 코드가 없었음) |
| 스키마 | `schema.sql` + `data.sql` | **dev 의 `ddl-auto: update`** 사용. DROP 이 든 내 schema.sql 은 가져오지 않음 |
| TourAPI 키 | `itda.tour-api.api-key` 별도 | dev 의 `PUBLIC_DATA_API_KEY` 재사용 |

이식 후 검증:

- ✅ `compileJava` / `compileTestJava` 통과
- ✅ 단위 테스트 **46개 전부 통과** (OpeningHoursParser 19, SpotScorer 11, DetourFilter 9, DayTemplate 7)
- ✅ h2 프로파일로 **기동 성공** (3.7초) — 매핑 충돌·빈 생성 오류 없음
- ✅ 엔드포인트 실호출로 라우팅 확인 (No.25/26/27/29, route/candidates 전부 서비스단까지 도달)

---

## 0. 내 담당 범위

명세서 **No.25 ~ No.32, 총 8개 API**. 도메인은 **장소(place)** 와 **일정·플래너(itinerary)**.

| No | Method | Endpoint | 설명 | 인증 |
|----|--------|----------|------|------|
| 25 | GET | `/places/:place_id` | 장소 상세 / 핀 미리보기 | 선택 |
| 26 | GET | `/places/alternative` | 일정 슬롯 대안 장소 ('다른 곳 추천') | 불필요 |
| 27 | GET | `/itineraries/recommend` | 콘텐츠 기반 추천 일정 (저장 전 미리보기) | 불필요 |
| 28 | POST | `/itineraries` | 일정 저장 (플래너에 저장) | 필요 |
| 29 | GET | `/itineraries` | 내 플래너 목록 | 필요 |
| 30 | GET | `/itineraries/:itinerary_id` | 저장 일정 상세 조회 | 필요 |
| 31 | PATCH | `/itineraries/:itinerary_id` | 일정 수정 (제목/날짜/장소 순서·확정) | 필요 |
| 32 | DELETE | `/itineraries/:itinerary_id` | 플래너 삭제 | 필요 |

관련 테이블: `place`, `place_image`, `content_place`, `itinerary`, `itinerary_place`, `content`

### 팀 분담 (참고)
- **안시현** — 인증·사용자, 마이페이지 (No.1~14)
- **박세현** — 콘텐츠, 북마크 (No.15~20), 커뮤니티·공유 (No.33~37, 임시)
- **김다연** — 탐색·카테고리 (No.21~24), 리뷰 (No.38~40, 임시)
- **권승훈(나)** — 장소, 일정·플래너 (No.25~32)

---

## 1. 확정된 기술 결정

| 항목 | 결정 |
|---|---|
| 스택 | Spring Boot 3.x + **Java 21** + **Gradle** |
| DB | **PostgreSQL** (Docker Compose) |
| 데이터 접근 | **JPA** (QueryDSL 은 실사용 코드가 없어 이식하면서 제거) |
| 인증 | JWT — **안시현 `global.jwt.JwtFilter` 사용 확정.** 내 임시 필터는 삭제 |
| 거리/소요시간 | **하버사인 직선거리 + 평균속도 환산**. 인터페이스로 분리해 추후 카카오 길찾기 API 교체 가능하게 |
| 응답 포맷 | **명세서 raw 그대로**. 에러는 ~~`{code, message}`~~ → **`{message}`** (dev 구현 기준, 4-0-1 참고) |
| 일정 기간 | **N박 M일 완전 지원** (`day_number`, `duration_label`) |
| 추천 로직 | `content_place.recommend_order` 순서 그대로 |
| 레포 구조 | 단일 레포 + 도메인별 패키지 + feat 브랜치 (**`com.tourism.itda.place`, `com.tourism.itda.planner`**) |
| DDL | dev 가 `ddl-auto: update` 라 **내 엔티티가 곧 DDL**. `place` 정의를 팀에 공유해야 함 |

---

## 2. 환경 설치 — 완료 ✅

| 항목 | 상태 |
|---|---|
| JDK 21 (`C:\Program Files\Microsoft\jdk-21.0.11.10-hotspot`) | 설치 완료 |
| IntelliJ IDEA Community 2025.2.6.2 | 설치 완료 |
| Docker Desktop 4.82.0 | 설치 완료 |
| WSL2 (Docker 백엔드) | 설치 완료 |

### ⚠️ 재부팅 후 할 일
WSL2와 가상화 기능이 방금 켜졌으므로 **재부팅 전까지 Docker는 동작하지 않음.**

- [x] 재부팅
- [x] Docker Desktop 최초 실행 (WSL2 백엔드 연결 초기 세팅)
- [ ] 확인: `docker --version` / `docker compose version` / `java -version`
      → ⚠️ 2026-08-21 기준 Docker 데몬이 500 을 반환한다. Postgres 검증이 막혀 있어
        현재는 H2 프로파일(`--spring.profiles.active=h2`)로 개발/테스트 중.

> 셸의 `java -version`은 여전히 17로 나올 수 있음 (PATH가 기존 JDK 17을 먼저 잡음).
> Gradle toolchain으로 21을 지정할 예정이라 빌드에는 문제없음.
> IntelliJ에서는 프로젝트 SDK를 21로 지정할 것.

---

## 3. 구현 TODO

### 3-1. 프로젝트 스캐폴딩
- [x] Gradle 프로젝트 생성 (Spring Boot 3.x, Java 21 toolchain + foojay-resolver)
- [x] 의존성: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `postgresql`, `querydsl-jpa(jakarta)`, `jjwt`, `lombok`
- [x] `docker-compose.yml` — PostgreSQL 16
- [x] `application.yml` + `application-local.yml` (DB 접속정보 분리) — H2 검증용 `application-h2.yml` 추가
- [x] 패키지 구조: `com.itda.{common, place, itinerary}` — 다른 팀원 도메인 자리 비워두기

### 3-2. 공통 인프라
- [x] `GlobalExceptionHandler` — 에러 응답 `{code, message}` 통일
- [x] 커스텀 예외 (`NotFoundException`, `ForbiddenException`, `InvalidRequestException`)
- [x] 임시 JWT 인증 필터 + `@LoginUser` ArgumentResolver (userId 주입)
- [x] `DistanceCalculator` 인터페이스 + `HaversineDistanceCalculator` 구현체

### 3-3. 스키마 / 엔티티
- [x] `schema.sql` 작성 — `place`, `place_image`, `content_place`, `itinerary`, `itinerary_place`
- [x] 엔티티: `Place`, `PlaceImage`, `ContentPlace`, `Itinerary`, `ItineraryPlace`
- [x] `itinerary.user_id`는 **FK 엔티티 대신 Long 값으로 보관** (안시현 User 엔티티와 나중에 합칠 때 충돌 방지)
- [x] `ItineraryPlace.status` enum: `PENDING | CONFIRMED | CHANGED`
- [x] 더미 시드 데이터 `data.sql` (장소 + 콘텐츠-장소 매핑, 위경도 포함)
- [x] 루트 기능용 `place` 컬럼 6개 추가: `place_type`, `source`, `external_id`, `open_time`, `close_time`, `night_open`
      → ⚠️ 기존 No.25 응답에는 노출하지 않음. 박세현/김다연도 `place` 를 쓰므로 DDL 공유 필요

### 3-4. API 구현 — 장소
- [x] **GET `/places/:place_id`**
  - 응답: `place_id, name, category, description, latitude, longitude, address, region, opening_hours, kakao_place_id, images[], is_bookmarked`
  - `images`는 `place_image` 전체, `is_primary` 포함
  - `is_bookmarked`는 **로그인 시에만** 계산 (비로그인 false)
  - ⚠️ `fee`(입장료) 컬럼은 v2에서 제거 확정 — 응답에 넣지 말 것
- [x] **GET `/places/alternative`**
  - query: `content_id, visit_order, exclude_place_id`
  - 같은 콘텐츠 내 `recommend_order` 기준 다음 순위 장소 1건 반환
  - `image_url`은 `place_image` 중 `is_primary=true` 1건

### 3-5. API 구현 — 일정·플래너
- [x] **GET `/itineraries/recommend`**
  - query: `content_id`
  - `content_place`를 `recommend_order`로 정렬 → `slots[]` 구성
  - 각 슬롯에 `to_next_distance_m`, `to_next_duration_min` 계산해 채우기 (하버사인)
  - **비저장 상태** — DB에 아무것도 안 씀
- [x] **POST `/itineraries`**
  - body: `content_id?, title, travel_date, region?, duration_label?, places[]`
  - `places[]` 각 항목: `place_id, day_number?, visit_order, status, memo?`
  - 응답: `{ itinerary_id }`
- [x] **GET `/itineraries`** (내 플래너 목록)
  - `thumbnail_url`은 `content.thumbnail_url` 조인
  - `place_count`는 `itinerary_place` 건수 계산
- [x] **GET `/itineraries/:itinerary_id`**
  - 본인 소유 검증 (남의 일정이면 403)
  - `places[]`에 `to_next_distance_m`, `to_next_duration_min` 포함
- [x] **PATCH `/itineraries/:itinerary_id`**
  - 부분 수정: `title?, travel_date?, region?, duration_label?, places?`
  - ⚠️ `places` 전달 시 **해당 일정의 `itinerary_place` 전체 교체** 방식
- [x] **DELETE `/itineraries/:itinerary_id`**
  - 본인 소유 검증
  - soft delete (`itinerary.deleted_at`) 로 구현 — 명세서 검토 8 반영

### 3-5b. 신규 — 하루 루트 만들기 (명세서 v4에 없음, ⚠️ 팀 합의 필요)

상세 스펙은 `API_변경사항_프론트전달.md` 참고.

- [x] ~~**GET `/contents/{content_id}/places`**~~ — **박세현 파트가 이미 dev 에 구현.** 내 중복 컨트롤러는 이식하면서 삭제했다.
      다만 그쪽은 아직 placeholder(`"정보 준비중"` / `"미분류"`)라 **실제 place 정보 연결이 남았다** (아래 4-2)
- [x] **POST `/api/itineraries/route`** — 하루 루트 생성 (슬롯 6칸 + 지도 타원 `segments`), DB 미저장
- [x] **GET `/api/itineraries/route/candidates`** — 구간별 식당/카페 후보 (TourAPI 온디맨드)
- [x] **POST `/api/places/import`** — 고른 후보를 `place` 로 확정하고 `place_id` 발급
- [x] `TourApiClient` + 영업시간 파서 + `night_open` 판정
- [x] 촬영지 자동 선택 스코어러(`SpotScorer`) + Claude 큐레이션(`SpotCurator`, 기본 OFF·폴백 있음)

### 3-6. 검증 / 마무리
- [x] 각 API 통합 테스트 또는 `.http` 요청 파일 작성 — `api.http` 에 신규 4개 포함
- [x] 단위 테스트 46개 통과 (`OpeningHoursParser`, `DayTemplate`, `DetourFilter`, `SpotScorer`)
- [x] H2 프로파일 기동 + 엔드포인트 라우팅 확인 — `.\gradlew.bat bootRun "--args=--spring.profiles.active=h2"`
- [ ] `docker compose up -d` → `gradlew bootRun` 전체 동작 확인
      → ⚠️ 미완. Docker 데몬이 500 을 반환해 막힘. `ItdaApplicationTests.contextLoads()` 도 이것 때문에 실패한다
- [ ] **시드 데이터 만들기** — dev 는 `ddl-auto: update` 라 테이블만 생기고 내용이 비어 있다.
      예전 `data.sql` 은 내 옛 `schema.sql` 기준이라 dev 의 `Content` 엔티티 컬럼과 안 맞아 그대로 못 쓴다.
      장소·콘텐츠 더미(위경도 포함) 없이는 추천/루트가 전부 404 다
- [ ] 내 파트 DDL을 팀에 공유
      → `place` 컬럼 6개가 추가돼서 특히 필요 (3-3 마지막 항목)

### 4-2. 박세현 파트와 맞물린 남은 일

- [ ] **`GET /api/contents/{id}/places` 의 placeholder 채우기**
      → `ContentPlaceListItemResponse.of(...)` 가 지금 `"정보 준비중"` / `"미분류"` / `null` 을 돌려준다.
        `PlaceRepository` + `PlaceQueryService` 는 준비돼 있으니 연결만 하면 된다.
        **그쪽 파일이라 내가 손대지 않았다.** 박세현이 채울지, 내가 PR 올릴지 정할 것

---

## 4. 팀과 합의해야 할 것 (내가 못 정하는 것)

### 4-0. dev 병합 — 해결됨 ✅

- [x] 패키지명 → **`com.tourism.itda` 로 통일** (dev 쪽에 내가 맞춤)
- [x] 경로 접두사 → **`/api` 유지** (dev 컨트롤러 4개가 전부 `/api`. 명세서 쪽을 고치는 방향)
- [x] 이식 방식 → **dev 위 새 브랜치(`feat/place-itinerary-v2`)에 파일만 얹기**
- [ ] **명세서 v4 를 `/api` 접두사로 고치기** — 문서 담당자 확인 필요
- [ ] **신규 4개 엔드포인트를 명세서 v4 에 반영할지** (아래 5절 중복)

### 4-0-1. 🔴 이식하면서 새로 생긴 확인 사항

- [ ] **에러 포맷에 `code` 가 없다** — dev 의 `ErrorResponse` 는 `record ErrorResponse(String message)`.
      팀 합의는 `{code, message}` 였는데 구현이 다르다. 되살릴지 / 상태코드로 갈지 결정 필요.
      되살리면 4명 전원의 에러 응답에 필드가 하나 늘어난다
- [ ] **시드 데이터가 없다** — dev 는 `ddl-auto: update` 라 테이블만 생기고 내용이 비어 있다.
      장소·콘텐츠 더미(위경도 포함)가 없으면 추천/루트가 전부 404 다. 누가 만들지 정해야 한다
- [ ] **`POST /api/places/import` 가 인증 없이 열려 있다** — 쓰기 작업인데 permitAll. 인증을 걸지 논의
- [ ] **`gradle.properties` 를 커밋에 포함했다** — JDK 21 툴체인 경로. 다른 PC 에선 무시되지만
      팀에 알리는 게 낫다 (dev 는 JAVA_HOME 이 21 이어야만 빌드된다)

### 4-1. 그 외

- [x] **JWT 클레임 키 규격** — dev 확인 완료. `JwtProvider` 가 `subject=loginId`, `claim("userId", Long)`,
      HS256, 만료 24시간. `JwtFilter` 가 `Authentication.principal` 에 userId(Long) 를 넣는다.
      내 `@LoginUser` 는 여기서 읽도록 재작성했다. **시크릿은 `${JWT_SECRET}` 환경변수 — 값 공유 필요**
- [ ] **에러 응답에 `code` 가 없다** → 4-0-1 로 옮김
- [x] **공통 패키지 네이밍** — `com.tourism.itda.*` 로 확정 (내가 맞춤)
- [ ] **`place` 테이블 DDL 최종 확정** — 박세현/김다연도 `place`를 조회함. 지금은 dev 가 `ddl-auto: update` 라
      **내 `Place` 엔티티가 사실상 DDL 소스**다. 컬럼 6개 추가분 포함해서 두 사람에게 공유 필요
- [x] **`content` 테이블은 박세현 소유** — dev 의 `content.entity.Content` 사용 확인.
      `title` / `thumbnailUrl` 둘 다 있어서 내 조인 코드 그대로 동작한다. ⚠️ `Content.id` 는 자동생성이 아니라
      **TMDB movieId 를 그대로 넣는 방식**이다 (`@Id` 만 있고 `@GeneratedValue` 없음)
- [x] **북마크(`bookmark`)는 박세현 소유** — dev 의 `content.entity.Bookmark` 사용.
      `@UniqueConstraint(columnNames = {"user_id","place_id"})` 로 이미 올바르게 걸려 있다.
      `existsByUserIdAndPlaceId` 도 있어서 내 `is_bookmarked` 계산 그대로 동작
- [ ] **일정 공유(No.33~34)는 박세현(임시) 담당** — `itinerary.is_shared` 플래그를 그쪽이 건드림.
      박세현이 로컬에서 `Itinerary.isShared` + `sourceItinerary`(자기참조) 를 추가해 검증까지 마쳤다고 함
      (원본 삭제 시 복사본 유지 + `sourceItinerary` 만 null). **내 `Itinerary` 엔티티에 반영할지 답해야 함**
- [ ] **더미 데이터 누가 만드는지** — 장소·콘텐츠 실데이터(위경도 포함)가 있어야 추천 일정이 말이 됨

---

## 5. 명세서에서 놓치면 안 되는 주의사항

- `fee`(입장료) 컬럼 **v2에서 제거 확정** — 어떤 응답에도 넣지 않음
- `bookmark`는 **v3부터 장소 전용** — 콘텐츠 북마크 아님
- `content`에는 `rating` 없음 → 평점순 정렬 미제공
- `place_category`에 정렬 컬럼 없음 → 이름/`place_id` 정렬 (검토 5, 보류 상태)
- `itinerary.source_itinerary_id` 컬럼 **이미 존재** (공유 루트 복사 출처 기록용)
- 회원 탈퇴는 soft delete 권장 (`review`/`itinerary` FK 무결성)

# 잇다(Itda) 백엔드 — 권승훈 파트 TODO

> 출처 명세서: `~/Downloads/잇다_API명세서_v4.xlsx - API 전체.pdf`
> 최종 갱신: 2026-07-20

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
| 데이터 접근 | **JPA + QueryDSL** |
| 인증 | JWT — 시크릿/클레임 규격만 팀 합의하고 각자 검증. 임시 JWT 필터는 내가 작성 후 안시현 것으로 교체 |
| 거리/소요시간 | **하버사인 직선거리 + 평균속도 환산**. 인터페이스로 분리해 추후 카카오 길찾기 API 교체 가능하게 |
| 응답 포맷 | **명세서 raw 그대로**. 에러만 공통 `{ "code": "...", "message": "..." }` |
| 일정 기간 | **N박 M일 완전 지원** (`day_number`, `duration_label`) |
| 추천 로직 | `content_place.recommend_order` 순서 그대로 |
| 레포 구조 | **단일 레포 + 도메인별 패키지 + feat 브랜치** (`com.itda.place`, `com.itda.itinerary`, ...) |
| DDL | 내 파트 테이블 DDL은 내가 작성해서 팀에 공유 |

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

- [ ] 재부팅
- [ ] Docker Desktop 최초 실행 (WSL2 백엔드 연결 초기 세팅)
- [ ] 확인: `docker --version` / `docker compose version` / `java -version`

> 셸의 `java -version`은 여전히 17로 나올 수 있음 (PATH가 기존 JDK 17을 먼저 잡음).
> Gradle toolchain으로 21을 지정할 예정이라 빌드에는 문제없음.
> IntelliJ에서는 프로젝트 SDK를 21로 지정할 것.

---

## 3. 구현 TODO

### 3-1. 프로젝트 스캐폴딩
- [ ] Gradle 프로젝트 생성 (Spring Boot 3.x, Java 21 toolchain + foojay-resolver)
- [ ] 의존성: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `postgresql`, `querydsl-jpa(jakarta)`, `jjwt`, `lombok`
- [ ] `docker-compose.yml` — PostgreSQL 16
- [ ] `application.yml` + `application-local.yml` (DB 접속정보 분리)
- [ ] 패키지 구조: `com.itda.{common, place, itinerary}` — 다른 팀원 도메인 자리 비워두기

### 3-2. 공통 인프라
- [ ] `GlobalExceptionHandler` — 에러 응답 `{code, message}` 통일
- [ ] 커스텀 예외 (`NotFoundException`, `ForbiddenException`, `InvalidRequestException`)
- [ ] 임시 JWT 인증 필터 + `@LoginUser` ArgumentResolver (userId 주입)
- [ ] `DistanceCalculator` 인터페이스 + `HaversineDistanceCalculator` 구현체

### 3-3. 스키마 / 엔티티
- [ ] `schema.sql` 작성 — `place`, `place_image`, `content_place`, `itinerary`, `itinerary_place`
- [ ] 엔티티: `Place`, `PlaceImage`, `ContentPlace`, `Itinerary`, `ItineraryPlace`
- [ ] `itinerary.user_id`는 **FK 엔티티 대신 Long 값으로 보관** (안시현 User 엔티티와 나중에 합칠 때 충돌 방지)
- [ ] `ItineraryPlace.status` enum: `PENDING | CONFIRMED | CHANGED`
- [ ] 더미 시드 데이터 `data.sql` (장소 + 콘텐츠-장소 매핑, 위경도 포함)

### 3-4. API 구현 — 장소
- [ ] **GET `/places/:place_id`**
  - 응답: `place_id, name, category, description, latitude, longitude, address, region, opening_hours, kakao_place_id, images[], is_bookmarked`
  - `images`는 `place_image` 전체, `is_primary` 포함
  - `is_bookmarked`는 **로그인 시에만** 계산 (비로그인 false)
  - ⚠️ `fee`(입장료) 컬럼은 v2에서 제거 확정 — 응답에 넣지 말 것
- [ ] **GET `/places/alternative`**
  - query: `content_id, visit_order, exclude_place_id`
  - 같은 콘텐츠 내 `recommend_order` 기준 다음 순위 장소 1건 반환
  - `image_url`은 `place_image` 중 `is_primary=true` 1건

### 3-5. API 구현 — 일정·플래너
- [ ] **GET `/itineraries/recommend`**
  - query: `content_id`
  - `content_place`를 `recommend_order`로 정렬 → `slots[]` 구성
  - 각 슬롯에 `to_next_distance_m`, `to_next_duration_min` 계산해 채우기 (하버사인)
  - **비저장 상태** — DB에 아무것도 안 씀
- [ ] **POST `/itineraries`**
  - body: `content_id?, title, travel_date, region?, duration_label?, places[]`
  - `places[]` 각 항목: `place_id, day_number?, visit_order, status, memo?`
  - 응답: `{ itinerary_id }`
- [ ] **GET `/itineraries`** (내 플래너 목록)
  - `thumbnail_url`은 `content.thumbnail_url` 조인
  - `place_count`는 `itinerary_place` 건수 계산
- [ ] **GET `/itineraries/:itinerary_id`**
  - 본인 소유 검증 (남의 일정이면 403)
  - `places[]`에 `to_next_distance_m`, `to_next_duration_min` 포함
- [ ] **PATCH `/itineraries/:itinerary_id`**
  - 부분 수정: `title?, travel_date?, region?, duration_label?, places?`
  - ⚠️ `places` 전달 시 **해당 일정의 `itinerary_place` 전체 교체** 방식
- [ ] **DELETE `/itineraries/:itinerary_id`**
  - 본인 소유 검증
  - ⚠️ 커뮤니티 공유(`is_shared`)·리뷰 FK 무결성 때문에 **soft delete(`deleted_at`) 권장** — 명세서 검토 8

### 3-6. 검증 / 마무리
- [ ] 각 API 통합 테스트 또는 `.http` 요청 파일 작성
- [ ] `docker compose up -d` → `gradlew bootRun` 전체 동작 확인
- [ ] 내 파트 DDL을 팀에 공유

---

## 4. 팀과 합의해야 할 것 (내가 못 정하는 것)

- [ ] **JWT 시크릿 값 + 클레임 키 규격** — 안시현과. 특히 userId를 어느 클레임에 넣을지 (`sub`? `userId`?), 만료시간, 알고리즘(HS256 가정)
- [ ] **에러 응답 포맷 `{code, message}`를 4명 전원이 쓰는지** — 프론트가 동일하게 파싱해야 함
- [ ] **공통 패키지 네이밍** — `com.itda.*` 로 갈지 팀 확인
- [ ] **`place` 테이블 DDL 최종 확정** — 박세현/김다연도 `place`를 조회함 (콘텐츠 관련 장소, 카테고리 관련 장소). 내가 만든 DDL을 두 사람이 받아쓰는 구조여야 충돌 없음
- [ ] **`content` 테이블은 박세현 소유** — 내가 조인만 함. 컬럼명(`thumbnail_url`, `title`) 확정되면 반영
- [ ] **북마크(`bookmark`)는 박세현 소유** — 내 `is_bookmarked` 계산이 이 테이블에 의존. v3부터 **장소 전용**으로 변경됨, `UNIQUE(user_id, place_id)` 권장
- [ ] **일정 공유(No.33~34)는 박세현(임시) 담당** — `itinerary.is_shared` 플래그를 그쪽이 건드림. `itinerary` 엔티티 소유권/수정 범위 정리 필요
- [ ] **더미 데이터 누가 만드는지** — 장소·콘텐츠 실데이터(위경도 포함)가 있어야 추천 일정이 말이 됨

---

## 5. 명세서에서 놓치면 안 되는 주의사항

- `fee`(입장료) 컬럼 **v2에서 제거 확정** — 어떤 응답에도 넣지 않음
- `bookmark`는 **v3부터 장소 전용** — 콘텐츠 북마크 아님
- `content`에는 `rating` 없음 → 평점순 정렬 미제공
- `place_category`에 정렬 컬럼 없음 → 이름/`place_id` 정렬 (검토 5, 보류 상태)
- `itinerary.source_itinerary_id` 컬럼 **이미 존재** (공유 루트 복사 출처 기록용)
- 회원 탈퇴는 soft delete 권장 (`review`/`itinerary` FK 무결성)

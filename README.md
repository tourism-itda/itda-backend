# 잇다(Itda) — 권승훈 파트 (장소 / 일정·플래너, No.25~32)

Spring Boot 3.5 · Java 21 · Gradle · JPA + QueryDSL · JWT(임시)

> **기준 명세서**: `잇다_API명세서_v4.xlsx - API 전체.pdf` (팀 회의 확정본, 팀 내부 문서라 리포 미포함). TODO.md 와 일치.
> 응답 형식은 **v4 기준**으로 구현/검증됨. (팀 결정: "응답은 명세서 raw 그대로")
> 핵심: 장소상세(No.25)에 `is_bookmarked` 포함·인증 선택(북마크는 v3부터 **장소 전용**),
> alternative/recommend 는 `place:{}` 래핑, PATCH→`{itinerary_id}`, DELETE→`{success:true}`, fee 없음.

## 실행 방법

### A) 정석 — PostgreSQL (Docker)  ※ virtualization 활성화 필요
```powershell
docker compose -p itda up -d            # (프로젝트명 -p itda 필수: 폴더명이 한글이라)
.\gradlew.bat bootRun                    # 기본 프로파일 = local = PostgreSQL
```
> ⚠️ 현재 이 PC는 **virtualization(VT-x/AMD-V) 미활성**이라 Docker Desktop 엔진이 안 뜬다.
> BIOS/펌웨어에서 가상화 활성화(또는 IT 관리자 요청) 후 사용.

### B) 임시 — H2 인메모리 (Docker 없이 기능 확인용)
```powershell
.\gradlew.bat bootRun "--args=--spring.profiles.active=h2"
```
- 같은 `schema.sql` / `data.sql` 사용 (H2 PostgreSQL 호환 모드)
- H2 콘솔: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:itda`)

## 테스트
- `api.http` (IntelliJ HTTP Client) — 위에서부터 실행하면 토큰/일정ID 자동 저장
- 개발용 JWT: `GET /dev/token?userId=1` (local·h2 프로파일 전용)
  - 8개 API 전부 스모크 테스트 통과 확인 완료 (2026-07-25, H2)

## API 요약 (v4 명세서 No.25~32)
| No | Method | Endpoint | 인증 | 응답 |
|----|--------|----------|------|------|
| 25 | GET | `/places/{placeId}` | 선택 | 장소상세 (로그인 시 is_bookmarked) |
| 26 | GET | `/places/alternative` | 불필요 | `{ place: {...} }` |
| 27 | GET | `/itineraries/recommend` | 불필요 | `{ ..., slots:[{ place:{ to_next_* } }] }` (DB 미저장) |
| 28 | POST | `/itineraries` | 필요 | `{ itinerary_id }` |
| 29 | GET | `/itineraries` | 필요 | 목록 (content_title, place_count) |
| 30 | GET | `/itineraries/{id}` | 필요 | 상세 (소유 검증 403) |
| 31 | PATCH | `/itineraries/{id}` | 필요 | `{ itinerary_id }` (places 전달 시 전체 교체) |
| 32 | DELETE | `/itineraries/{id}` | 필요 | `{ success: true }` (soft delete) |

## 팀 합의 필요 — 코드에 임시값으로 반영됨
- JWT 시크릿 / userId 클레임 키: `application.yml`의 `itda.jwt.*` (기본 `userId`, sub 폴백)
- 에러 포맷 `{code, message}` — 전원 동일 파싱 전제
- `place` DDL(`schema.sql`) 을 박세현/김다연이 받아쓰기
- `content` 는 박세현 소유 → 지금은 placeholder 테이블/엔티티 (title, thumbnail_url 조인용)
- 북마크(`bookmark`)는 박세현 소유, **v3부터 장소 전용**(`UNIQUE(user_id, place_id)`) → 내 No.25 is_bookmarked 가 의존

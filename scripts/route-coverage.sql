-- 루트 생성 커버리지 측정 — 읽기 전용. INSERT/UPDATE/DELETE 없음.
--
-- 목적: 공개 작품 몇 편이 "작품 관련 명소(앵커)"를 확보할 수 있는지 센다.
--   경로 1  content_place               — 사람이 승인한 매핑
--   경로 2  content_person→place_person — 인물 체인 (수집 때 자동으로 쌓임)
--
-- psql 전용 명령(\echo 등)을 쓰지 않았다. DBeaver·pgAdmin에 그대로 붙여넣어도 되고
-- psql -f 로 실행해도 된다. 각 결과의 첫 열 section 으로 어느 질의인지 구분한다.

-- ============================================================
-- 1. 전체 커버리지 요약  ← 이거랑 3번만 보내주시면 됩니다
-- ============================================================
WITH pub AS (
    SELECT id, title FROM content WHERE status = 'PUBLISHED'
),
cp AS (
    SELECT content_id, COUNT(*) AS n FROM content_place GROUP BY content_id
),
chain AS (
    SELECT c.content_id, COUNT(DISTINCT pp.place_id) AS n
    FROM content_person c
    JOIN place_person pp ON pp.person_id = c.person_id
    GROUP BY c.content_id
)
SELECT
    '1_summary'                                                           AS section,
    COUNT(*)                                                              AS published_total,
    COUNT(*) FILTER (WHERE COALESCE(cp.n, 0) > 0)                         AS has_content_place,
    COUNT(*) FILTER (WHERE COALESCE(chain.n, 0) > 0)                      AS has_person_chain,
    COUNT(*) FILTER (WHERE COALESCE(cp.n, 0) + COALESCE(chain.n, 0) = 0)  AS anchors_zero,
    COUNT(*) FILTER (WHERE COALESCE(cp.n, 0) + COALESCE(chain.n, 0) = 1)  AS anchors_one,
    COUNT(*) FILTER (WHERE COALESCE(cp.n, 0) + COALESCE(chain.n, 0) >= 2) AS anchors_two_plus
FROM pub
LEFT JOIN cp    ON cp.content_id    = pub.id
LEFT JOIN chain ON chain.content_id = pub.id;

-- ============================================================
-- 2. 작품별 앵커 수 (적은 순)
-- ============================================================
WITH pub AS (
    SELECT id, title FROM content WHERE status = 'PUBLISHED'
),
cp AS (
    SELECT content_id, COUNT(*) AS n FROM content_place GROUP BY content_id
),
chain AS (
    SELECT c.content_id, COUNT(DISTINCT pp.place_id) AS n
    FROM content_person c
    JOIN place_person pp ON pp.person_id = c.person_id
    GROUP BY c.content_id
)
SELECT '2_per_content' AS section,
       pub.id, pub.title,
       COALESCE(cp.n, 0)    AS content_place_anchors,
       COALESCE(chain.n, 0) AS person_chain_anchors
FROM pub
LEFT JOIN cp    ON cp.content_id    = pub.id
LEFT JOIN chain ON chain.content_id = pub.id
ORDER BY COALESCE(cp.n, 0) + COALESCE(chain.n, 0) ASC, pub.id;

-- ============================================================
-- 3. 인물 체인 앵커가 한 지역에 모여 있는가 (앵커 간 최대 직선거리)
--    25km 를 크게 넘으면 하루 코스로 못 묶어 지역을 갈라야 한다.
-- ============================================================
WITH anchors AS (
    SELECT DISTINCT c.content_id, p.place_id, p.latitude, p.longitude
    FROM content_person c
    JOIN place_person pp ON pp.person_id = c.person_id
    JOIN place p         ON p.place_id   = pp.place_id
    WHERE p.place_type = 'SPOT'
)
SELECT '3_spread' AS section,
       a.content_id,
       ct.title,
       COUNT(*) AS pair_count,
       ROUND(MAX(6371000 * 2 * asin(sqrt(
           power(sin(radians(b.latitude - a.latitude) / 2), 2)
           + cos(radians(a.latitude)) * cos(radians(b.latitude))
           * power(sin(radians(b.longitude - a.longitude) / 2), 2)
       )))::numeric) AS max_pair_distance_m
FROM anchors a
JOIN anchors b  ON b.content_id = a.content_id AND b.place_id > a.place_id
JOIN content ct ON ct.id = a.content_id
GROUP BY a.content_id, ct.title
ORDER BY max_pair_distance_m DESC;

-- ============================================================
-- 4. 인물별 연결 장소 수 (앵커 공급원 분포)
-- ============================================================
SELECT '4_person_supply' AS section,
       p.name, p.kingdom, COUNT(pp.place_id) AS places
FROM person p
LEFT JOIN place_person pp ON pp.person_id = p.person_id
GROUP BY p.person_id, p.name, p.kingdom
ORDER BY places ASC, p.name;

-- ============================================================
-- 5. place 테이블 상태 (좌표 이상 탐지 포함)
-- ============================================================
SELECT '5_place_health' AS section,
       place_type, source, COUNT(*) AS n,
       COUNT(*) FILTER (WHERE latitude IS NULL OR longitude IS NULL
                           OR latitude = 0 OR longitude = 0)     AS bad_coord,
       COUNT(*) FILTER (WHERE latitude NOT BETWEEN 33 AND 39
                           OR longitude NOT BETWEEN 124 AND 132) AS outside_korea
FROM place
GROUP BY place_type, source
ORDER BY place_type, source;

-- 검증된 작품 관련 명소를 content_place 에 적재한다 (출처: KAKAO).
-- 각 앵커는 관광공사 TourAPI 또는 카카오 지도에서 실재와 좌표를 확인했다.
-- 이미 있는 place/매핑은 건드리지 않는다(멱등).
SET client_encoding TO 'UTF8';
\set ON_ERROR_STOP on
BEGIN;

-- [51200] 포화 속으로
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='9593079' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '학도의용군 전승기념관', '관광지', 36.0360723282947, 129.356702772918, '경북 포항시 북구 용흥동 96-1', '경북',
         'SPOT', 'KAKAO', NULL, '9593079', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 51200, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 51200 AND cp.place_id = t.place_id
);

-- [58069] 화려한 휴가
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='7955344' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '5.18기념공원', '관광지', 35.1562739086397, 126.856891843429, '전남광주통합특별시 서구 쌍촌동 1268', '전남광주통합특별시',
         'SPOT', 'KAKAO', NULL, '7955344', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 58069, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 58069 AND cp.place_id = t.place_id
);

-- [391734] 마지막 위안부
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='12456469' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '나눔의집', '관광지', 37.4429742406497, 127.321621759356, '경기 광주시 퇴촌면 원당리 65', '경기',
         'SPOT', 'KAKAO', NULL, '12456469', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 391734, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 391734 AND cp.place_id = t.place_id
);

-- [473196] 이름없는 별들
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='8772547' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '광주학생독립운동기념관', '관광지', 35.1406788755767, 126.865012919534, '전남광주통합특별시 서구 화정동 512', '전남광주통합특별시',
         'SPOT', 'KAKAO', NULL, '8772547', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 473196, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 473196 AND cp.place_id = t.place_id
);

-- [540550] DMZ, 비무장지대
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='11265760' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '제3땅굴', '관광지', 37.916473623591, 126.699195135329, '경기 파주시 군내면 점원리 1082-1', '경기',
         'SPOT', 'KAKAO', NULL, '11265760', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 540550, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 540550 AND cp.place_id = t.place_id
);

-- [554779] 스윙키즈
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='8648828' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '거제포로수용소유적공원', '관광지', 34.876351874728094, 128.62543709046446, '경남 거제시 고현동 362', '경남',
         'SPOT', 'KAKAO', NULL, '8648828', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 554779, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 554779 AND cp.place_id = t.place_id
);

-- [564863] 말모이
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='25612651' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '국립한글박물관', '관광지', 37.5211853774917, 126.980977473728, '서울 용산구 용산동6가 168-6', '서울',
         'SPOT', 'KAKAO', NULL, '25612651', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 564863, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 564863 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='1444605293' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '조선어학회 한말글 수호기념탑', '관광지', 37.5733378370269, 126.975741148758, '서울 종로구 세종로 80-1', '서울',
         'SPOT', 'KAKAO', NULL, '1444605293', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 564863, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 564863 AND cp.place_id = t.place_id
);

-- [567629] 장사리: 잊혀진 영웅들
WITH existing AS (
  SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='27305472' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '장사상륙작전 전승기념관', '관광지', 36.280085725103795, 129.37897954557522, '경북 영덕군 남정면 장사리', '경북',
         'SPOT', 'KAKAO', NULL, '27305472', false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 567629, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 567629 AND cp.place_id = t.place_id
);

COMMIT;
\echo '--- 적재 후 확인 ---'
SELECT count(*) AS content_place행, count(DISTINCT content_id) AS 매핑된작품 FROM content_place;
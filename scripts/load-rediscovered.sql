-- 재발굴 앵커 중 사람이 채택한 것만 적재한다.
SET client_encoding TO 'UTF8';
\set ON_ERROR_STOP on
BEGIN;

-- [11658] 태극기 휘날리며 -> 전쟁기념관 (TOUR_API)
WITH existing AS (SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='130431' LIMIT 1),
ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '전쟁기념관', '관광지', 37.5373270838, 126.9784385198, '서울특별시 용산구 이태원로 29', '서울특별시',
         'SPOT', 'TOUR_API', '130431', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing) RETURNING place_id),
target AS (SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 11658, t.place_id, 1 FROM target t
WHERE NOT EXISTS (SELECT 1 FROM content_place cp
                  WHERE cp.content_id = 11658 AND cp.place_id = t.place_id);

-- [79553] 고지전 -> 백마고지전적지 (KAKAO)
WITH existing AS (SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='12697191' LIMIT 1),
ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '백마고지전적지', '관광지', 38.2697602685392, 127.164964432568, '강원특별자치도 철원군 철원읍 산명리 1822-1', '강원특별자치도',
         'SPOT', 'KAKAO', NULL, '12697191', false
  WHERE NOT EXISTS (SELECT 1 FROM existing) RETURNING place_id),
target AS (SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 79553, t.place_id, 1 FROM target t
WHERE NOT EXISTS (SELECT 1 FROM content_place cp
                  WHERE cp.content_id = 79553 AND cp.place_id = t.place_id);

-- [242452] 변호인 -> 봉하마을 (TOUR_API)
WITH existing AS (SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='2514460' LIMIT 1),
ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '봉하마을', '관광지', 35.3142497557, 128.7698237977, '경상남도 김해시 진영읍 봉하로 115', '경상남도',
         'SPOT', 'TOUR_API', '2514460', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing) RETURNING place_id),
target AS (SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 242452, t.place_id, 1 FROM target t
WHERE NOT EXISTS (SELECT 1 FROM content_place cp
                  WHERE cp.content_id = 242452 AND cp.place_id = t.place_id);

-- [760497] 승부 -> 한국기원 (KAKAO)
WITH existing AS (SELECT place_id FROM place WHERE source='KAKAO' AND kakao_place_id='20949971' LIMIT 1),
ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '한국기원', '관광지', 37.566208835423296, 127.033028735844, '서울 성동구 홍익동 315', '서울',
         'SPOT', 'KAKAO', NULL, '20949971', false
  WHERE NOT EXISTS (SELECT 1 FROM existing) RETURNING place_id),
target AS (SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 760497, t.place_id, 1 FROM target t
WHERE NOT EXISTS (SELECT 1 FROM content_place cp
                  WHERE cp.content_id = 760497 AND cp.place_id = t.place_id);

COMMIT;
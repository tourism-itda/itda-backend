-- 검증된 작품 관련 명소를 content_place 에 적재한다 (출처: TOUR_API).
-- 각 앵커는 관광공사 TourAPI 또는 카카오 지도에서 실재와 좌표를 확인했다.
-- 이미 있는 place/매핑은 건드리지 않는다(멱등).
SET client_encoding TO 'UTF8';
\set ON_ERROR_STOP on
BEGIN;

-- [11658] 태극기 휘날리며
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='130431' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '전쟁기념관', '문화시설', 37.5373270838, 126.9784385198, '서울특별시 용산구 이태원로 29', '서울특별시',
         'SPOT', 'TOUR_API', '130431', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 11658, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 11658 AND cp.place_id = t.place_id
);

-- [58069] 화려한 휴가
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='126425' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '국립5·18민주묘지', '관광지', 35.2357657421, 126.9392538751, '전남광주통합특별시 북구 민주로 200 (운정동)', '전남광주통합특별시',
         'SPOT', 'TOUR_API', '126425', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 58069, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 58069 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='2666987' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '전일빌딩245', '관광지', 35.1483590456295, 126.918717121719, '전남광주통합특별시 동구 금남로 245', '전남광주통합특별시',
         'SPOT', 'TOUR_API', '2666987', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 58069, t.place_id, 3 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 58069 AND cp.place_id = t.place_id
);

-- [79224] 최종병기 활
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='1624958' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '남한산성 수어장대', '관광지', 37.4798, 127.1765, '경기도 광주시 남한산성면 산성리', '경기도',
         'SPOT', 'TOUR_API', '1624958', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 79224, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 79224 AND cp.place_id = t.place_id
);

-- [113464] 인천
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='130060' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '인천상륙작전기념관', '문화시설', 37.4194730393, 126.6534906799, '인천광역시 연수구 청량로 138 (옥련동)', '인천광역시',
         'SPOT', 'TOUR_API', '130060', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='125519' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '자유공원(인천)', '관광지', 37.4752405648, 126.6222615765, '인천광역시 제물포구 제물량로232번길 46', '인천광역시',
         'SPOT', 'TOUR_API', '125519', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='127585' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '월미도', '관광지', 37.4722, 126.6001, '인천광역시 제물포구 월미문화로 36 (북성동1가)', '인천광역시',
         'SPOT', 'TOUR_API', '127585', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, t.place_id, 3 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = t.place_id
);

-- [133756] 지슬: 끝나지 않은 세월 2
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='1907801' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '제주4·3평화공원', '관광지', 33.4498231292, 126.6177472981, '제주특별자치도 제주시 명림로 430', '제주특별자치도',
         'SPOT', 'TOUR_API', '1907801', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 133756, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 133756 AND cp.place_id = t.place_id
);

-- [271771] 비무장지대
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='3022911' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '임진각 수풀누리', '관광지', 37.892013456157144, 126.74474640589587, '경기도 파주시 문산읍 임진각로 148-40', '경기도',
         'SPOT', 'TOUR_API', '3022911', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 271771, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 271771 AND cp.place_id = t.place_id
);

-- [333167] 순수의 시대
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='126508' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '경복궁', '관광지', 37.576030700049394, 126.97672186606306, '서울특별시 종로구 사직로 161 (세종로)', '서울특별시',
         'SPOT', 'TOUR_API', '126508', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 333167, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 333167 AND cp.place_id = t.place_id
);

-- [363093] 대호
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='126594' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '지리산국립공원(산청)', '관광지', 35.2695383058, 127.8531803404, '경상남도 산청군 시천면 남명로 376', '경상남도',
         'SPOT', 'TOUR_API', '126594', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 363093, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 363093 AND cp.place_id = t.place_id
);

-- [407887] 인천상륙작전
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='130060' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '인천상륙작전기념관', '문화시설', 37.4194730393, 126.6534906799, '인천광역시 연수구 청량로 138 (옥련동)', '인천광역시',
         'SPOT', 'TOUR_API', '130060', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='125519' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '자유공원(인천)', '관광지', 37.4752405648, 126.6222615765, '인천광역시 제물포구 제물량로232번길 46', '인천광역시',
         'SPOT', 'TOUR_API', '125519', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='127585' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '월미도', '관광지', 37.4722, 126.6001, '인천광역시 제물포구 월미문화로 36 (북성동1가)', '인천광역시',
         'SPOT', 'TOUR_API', '127585', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, t.place_id, 3 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = t.place_id
);

-- [408360] 덕혜옹주
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='126509' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '덕수궁', '관광지', 37.565054919430565, 126.97657463444833, '서울특별시 중구 세종대로 99 (정동)', '서울특별시',
         'SPOT', 'TOUR_API', '126509', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 408360, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 408360 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='1604941' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '창덕궁 낙선재', '관광지', 37.5777031595, 126.9902446339, '서울특별시 종로구 율곡로 99 (와룡동)', '서울특별시',
         'SPOT', 'TOUR_API', '1604941', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 408360, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 408360 AND cp.place_id = t.place_id
);

-- [436391] 군함도
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='2551424' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '국립일제강제동원역사관', '문화시설', 35.124284058, 129.0918442401, '부산광역시 남구 홍곡로320번길 100', '부산광역시',
         'SPOT', 'TOUR_API', '2551424', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 436391, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 436391 AND cp.place_id = t.place_id
);

-- [437068] 택시운전사
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='126425' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '국립5·18민주묘지', '관광지', 35.2357657421, 126.9392538751, '전남광주통합특별시 북구 민주로 200 (운정동)', '전남광주통합특별시',
         'SPOT', 'TOUR_API', '126425', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437068, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 437068 AND cp.place_id = t.place_id
);
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='2666987' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '전일빌딩245', '관광지', 35.1483590456295, 126.918717121719, '전남광주통합특별시 동구 금남로 245', '전남광주통합특별시',
         'SPOT', 'TOUR_API', '2666987', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437068, t.place_id, 2 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 437068 AND cp.place_id = t.place_id
);

-- [437103] 1987
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='130961' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '이한열기념관', '문화시설', 37.5550242286, 126.9338531935, '서울특별시 마포구 신촌로12나길 26', '서울특별시',
         'SPOT', 'TOUR_API', '130961', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437103, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 437103 AND cp.place_id = t.place_id
);

-- [554137] 국가부도의 날
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='2762802' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '한국은행 화폐박물관', '문화시설', 37.5621506093431, 126.979991760891, '서울특별시 중구 남대문로 39 (남대문로3가)', '서울특별시',
         'SPOT', 'TOUR_API', '2762802', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 554137, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 554137 AND cp.place_id = t.place_id
);

-- [770322] 태조 왕건
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='125899' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '개태사(논산)', '관광지', 36.2407281973, 127.2293710155, '충청남도 논산시 연산면 계백로 2614-11', '충청남도',
         'SPOT', 'TOUR_API', '125899', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 770322, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 770322 AND cp.place_id = t.place_id
);

-- [792107] 젊은 그들
WITH existing AS (
  SELECT place_id FROM place WHERE source='TOUR_API' AND external_id='127454' LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT '서울 운현궁', '관광지', 37.5764588036, 126.9871421746, '서울특별시 종로구 삼일대로 464', '서울특별시',
         'SPOT', 'TOUR_API', '127454', NULL, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 792107, t.place_id, 1 FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = 792107 AND cp.place_id = t.place_id
);

COMMIT;
\echo '--- 적재 후 확인 ---'
SELECT count(*) AS content_place행, count(DISTINCT content_id) AS 매핑된작품 FROM content_place;
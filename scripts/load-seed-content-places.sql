-- 콘텐츠 ↔ 장소 매핑 시드
-- 생성: 2026-09-18 00:15 / 출처: scripts\seed-candidates.csv (사람 검수 통과분만)
--
-- place 는 (source, external_id) 로 중복을 막고, content_place 는 PK 로 막는다.
-- 여러 번 실행해도 같은 결과가 되도록 전부 NOT EXISTS 가드를 걸었다.

BEGIN;

-- [113464/인천] D-지명 → 인천상륙작전기념관  (근거 B등급, 키워드: 인천상륙작전기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '인천상륙작전기념관', '문화시설', NULL,
       37.4194730393, 126.6534906799, '인천광역시 연수구 청량로 138 (옥련동)', '인천광역시',
       'SPOT', 'TOUR_API', '130060', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130060'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130060'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 113464)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = p.place_id
  );

-- [113464/인천] D-지명 → 월미도  (근거 B등급, 키워드: 월미도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '월미도', '관광지', NULL,
       37.4722, 126.6001, '인천광역시 제물포구 월미문화로 36 (북성동1가)', '인천광역시',
       'SPOT', 'TOUR_API', '127585', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '127585'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '127585'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 113464)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = p.place_id
  );

-- [113464/인천] D-지명 → 자유공원(인천)  (근거 B등급, 키워드: 자유공원)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '자유공원(인천)', '관광지', NULL,
       37.4752405648, 126.6222615765, '인천광역시 제물포구 제물량로232번길 46', '인천광역시',
       'SPOT', 'TOUR_API', '125519', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '125519'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '125519'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 113464)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = p.place_id
  );

-- [113464/인천] D-지명 → 인천 팔미도 등대  (근거 B등급, 키워드: 팔미도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '인천 팔미도 등대', '관광지', NULL,
       37.3583198861852, 126.510825269345, '인천광역시 영종구 팔미로 28 (무의동)', '인천광역시',
       'SPOT', 'TOUR_API', '129128', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '129128'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 113464, p.place_id, 4
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '129128'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 113464)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 113464 AND cp.place_id = p.place_id
  );

-- [11658/태극기 휘날리며] D-지명 → 낙동강승전기념관  (근거 B등급, 키워드: 낙동강승전기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '낙동강승전기념관', '문화시설', NULL,
       35.827094311019, 128.58876068184, '대구광역시 남구 앞산순환로 574-110', '대구광역시',
       'SPOT', 'TOUR_API', '130660', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130660'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 11658, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130660'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 11658)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 11658 AND cp.place_id = p.place_id
  );

-- [11658/태극기 휘날리며] D-지명 → 전쟁기념관  (근거 B등급, 키워드: 전쟁기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '전쟁기념관', '문화시설', NULL,
       37.5373270838, 126.9784385198, '서울특별시 용산구 이태원로 29', '서울특별시',
       'SPOT', 'TOUR_API', '130431', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130431'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 11658, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130431'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 11658)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 11658 AND cp.place_id = p.place_id
  );

-- [11658/태극기 휘날리며] D-지명 → 다부동전적기념관  (근거 B등급, 키워드: 다부동)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '다부동전적기념관', '문화시설', NULL,
       36.0464419062, 128.5188743903, '경상북도 칠곡군 가산면 호국로 1486', '경상북도',
       'SPOT', 'TOUR_API', '2406077', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2406077'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 11658, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2406077'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 11658)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 11658 AND cp.place_id = p.place_id
  );

-- [133756/지슬: 끝나지 않은 세월 2] D-지명 → 제주4·3평화공원  (근거 B등급, 키워드: 제주4·3평화공원)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '제주4·3평화공원', '관광지', NULL,
       33.4498231292, 126.6177472981, '제주특별자치도 제주시 명림로 430', '제주특별자치도',
       'SPOT', 'TOUR_API', '1907801', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '1907801'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 133756, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '1907801'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 133756)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 133756 AND cp.place_id = p.place_id
  );

-- [133756/지슬: 끝나지 않은 세월 2] D-지명 → 주정공장수용소 4·3역사관  (근거 B등급, 키워드: 4·3)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '주정공장수용소 4·3역사관', '문화시설', NULL,
       33.5181071040829, 126.534809355193, '제주특별자치도 제주시 임항로 98 (건입동)', '제주특별자치도',
       'SPOT', 'TOUR_API', '3386123', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '3386123'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 133756, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '3386123'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 133756)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 133756 AND cp.place_id = p.place_id
  );

-- [133756/지슬: 끝나지 않은 세월 2] D-지명 → 북촌마을 4·3길  (근거 B등급, 키워드: 4·3)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '북촌마을 4·3길', '관광지', NULL,
       33.546021407, 126.688682938, '제주특별자치도 제주시 조천읍 북촌3길 3', '제주특별자치도',
       'SPOT', 'TOUR_API', '2661523', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2661523'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 133756, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2661523'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 133756)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 133756 AND cp.place_id = p.place_id
  );

-- [133756/지슬: 끝나지 않은 세월 2] D-지명 → 신촌 4·3 성터  (근거 B등급, 키워드: 4·3)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '신촌 4·3 성터', '관광지', NULL,
       33.5376989336, 126.6130984926, '제주특별자치도 제주시 조천읍 신촌리', '제주특별자치도',
       'SPOT', 'TOUR_API', '2872927', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2872927'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 133756, p.place_id, 4
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2872927'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 133756)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 133756 AND cp.place_id = p.place_id
  );

-- [1371481/춘천대첩 72시간] D → 춘천지구 전적비(춘천지구 전적기념관)  (근거 B등급, 키워드: 춘천지구 전적비(춘천지구 전적기념관))
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '춘천지구 전적비(춘천지구 전적기념관)', NULL, NULL,
       37.8739262304, 127.7049042815, '강원특별자치도 춘천시 수변공원길 45 (삼천동)', NULL,
       'SPOT', 'TOUR_API', '914428', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '914428'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 1371481, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '914428'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 1371481)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 1371481 AND cp.place_id = p.place_id
  );

-- [1417288/독립군: 끝나지 않은 전쟁] D-지명 → 독립기념관  (근거 B등급, 키워드: 독립기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '독립기념관', '문화시설', NULL,
       36.7818719998, 127.2303981117, '충청남도 천안시 동남구 목천읍 독립기념관로 1', '충청남도',
       'SPOT', 'TOUR_API', '129790', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '129790'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 1417288, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '129790'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 1417288)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 1417288 AND cp.place_id = p.place_id
  );

-- [1417288/독립군: 끝나지 않은 전쟁] D-지명 → 서대문형무소역사관  (근거 B등급, 키워드: 서대문형무소)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '서대문형무소역사관', '문화시설', NULL,
       37.574303895, 126.9555345762, '서울특별시 서대문구 통일로 251', '서울특별시',
       'SPOT', 'TOUR_API', '130152', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130152'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 1417288, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130152'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 1417288)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 1417288 AND cp.place_id = p.place_id
  );

-- [282631/명량] D-지명 → 명량대첩해전사 기념전시관  (근거 B등급, 키워드: 명량대첩)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '명량대첩해전사 기념전시관', '문화시설', NULL,
       34.573316988, 126.3108108955, '전남광주통합특별시 해남군 문내면 관광레저로 12', '전남광주통합특별시',
       'SPOT', 'TOUR_API', '2708333', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2708333'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 282631, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2708333'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 282631)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 282631 AND cp.place_id = p.place_id
  );

-- [282631/명량] A-제목 → 명량해협 울돌목  (근거 B등급, 키워드: 명량)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '명량해협 울돌목', '관광지', NULL,
       34.5717059772807, 126.305303120209, '전남광주통합특별시 진도군 군내면 녹진리', '전남광주통합특별시',
       'SPOT', 'TOUR_API', '1624311', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '1624311'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 282631, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '1624311'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 282631)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 282631 AND cp.place_id = p.place_id
  );

-- [282631/명량] D-지명 → 해남 명량대첩비  (근거 B등급, 키워드: 명량대첩)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '해남 명량대첩비', '관광지', NULL,
       34.5844226722, 126.3141468496, '전남광주통합특별시 해남군 문내면 우수영안길 34', '전남광주통합특별시',
       'SPOT', 'TOUR_API', '231919', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '231919'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 282631, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '231919'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 282631)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 282631 AND cp.place_id = p.place_id
  );

-- [343843/연평해전] D-지명 → 연평도  (근거 B등급, 키워드: 연평도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '연평도', '관광지', NULL,
       37.6648819182778, 125.701999411438, '인천광역시 옹진군 연평면 연평중앙로24번길 3', '인천광역시',
       'SPOT', 'TOUR_API', '127412', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '127412'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 343843, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '127412'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 343843)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 343843 AND cp.place_id = p.place_id
  );

-- [343843/연평해전] D-지명 → 서해수호관  (근거 B등급, 키워드: 서해수호)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '서해수호관', '문화시설', NULL,
       19.69442748, 117.9925662504, '경기도 평택시 포승읍 2함대길 122', '경기도',
       'SPOT', 'TOUR_API', '2649844', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2649844'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 343843, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2649844'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 343843)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 343843 AND cp.place_id = p.place_id
  );

-- [361297/서부전선] D-지명 → 도라전망대  (근거 B등급, 키워드: 도라전망대)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '도라전망대', '관광지', NULL,
       37.9088872381, 126.7047863286, '경기도 파주시 장단면 제3땅굴로 310', '경기도',
       'SPOT', 'TOUR_API', '252560', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '252560'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 361297, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '252560'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 361297)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 361297 AND cp.place_id = p.place_id
  );

-- [361297/서부전선] D-지명 → 파주 임진각(평화누리공원)  (근거 B등급, 키워드: 임진각)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '파주 임진각(평화누리공원)', '관광지', NULL,
       37.8895364868545, 126.740185526631, '경기도 파주시 문산읍 임진각로 177', '경기도',
       'SPOT', 'TOUR_API', '127548', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '127548'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 361297, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '127548'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 361297)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 361297 AND cp.place_id = p.place_id
  );

-- [391734/마지막 위안부] D → 일본군 위안부 역사관  (근거 B등급, 키워드: 일본군 위안부 역사관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '일본군 위안부 역사관', NULL, NULL,
       37.4427876885, 127.3219804373, '경기도 광주시 퇴촌면 가새골길 85', NULL,
       'SPOT', 'TOUR_API', '130476', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130476'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 391734, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130476'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 391734)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 391734 AND cp.place_id = p.place_id
  );

-- [407887/인천상륙작전] D-지명 → 인천상륙작전기념관  (근거 B등급, 키워드: 인천상륙작전기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '인천상륙작전기념관', '문화시설', NULL,
       37.4194730393, 126.6534906799, '인천광역시 연수구 청량로 138 (옥련동)', '인천광역시',
       'SPOT', 'TOUR_API', '130060', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '130060'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '130060'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 407887)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = p.place_id
  );

-- [407887/인천상륙작전] D-지명 → 월미도  (근거 B등급, 키워드: 월미도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '월미도', '관광지', NULL,
       37.4722, 126.6001, '인천광역시 제물포구 월미문화로 36 (북성동1가)', '인천광역시',
       'SPOT', 'TOUR_API', '127585', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '127585'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '127585'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 407887)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = p.place_id
  );

-- [407887/인천상륙작전] D-지명 → 자유공원(인천)  (근거 B등급, 키워드: 자유공원)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '자유공원(인천)', '관광지', NULL,
       37.4752405648, 126.6222615765, '인천광역시 제물포구 제물량로232번길 46', '인천광역시',
       'SPOT', 'TOUR_API', '125519', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '125519'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 407887, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '125519'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 407887)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 407887 AND cp.place_id = p.place_id
  );

-- [437081/남한산성] D-지명 → 남한산성 수어장대  (근거 B등급, 키워드: 남한산성)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '남한산성 수어장대', '관광지', NULL,
       37.4798, 127.1765, '경기도 광주시 남한산성면 산성리', '경기도',
       'SPOT', 'TOUR_API', '1624958', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '1624958'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437081, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '1624958'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 437081)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 437081 AND cp.place_id = p.place_id
  );

-- [437081/남한산성] D-지명 → 남한산성행궁  (근거 B등급, 키워드: 남한산성)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '남한산성행궁', '관광지', NULL,
       37.4788986337, 127.181530029, '경기도 광주시 남한산성면 산성리 935-1', '경기도',
       'SPOT', 'TOUR_API', '2751248', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2751248'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437081, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2751248'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 437081)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 437081 AND cp.place_id = p.place_id
  );

-- [437081/남한산성] D-지명 → 서울 삼전도비  (근거 B등급, 키워드: 삼전도비)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '서울 삼전도비', '관광지', NULL,
       37.5091158401, 127.0978839997, '서울특별시 송파구 삼학사로 136 (잠실동)', '서울특별시',
       'SPOT', 'TOUR_API', '231897', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '231897'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 437081, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '231897'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 437081)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 437081 AND cp.place_id = p.place_id
  );

-- [535389/안시성] D-지명 → 아차산성  (근거 B등급, 키워드: 아차산성)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '아차산성', '관광지', NULL,
       37.5588, 127.1048, '서울특별시 광진구 광장동', '서울특별시',
       'SPOT', 'TOUR_API', '126518', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126518'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 535389, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126518'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 535389)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 535389 AND cp.place_id = p.place_id
  );

-- [535389/안시성] D-지명 → 고구려대장간마을  (근거 B등급, 키워드: 고구려)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '고구려대장간마을', '문화시설', NULL,
       37.5608078747, 127.1109491707, '경기도 구리시 우미내길 41 (아천동)', '경기도',
       'SPOT', 'TOUR_API', '4075415', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '4075415'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 535389, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '4075415'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 535389)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 535389 AND cp.place_id = p.place_id
  );

-- [535389/안시성] D-지명 → 충주고구려비전시관  (근거 B등급, 키워드: 고구려)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '충주고구려비전시관', '문화시설', NULL,
       37.0283295264, 127.8482076062, '충청북도 충주시 중앙탑면 감노로 2319', '충청북도',
       'SPOT', 'TOUR_API', '1938381', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '1938381'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 535389, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '1938381'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 535389)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 535389 AND cp.place_id = p.place_id
  );

-- [535389/안시성] D-지명 → 방동리 고구려고분  (근거 B등급, 키워드: 고구려)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '방동리 고구려고분', '관광지', NULL,
       37.903245, 127.661143, '강원특별자치도 춘천시 서면 방동리', '강원특별자치도',
       'SPOT', 'TOUR_API', '128786', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '128786'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 535389, p.place_id, 4
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '128786'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 535389)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 535389 AND cp.place_id = p.place_id
  );

-- [567629/장사리: 잊혀진 영웅들] D-지명 → 장사상륙작전 전승기념관  (근거 B등급, 키워드: 장사상륙작전)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '장사상륙작전 전승기념관', '문화시설', NULL,
       36.2801354767, 129.3789819656, '경상북도 영덕군 남정면 동해대로 3560', '경상북도',
       'SPOT', 'TOUR_API', '2732161', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2732161'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 567629, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2732161'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 567629)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 567629 AND cp.place_id = p.place_id
  );

-- [573791/봉오동 전투] D-지명 → 독립기념관  (근거 B등급, 키워드: 독립기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '독립기념관', '문화시설', NULL,
       36.7818719998, 127.2303981117, '충청남도 천안시 동남구 목천읍 독립기념관로 1', '충청남도',
       'SPOT', 'TOUR_API', '129790', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '129790'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 573791, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '129790'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 573791)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 573791 AND cp.place_id = p.place_id
  );

-- [588108/한산: 용의 출현] D-지명 → 한산도  (근거 B등급, 키워드: 한산도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '한산도', '관광지', NULL,
       34.7783, 128.4868, '경상남도 통영시 한산면 두억리', '경상남도',
       'SPOT', 'TOUR_API', '127105', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '127105'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 588108, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '127105'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 588108)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 588108 AND cp.place_id = p.place_id
  );

-- [588108/한산: 용의 출현] D-지명 → 제승당  (근거 B등급, 키워드: 제승당)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '제승당', '관광지', NULL,
       34.7960941315, 128.4744856404, '경상남도 통영시 한산일주로 70 제승당', '경상남도',
       'SPOT', 'TOUR_API', '2763965', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '2763965'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 588108, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '2763965'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 588108)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 588108 AND cp.place_id = p.place_id
  );

-- [588108/한산: 용의 출현] D-지명 → 통영 한산도 이충무공 유적  (근거 B등급, 키워드: 한산도)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '통영 한산도 이충무공 유적', '관광지', NULL,
       34.7943331719, 128.4719247986, '경상남도 통영시 한산면 한산일주로 70', '경상남도',
       'SPOT', 'TOUR_API', '126948', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126948'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 588108, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126948'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 588108)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 588108 AND cp.place_id = p.place_id
  );

-- [610251/하얼빈] D-지명 → 안중근의사기념관  (근거 B등급, 키워드: 안중근의사기념관)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '안중근의사기념관', '문화시설', NULL,
       37.5534661728908, 126.980402427443, '서울특별시 중구 소월로 91 (남대문로5가)', '서울특별시',
       'SPOT', 'TOUR_API', '129794', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '129794'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 610251, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '129794'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 610251)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 610251 AND cp.place_id = p.place_id
  );

-- [65881/평양성] D-지명 → 아차산성  (근거 B등급, 키워드: 아차산성)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '아차산성', '관광지', NULL,
       37.5588, 127.1048, '서울특별시 광진구 광장동', '서울특별시',
       'SPOT', 'TOUR_API', '126518', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126518'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 65881, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126518'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 65881)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 65881 AND cp.place_id = p.place_id
  );

-- [65881/평양성] D-지명 → 단양 온달산성  (근거 B등급, 키워드: 온달산성)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '단양 온달산성', '관광지', NULL,
       37.0575755408596, 128.484646133408, '충청북도 단양군 영춘면 하리', '충청북도',
       'SPOT', 'TOUR_API', '125978', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '125978'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 65881, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '125978'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 65881)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 65881 AND cp.place_id = p.place_id
  );

-- [660360/노량: 죽음의 바다] D-지명 → 충렬사(통영)  (근거 B등급, 키워드: 충렬사)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '충렬사(통영)', '관광지', NULL,
       34.8466459874541, 128.417434100219, '경상남도 통영시 여황로 251', '경상남도',
       'SPOT', 'TOUR_API', '126662', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126662'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 660360, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126662'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 660360)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 660360 AND cp.place_id = p.place_id
  );

-- [660360/노량: 죽음의 바다] D-지명 → 남해 관음포 이충무공 유적  (근거 B등급, 키워드: 관음포)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '남해 관음포 이충무공 유적', '관광지', NULL,
       34.912547961, 127.8573559204, '경상남도 남해군 고현면 남해대로 3829', '경상남도',
       'SPOT', 'TOUR_API', '129480', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '129480'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 660360, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '129480'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 660360)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 660360 AND cp.place_id = p.place_id
  );

-- [660360/노량: 죽음의 바다] D-지명 → 남해 충렬사  (근거 B등급, 키워드: 충렬사)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '남해 충렬사', '관광지', NULL,
       34.9422116278, 127.8761229565, '경상남도 남해군 설천면 노량로183번길 27', '경상남도',
       'SPOT', 'TOUR_API', '128104', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '128104'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 660360, p.place_id, 3
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '128104'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 660360)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 660360 AND cp.place_id = p.place_id
  );

-- [770322/태조 왕건] D-지명 → 개태사(논산)  (근거 B등급, 키워드: 개태사)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '개태사(논산)', '관광지', NULL,
       36.2407281973, 127.2293710155, '충청남도 논산시 연산면 계백로 2614-11', '충청남도',
       'SPOT', 'TOUR_API', '125899', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '125899'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 770322, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '125899'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 770322)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 770322 AND cp.place_id = p.place_id
  );

-- [770322/태조 왕건] D-지명 → 태봉국 궁예왕 역사공원  (근거 B등급, 키워드: 궁예)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '태봉국 궁예왕 역사공원', '관광지', NULL,
       38.3074276895, 127.2280413362, '강원특별자치도 철원군 철원읍 두루미로 1877-1', '강원특별자치도',
       'SPOT', 'TOUR_API', '4090474', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '4090474'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 770322, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '4090474'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 770322)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 770322 AND cp.place_id = p.place_id
  );

-- [919207/서울의 봄] D-지명 → 경복궁  (근거 B등급, 키워드: 경복궁)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '경복궁', '관광지', NULL,
       37.5760307000494, 126.976721866063, '서울특별시 종로구 사직로 161 (세종로)', '서울특별시',
       'SPOT', 'TOUR_API', '126508', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126508'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 919207, p.place_id, 1
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126508'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 919207)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 919207 AND cp.place_id = p.place_id
  );

-- [919207/서울의 봄] D-지명 → 국립서울현충원  (근거 B등급, 키워드: 국립서울현충원)
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT '국립서울현충원', '관광지', NULL,
       37.5013020026689, 126.973234928244, '서울특별시 동작구 현충로 210', '서울특별시',
       'SPOT', 'TOUR_API', '126521', false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = '126521'
);
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT 919207, p.place_id, 2
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = '126521'
  AND EXISTS (SELECT 1 FROM content c WHERE c.id = 919207)
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = 919207 AND cp.place_id = p.place_id
  );

COMMIT;


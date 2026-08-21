-- =====================================================================
-- 더미 시드 데이터 (실데이터 기반: 제주 / 서울 관광지, 실제 위경도)
--   위경도가 실제라 하버사인 거리/소요시간이 말이 되게 나온다.
--   이미지 URL은 더미(dummy) — 외부 요청 안 함.
-- =====================================================================

-- ---------- place ----------
-- place_type/source 는 전부 시드 촬영지(SPOT/SEED). 식당·카페는 관광API 에서 온디맨드로 들어온다.
-- open_time/close_time/night_open 은 opening_hours 원문을 OpeningHoursParser 규칙대로 손으로 옮긴 값이다.
--   night_open = 20시 이후에도 열려 있는가. 하루 마지막 촬영지 자리 배치에 쓰인다.
INSERT INTO place (place_id, name, category, description, latitude, longitude, address, region, opening_hours, kakao_place_id, place_type, source, open_time, close_time, night_open) VALUES
 (1,  '성산일출봉',       '자연',   '유네스코 세계자연유산, 일출 명소',        33.458031, 126.942520, '제주 서귀포시 성산읍 일출로 284-12', '제주', '07:00~20:00',        'kakao-1001', 'SPOT', 'SEED', '07:00:00', '20:00:00', TRUE),
 (2,  '협재해수욕장',     '해변',   '에메랄드빛 바다와 비양도 전망',           33.394046, 126.239621, '제주 제주시 한림읍 협재리',           '제주', '상시개방',            'kakao-1002', 'SPOT', 'SEED', '00:00:00', '23:59:59', TRUE),
 (3,  '한라산국립공원',   '산',     '남한 최고봉, 사계절 트레킹',              33.361667, 126.529167, '제주 제주시 1100로 2070-61',          '제주', '05:00~일몰',          'kakao-1003', 'SPOT', 'SEED', '05:00:00', NULL,       FALSE),
 (4,  '우도',             '섬',     '소가 누운 형상의 섬, 땅콩아이스크림',     33.500623, 126.953014, '제주 제주시 우도면',                  '제주', '페리 운항시간',       'kakao-1004', 'SPOT', 'SEED', NULL,       NULL,       FALSE),
 (5,  '천지연폭포',       '폭포',   '밤에도 조명이 켜지는 3대 폭포',           33.246505, 126.554021, '제주 서귀포시 천지동',                '제주', '09:00~22:00',        'kakao-1005', 'SPOT', 'SEED', '09:00:00', '22:00:00', TRUE),
 (6,  '카멜리아힐',       '정원',   '동백 정원, 인생샷 명소',                  33.291560, 126.368970, '제주 서귀포시 안덕면 병악로 166',     '제주', '08:30~18:00',        'kakao-1006', 'SPOT', 'SEED', '08:30:00', '18:00:00', FALSE),
 (7,  '오설록티뮤지엄',   '카페',   '녹차밭과 티 뮤지엄',                      33.305500, 126.289600, '제주 서귀포시 안덕면 신화역사로 15',  '제주', '09:00~18:00',        'kakao-1007', 'SPOT', 'SEED', '09:00:00', '18:00:00', FALSE),
 (8,  '경복궁',           '고궁',   '조선의 법궁, 수문장 교대식',              37.579617, 126.977041, '서울 종로구 사직로 161',              '서울', '09:00~18:00(화휴무)', 'kakao-2001', 'SPOT', 'SEED', '09:00:00', '18:00:00', FALSE),
 (9,  '북촌한옥마을',     '전통마을', '한옥이 밀집한 전통 골목',               37.582604, 126.983001, '서울 종로구 계동길 37',               '서울', '상시개방',            'kakao-2002', 'SPOT', 'SEED', '00:00:00', '23:59:59', TRUE),
 (10, 'N서울타워',        '전망대', '남산 정상의 서울 야경 명소',              37.551169, 126.988227, '서울 용산구 남산공원길 105',          '서울', '10:00~23:00',        'kakao-2003', 'SPOT', 'SEED', '10:00:00', '23:00:00', TRUE),
 (11, '광장시장',         '시장',   '빈대떡·마약김밥 먹거리 천국',             37.570028, 126.999790, '서울 종로구 창경궁로 88',             '서울', '09:00~23:00',        'kakao-2004', 'SPOT', 'SEED', '09:00:00', '23:00:00', TRUE),
 (12, '인사동',           '거리',   '전통 공예와 찻집 거리',                   37.574040, 126.985620, '서울 종로구 인사동길',                '서울', '상시개방',            'kakao-2005', 'SPOT', 'SEED', '00:00:00', '23:59:59', TRUE),
 (13, '창덕궁',           '고궁',   '유네스코 세계유산, 후원이 아름다운 궁',   37.579391, 126.991001, '서울 종로구 율곡로 99',               '서울', '09:00~18:00(월휴무)', 'kakao-2006', 'SPOT', 'SEED', '09:00:00', '18:00:00', FALSE),
 (14, '홍대',             '거리',   '젊음의 거리, 버스킹과 카페',              37.556279, 126.923657, '서울 마포구 양화로',                  '서울', '상시개방',            'kakao-2007', 'SPOT', 'SEED', '00:00:00', '23:59:59', TRUE);

-- ---------- place_image ----------
INSERT INTO place_image (place_id, image_url, is_primary, sort_order) VALUES
 (1,  'https://cdn.itda.example/places/1/main.jpg',  TRUE,  0),
 (1,  'https://cdn.itda.example/places/1/sub1.jpg',  FALSE, 1),
 (2,  'https://cdn.itda.example/places/2/main.jpg',  TRUE,  0),
 (3,  'https://cdn.itda.example/places/3/main.jpg',  TRUE,  0),
 (4,  'https://cdn.itda.example/places/4/main.jpg',  TRUE,  0),
 (4,  'https://cdn.itda.example/places/4/sub1.jpg',  FALSE, 1),
 (5,  'https://cdn.itda.example/places/5/main.jpg',  TRUE,  0),
 (6,  'https://cdn.itda.example/places/6/main.jpg',  TRUE,  0),
 (7,  'https://cdn.itda.example/places/7/main.jpg',  TRUE,  0),
 (8,  'https://cdn.itda.example/places/8/main.jpg',  TRUE,  0),
 (9,  'https://cdn.itda.example/places/9/main.jpg',  TRUE,  0),
 (10, 'https://cdn.itda.example/places/10/main.jpg', TRUE,  0),
 (11, 'https://cdn.itda.example/places/11/main.jpg', TRUE,  0),
 (12, 'https://cdn.itda.example/places/12/main.jpg', TRUE,  0),
 (13, 'https://cdn.itda.example/places/13/main.jpg', TRUE,  0),
 (14, 'https://cdn.itda.example/places/14/main.jpg', TRUE,  0);

-- ---------- content (박세현 placeholder) ----------
INSERT INTO content (content_id, title, thumbnail_url) VALUES
 (1, '제주 인생샷 코스',     'https://cdn.itda.example/contents/1/thumb.jpg'),
 (2, '서울 고궁 나들이',     'https://cdn.itda.example/contents/2/thumb.jpg'),
 (3, '서울 야경 & 먹방',     'https://cdn.itda.example/contents/3/thumb.jpg');

-- ---------- content_place (recommend_order) ----------
-- content 1: 제주 인생샷 코스
INSERT INTO content_place (content_id, place_id, recommend_order) VALUES
 (1, 1, 1),   -- 성산일출봉
 (1, 4, 2),   -- 우도
 (1, 6, 3),   -- 카멜리아힐
 (1, 7, 4),   -- 오설록티뮤지엄
 (1, 5, 5);   -- 천지연폭포 (대안 후보로도 사용됨)
-- content 2: 서울 고궁 나들이
INSERT INTO content_place (content_id, place_id, recommend_order) VALUES
 (2, 8,  1),  -- 경복궁
 (2, 9,  2),  -- 북촌한옥마을
 (2, 13, 3),  -- 창덕궁
 (2, 12, 4);  -- 인사동
-- content 3: 서울 야경 & 먹방
INSERT INTO content_place (content_id, place_id, recommend_order) VALUES
 (3, 10, 1),  -- N서울타워
 (3, 11, 2),  -- 광장시장
 (3, 14, 3);  -- 홍대

-- ---------- bookmark (박세현 placeholder, 장소 전용, user_id=1 데모용) ----------
INSERT INTO bookmark (user_id, place_id) VALUES
 (1, 1),   -- user 1 이 성산일출봉 북마크
 (1, 8);   -- user 1 이 경복궁 북마크

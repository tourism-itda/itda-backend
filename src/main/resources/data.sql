-- =====================================================================
-- ⚠️ 플레이스홀더 데이터 — 콘텐츠·촬영지 매핑 실데이터가 아직 없어서 검증용으로 넣었다.
-- 실제 촬영지 데이터가 들어오면 이 INSERT 문들은 지워야 한다.
--
-- content_id = 900000001 은 TMDB 영화 id 와 절대 충돌하지 않도록 일부러 9자리로 크게 잡은
-- 가짜 값이다 (TMDB id 는 현재 200만 미만). 실제 콘텐츠로 테스트하려면 먼저
-- POST /api/contents/{실제 tmdbId} 로 콘텐츠를 저장한 뒤, 그 id 로 content_spot 을 새로 넣을 것.
-- =====================================================================

INSERT INTO content (id, tmdb_id, title, release_year, media_type, overview)
VALUES (900000001, 900000001, '[플레이스홀더] 테스트 콘텐츠', 2024, 'MOVIE', '촬영지 매핑 검증용 임시 데이터입니다.')
ON CONFLICT (id) DO NOTHING;

INSERT INTO spot (id, name, category, description, image_url, opening_hours, region, latitude, longitude) VALUES
 (1, '경복궁',       '고궁',     '조선의 법궁, 수문장 교대식',    'https://cdn.itda.example/spots/1/main.jpg', '09:00~18:00(화휴무)', '서울', 37.579617, 126.977041),
 (2, '북촌한옥마을',  '전통마을', '한옥이 밀집한 전통 골목',       'https://cdn.itda.example/spots/2/main.jpg', '상시개방',            '서울', 37.582604, 126.983001),
 (3, 'N서울타워',     '전망대',   '남산 정상의 서울 야경 명소',    'https://cdn.itda.example/spots/3/main.jpg', '10:00~23:00',         '서울', 37.551169, 126.988227)
ON CONFLICT (id) DO NOTHING;

INSERT INTO content_spot (id, content_id, spot_id, recommend_order) VALUES
 (1, 900000001, 1, 1),
 (2, 900000001, 2, 2),
 (3, 900000001, 3, 3)
ON CONFLICT (id) DO NOTHING;

-- place_image / place.description backfill from TourAPI
-- generated: 2026-09-18 00:32
SET client_encoding TO 'UTF8';
\set ON_ERROR_STOP on
BEGIN;

-- [5] 공산성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 5, 'https://tong.visitkorea.or.kr/cms/resource/85/3038485_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=5 AND image_url='https://tong.visitkorea.or.kr/cms/resource/85/3038485_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 5, 'https://tong.visitkorea.or.kr/cms/resource/81/3038481_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=5 AND image_url='https://tong.visitkorea.or.kr/cms/resource/81/3038481_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 5, 'https://tong.visitkorea.or.kr/cms/resource/82/3038482_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=5 AND image_url='https://tong.visitkorea.or.kr/cms/resource/82/3038482_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 5, 'https://tong.visitkorea.or.kr/cms/resource/83/3038483_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=5 AND image_url='https://tong.visitkorea.or.kr/cms/resource/83/3038483_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 5, 'https://tong.visitkorea.or.kr/cms/resource/84/3038484_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=5 AND image_url='https://tong.visitkorea.or.kr/cms/resource/84/3038484_image2_1.JPG');
-- [7] 부소산성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 7, 'https://tong.visitkorea.or.kr/cms/resource/56/1601156_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=7 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/1601156_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 7, 'https://tong.visitkorea.or.kr/cms/resource/64/1601164_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=7 AND image_url='https://tong.visitkorea.or.kr/cms/resource/64/1601164_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 7, 'https://tong.visitkorea.or.kr/cms/resource/70/1601170_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=7 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/1601170_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 7, 'https://tong.visitkorea.or.kr/cms/resource/77/1601177_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=7 AND image_url='https://tong.visitkorea.or.kr/cms/resource/77/1601177_image2_1.jpg');
-- [8] 정림사지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 8, 'https://tong.visitkorea.or.kr/cms/resource/27/3563827_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=8 AND image_url='https://tong.visitkorea.or.kr/cms/resource/27/3563827_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 8, 'https://tong.visitkorea.or.kr/cms/resource/29/3563829_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=8 AND image_url='https://tong.visitkorea.or.kr/cms/resource/29/3563829_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 8, 'https://tong.visitkorea.or.kr/cms/resource/21/4106521_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=8 AND image_url='https://tong.visitkorea.or.kr/cms/resource/21/4106521_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 8, 'https://tong.visitkorea.or.kr/cms/resource/63/4110563_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=8 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/4110563_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 8, 'https://tong.visitkorea.or.kr/cms/resource/64/4110564_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=8 AND image_url='https://tong.visitkorea.or.kr/cms/resource/64/4110564_image2_1.jpg');
-- [9] 첨성대
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 9, 'https://tong.visitkorea.or.kr/cms/resource/35/4097535_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=9 AND image_url='https://tong.visitkorea.or.kr/cms/resource/35/4097535_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 9, 'https://tong.visitkorea.or.kr/cms/resource/57/3100157_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=9 AND image_url='https://tong.visitkorea.or.kr/cms/resource/57/3100157_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 9, 'https://tong.visitkorea.or.kr/cms/resource/75/3422675_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=9 AND image_url='https://tong.visitkorea.or.kr/cms/resource/75/3422675_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 9, 'https://tong.visitkorea.or.kr/cms/resource/69/3554369_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=9 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/3554369_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 9, 'https://tong.visitkorea.or.kr/cms/resource/81/3554381_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=9 AND image_url='https://tong.visitkorea.or.kr/cms/resource/81/3554381_image2_1.jpg');
-- [10] 황룡사지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 10, 'http://tong.visitkorea.or.kr/cms/resource/86/3542386_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=10 AND image_url='http://tong.visitkorea.or.kr/cms/resource/86/3542386_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 10, 'http://tong.visitkorea.or.kr/cms/resource/87/3542387_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=10 AND image_url='http://tong.visitkorea.or.kr/cms/resource/87/3542387_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 10, 'http://tong.visitkorea.or.kr/cms/resource/88/3542388_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=10 AND image_url='http://tong.visitkorea.or.kr/cms/resource/88/3542388_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 10, 'http://tong.visitkorea.or.kr/cms/resource/89/3542389_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=10 AND image_url='http://tong.visitkorea.or.kr/cms/resource/89/3542389_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 10, 'http://tong.visitkorea.or.kr/cms/resource/90/3542390_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=10 AND image_url='http://tong.visitkorea.or.kr/cms/resource/90/3542390_image2_1.jpg');
-- [11] 대릉원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 11, 'http://tong.visitkorea.or.kr/cms/resource/19/3491219_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=11 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/3491219_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 11, 'http://tong.visitkorea.or.kr/cms/resource/17/3491217_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=11 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3491217_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 11, 'http://tong.visitkorea.or.kr/cms/resource/20/3491220_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=11 AND image_url='http://tong.visitkorea.or.kr/cms/resource/20/3491220_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 11, 'http://tong.visitkorea.or.kr/cms/resource/21/3491221_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=11 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3491221_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 11, 'http://tong.visitkorea.or.kr/cms/resource/22/3491222_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=11 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3491222_image2_1.jpg');
-- [14] 수로왕릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 14, 'https://tong.visitkorea.or.kr/cms/resource/45/3510645_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=14 AND image_url='https://tong.visitkorea.or.kr/cms/resource/45/3510645_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 14, 'https://tong.visitkorea.or.kr/cms/resource/43/3510643_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=14 AND image_url='https://tong.visitkorea.or.kr/cms/resource/43/3510643_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 14, 'https://tong.visitkorea.or.kr/cms/resource/44/3510644_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=14 AND image_url='https://tong.visitkorea.or.kr/cms/resource/44/3510644_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 14, 'https://tong.visitkorea.or.kr/cms/resource/46/3510646_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=14 AND image_url='https://tong.visitkorea.or.kr/cms/resource/46/3510646_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 14, 'https://tong.visitkorea.or.kr/cms/resource/47/3510647_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=14 AND image_url='https://tong.visitkorea.or.kr/cms/resource/47/3510647_image2_1.jpg');
-- [17] 불국사
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 17, 'https://tong.visitkorea.or.kr/cms/resource/70/3506170_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=17 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/3506170_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 17, 'https://tong.visitkorea.or.kr/cms/resource/65/3506165_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=17 AND image_url='https://tong.visitkorea.or.kr/cms/resource/65/3506165_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 17, 'https://tong.visitkorea.or.kr/cms/resource/66/3506166_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=17 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/3506166_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 17, 'https://tong.visitkorea.or.kr/cms/resource/68/3506168_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=17 AND image_url='https://tong.visitkorea.or.kr/cms/resource/68/3506168_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 17, 'https://tong.visitkorea.or.kr/cms/resource/69/3506169_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=17 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/3506169_image2_1.jpg');
-- [18] 석굴암
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 18, 'https://tong.visitkorea.or.kr/cms/resource/69/3581269_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=18 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/3581269_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 18, 'https://tong.visitkorea.or.kr/cms/resource/65/3581265_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=18 AND image_url='https://tong.visitkorea.or.kr/cms/resource/65/3581265_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 18, 'https://tong.visitkorea.or.kr/cms/resource/66/3581266_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=18 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/3581266_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 18, 'https://tong.visitkorea.or.kr/cms/resource/67/3581267_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=18 AND image_url='https://tong.visitkorea.or.kr/cms/resource/67/3581267_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 18, 'https://tong.visitkorea.or.kr/cms/resource/68/3581268_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=18 AND image_url='https://tong.visitkorea.or.kr/cms/resource/68/3581268_image2_1.jpg');
-- [29] 경복궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 29, 'http://tong.visitkorea.or.kr/cms/resource/80/3553780_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=29 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3553780_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 29, 'http://tong.visitkorea.or.kr/cms/resource/81/3553781_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=29 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3553781_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 29, 'http://tong.visitkorea.or.kr/cms/resource/82/3553782_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=29 AND image_url='http://tong.visitkorea.or.kr/cms/resource/82/3553782_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 29, 'http://tong.visitkorea.or.kr/cms/resource/83/3553783_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=29 AND image_url='http://tong.visitkorea.or.kr/cms/resource/83/3553783_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 29, 'http://tong.visitkorea.or.kr/cms/resource/84/3553784_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=29 AND image_url='http://tong.visitkorea.or.kr/cms/resource/84/3553784_image2_1.JPG');
-- [30] 창덕궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 30, 'http://tong.visitkorea.or.kr/cms/resource/78/3384878_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=30 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3384878_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 30, 'http://tong.visitkorea.or.kr/cms/resource/79/3384879_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=30 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3384879_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 30, 'http://tong.visitkorea.or.kr/cms/resource/80/3384880_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=30 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3384880_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 30, 'http://tong.visitkorea.or.kr/cms/resource/81/3384881_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=30 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3384881_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 30, 'http://tong.visitkorea.or.kr/cms/resource/82/3384882_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=30 AND image_url='http://tong.visitkorea.or.kr/cms/resource/82/3384882_image2_1.JPG');
-- [31] 종묘
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 31, 'http://tong.visitkorea.or.kr/cms/resource/98/3413898_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=31 AND image_url='http://tong.visitkorea.or.kr/cms/resource/98/3413898_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 31, 'http://tong.visitkorea.or.kr/cms/resource/99/3413899_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=31 AND image_url='http://tong.visitkorea.or.kr/cms/resource/99/3413899_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 31, 'http://tong.visitkorea.or.kr/cms/resource/00/3413900_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=31 AND image_url='http://tong.visitkorea.or.kr/cms/resource/00/3413900_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 31, 'http://tong.visitkorea.or.kr/cms/resource/01/3413901_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=31 AND image_url='http://tong.visitkorea.or.kr/cms/resource/01/3413901_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 31, 'http://tong.visitkorea.or.kr/cms/resource/02/3413902_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=31 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/3413902_image2_1.jpg');
-- [32] 수원 화성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 32, 'https://tong.visitkorea.or.kr/cms/resource_photo/97/3478597_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=32 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/97/3478597_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 32, 'https://tong.visitkorea.or.kr/cms/resource_photo/91/3478491_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=32 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/91/3478491_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 32, 'https://tong.visitkorea.or.kr/cms/resource_photo/92/3478492_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=32 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/92/3478492_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 32, 'https://tong.visitkorea.or.kr/cms/resource_photo/57/3478557_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=32 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/57/3478557_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 32, 'https://tong.visitkorea.or.kr/cms/resource_photo/83/3478483_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=32 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/83/3478483_image2_1.jpg');
-- [33] 덕수궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 33, 'http://tong.visitkorea.or.kr/cms/resource/50/2658350_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=33 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/2658350_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 33, 'http://tong.visitkorea.or.kr/cms/resource/49/2658349_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=33 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/2658349_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 33, 'http://tong.visitkorea.or.kr/cms/resource/51/2658351_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=33 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/2658351_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 33, 'http://tong.visitkorea.or.kr/cms/resource/52/2658352_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=33 AND image_url='http://tong.visitkorea.or.kr/cms/resource/52/2658352_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 33, 'http://tong.visitkorea.or.kr/cms/resource/38/2690538_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=33 AND image_url='http://tong.visitkorea.or.kr/cms/resource/38/2690538_image2_1.jpg');
-- [34] 석조전
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 34, 'https://tong.visitkorea.or.kr/cms/resource/10/4102010_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=34 AND image_url='https://tong.visitkorea.or.kr/cms/resource/10/4102010_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 34, 'https://tong.visitkorea.or.kr/cms/resource/02/4073902_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=34 AND image_url='https://tong.visitkorea.or.kr/cms/resource/02/4073902_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 34, 'https://tong.visitkorea.or.kr/cms/resource/03/4073903_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=34 AND image_url='https://tong.visitkorea.or.kr/cms/resource/03/4073903_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 34, 'https://tong.visitkorea.or.kr/cms/resource/04/4073904_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=34 AND image_url='https://tong.visitkorea.or.kr/cms/resource/04/4073904_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 34, 'https://tong.visitkorea.or.kr/cms/resource/05/4073905_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=34 AND image_url='https://tong.visitkorea.or.kr/cms/resource/05/4073905_image2_1.JPG');
-- [35] 환구단
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 35, 'https://tong.visitkorea.or.kr/cms/resource/73/4002373_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=35 AND image_url='https://tong.visitkorea.or.kr/cms/resource/73/4002373_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 35, 'https://tong.visitkorea.or.kr/cms/resource/69/4002369_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=35 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/4002369_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 35, 'https://tong.visitkorea.or.kr/cms/resource/70/4002370_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=35 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/4002370_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 35, 'https://tong.visitkorea.or.kr/cms/resource/72/4002372_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=35 AND image_url='https://tong.visitkorea.or.kr/cms/resource/72/4002372_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 35, 'https://tong.visitkorea.or.kr/cms/resource/74/4002374_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=35 AND image_url='https://tong.visitkorea.or.kr/cms/resource/74/4002374_image2_1.jpg');
-- [36] 독립문
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 36, 'http://tong.visitkorea.or.kr/cms/resource/07/3530107_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=36 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3530107_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 36, 'http://tong.visitkorea.or.kr/cms/resource/08/3530108_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=36 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3530108_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 36, 'http://tong.visitkorea.or.kr/cms/resource/09/3530109_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=36 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3530109_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 36, 'http://tong.visitkorea.or.kr/cms/resource/10/3530110_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=36 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3530110_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 36, 'http://tong.visitkorea.or.kr/cms/resource/11/3530111_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=36 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/3530111_image2_1.jpg');
-- [37] 서대문형무소역사관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 37, 'https://tong.visitkorea.or.kr/cms/resource/29/3520329_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=37 AND image_url='https://tong.visitkorea.or.kr/cms/resource/29/3520329_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 37, 'https://tong.visitkorea.or.kr/cms/resource/56/3509056_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=37 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/3509056_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 37, 'https://tong.visitkorea.or.kr/cms/resource/57/3509057_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=37 AND image_url='https://tong.visitkorea.or.kr/cms/resource/57/3509057_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 37, 'https://tong.visitkorea.or.kr/cms/resource/62/3509062_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=37 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/3509062_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 37, 'https://tong.visitkorea.or.kr/cms/resource/63/3509063_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=37 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/3509063_image2_1.jpg');
-- [38] 탑골공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 38, 'http://tong.visitkorea.or.kr/cms/resource/19/3414019_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=38 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/3414019_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 38, 'http://tong.visitkorea.or.kr/cms/resource/18/3414018_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=38 AND image_url='http://tong.visitkorea.or.kr/cms/resource/18/3414018_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 38, 'http://tong.visitkorea.or.kr/cms/resource/20/3414020_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=38 AND image_url='http://tong.visitkorea.or.kr/cms/resource/20/3414020_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 38, 'http://tong.visitkorea.or.kr/cms/resource/21/3414021_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=38 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3414021_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 38, 'http://tong.visitkorea.or.kr/cms/resource/22/3414022_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=38 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3414022_image2_1.jpg');
-- [39] 대한민국역사박물관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 39, 'http://tong.visitkorea.or.kr/cms/resource/06/2987806_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=39 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/2987806_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 39, 'http://tong.visitkorea.or.kr/cms/resource/02/2987802_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=39 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/2987802_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 39, 'http://tong.visitkorea.or.kr/cms/resource/03/2987803_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=39 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/2987803_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 39, 'http://tong.visitkorea.or.kr/cms/resource/04/2987804_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=39 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/2987804_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 39, 'http://tong.visitkorea.or.kr/cms/resource/05/2987805_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=39 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/2987805_image2_1.jpg');
-- [40] 경교장
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 40, 'http://tong.visitkorea.or.kr/cms/resource/70/3081470_image2_1.jpeg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=40 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3081470_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 40, 'http://tong.visitkorea.or.kr/cms/resource/67/3081467_image2_1.jpeg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=40 AND image_url='http://tong.visitkorea.or.kr/cms/resource/67/3081467_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 40, 'http://tong.visitkorea.or.kr/cms/resource/68/3081468_image2_1.jpeg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=40 AND image_url='http://tong.visitkorea.or.kr/cms/resource/68/3081468_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 40, 'http://tong.visitkorea.or.kr/cms/resource/69/3081469_image2_1.jpeg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=40 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3081469_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 40, 'http://tong.visitkorea.or.kr/cms/resource/71/3081471_image2_1.jpeg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=40 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3081471_image2_1.jpeg');
-- [42] 대한민국역사박물관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 42, 'http://tong.visitkorea.or.kr/cms/resource/06/2987806_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=42 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/2987806_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 42, 'http://tong.visitkorea.or.kr/cms/resource/02/2987802_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=42 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/2987802_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 42, 'http://tong.visitkorea.or.kr/cms/resource/03/2987803_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=42 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/2987803_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 42, 'http://tong.visitkorea.or.kr/cms/resource/04/2987804_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=42 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/2987804_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 42, 'http://tong.visitkorea.or.kr/cms/resource/05/2987805_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=42 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/2987805_image2_1.jpg');
-- [48] 헌릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 48, 'http://tong.visitkorea.or.kr/cms/resource/76/3530376_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=48 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3530376_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 48, 'http://tong.visitkorea.or.kr/cms/resource/77/3530377_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=48 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3530377_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 48, 'http://tong.visitkorea.or.kr/cms/resource/78/3530378_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=48 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3530378_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 48, 'http://tong.visitkorea.or.kr/cms/resource/79/3530379_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=48 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3530379_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 48, 'http://tong.visitkorea.or.kr/cms/resource/80/3530380_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=48 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3530380_image2_1.jpg');
-- [53] 광릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 53, 'http://tong.visitkorea.or.kr/cms/resource/73/3466173_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=53 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3466173_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 53, 'http://tong.visitkorea.or.kr/cms/resource/71/3466171_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=53 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3466171_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 53, 'http://tong.visitkorea.or.kr/cms/resource/72/3466172_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=53 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3466172_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 53, 'http://tong.visitkorea.or.kr/cms/resource/74/3466174_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=53 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3466174_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 53, 'http://tong.visitkorea.or.kr/cms/resource/75/3466175_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=53 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3466175_image2_1.JPG');
-- [62] 남한산성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 62, 'http://tong.visitkorea.or.kr/cms/resource/56/3350256_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=62 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3350256_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 62, 'http://tong.visitkorea.or.kr/cms/resource/57/3350257_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=62 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3350257_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 62, 'http://tong.visitkorea.or.kr/cms/resource/58/3350258_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=62 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3350258_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 62, 'http://tong.visitkorea.or.kr/cms/resource/59/3350259_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=62 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3350259_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 62, 'http://tong.visitkorea.or.kr/cms/resource/60/3350260_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=62 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3350260_image2_1.jpg');
-- [67] 의릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 67, 'https://tong.visitkorea.or.kr/cms/resource/89/3382889_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=67 AND image_url='https://tong.visitkorea.or.kr/cms/resource/89/3382889_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 67, 'https://tong.visitkorea.or.kr/cms/resource/90/3382890_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=67 AND image_url='https://tong.visitkorea.or.kr/cms/resource/90/3382890_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 67, 'https://tong.visitkorea.or.kr/cms/resource/91/3382891_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=67 AND image_url='https://tong.visitkorea.or.kr/cms/resource/91/3382891_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 67, 'https://tong.visitkorea.or.kr/cms/resource/93/3382893_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=67 AND image_url='https://tong.visitkorea.or.kr/cms/resource/93/3382893_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 67, 'https://tong.visitkorea.or.kr/cms/resource/94/3382894_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=67 AND image_url='https://tong.visitkorea.or.kr/cms/resource/94/3382894_image2_1.JPG');
-- [68] 경희궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 68, 'http://tong.visitkorea.or.kr/cms/resource/55/3384855_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=68 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/3384855_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 68, 'http://tong.visitkorea.or.kr/cms/resource/56/3384856_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=68 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3384856_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 68, 'http://tong.visitkorea.or.kr/cms/resource/57/3384857_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=68 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3384857_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 68, 'http://tong.visitkorea.or.kr/cms/resource/58/3384858_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=68 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3384858_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 68, 'http://tong.visitkorea.or.kr/cms/resource/59/3384859_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=68 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3384859_image2_1.JPG');
-- [70] 화성행궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 70, 'https://tong.visitkorea.or.kr/cms/resource/72/2849972_image2_1.jpeg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=70 AND image_url='https://tong.visitkorea.or.kr/cms/resource/72/2849972_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 70, 'https://tong.visitkorea.or.kr/cms/resource/70/2849970_image2_1.jpeg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=70 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/2849970_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 70, 'https://tong.visitkorea.or.kr/cms/resource/09/4082009_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=70 AND image_url='https://tong.visitkorea.or.kr/cms/resource/09/4082009_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 70, 'https://tong.visitkorea.or.kr/cms/resource/13/4082013_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=70 AND image_url='https://tong.visitkorea.or.kr/cms/resource/13/4082013_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 70, 'https://tong.visitkorea.or.kr/cms/resource/15/4082015_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=70 AND image_url='https://tong.visitkorea.or.kr/cms/resource/15/4082015_image2_1.jpg');
-- [71] 융릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 71, 'https://tong.visitkorea.or.kr/cms/resource/68/4080968_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=71 AND image_url='https://tong.visitkorea.or.kr/cms/resource/68/4080968_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 71, 'https://tong.visitkorea.or.kr/cms/resource/52/3003952_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=71 AND image_url='https://tong.visitkorea.or.kr/cms/resource/52/3003952_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 71, 'https://tong.visitkorea.or.kr/cms/resource/53/3003953_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=71 AND image_url='https://tong.visitkorea.or.kr/cms/resource/53/3003953_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 71, 'https://tong.visitkorea.or.kr/cms/resource/58/3003958_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=71 AND image_url='https://tong.visitkorea.or.kr/cms/resource/58/3003958_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 71, 'https://tong.visitkorea.or.kr/cms/resource/66/4080966_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=71 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/4080966_image2_1.jpg');
-- [72] 인릉
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 72, 'http://tong.visitkorea.or.kr/cms/resource/76/3530376_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=72 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3530376_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 72, 'http://tong.visitkorea.or.kr/cms/resource/77/3530377_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=72 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3530377_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 72, 'http://tong.visitkorea.or.kr/cms/resource/78/3530378_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=72 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3530378_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 72, 'http://tong.visitkorea.or.kr/cms/resource/79/3530379_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=72 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3530379_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 72, 'http://tong.visitkorea.or.kr/cms/resource/80/3530380_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=72 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3530380_image2_1.jpg');
-- [84] 도산서원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 84, 'https://tong.visitkorea.or.kr/cms/resource/06/4064706_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=84 AND image_url='https://tong.visitkorea.or.kr/cms/resource/06/4064706_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 84, 'https://tong.visitkorea.or.kr/cms/resource/70/3422970_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=84 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/3422970_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 84, 'https://tong.visitkorea.or.kr/cms/resource/72/3422972_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=84 AND image_url='https://tong.visitkorea.or.kr/cms/resource/72/3422972_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 84, 'https://tong.visitkorea.or.kr/cms/resource/73/3422973_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=84 AND image_url='https://tong.visitkorea.or.kr/cms/resource/73/3422973_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 84, 'https://tong.visitkorea.or.kr/cms/resource/24/3437624_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=84 AND image_url='https://tong.visitkorea.or.kr/cms/resource/24/3437624_image2_1.png');
-- [85] 분황사
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 85, 'http://tong.visitkorea.or.kr/cms/resource/72/3538772_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=85 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3538772_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 85, 'http://tong.visitkorea.or.kr/cms/resource/71/3538771_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=85 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3538771_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 85, 'http://tong.visitkorea.or.kr/cms/resource/73/3538773_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=85 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3538773_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 85, 'http://tong.visitkorea.or.kr/cms/resource/74/3538774_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=85 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3538774_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 85, 'http://tong.visitkorea.or.kr/cms/resource/75/3538775_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=85 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3538775_image2_1.jpg');
-- [99] 건청궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 99, 'http://tong.visitkorea.or.kr/cms/resource/10/2527110_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=99 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/2527110_image2_1.jpg');
-- [100] 재매정
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 100, 'http://tong.visitkorea.or.kr/cms/resource/08/3412508_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=100 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3412508_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 100, 'http://tong.visitkorea.or.kr/cms/resource/07/3412507_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=100 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3412507_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 100, 'http://tong.visitkorea.or.kr/cms/resource/09/3412509_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=100 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3412509_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 100, 'http://tong.visitkorea.or.kr/cms/resource/10/3412510_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=100 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3412510_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 100, 'http://tong.visitkorea.or.kr/cms/resource/11/3412511_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=100 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/3412511_image2_1.jpg');
-- [105] 부석사
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 105, 'https://tong.visitkorea.or.kr/cms/resource/43/3572443_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=105 AND image_url='https://tong.visitkorea.or.kr/cms/resource/43/3572443_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 105, 'https://tong.visitkorea.or.kr/cms/resource/42/3572442_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=105 AND image_url='https://tong.visitkorea.or.kr/cms/resource/42/3572442_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 105, 'https://tong.visitkorea.or.kr/cms/resource/44/3572444_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=105 AND image_url='https://tong.visitkorea.or.kr/cms/resource/44/3572444_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 105, 'https://tong.visitkorea.or.kr/cms/resource/45/3572445_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=105 AND image_url='https://tong.visitkorea.or.kr/cms/resource/45/3572445_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 105, 'https://tong.visitkorea.or.kr/cms/resource/52/3572452_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=105 AND image_url='https://tong.visitkorea.or.kr/cms/resource/52/3572452_image2_1.jpg');
-- [106] 효창공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 106, 'https://tong.visitkorea.or.kr/cms/resource/87/3505687_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=106 AND image_url='https://tong.visitkorea.or.kr/cms/resource/87/3505687_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 106, 'https://tong.visitkorea.or.kr/cms/resource/88/3505688_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=106 AND image_url='https://tong.visitkorea.or.kr/cms/resource/88/3505688_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 106, 'https://tong.visitkorea.or.kr/cms/resource/89/3505689_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=106 AND image_url='https://tong.visitkorea.or.kr/cms/resource/89/3505689_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 106, 'https://tong.visitkorea.or.kr/cms/resource/90/3505690_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=106 AND image_url='https://tong.visitkorea.or.kr/cms/resource/90/3505690_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 106, 'https://tong.visitkorea.or.kr/cms/resource/91/3505691_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=106 AND image_url='https://tong.visitkorea.or.kr/cms/resource/91/3505691_image2_1.jpg');
-- [113] 백범김구기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 113, 'http://tong.visitkorea.or.kr/cms/resource/98/3521598_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=113 AND image_url='http://tong.visitkorea.or.kr/cms/resource/98/3521598_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 113, 'http://tong.visitkorea.or.kr/cms/resource/93/3521593_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=113 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3521593_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 113, 'http://tong.visitkorea.or.kr/cms/resource/96/3521596_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=113 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3521596_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 113, 'http://tong.visitkorea.or.kr/cms/resource/03/3521603_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=113 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/3521603_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 113, 'http://tong.visitkorea.or.kr/cms/resource/04/3521604_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=113 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/3521604_image2_1.jpg');
-- [118] 낙성대
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 118, 'https://tong.visitkorea.or.kr/cms/resource/63/3503463_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=118 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/3503463_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 118, 'https://tong.visitkorea.or.kr/cms/resource/59/3503459_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=118 AND image_url='https://tong.visitkorea.or.kr/cms/resource/59/3503459_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 118, 'https://tong.visitkorea.or.kr/cms/resource/60/3503460_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=118 AND image_url='https://tong.visitkorea.or.kr/cms/resource/60/3503460_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 118, 'https://tong.visitkorea.or.kr/cms/resource/61/3503461_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=118 AND image_url='https://tong.visitkorea.or.kr/cms/resource/61/3503461_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 118, 'https://tong.visitkorea.or.kr/cms/resource/62/3503462_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=118 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/3503462_image2_1.jpg');
-- [122] 민영환 자결터
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 122, 'http://tong.visitkorea.or.kr/cms/resource/62/3050162_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=122 AND image_url='http://tong.visitkorea.or.kr/cms/resource/62/3050162_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 122, 'http://tong.visitkorea.or.kr/cms/resource/63/3050163_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=122 AND image_url='http://tong.visitkorea.or.kr/cms/resource/63/3050163_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 122, 'http://tong.visitkorea.or.kr/cms/resource/64/3050164_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=122 AND image_url='http://tong.visitkorea.or.kr/cms/resource/64/3050164_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 122, 'http://tong.visitkorea.or.kr/cms/resource/65/3050165_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=122 AND image_url='http://tong.visitkorea.or.kr/cms/resource/65/3050165_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 122, 'http://tong.visitkorea.or.kr/cms/resource/66/3050166_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=122 AND image_url='http://tong.visitkorea.or.kr/cms/resource/66/3050166_image2_1.JPG');
UPDATE place SET description='민영환 자결터는 민영환이 을사늑약에 항거하다 자결하여 순국한 곳이다. 조선말의 충신 민영환은 을사늑약의 폐기를 궁궐 앞에서 주장하다가 일본 헌병의 강제해산으로 실패하자 1905년 11월 30일 새벽 6시에 청지기의 집에서 국민, 외교사절, 황제에게 보내는 유서 3통을 남기고 자결하였다. 순국 후 피 묻은 옷과 칼을 산청 마루방에 걸어두었는데 이듬해 5월 산청의 문을 열어보니 대나무 네 줄기가 마룻바닥과 피 묻은 옷을 뚫고 올라왔다고 한다. 사람들은 그의 충정이 혈죽으로 나타났다고 하여 이 나무를 절죽이라 하였다. 종로구 인사동 하나빌딩 앞 민영환의 자결터에 김충헌이 글씨를 쓰고 백문기가 조각한 추모 조형물이 세워져 있다. 이 조형물에는 그의 충정이 나타났다고 하는 혈죽을 상징하는 대나무와 창호문이 조각되어 있고 자결할 때 사용한 단검과 유서 등을 배치했다.' WHERE place_id=122 AND (description IS NULL OR description='');
UPDATE place SET description='남덕유산 동쪽 자락의 해발 600에 위치하고 있는 월성마을은 산간 지형에 자연적으로 부락이 형성된 전형적인 산촌마을로 마을 앞에는 작은 폭포와 여울을 만드는 월성계곡이 흐르고 있어 여름이면 물놀이를 하기에 좋다. 또한 계곡 옆 키 큰 나무들로 우거진 월성 숲은 시원한 그늘을 만들어 준다. 월성마을은 고랭지 농산물이 주 소득작물이며 표고버섯, 고랭지 야채, 10년산 산 더덕, 10년산 약 도라지, 사과, 우렁이쌀이 주 생산물이다.' WHERE place_id=123 AND (description IS NULL OR description='');
UPDATE place SET description='우리유황온천은 서울 도심 속에서 온천욕을 즐길 수 있는 곳이다. 강남과 강북의 접점인 잠실대교 북단 인근에 위치하고 있다. 우리유황온천은 지하 1,040m에서 분출된 천연유황온천수를 매일 신선하게 공급하고 있다. 주요 시설로는 온천욕 시설인 유황대온천탕, 유황열탕, 바가지탕과 부대시설인 토굴방, 동굴방, 아이스방, 히노끼탕 등이 있다.' WHERE place_id=124 AND (description IS NULL OR description='');
-- [125] 청석공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 125, 'http://tong.visitkorea.or.kr/cms/resource/43/3527243_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=125 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/3527243_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 125, 'http://tong.visitkorea.or.kr/cms/resource/40/3527240_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=125 AND image_url='http://tong.visitkorea.or.kr/cms/resource/40/3527240_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 125, 'http://tong.visitkorea.or.kr/cms/resource/41/3527241_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=125 AND image_url='http://tong.visitkorea.or.kr/cms/resource/41/3527241_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 125, 'http://tong.visitkorea.or.kr/cms/resource/42/3527242_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=125 AND image_url='http://tong.visitkorea.or.kr/cms/resource/42/3527242_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 125, 'http://tong.visitkorea.or.kr/cms/resource/44/3527244_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=125 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3527244_image2_1.jpg');
UPDATE place SET description='광주시민들의 여가와 휴식을 위해 경기도 광주시 쌍령동에 건립한 근린공원으로 체육공원과 잔디공원으로 구성되어 있다. 체육공원은 면적 4만 3,999㎡로서 족구장 2개소와 농구장 2개소, 인라인스케이트장, 미니축구장 등을 갖추고 있으며, 1.4㎞에 이르는 산책로와 지압로가 있다. 잔디공원은 면적 약 4만㎡로 잔디광장과 야구장이 들어서 있다. 간이공연장, 급수대, 벤치, 주차장, 화장실 등 편의시설도 갖추고 있으며, 반려견 출입도 가능하여 함께 산책하기 좋다.' WHERE place_id=125 AND (description IS NULL OR description='');
-- [126] 서울학도병참전기념비
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 126, 'http://tong.visitkorea.or.kr/cms/resource/99/3465699_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=126 AND image_url='http://tong.visitkorea.or.kr/cms/resource/99/3465699_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 126, 'http://tong.visitkorea.or.kr/cms/resource/93/3465693_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=126 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3465693_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 126, 'http://tong.visitkorea.or.kr/cms/resource/94/3465694_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=126 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/3465694_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 126, 'http://tong.visitkorea.or.kr/cms/resource/95/3465695_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=126 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3465695_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 126, 'http://tong.visitkorea.or.kr/cms/resource/96/3465696_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=126 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3465696_image2_1.jpg');
UPDATE place SET description='1950년 9월 28일 국군이 서울을 탈환하고 북진할 때, 국군은 포병으로 참전을 자원한 학도 의용병들을 용산고등학교에 집결하도록 하였다. 수많은 학생들 중에서 341명이 선발되어 10월 20일 서울을 출발, 다음날 21일 평양에 도착하여 육군 제17사단 18 포병 대대에 배속되었다. 그 이후 1953년 7월 23일 휴전 때까지 장교 임관하거나 기간요원으로 전선에서 활약하였고 이들 중 112명이 전사·실종되었다. 순국 학도탑은 용산고등학교 학생 80명이 포병으로 참전한 것 이외에도 다른 군부대에 많은 학우들이 참전하여 꽃다운 젊음을 조국에 바친 넋을 기리기 위하여 1956년 10월 23일에 용산고등학교에서 건립하였다. 서울 학도병 참전기념비는 ‘조국과 자유민주주의’를 수호하기 위하여 참전한 학도 의용병들의 숭고한 희생정신을 후세에 전하기 위해 참전 55주년을 기념하여 2005년 10월 20일에 서울학도포병동지회에서 건립하였다. 서울 학도포병 참전자 명비는 참전 65주년을 기념하여 2015년 10월 20일에 서울학도포병동지회에서 건립하였으며, 1950년 10월 20일 용산고에서 출정식을 하고 포병으로 참전한 서울 지역 학도의용군 341명의 이름이 새겨져 있다. 서울학도포병동지회와 용산고등학교, 용산고등학교 동창회 주관으로 매년 6월과 10월에 학도 의용병들의 숭고한 나라사랑 정신을 기리는 기념식을 개최하고 있다.' WHERE place_id=126 AND (description IS NULL OR description='');
-- [127] 곡교천 은행나무길
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 127, 'http://tong.visitkorea.or.kr/cms/resource/05/3535305_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=127 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/3535305_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 127, 'http://tong.visitkorea.or.kr/cms/resource/06/3535306_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=127 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3535306_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 127, 'http://tong.visitkorea.or.kr/cms/resource/07/3535307_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=127 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3535307_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 127, 'http://tong.visitkorea.or.kr/cms/resource/08/3535308_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=127 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3535308_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 127, 'http://tong.visitkorea.or.kr/cms/resource/09/3535309_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=127 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3535309_image2_1.jpg');
UPDATE place SET description='곡교천 은행나무길은 ‘전국의 아름다운 10대 가로수길’로 선정된 충남 아산의 명소다. 현충사 입구의 곡교천 충무교에서부터 현충사 입구까지 2.2㎞ 길이의 도로에 조성되어 있다. 350여 그루의 은행나무가 일제히 노란빛을 내는 가을이면 평일에도 많은 인파가 몰려들고, 휴일이면 찾아오는 사람들로 인산인해를 이룬다. 현충사를 찾는 관광객에게는 꼭 들러야 하는 필수 여행코스다. 청명한 가을 하늘을 배경으로 한 노란 은행나무길은 가을 정취를 만끽하고, 사진으로 남겨두기에 적격이다. 봄이면 유채꽃이 피어 또 다른 멋을 선사한다.' WHERE place_id=127 AND (description IS NULL OR description='');
-- [128] 아산그린타워
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 128, 'http://tong.visitkorea.or.kr/cms/resource/21/3568621_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=128 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3568621_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 128, 'http://tong.visitkorea.or.kr/cms/resource/22/3568622_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=128 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3568622_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 128, 'http://tong.visitkorea.or.kr/cms/resource/24/3568624_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=128 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3568624_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 128, 'http://tong.visitkorea.or.kr/cms/resource/25/3568625_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=128 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3568625_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 128, 'http://tong.visitkorea.or.kr/cms/resource/26/3568626_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=128 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3568626_image2_1.jpg');
UPDATE place SET description='아산 그린타워는 충청남도 아산시 배미동에 자리했다. 생활 쓰레기 소각시설이 환경 과학공원으로 탈바꿈하면서 마련된 전망대로 약 아파트 50층 높이이다. 엘리베이터를 타고 전망대에 오르면 아산시의 풍경을 360도로 조망할 수 있다. 발아래 투명 유리 위에 서면 아찔함을 느낄 수도 있다. 전망대에 카페와 레스토랑이 있어 천천히 경치를 즐길 수 있다. 아산 그린타워 주변에는 신정호 국민관광지, 온양온천 등 관광지가 많아 연계 여행에 나서기 좋다.' WHERE place_id=128 AND (description IS NULL OR description='');
-- [129] 서울 종로 낙지볶음 골목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 129, 'http://tong.visitkorea.or.kr/cms/resource/46/3478546_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=129 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3478546_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 129, 'http://tong.visitkorea.or.kr/cms/resource/72/3384772_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=129 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3384772_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 129, 'http://tong.visitkorea.or.kr/cms/resource/75/3384775_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=129 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3384775_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 129, 'http://tong.visitkorea.or.kr/cms/resource/77/3384777_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=129 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3384777_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 129, 'http://tong.visitkorea.or.kr/cms/resource/78/3384778_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=129 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3384778_image2_1.JPG');
UPDATE place SET description='서울 종로 낙지볶음 골목은 청계천 청계광장 인근 무교동 사거리에서 종로구청으로 이어지는 일대를 말한다. 1960년대 종로구 서린동에 낙지 골목이 형성된 것을 기원으로 본다. 과거 청계천 인근 회사에 다니던 언론인과 공무원을 비롯한 직장인들이 주로 이 골목을 찾아 매운 낙지볶음과 시원한 조개탕을 곁들여 먹으며 삶의 애환과 스트레스를 날리고, 젊은 열정을 불태웠던 낭만과 추억의 거리로 알려져 있다. 무교동 낙지골목이라는 이름으로 불리기도 한다. 1970년대에서 1990년대에 이르는 여러 차례의 도심 재개발로 인해 서린동에서 무교동, 다동, 피맛골 일대, 종로 1가 등 다양한 행정동으로 상권이 이동했는데, 무교동이 당시 가장 널리 알려진 이름이었기 때문으로 추정된다. 여러 번의 이전에도 없어지지 않고 50년 이상의 세월 동안 매운 낙지볶음과 시원한 국물의 맛을 지켜온 가게들은 재개발 이후 신축된 광화문 르메이에르빌딩으로 들어가거나, 그 주변으로 이사를 했다. 세월이 흘러 그때의 낙지골목의 모습은 사라졌지만 종로 일대로 다양하게 퍼져나가 지금까지도 맛있는 낙지볶음 요리를 선보이고 단골손님을 만들어 가고 있다. 오동통하고 쫄깃한 낙지가 어우러져 사람들의 발길을 잡고 있다.' WHERE place_id=129 AND (description IS NULL OR description='');
-- [130] 중앙고등학교
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 130, 'http://tong.visitkorea.or.kr/cms/resource/11/3573511_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=130 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/3573511_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 130, 'http://tong.visitkorea.or.kr/cms/resource/12/3573512_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=130 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/3573512_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 130, 'http://tong.visitkorea.or.kr/cms/resource/13/3573513_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=130 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3573513_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 130, 'http://tong.visitkorea.or.kr/cms/resource/14/3573514_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=130 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3573514_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 130, 'http://tong.visitkorea.or.kr/cms/resource/15/3573515_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=130 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3573515_image2_1.jpg');
UPDATE place SET description='중앙고등학교는 1908년 기호지방의 우국지사들에 의해 설립된 기호학교가 1910년 9월 흥사단이 설립한 융희학교를 합병하여 설립된 학교이다. 1910년 11월 모두 통합하여 중앙학교로 학교 이름을 개칭하였다. 1915년 김성수가 인수하였고, 1917년에는 계산 언덕에 교사를 신축하고 이전하였다. 본관, 서관, 동관 등의 건물을 12월에 준공하였으며 당시의 교사는 2층 벽돌집이었다. 1934년 본관이 화재로 소실되었으나, 1935년 6월 우리나라 근대 건축가이자 고려대학교 본관과 도서관, 조선일보 사옥을 설계한 박동진(1899~1980)의 설계로 다시 착공하여 1937년 9월에 준공하였다. 중앙고등학교 본관, 서관, 동관은 각각 사적으로 지정되어 있다. 본관은 학교 정문에서 바라볼 때 정면에 세워졌으며, 석조 콘크리트 철근 2층의 근대식 건물이다. 좌우가 대칭되는 H자형 평면의 중앙에는 중세 시대 고딕 성관풍의 4층 탑을 두고, 그 좌우로 1층에는 교무실, 2층에는 교실을 두었다. 서관은 1921년 10월에 2층 붉은 벽돌집으로 준공되었으며, 설계자는 확실치 않다. 1934년 화재로 없어진 구 본관 건물(지금의 동상이 있는 곳)을 중심으로 왼쪽에 동관이 있고, 오른쪽에 바로 서관이 자리 잡고 있다. 회백색의 화강암 돌과 붉은 벽돌로 엇물려 지은 이층집이다. 설계자는 일본인 중촌여자평이다. 평면은 T자형이며, 박공면이 돌출해 있다. 이곳에는 모두 교실을 배치하였다. 또한, 3·1운동 이후 조선 소년군 창설, 6·10만세운동, 광주학생운동을 시작한 곳이기도 하다. 동관은 1923년 10월 준공된 2층의 붉은 벽돌집으로 1934년 화재로 소실된 구 본관 왼쪽에 지어져 건너편의 서관과 마주 보고 있다. 서관과 마찬가지로 설계자와 시공자는 기록이 없어 확실하지 않다. 동관의 건물구조와 특징은 서관과 비슷하다. 붉은 벽돌집 슬레이트 이름의 고딕식이다. 평면은 T자형이며, 박공면이 돌출되어 있다.' WHERE place_id=130 AND (description IS NULL OR description='');
-- [151] 전쟁기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 151, 'http://tong.visitkorea.or.kr/cms/resource/29/3465929_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=151 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3465929_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 151, 'http://tong.visitkorea.or.kr/cms/resource/66/3301966_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=151 AND image_url='http://tong.visitkorea.or.kr/cms/resource/66/3301966_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 151, 'http://tong.visitkorea.or.kr/cms/resource/69/3301969_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=151 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3301969_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 151, 'http://tong.visitkorea.or.kr/cms/resource/70/3301970_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=151 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3301970_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 151, 'http://tong.visitkorea.or.kr/cms/resource/71/3301971_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=151 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3301971_image2_1.png');
UPDATE place SET description='서울시 용산구 용산동에 호국전쟁의 실체를 보여주는 자료를 보존 전시하고 국민정신교육장으로 역할을 하며 조국을 위해 목숨을 바친 선열들의 숭고한 희생을 추모하고 그 업적을 기리기 위해 전쟁기념사업회에서 1994년 6월 10일 전쟁기념관을 세웠다. 전쟁기념관은 옥내전시와 옥외전시로 구분되어 있으며 3만 3천여 점의 소장유물 중 1만여 점을 전시하고 있다. 1만 900여 평의 옥내전시실은 호국추모실, 전쟁역사실, 6·25전쟁실, 해외파병실, 국군발전실, 대형장비실 등 6개 전시실로 구성되어 있으며 대형무기들이 전시되어 있는 옥외전시실이 있다. 3층 규모의 옥내전시실은 삼국시대로부터 현대까지 5천년 대한민국 전쟁사와 위국 헌신한 분들의 공로와 훈장 등이 실물·디오라마·복제품·기록화·영상 등의 다양한 전시기법에 따라 역동적이고 입체적으로 전시되어 있다. 특히 6·25전쟁실은 전쟁이 일어난 원인과 전쟁 경과 및 휴전에 이르기까지의 전 과정을 쉽고 올바르게 이해할 수 있도록 체험시설 등으로 구성되어 있다. 옥외전시장에는 세계 각국의 대형무기와 6·25전쟁 상징 조형물, 광개토대왕릉비, 형제의 상, 평화의 시계탑 등이 전시되어 있다. 제2차 세계대전, 6·25전쟁, 베트남전쟁 당시 사용되었던 장비를 전시하고 있는데, K-1전차를 비롯하여 곡사포, 미사일, 헬리콥터, 수송기 등이 전시되어 있다. 기념관 양측 회랑에는 국군 전사자와 유엔군 전사자 20여 만 명의 이름이 새겨진 전사자 명비가 있으며, 전쟁기념관을 대국민 호국문화의 장으로서도 큰 역할을 하고 있다. 어린이부터 일반인까지 다양한 계층을 대상으로 한 20여개의 교육프로그램을 운영함은 물론, 국군 군악·의장행사, 어린이날 문화축제, 현충일 그림그리기 대회, 6·25 호국문화행사 등 다양한 문화행사를 연간 개최하고 있어 기념관을 찾는 관람객들에게 새로운 볼거리를 제공하고 있다. 전쟁기념관의 캐릭터는 무돌이로, 나라를 지킨다는 뜻을 가진 철모와 평화를 상징하는 월계수잎이 특징적이다.' WHERE place_id=151 AND (description IS NULL OR description='');
-- [152] 국립5·18민주묘지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 152, 'https://tong.visitkorea.or.kr/cms/resource/59/4102359_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=152 AND image_url='https://tong.visitkorea.or.kr/cms/resource/59/4102359_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 152, 'https://tong.visitkorea.or.kr/cms/resource/55/2364355_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=152 AND image_url='https://tong.visitkorea.or.kr/cms/resource/55/2364355_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 152, 'https://tong.visitkorea.or.kr/cms/resource/55/4102355_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=152 AND image_url='https://tong.visitkorea.or.kr/cms/resource/55/4102355_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 152, 'https://tong.visitkorea.or.kr/cms/resource/56/4102356_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=152 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/4102356_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 152, 'https://tong.visitkorea.or.kr/cms/resource/57/4102357_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=152 AND image_url='https://tong.visitkorea.or.kr/cms/resource/57/4102357_image2_1.jpg');
UPDATE place SET description='1980년에 일어난 5·18민주화운동의 희생자들이 묻힌 공동 묘지인 국립5·18민주묘지는 5·18 묘역이라고도 한다. 민주 성지인 전남광주통합특별시의 대표적인 상징으로 묘역 안에는 5·18 영령의 묘가 있으며, 참배 광장, 전시 공간, 상징 조형물, 광주민주화운동 추모탑 등으로 구성되어 있다. 이곳 중앙에 있는 사각기둥인 탑신은 높이 40m로 우리나라 전통 석조물인 당간지주를 현대감각에 맞게 형상화했다. 탑신 가운데 감싸 쥔 손 모양으로 중앙에 설치된 타원형 형상은 새로운 생명의 부활을 상징하며, 시시각각 변하는 태양광에 반사된 빛은 희망의 씨앗이라고 볼 수 있다. 또한 5·18 추모관은 민주화를 위해 희생한 민주 영령들을 추모하고 그 뜻을 올바르게 계승하기 위해 건립되었다. 5·18민주화운동과 그 희생자 그리고 아직 밝혀지지 않은 진실 등을 객관적으로 이해할 수 있으며, 단순한 관람보다는 직접 참여하여 5·18 실상과 정신을 느낄 수 있는 체험 공간으로 구성되어 있다. 경건한 참배 분위기를 위하여 슬리퍼, 민소매 등의 복장은 자제해야 한다.' WHERE place_id=152 AND (description IS NULL OR description='');
-- [153] 전일빌딩245
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 153, 'https://tong.visitkorea.or.kr/cms/resource/77/4007777_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=153 AND image_url='https://tong.visitkorea.or.kr/cms/resource/77/4007777_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 153, 'https://tong.visitkorea.or.kr/cms/resource/78/4007778_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=153 AND image_url='https://tong.visitkorea.or.kr/cms/resource/78/4007778_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 153, 'https://tong.visitkorea.or.kr/cms/resource/79/4007779_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=153 AND image_url='https://tong.visitkorea.or.kr/cms/resource/79/4007779_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 153, 'https://tong.visitkorea.or.kr/cms/resource/80/4007780_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=153 AND image_url='https://tong.visitkorea.or.kr/cms/resource/80/4007780_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 153, 'https://tong.visitkorea.or.kr/cms/resource/81/4007781_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=153 AND image_url='https://tong.visitkorea.or.kr/cms/resource/81/4007781_image2_1.jpg');
UPDATE place SET description='전일빌딩 245 Bl는 역사적 현장으로서의 건축형태와 공간을 245로 상징화하여 전일빌딩의 의미를 되새기고 미래로의 스토리텔링을 담고자 하였다. 245 정중앙의 원은 5·18 민주화운동 당시 헬기 사격의 선명한 탄흔을, 4가지 컬러 구성은 전일빌딩의 콘텐츠 공간을 형상화하였다. 전일빌딩에 헬기 사격이 가해졌다는 사실은 수많은 목격자와 증거, 탄흔이 기억하고 있다. 전일빌딩에서 발견된 245개 탄흔이 가장 명확한 증거이며 헬기 사격을 증거 하는 문서 등이 이를 뒷받침한다. 전일빌딩 245의 탄흔을 지속 가능하게 보존해야 하는 이유는 5·18 민주화운동을 후대에 온몸으로 알리는 주인공이기 때문이다.' WHERE place_id=153 AND (description IS NULL OR description='');
-- [154] 남한산성 수어장대
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 154, 'https://tong.visitkorea.or.kr/cms/resource/69/3524569_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=154 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/3524569_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 154, 'https://tong.visitkorea.or.kr/cms/resource/64/3524564_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=154 AND image_url='https://tong.visitkorea.or.kr/cms/resource/64/3524564_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 154, 'https://tong.visitkorea.or.kr/cms/resource/65/3524565_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=154 AND image_url='https://tong.visitkorea.or.kr/cms/resource/65/3524565_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 154, 'https://tong.visitkorea.or.kr/cms/resource/66/3524566_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=154 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/3524566_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 154, 'https://tong.visitkorea.or.kr/cms/resource/67/3524567_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=154 AND image_url='https://tong.visitkorea.or.kr/cms/resource/67/3524567_image2_1.jpg');
UPDATE place SET description='수어장대는 지휘와 관측을 위한 군사적 목적에서 지은 누각으로 남한산성에 있던 5개의 장대(동장대, 서장대, 남장대, 북장대, 외동장대) 중 유일하게 남아있다. 청량산 정상(해발 482m)에 위치하고 있으며 성 안에 남아 있는 건물 중 가장 화려하고 웅장하다. 하층은 정면 5칸, 측면 3칸, 상층 정면 3칸 측면 2칸의 팔작지붕양식의 2층 누각이다. 지붕은 상하층 모두 겹처마루를 둘렀으며 사래 끝에는 토수를 달고 추녀마루에는 용두를 올렸으며 용마루에는 취두를 올렸다. 건물의 기둥은 높이 60cm의 팔각장주초석 위에 올려져 있고, 포는 주심포 양식의 이출목 익공식이다. 영조 27년(1751) 2층 누각으로 증축하고 외부에 ‘수어장대(守禦將臺)’ 내부에 ‘무망루(無忘樓)’라고 편액을 제작하여 설치하였다. (출처 : 경기도남한산성세계유산센터)' WHERE place_id=154 AND (description IS NULL OR description='');
-- [155] 인천상륙작전기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 155, 'https://tong.visitkorea.or.kr/cms/resource/97/3037297_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=155 AND image_url='https://tong.visitkorea.or.kr/cms/resource/97/3037297_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 155, 'https://tong.visitkorea.or.kr/cms/resource/96/3037296_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=155 AND image_url='https://tong.visitkorea.or.kr/cms/resource/96/3037296_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 155, 'https://tong.visitkorea.or.kr/cms/resource/98/3037298_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=155 AND image_url='https://tong.visitkorea.or.kr/cms/resource/98/3037298_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 155, 'https://tong.visitkorea.or.kr/cms/resource/99/3037299_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=155 AND image_url='https://tong.visitkorea.or.kr/cms/resource/99/3037299_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 155, 'https://tong.visitkorea.or.kr/cms/resource/00/3037300_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=155 AND image_url='https://tong.visitkorea.or.kr/cms/resource/00/3037300_image2_1.jpg');
UPDATE place SET description='한국전쟁의 전세를 뒤바꾼 인천상륙작전의 역사적 사실을 기념하고 보존하기 위해 건립된 전쟁기념관이다. 기념관은 9월 15일 인천상륙작전이 개시된 날짜에 맞춰 1984년 9월 15일에 개관했다. 두 개의 실내 전시관 중 제1전시관은 인천상륙작전의 구상, 계획의 발전 과정, 인천상륙작전의 특징, 한국전쟁 당시 각 군의 역할에 대해 소개한다. 제2전시관은 인천상륙작전 상황을 담은 디오라마와 영상실, 맥아더 장군 포토존 등 체험형 전시와 관련 유물을 전시한다. 넓은 야외 전시장에는 탱크, 수륙양용장갑차, 고사기관총, 함포, 호크 유도탄, 전투 정찰기, 카고트럭 등 전쟁 관련 대형 장비가 다수 전시되어 있다. 해벽을 오르는 미 해병대, 응봉산(지금의 자유공원) 기상대 탈환 후 환호하는 미 해병대 모습을 담은 조형물도 볼 수 있다. 자유수호의 탑이 세워진 기념관 상부에 오르면 서해바다와 인천 시가지가 시원하게 내다보인다.' WHERE place_id=155 AND (description IS NULL OR description='');
-- [156] 자유공원(인천)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 156, 'https://tong.visitkorea.or.kr/cms/resource/85/4061685_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=156 AND image_url='https://tong.visitkorea.or.kr/cms/resource/85/4061685_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 156, 'https://tong.visitkorea.or.kr/cms/resource/84/3396084_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=156 AND image_url='https://tong.visitkorea.or.kr/cms/resource/84/3396084_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 156, 'https://tong.visitkorea.or.kr/cms/resource/92/3396092_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=156 AND image_url='https://tong.visitkorea.or.kr/cms/resource/92/3396092_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 156, 'https://tong.visitkorea.or.kr/cms/resource/93/3396093_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=156 AND image_url='https://tong.visitkorea.or.kr/cms/resource/93/3396093_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 156, 'https://tong.visitkorea.or.kr/cms/resource/94/3396094_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=156 AND image_url='https://tong.visitkorea.or.kr/cms/resource/94/3396094_image2_1.JPG');
UPDATE place SET description='자유공원은 인천항 개항 5년 만에 만들어진 우리나라 최초의 서구식 공원이다. 지대가 높은 데다 터가 넓고 숲이 울창해 산책하기 알맞다. 정상엔 한미수교 백주년기념탑이 있다. 1882년 4월 우리나라와 미국 사이에 조인된 한미수호 통상조약체결을 기념하기 위해 100주년이 되는 1982년에 세운 것이다. 한국전쟁 당시 인천상륙작전을 성공시킨 맥아더장군의 전공을 기리는 맥아더장군 동상도 그 옆에 있다. 인천상륙작전 성공 이후 7주년이 되는 1957년 9월 15일에 완공됐다. 자유공원 정상에서는 멀리 인천 앞바다까지도 훤히 내려다보인다. 자유공원 안에는 소규모 동물원과 팔각정, 연오정, 의자 등 쉼터가 마련되어 있다. 매년 4월이면 자유공원으로 오르는 길은 벚꽃으로 만발한다. 이를 기념해 벚꽃축제가 열린다. 공원 정상에서 인천항과 월미도를 바라보는 맛도 그만이다. 늦은 밤 이곳에서 바라보는 인천항의 밤 경치는 연인들의 데이트코스로도 유명하다.' WHERE place_id=156 AND (description IS NULL OR description='');
-- [157] 월미도
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 157, 'https://tong.visitkorea.or.kr/cms/resource/94/3518594_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=157 AND image_url='https://tong.visitkorea.or.kr/cms/resource/94/3518594_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 157, 'https://tong.visitkorea.or.kr/cms/resource/95/3518595_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=157 AND image_url='https://tong.visitkorea.or.kr/cms/resource/95/3518595_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 157, 'https://tong.visitkorea.or.kr/cms/resource/96/3518596_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=157 AND image_url='https://tong.visitkorea.or.kr/cms/resource/96/3518596_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 157, 'https://tong.visitkorea.or.kr/cms/resource/97/3518597_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=157 AND image_url='https://tong.visitkorea.or.kr/cms/resource/97/3518597_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 157, 'https://tong.visitkorea.or.kr/cms/resource/98/3518598_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=157 AND image_url='https://tong.visitkorea.or.kr/cms/resource/98/3518598_image2_1.jpg');
UPDATE place SET description='1989년 7월 문화의 거리가 조성된 이래 문화예술의 장, 만남과 교환의 장 그리고 공연놀이마당 등으로도 알려지기 시작한 월미도는 인천 하면 떠올릴 만큼 유명한 곳이다. 이곳은 카페, 회센터 등 조화를 이루며 늘어서 있어 시원한 바다를 바라보며 구미에 맞는 음식을 골라 즐길 수도 있다. 휴일엔 각지에서 수십만 명의 인파가 몰리며 인천시민과 그 주변 시민의 휴식 공간으로 자리하고 있다. 월미도 문화의 거리는 관광객을 위하여 특성있는 구간을 조성하여 만남교환의 장, 문화예술의 장과 각종 공연을 위한 연출의 장을 설치하여 이용객의 편의를 도모하여 시민문화 의식의 고취와 지방 예술 문화창달에 기여하고 있다.' WHERE place_id=157 AND (description IS NULL OR description='');
-- [158] 제주4·3평화공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 158, 'https://tong.visitkorea.or.kr/cms/resource/72/4094972_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=158 AND image_url='https://tong.visitkorea.or.kr/cms/resource/72/4094972_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 158, 'https://tong.visitkorea.or.kr/cms/resource/73/4094973_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=158 AND image_url='https://tong.visitkorea.or.kr/cms/resource/73/4094973_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 158, 'https://tong.visitkorea.or.kr/cms/resource/74/4094974_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=158 AND image_url='https://tong.visitkorea.or.kr/cms/resource/74/4094974_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 158, 'https://tong.visitkorea.or.kr/cms/resource/75/4094975_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=158 AND image_url='https://tong.visitkorea.or.kr/cms/resource/75/4094975_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 158, 'https://tong.visitkorea.or.kr/cms/resource/76/4094976_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=158 AND image_url='https://tong.visitkorea.or.kr/cms/resource/76/4094976_image2_1.jpg');
UPDATE place SET description='제주 4·3 평화공원은 4·3 사건으로 인한 제주도 민간인 학살과 처절한 삶을 기억하고 추념하며, 화해와 상생의 미래를 열어가기 위한 평화 인권기념공원이다. 제주 4·3 평화공원 조성사업은 제주 4·3 사건에 대한 공동체적 보상의 하나로 이루어졌으며, 4·3 특별법 공포(2000), 공원부지 매입, 조성기본계획 연구 용역, 현상공모, 건축공사, 전시물 제작 및 설치 등이 이어져 2008년 3월 28일 개관하게 되었다. 공원 안에는 제주 4·3 평화기념관, 위령제단, 위령탑, 봉안관 등이 이곳을 지키고 있다. 위령제단은 연중 4·3 희생자에 대해 참배를 진행하는 곳이며, 그들을 모시고 있는 위패봉안실이 따로 마련되어 있다. 봉안관은 4·3 유해발굴 사업 시기에 발굴된 유해를 봉안하는 장소로 현재 380기가 안치되어 있다. 각 명비원에는 희생자의 성명과 성별, 당시 연령 등을 기록해 두었다. 제주 4·3 평화기념관에는 총 6개의 특별 전시관이 있다. 제1관에서는 주민들의 피신처로 활용되었다는 천연동굴을 주제로 한 역사관이 있으며, 제2관에서는 해방과 좌절이라는 주제로 해방 후 3·1절 기념행사에서 사망한 6명의 민간인의 이야기를 담아내고 있다. 제3관에서는 무장봉기와 분단 거부라는 주제로 1948년 4월 3일에 일어난 무장봉기에 대한 이야기가 있고, 제4관에서는 학살에 관한 내용을 다루고 있다. 마지막으로 5관과 6관에서는 진상 규명 운동으로 상처를 극복해 내는 과정과 관람 후의 소감문이 걸려 있다.' WHERE place_id=158 AND (description IS NULL OR description='');
-- [159] 임진각 수풀누리
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 159, 'https://tong.visitkorea.or.kr/cms/resource/01/3022901_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=159 AND image_url='https://tong.visitkorea.or.kr/cms/resource/01/3022901_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 159, 'https://tong.visitkorea.or.kr/cms/resource/92/3022892_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=159 AND image_url='https://tong.visitkorea.or.kr/cms/resource/92/3022892_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 159, 'https://tong.visitkorea.or.kr/cms/resource/93/3022893_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=159 AND image_url='https://tong.visitkorea.or.kr/cms/resource/93/3022893_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 159, 'https://tong.visitkorea.or.kr/cms/resource/95/3022895_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=159 AND image_url='https://tong.visitkorea.or.kr/cms/resource/95/3022895_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 159, 'https://tong.visitkorea.or.kr/cms/resource/96/3022896_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=159 AND image_url='https://tong.visitkorea.or.kr/cms/resource/96/3022896_image2_1.jpg');
UPDATE place SET description='임진각관광지 내에 위치한 임진각 수풀누리는 한반도의 평화를 기원하며 만들어진 아름다운 습지체험원으로 낮에는 푸른 나무와 꽃들이 가득해 힐링을 선사하고 밤에는 환상적인 야간조명과 첨단 미디어 쇼가 펼쳐지는 야간관광 명소이다. 남북 분단의 아픔을 간직한 한반도의 평화와 인공으로 조성된 습지의 교육적 가치를 동시에 체험할 수 있어 모두가 방문하기 좋다. 시민들의 습지체험원으로 조성된 공간으로 비교적 한적하게 산책하며 사색을 즐길 수 있는 곳이다. 메타세쿼이아길, 창포섬, 관찰 데크, DMZ정원, 잔디동산, 잔디광장으로 조성된 습지 생태를 엿볼 수 있으며 평화와 통일을 염원하는 조형물도 곳곳에 있어 관람 요소를 더한다. 특히 야간관광 활성화를 위해 설치된 경관조명 및 미디어 공간이 유명한데 특별한 밤의 추억을 만들 수 있다. 북쪽에서 날아온 꽃씨가 무궁화와 함박꽃을 함께 피워내는 하나그루로 자라고 그 꽃잎이 흩날리며 화합과 희망의 메시지를 전달한다는 내용으로 메타세쿼이아길의 ‘희망의 꽃씨’를 출발점으로 맥동, 하나그루, 하나의 메아리, 화합의 길, 평화의 꽃가루로 구성되어 있다. 임진각관광지 내의 평화누리공원, 평화랜드, 평화곤돌라, 누리성 모험놀이터 등의 시설을 함께 이용할 수 있다.' WHERE place_id=159 AND (description IS NULL OR description='');
-- [160] 경복궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 160, 'https://tong.visitkorea.or.kr/cms/resource/98/3487598_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=160 AND image_url='https://tong.visitkorea.or.kr/cms/resource/98/3487598_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 160, 'https://tong.visitkorea.or.kr/cms/resource/94/3487594_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=160 AND image_url='https://tong.visitkorea.or.kr/cms/resource/94/3487594_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 160, 'https://tong.visitkorea.or.kr/cms/resource/95/3487595_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=160 AND image_url='https://tong.visitkorea.or.kr/cms/resource/95/3487595_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 160, 'https://tong.visitkorea.or.kr/cms/resource/97/3487597_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=160 AND image_url='https://tong.visitkorea.or.kr/cms/resource/97/3487597_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 160, 'https://tong.visitkorea.or.kr/cms/resource/99/3487599_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=160 AND image_url='https://tong.visitkorea.or.kr/cms/resource/99/3487599_image2_1.jpg');
UPDATE place SET description='경복궁은 1392년 조선 건국 후 1395년(태조 4)에 창건한 조선왕조 제일의 법궁이다. 경복궁은 백악산(북악산)을 주산으로 넓은 지형에 건물을 배치하였고 정문인 광화문 앞으로 넓은 육조거리가 펼쳐진 한양의 중심이었다. ‘경복’의 이름은 ‘새 왕조가 큰 복을 누려 번영할 것’이라는 의미가 담겨있으며, 이곳에서 세종 대에 훈민정음이 창제되어 반포되기도 하였다. 또한, 동궐(창덕궁)이나 서궐(경희궁)에 비해 위치가 북쪽에 있어 ‘북궐’이라 불리기도 했다. 경복궁 근정전에서 즉위식을 가진 왕들을 보면 제2대 정종, 제4대 세종, 제6대 단종, 제7대 세조, 제9대 성종, 제11대 중종, 제13대 명종 등이 있다. 경복궁은 1592년(선조 25) 임진왜란으로 소실되었는데, 그 후 복구되지 못하였다가 270여 년이 지난 1867년(고종 4)에 다시 지어졌다. 고종 대에 들어 건청궁과 태원전, 집옥재 등이 조성되었으며, 특히 건청궁 옥호루는 1895년 을미사변으로 명성황후가 시해되는 비운의 장소이기도 하다. 1910년 경술국치 후 경복궁은 계획적으로 훼손되기 시작하여 1915년 조선물산공진회를 개최한다는 명분으로 대부분의 전각들이 철거되었고, 1926년에는 조선총독부 청사를 지어 경복궁의 경관을 훼손하였다. 이후 1990년대부터 본격적으로 경복궁 복원공사가 진행되었고, 1995년부터 1997년까지 조선총독부 청사를 철거하였으며 흥례문 일원, 침전 권역, 건청궁과 태원전, 그리고 광화문 등이 복원되어 현재에 이르고 있다. 경복궁에는 조선시대의 대표적인 건축물인 경회루와 향원정의 연못이 원형대로 남아 있으며, 근정전의 월대와 조각상들은 당시의 조각미술을 대표한다. 현재 흥례문 밖 서편에는 국립고궁 박물관이 위치하고 있고, 경복궁 내 향원정의 동편에는 국립민속 박물관이 위치하고 있다. 경복궁의 주요 문화재로는 사적 경복궁, 국보 경복궁 근정전, 국보 경복궁 경회루, 보물 경복궁 자경전, 보물 경복궁 자경전 십장생 굴뚝, 보물 경복궁 아미산굴뚝, 보물 경복궁 근정문 및 행각, 보물 경복궁 풍기대 등이 있다. ◎ 한류의 매력을 만나는 여행 정보 미국의 국민 TV 쇼 ‘더 투나잇 쇼 스타링 지미 팰런’에서는 ‘BTS위크’라는 이름을 붙여 닷새간 BTS 특별 방송을 진행했는데, 그중 BTS가 ‘맵 오브 더 솔 : 페르소나’ 미니앨범 수록곡 ‘소우주’와 ‘IDOL’을 부른 장소가 화제다. 그 장소는 바로 조선시대의 궁궐 중 하나인 ‘경복궁’의 경회루와 근정전이다. 보랏빛 조명에 아름답게 빛나던 경복궁에서 한국의 과거를 체험해 보길 추천한다.' WHERE place_id=160 AND (description IS NULL OR description='');
-- [161] 지리산국립공원(산청)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 161, 'http://tong.visitkorea.or.kr/cms/resource/75/3532475_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=161 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3532475_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 161, 'http://tong.visitkorea.or.kr/cms/resource/73/3532473_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=161 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3532473_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 161, 'http://tong.visitkorea.or.kr/cms/resource/74/3532474_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=161 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3532474_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 161, 'http://tong.visitkorea.or.kr/cms/resource/76/3532476_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=161 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3532476_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 161, 'http://tong.visitkorea.or.kr/cms/resource/77/3532477_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=161 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3532477_image2_1.jpg');
UPDATE place SET description='1967년 12월 29일 우리나라 국립공원으로 지정된 지리산은 세 개도 읍·면의 행정구역이 속해 있으며, 국립공원 중 가장 넓은 면적의 산악형 국립공원이다. 지리산(智異山)을 글자 그대로 풀면 ‘지혜로운 이인(異人)의 산’이라 한다. 이 때문인지 지리산은 여느 산보다 많은 은자(隱者)들이 도를 닦으며 정진하여 왔으며 지리산 골짜기에 꼭꼭 숨어든 은자는 그 수를 추정하기 어려웠다고 한다. 지리산은 예로부터 금강산, 한라산과 함께 삼신산(三神山)의 하나로 민족적 숭앙을 받아 온 민족 신앙의 영지(靈地)였다. 지리산의 영봉인 천왕봉에는 성모사란 사당이 세워져 성모석상이 봉안되었으며, 노고단에는 신라시대부터 선도성모를 모시는 남악사가 있었다. 반야봉, 종석대, 영신대, 노고단과 같은 이름들도 신앙을 상징한다. 경상남도에서는 하동, 함양, 산청에 걸쳐 있으며, 지리산은 풍부한 동·식물만큼 그 문화는 동서간을 이질적이면서도 다양한 문화권으로 만들기도 했다.' WHERE place_id=161 AND (description IS NULL OR description='');
-- [162] 덕수궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 162, 'https://tong.visitkorea.or.kr/cms/resource/44/3584644_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=162 AND image_url='https://tong.visitkorea.or.kr/cms/resource/44/3584644_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 162, 'https://tong.visitkorea.or.kr/cms/resource/39/3584639_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=162 AND image_url='https://tong.visitkorea.or.kr/cms/resource/39/3584639_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 162, 'https://tong.visitkorea.or.kr/cms/resource/40/3584640_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=162 AND image_url='https://tong.visitkorea.or.kr/cms/resource/40/3584640_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 162, 'https://tong.visitkorea.or.kr/cms/resource/42/3584642_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=162 AND image_url='https://tong.visitkorea.or.kr/cms/resource/42/3584642_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 162, 'https://tong.visitkorea.or.kr/cms/resource/45/3584645_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=162 AND image_url='https://tong.visitkorea.or.kr/cms/resource/45/3584645_image2_1.jpg');
UPDATE place SET description='덕수궁은 1897년에 선포된 황제국, 대한제국의 황궁으로 옛 이름은 경운궁이다. 덕수궁은 원래 조선 제 9대 성종의 형인 월산대군의 사저였고, 그 후에도 월산대군의 후손이 살던 곳이었다. 1592년(선조 25) 임진왜란으로 도성의 궁들이 모두 소실되자 1593년(선조 26)부터 임시 궁궐로 사용하여 정릉동 행궁(貞陵洞 行宮)이라 불렸다. 이후 1611년(광해군 3) 경운궁(慶運宮)으로 이름이 정해지면서 정식 궁궐이 되었다가, 창덕궁이 중건되면서 다시 별궁으로 남게 되었다. 그러다가 1897년(광무 1) 고종이 대한제국을 선포하고 황제의 자리에 오르자 대한제국의 황궁으로 사용하였다. 이후 황궁에 맞게 규모를 확장하고 격식을 높였으며, 궁궐 내 서양식 건물을 짓기 시작하여 전통 건축물과 서양식 건축물이 조화를 이루게 되었다. 그러나 1904년(광무 8) 대화재로 많은 건물이 소실되었고, 1907년 일제에 의해 고종이 황위에서 물러나자 궁의 이름이 덕수궁으로 바뀌게 되었다. 일제 강점기 이후에는 덕수궁의 규모가 대폭 축소되어 대부분의 건물들이 철거되었다. 동시에 공원화가 진행되어 궁궐로서의 면모를 잃게 되었다. 1946~47년에는 덕수궁 석조전에서 제1·2차 미소공동위원회를 개최하기도 하였다. 이후 덕수궁의 복원이 꾸준히 이루어져 현재의 모습이 되었다.' WHERE place_id=162 AND (description IS NULL OR description='');
UPDATE place SET description='낙선재는 창덕궁과 창경궁 경계에 위치하고 있다. 낙선재 일원은 조선 24대 헌종이 후궁 경빈 김씨를 맞이하면서 1847년(헌종 13)에 창경궁 낙선당 터에 낙선재를 지었고, 이듬해에 석복헌(錫福軒)과 수강재(壽康齋)를 지었다. 낙선재는 헌종의 서재 겸 사랑채로 사용하였고, 석복헌은 경빈의 처소, 수강재는 당시 대왕대비였던 순원황후 김씨(순조의 왕비)의 처소로 사용되었다. 특히 이곳은 대한제국의 마지막 황후 순정황후 윤씨와 의민황태자비(이방자 여사), 덕혜옹주 등 대한제국 마지막 황실 가족이 생활하다가 세상을 떠난 곳이기도 하다. 낙선재 일원은 단청을 하지 않은 소박한 모습이고, 낙선재 뒤로는 후원을 만들었다. 건물과 후원 사이에는 작은 석축들을 계단식으로 쌓아 화초를 심었고, 그 사이사이에 세련된 굴뚝과 괴석들을 배열했다. 궁궐의 품격과 여인의 공간 특유의 아기자기함이 어우러진 대표적인 정원이다.' WHERE place_id=163 AND (description IS NULL OR description='');
-- [164] 국립일제강제동원역사관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 164, 'https://tong.visitkorea.or.kr/cms/resource/66/4102066_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=164 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/4102066_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 164, 'https://tong.visitkorea.or.kr/cms/resource/78/3530178_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=164 AND image_url='https://tong.visitkorea.or.kr/cms/resource/78/3530178_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 164, 'https://tong.visitkorea.or.kr/cms/resource/85/3530185_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=164 AND image_url='https://tong.visitkorea.or.kr/cms/resource/85/3530185_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 164, 'https://tong.visitkorea.or.kr/cms/resource/60/4102060_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=164 AND image_url='https://tong.visitkorea.or.kr/cms/resource/60/4102060_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 164, 'https://tong.visitkorea.or.kr/cms/resource/61/4102061_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=164 AND image_url='https://tong.visitkorea.or.kr/cms/resource/61/4102061_image2_1.jpg');
UPDATE place SET description='국립일제강제동원역사관은 일제 강점기 강제동원의 실상을 규명함으로써 성숙한 역사의식을 고취하고, 인권과 세계평화에 대한 국민 교육의 장을 제공하는 것을 목적으로 건립되었다. 일제 강점기 때 부산항이 대부분의 강제동원 출발지였고, 강제동원자의 일부가 경상도 출신이었다는 역사성과 접근성 등을 감안하여, 2008년 9월 부산에 건립 방침을 확정했다. 역사관 내부에는 정부에서 수집한 강제동원 수기, 사진, 박물류 등을 전시하고 있다. 그리고 일제 강제동원 희생자를 추모할 수 있는 추모탑과 기억의 터(위패관)가 마련되어 있고, 미래세대에게 평화의 소중함을 알리기 위한 어린이체험관과 어린이 도서관 다독다독이 있어 ''유족을 위한 추도, 기념시설''로서의 역할은 물론 ''일제강제동원 역사 교육 공간''으로서의 역할을 수행하고 있다.' WHERE place_id=164 AND (description IS NULL OR description='');
-- [165] 이한열기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 165, 'http://tong.visitkorea.or.kr/cms/resource/06/3505706_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=165 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3505706_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 165, 'http://tong.visitkorea.or.kr/cms/resource/07/3505707_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=165 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3505707_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 165, 'http://tong.visitkorea.or.kr/cms/resource/08/3505708_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=165 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3505708_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 165, 'http://tong.visitkorea.or.kr/cms/resource/09/3505709_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=165 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3505709_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 165, 'http://tong.visitkorea.or.kr/cms/resource/10/3505710_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=165 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3505710_image2_1.jpg');
UPDATE place SET description='이한열기념관은 이한열 열사의 유물이 보존·복원될 수 있도록 열사의 어머니가 국가로부터 받은 배상금과 시민 성금으로 2004년에 세워졌으며, 2014년 사립박물관으로 새롭게 개관한 곳이다. 이한열 열사의 유품을 비롯한 1987년 유월 항쟁의 기록을 보존하고, 연구하며, 전시를 통해 민주주의의 역사를 교육하는 박물관이다. 이한열 기념관에는 최병수 작가의 꿈 솟대가 옥상에 세워져 있으며 화단 담벼락에는 김야천 작가의 벽화가, 전시실 입구에는 이경복 작가의 모자이크 벽화가 관람객을 맞이한다. 전시장 3층과 4층을 이어주는 벽면에는 장례식 행렬을 이끌었던 영정 그림이 있으며, 4층 상설 전시장에는 그가 쓰러질 때 입었던 옷과 신발, 사진과 유품이 전시되어 있다. 또한 이한열 연혁, 정태원 기자(로이터 통신)가 포착한 피격 장면 사진, 중고등학교 시절 유품과 글, 유월 항쟁 기록물을 만나 볼 수 있다.' WHERE place_id=165 AND (description IS NULL OR description='');
-- [166] 한국은행 화폐박물관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 166, 'https://tong.visitkorea.or.kr/cms/resource/79/3510479_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=166 AND image_url='https://tong.visitkorea.or.kr/cms/resource/79/3510479_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 166, 'https://tong.visitkorea.or.kr/cms/resource/80/3510480_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=166 AND image_url='https://tong.visitkorea.or.kr/cms/resource/80/3510480_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 166, 'https://tong.visitkorea.or.kr/cms/resource/81/3510481_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=166 AND image_url='https://tong.visitkorea.or.kr/cms/resource/81/3510481_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 166, 'https://tong.visitkorea.or.kr/cms/resource/82/3510482_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=166 AND image_url='https://tong.visitkorea.or.kr/cms/resource/82/3510482_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 166, 'https://tong.visitkorea.or.kr/cms/resource/83/3510483_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=166 AND image_url='https://tong.visitkorea.or.kr/cms/resource/83/3510483_image2_1.jpg');
UPDATE place SET description='한국은행 화폐박물관은 우리나라 초기 근대 건축물로서 대한민국 금융의 역사와 함께 해온 유서 깊은 화폐박물관 건물은 1981년 국가중요문화유산으로 지정되었다. 1907년 일본 제일은행이 사용하기 위해 공사를 시작했지만 1909년 대한제국의 중앙은행으로 (구) 한국은행이 설립되어 준공 이후에는 (구) 한국은행 건물로 사용될 예정이었다. 그러나 일제강점기에 (구) 한국은행이 조선은행으로 개칭되고, 1912년 건물이 완공된 뒤에는 조선은행 본점 건물로 이용되었다. 1950년 6월 12일 한국은행이 대한민국의 중앙은행으로 창립되면서 한국은행 본점 건물이 되었다. 한국전쟁 때 내부가 거의 파괴되었지만 1958년에 복구하였다. 1987년 이 건물 뒤편에 한국은행 신관(현 본관)이 준공되면서 원형복원 공사를 착수했다. 외벽은 이전과 같이 복원하였으나 내부는 대리석으로 마감하는 등 현대적 건물로서 기능을 수행할 수 있도록 변경하여 1989년 완공했다. 이후 2001년 한국은행 창립 50주년을 맞이하여 2001년 6월 12일에 화폐박물관으로 개관하여 현재에 이르고 있다. 화폐박물관은 다양한 화폐와 소장 미술품 기획전을 개최하여 국내외 관람객들에게 화폐의 역사와 문화 향유의 기회를 제공하고 있다. 한국은행이 하는 일과 중앙은행 제도에 대해 알아볼 수 있고 화폐의 제조, 순환과정과 위·변조 화폐의 식별법도 설명해 준다. 화폐·금융·경제 관련 자료를 수집, 보존, 연구, 전시함으로써 어린이와 청소년을 비롯한 많은 국민들에게 화폐경제교육의 생생한 체험의 장이 되고 있다. 화폐박물관의 상설전시실은 총 2층과 13개의 전시실로 구성되어 있으며, 전시물은 외부 전시 일정 및 전시품의 보존 상태를 위하여 주기적으로 교체하고 있다. 기획전시실은 일정 별로 화폐에 관한 다양한 주제로 하여 특별기획전을 개최하고 있으며, 체험학습실에서 다양한 체험활동을 통해 화폐를 좀 더 즐겁게 학습할 수 있다.' WHERE place_id=166 AND (description IS NULL OR description='');
-- [167] 개태사(논산)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 167, 'http://tong.visitkorea.or.kr/cms/resource/77/3050177_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=167 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3050177_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 167, 'http://tong.visitkorea.or.kr/cms/resource/78/3050178_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=167 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3050178_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 167, 'http://tong.visitkorea.or.kr/cms/resource/79/3050179_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=167 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3050179_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 167, 'http://tong.visitkorea.or.kr/cms/resource/80/3050180_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=167 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3050180_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 167, 'http://tong.visitkorea.or.kr/cms/resource/81/3050181_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=167 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3050181_image2_1.JPG');
UPDATE place SET description='개태사(논산)는 천호산(371.6m) 서쪽 자락에 있는 사찰이다. 936년(태조 19)에 고려 태조가 후백제의 신검을 무찌르고 후삼국을 통일한 것을 기념하여 황산을 천호산이라 개칭하고 창건하였다. 또한 후백제를 세웠다가 고려로 귀부한 견훤이 병사한 곳으로도 전해진다. 이곳에는 태조의 영정을 모시는 진전이 있었으며, 국가에 변고가 있을 때에는 신탁을 받는 등 왕실과 긴밀한 관계를 맺으면서 유지되어 왔다. 그러나 고려 말기에 이르러 왜구의 침입을 받아 쇠퇴하였다가 조선시대와 1930년대에 점차적으로 재건되었다. 이곳의 중요문화재로는 보물 사지석불입상, 충청남도 민속문화재 개태사철확, 충청남도 문화재자료 5층 석탑과 석조가 있다. 그중 예전에 이 절에서 쓰던 철확, 즉 큰 가마솥은 승려들의 식사를 위해 국을 끓이던 것으로 지름 3m, 높이 1m, 둘레 9.4m이다.' WHERE place_id=167 AND (description IS NULL OR description='');
-- [168] 서울 운현궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 168, 'http://tong.visitkorea.or.kr/cms/resource/06/3577706_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=168 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3577706_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 168, 'http://tong.visitkorea.or.kr/cms/resource/07/3577707_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=168 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3577707_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 168, 'http://tong.visitkorea.or.kr/cms/resource/08/3577708_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=168 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3577708_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 168, 'http://tong.visitkorea.or.kr/cms/resource/09/3577709_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=168 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3577709_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 168, 'http://tong.visitkorea.or.kr/cms/resource/10/3577710_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=168 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3577710_image2_1.jpg');
UPDATE place SET description='운현궁은 경복궁과 같은 궁궐이 아니라 왕족의 친족들이 거주하던 궁으로 종로구 운니동에 위치한 사적이다. 흥선대원군의 일가가 거주하고 생활한 사저였으며, 고종이 임금이 되기 전까지 태어나고 자란 곳이다. 운현궁은 조선조 말기의 역사적 사건들 대부분이 시작된 곳이며 수많은 개혁정책과 쇄국정책이 시행된 곳이었기에 역사적 상징성이 남다르다. 운현궁의 대표적인 건물은 1864년 9월에 준공한 노안당과 노락당, 그리고 6년 후에 증축한 이로당이 있고, 지금은 한 개뿐이지만 그 당시 네 개였던 대문이 있다. 노락당은 운현궁에서 가장 중심이 되는 건물로서 가족들의 회갑이나 잔치 등 큰 행사때 주로 이용되었다. 노안당은 대원군이 사랑채로 사용하던 건물이다. 노안당은 전형적인 한식 기와집으로 추녀 끝이 섬세하고 아름다운 것이 특징이다.' WHERE place_id=168 AND (description IS NULL OR description='');
-- [169] 학도의용군 전승기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 169, 'http://tong.visitkorea.or.kr/cms/resource/34/3586534_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=169 AND image_url='http://tong.visitkorea.or.kr/cms/resource/34/3586534_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 169, 'http://tong.visitkorea.or.kr/cms/resource/33/3586533_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=169 AND image_url='http://tong.visitkorea.or.kr/cms/resource/33/3586533_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 169, 'http://tong.visitkorea.or.kr/cms/resource/35/3586535_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=169 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/3586535_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 169, 'http://tong.visitkorea.or.kr/cms/resource/36/3586536_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=169 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/3586536_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 169, 'http://tong.visitkorea.or.kr/cms/resource/37/3586537_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=169 AND image_url='http://tong.visitkorea.or.kr/cms/resource/37/3586537_image2_1.jpg');
UPDATE place SET description='학도의용군 전승기념관은 6·25전쟁 당시 조국이 위기에 처하자 연필 대신 총을 잡고 나라를 구하겠다는 일념으로 모인 학도의용군을 기억하고, 이들이 낙동강 전선을 마지막까지 지켜내며 승전했던 영광을 기념하는 공간이다. 1층 전시관에서는 학도의용군의 창설과 활동 과정을 자세히 살펴볼 수 있으며, 이들의 이름과 함께 태극기가 전시되어 있다. 당시 사용했던 북한군의 총기류에 비해 열악했던 학도의용군의 공용화기들, 그리고 정식 군인이 아니기에 교복을 입고 전투에 참여했던 학생들의 모습은 보는 이들을 숙연하게 만든다. 영화 의 모티프가 되었던 포항여중 전투와 당시 전투에 참여한 이우근 학생의 편지를 읽다 보면 전쟁의 참담함과 소년의 고뇌가 고스란히 느껴진다. 한국전쟁의 치열한 기록을 담은 역사의 계단과 충혼탑도 오래 머물며 생각하게 만드는 공간이다.' WHERE place_id=169 AND (description IS NULL OR description='');
-- [174] 거제포로수용소유적공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 174, 'http://tong.visitkorea.or.kr/cms/resource/79/3521079_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=174 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3521079_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 174, 'http://tong.visitkorea.or.kr/cms/resource/74/3521074_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=174 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3521074_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 174, 'http://tong.visitkorea.or.kr/cms/resource/75/3521075_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=174 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3521075_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 174, 'http://tong.visitkorea.or.kr/cms/resource/76/3521076_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=174 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3521076_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 174, 'http://tong.visitkorea.or.kr/cms/resource/77/3521077_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=174 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3521077_image2_1.jpg');
UPDATE place SET description='거제도 포로수용소 유적공원은 6·25 전쟁에서 사로잡은 북한군과 중국군 포로를 수용하기 위해 설치된 거제도 포로수용소 부지에 건립된 역사유적공원이다. 최대 17만 3천여 명의 전쟁 포로를 수용하였고, 6·25 전쟁 당시 최대 규모의 포로수용소로 운영되었으며, 전쟁의 비극 속에서도 제네바협약에 의한 인류애를 지키기 위해 노력한 역설적인 무대였다. 거제도 포로수용소 유적공원 내에는 1983년 경상남도 문화재 자료 제99호로 지정된 포로수용소 잔존 유적지가 위치해 있으며, 유적공원에는 다양한 소장품과 기록물, 영상 자료를 활용하여 전시, 교육 콘텐츠를 개발하여 전쟁 역사의 산교육장이자 다크 투어리즘의 메카로 자리 잡고 있다.' WHERE place_id=174 AND (description IS NULL OR description='');
-- [177] 장사상륙작전 전승기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 177, 'http://tong.visitkorea.or.kr/cms/resource/17/3576417_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=177 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3576417_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 177, 'http://tong.visitkorea.or.kr/cms/resource/14/3576414_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=177 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3576414_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 177, 'http://tong.visitkorea.or.kr/cms/resource/15/3576415_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=177 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3576415_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 177, 'http://tong.visitkorea.or.kr/cms/resource/16/3576416_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=177 AND image_url='http://tong.visitkorea.or.kr/cms/resource/16/3576416_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 177, 'http://tong.visitkorea.or.kr/cms/resource/18/3576418_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=177 AND image_url='http://tong.visitkorea.or.kr/cms/resource/18/3576418_image2_1.jpg');
UPDATE place SET description='장사상륙작전 전승기념공원은 한국전쟁 당시 장사상륙작전에 참전한 학도병들의 넋을 기리고자 건립됐다. 공원은 장사상륙작전 기념관과 장사해수욕장 사이에 있다. 이 때문에 공원과 해수욕장의 풍경을 동시에 조망할 수 있다. 모래사장 위에는 학도병들의 상륙 모습을 담은 조형물이 있다. 10인의 학도병을 형상화한 조형물은 바다에서 육지로 돌진하는 실감 나는 모습이다. 공원 중앙에는 솔숲을 배경으로 장사상륙작전 전몰 용사 위령탑이 있다. 위령탑 뒤편엔 장사상륙작전에 참전한 학도병들의 이름이 빼곡하게 적혀 있다.' WHERE place_id=177 AND (description IS NULL OR description='');
-- [178] 뜻모아센터
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 178, 'http://tong.visitkorea.or.kr/cms/resource/53/3353753_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=178 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/3353753_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 178, 'http://tong.visitkorea.or.kr/cms/resource/46/3353746_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=178 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3353746_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 178, 'http://tong.visitkorea.or.kr/cms/resource/47/3353747_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=178 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3353747_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 178, 'http://tong.visitkorea.or.kr/cms/resource/48/3353748_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=178 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/3353748_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 178, 'http://tong.visitkorea.or.kr/cms/resource/49/3353749_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=178 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/3353749_image2_1.png');
UPDATE place SET description='유한회사 뜻모아센터는 탄소중립공예를 주력으로 하는 공예 체험 업체이다. 재활용 가능한 다양한 재료와 공기정화식물인 스칸디아모스를 활용한 공예 체험 프로그램을 개발해 지역축제에서 다양한 연령층의 관광객을 만나고 있다. 스칸디아모스와 접목한 추억의 못난이 인형, 언제나 싱그러운 스칸디아모스 커피박 화분, 키링 만들기는 유한회사 뜻모아센터의 인기 체험 프로그램이다.' WHERE place_id=178 AND (description IS NULL OR description='');
-- [179] 옥산성지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 179, 'http://tong.visitkorea.or.kr/cms/resource/30/3532030_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=179 AND image_url='http://tong.visitkorea.or.kr/cms/resource/30/3532030_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 179, 'http://tong.visitkorea.or.kr/cms/resource/26/3532026_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=179 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3532026_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 179, 'http://tong.visitkorea.or.kr/cms/resource/27/3532027_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=179 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/3532027_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 179, 'http://tong.visitkorea.or.kr/cms/resource/28/3532028_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=179 AND image_url='http://tong.visitkorea.or.kr/cms/resource/28/3532028_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 179, 'http://tong.visitkorea.or.kr/cms/resource/29/3532029_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=179 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3532029_image2_1.jpg');
UPDATE place SET description='거제 옥산성지는 거제 계룡산 자락의 수정봉(해발 143m) 정상 부분을 타원형으로 둘러쌓은 테뫼식 산성으로, 동쪽은 폭이 넓고 서쪽은 폭이 좁은 표주박 형태로 되어 있다. 조선시대에는 수정봉성이라 하였는데 지금은 서쪽 성문 입구에 있는 바위에 새겨진 옥산금성이라는 문구를 따라 옥산성이라 부른다. 옥산성이 자리한 거제면 일대는 현종 4년(1663)에 거제의 읍치를 고현 지역에서 이곳 거제면으로 옮겨 조선시대 말기까지 거제현 관아가 위치한 곳이었다. 거제 옥산성이 만들어진 시기는 성 안에 설치되어 있는 축성비의 내용에 따라 고종 10년(1873)에 거제 백성들이 힘을 모아 축조한 것으로 되어 있어, 우리나라 산성 중 가장 늦게 축조된 산성으로 알려져 왔으나, 2017년 성내 집수지 및 2020년 건물지 발굴 당시 7세기의 유물이 출토됨에 따라, 옥산성의 최초 축조 시기는 통일신라시대인 7세기 이전임이 확인되었다. 성을 쌓기 위한 돌은 가까운 계룡산 자락과 인근 야산에서 구해온 것으로 크지 않은 막돌을 사용하였고, 현재 성곽은 원상태로 잘 보존되어 있다. 문지는 서문지와 동문지 2개가 있는데 원형이 잘 보존되어 있으며, 성문 입구를 사각형 이중 성곽의 내옹성으로 만들어 성문을 보호하고 돌계단을 두어 성안으로 출입하게 하였다. 성 안에는 다수의 건물지와 집수지, 투석용 몽돌 저장시설 등이 남아있다.' WHERE place_id=179 AND (description IS NULL OR description='');
-- [180] 반곡서원(거제)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 180, 'http://tong.visitkorea.or.kr/cms/resource/88/3039888_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=180 AND image_url='http://tong.visitkorea.or.kr/cms/resource/88/3039888_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 180, 'http://tong.visitkorea.or.kr/cms/resource/89/3039889_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=180 AND image_url='http://tong.visitkorea.or.kr/cms/resource/89/3039889_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 180, 'http://tong.visitkorea.or.kr/cms/resource/90/3039890_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=180 AND image_url='http://tong.visitkorea.or.kr/cms/resource/90/3039890_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 180, 'http://tong.visitkorea.or.kr/cms/resource/91/3039891_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=180 AND image_url='http://tong.visitkorea.or.kr/cms/resource/91/3039891_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 180, 'http://tong.visitkorea.or.kr/cms/resource/92/3039892_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=180 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/3039892_image2_1.JPG');
UPDATE place SET description='반곡서원은 우암 송시열선생이 1679년(숙종 5년) 거제도로 귀향을 왔을 때 머물렀던 곳에 세워진 서원이다. 거제 유림 윤도원, 옥삼헌 등이 문정공 우암 송시열선생의 학문과 덕행을 추모하기 위해 1704년(숙종 30년) 창건하였으며, 그 후 죽천 김진규, 몽와 김창집, 학공 이중협, 문충공 민진원, 정문공 김수근 등을 추가로 배향되었다. 흥선 대원군의 서원철폐령으로 철폐되었으나 1906년 거제 유림에서 서원의 옛 터에 제단과 반곡서원유허비를 세우고 매년 가을에 단제를 봉행하였다. 1971년 거제향교 전교 윤병재가 서원의 복원을 발의하고 유림총회에서 1974년 우암사를 중건하고, 인접 지역 고택 3채를 철거하여 나온 부재로 강당을 보수하여 개축하는 등 옛 모습을 되찾았다. 2010년부터 3년간의 대대적인 복원 사업을 진행하여 현재의 모습을 갖추게 되었다. 동록당은 추사 김정희가 군자라 칭송했던 동록 정혼성을 배향한 곳이다.' WHERE place_id=180 AND (description IS NULL OR description='');
-- [181] 제주 노루생태관찰원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 181, 'http://tong.visitkorea.or.kr/cms/resource/67/3389767_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=181 AND image_url='http://tong.visitkorea.or.kr/cms/resource/67/3389767_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 181, 'http://tong.visitkorea.or.kr/cms/resource/66/3389766_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=181 AND image_url='http://tong.visitkorea.or.kr/cms/resource/66/3389766_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 181, 'http://tong.visitkorea.or.kr/cms/resource/68/3389768_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=181 AND image_url='http://tong.visitkorea.or.kr/cms/resource/68/3389768_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 181, 'http://tong.visitkorea.or.kr/cms/resource/69/3389769_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=181 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3389769_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 181, 'http://tong.visitkorea.or.kr/cms/resource/70/3389770_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=181 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3389770_image2_1.jpg');
UPDATE place SET description='제주 노루생태관찰원은 2007년 8월 3일 개장하였다. 각종 동식물이 자연 그대로 보존된 52㏊의 공간에 오름에서 자유롭게 뛰노는 노루를 관찰할 수 있는 숲길 관찰로와 관람객이 가까이에서 직접 노루를 만져보고 먹이 주기 체험을 할 수 있는 상시관찰원이 조성되어 있다. 전시관에는 제주의 다양한 동식물과 한라산의 식생분포 등 제주의 자연에 대한 동물 이야기관과 노루의 특징, 역사 속에 나오는 노루의 기록 등 노루에 대한 지식을 제공하는 노루의 생태관, 노루의 생태와 생활상을 영상으로 보여주는 노루이야기 영상관이 있다. 천혜의 자연환경을 바탕으로 제주의 상징인 노루를 직접 관찰하고 휴식을 취하면서 노루에 대한 지식을 얻을 수 있는 노루생태관찰원은 자연학습과 생태체험을 동시에 즐길 수 있는 우리들의 공간이다. 거친 오름 중턱을 따라 조성된 숲길 관찰로는 온 가족이 쉽게 이용할 수 있는 트래킹 코스로 약 40분이 소요되며 탁 트인 전망과 함께 자연 속의 휴식공간을 제공한다. 노루생태관찰원에서는 노루먹이 주기 체험과 나무를 이용한 노루 만들기 체험 등을 진행하고 있다.' WHERE place_id=181 AND (description IS NULL OR description='');
-- [182] 한라생태숲
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 182, 'http://tong.visitkorea.or.kr/cms/resource/23/3027023_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=182 AND image_url='http://tong.visitkorea.or.kr/cms/resource/23/3027023_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 182, 'http://tong.visitkorea.or.kr/cms/resource/28/3027028_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=182 AND image_url='http://tong.visitkorea.or.kr/cms/resource/28/3027028_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 182, 'http://tong.visitkorea.or.kr/cms/resource/29/3027029_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=182 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3027029_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 182, 'http://tong.visitkorea.or.kr/cms/resource/32/3027032_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=182 AND image_url='http://tong.visitkorea.or.kr/cms/resource/32/3027032_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 182, 'http://tong.visitkorea.or.kr/cms/resource/33/3027033_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=182 AND image_url='http://tong.visitkorea.or.kr/cms/resource/33/3027033_image2_1.jpg');
UPDATE place SET description='한라생태숲은 1970년대 초부터 1995년까지 개인에게 대부돼 마소의 방목지로 사용했던 곳이었다. 제주 식물의 보고에 걸맞은 산림생물 난대, 온대, 한대 식물 등 다양한 식물상을 조화롭게 설계하여 식재 생태복원 시켰으며, 곶자왈 지대, 천연림 지역을 유전자원 보전지역으로 관리하고 있다. 또한 한라생태숲은 시험연구림으로서의 기능도 갖추고 있어 제주도의 온.난대 수종 및 한라산 고산대 희귀수종에 대한 유전자 보전 연구와 한라산의 훼손지 복구를 위한 식물증식 및 내한성 적응시험림의 역할도 수행하고 있다. 한라생태숲은 훼손되어 방치되었던 야초지를 원래의 숲으로 복원 조성한 곳으로 산림트래킹과 함께 자연생태계의 다양한 모습을 즐길 수 있다. 한라산에 서식하는 동물을 만날 수 있으며, 특히 난대성식물에서부터 한라산 고산식물까지 모두 볼 수 있다. 생태로, 전망대, 양묘하우스, 테마별 산책로, 유전자보존 조직배양실 등 기반시설과 단풍나무숲, 벚나무숲, 구상나무숲, 참꽃나무숲 등 13개의 테마숲, 생태숲 전체의 축소판인 암석원이 중앙에 조성되어 숲다운 숲의 면모를 보여주고 있다.' WHERE place_id=182 AND (description IS NULL OR description='');
-- [183] 인천업사이클에코센터
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 183, 'http://tong.visitkorea.or.kr/cms/resource/77/3431877_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=183 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3431877_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 183, 'http://tong.visitkorea.or.kr/cms/resource/60/3431860_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=183 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3431860_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 183, 'http://tong.visitkorea.or.kr/cms/resource/61/3431861_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=183 AND image_url='http://tong.visitkorea.or.kr/cms/resource/61/3431861_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 183, 'http://tong.visitkorea.or.kr/cms/resource/62/3431862_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=183 AND image_url='http://tong.visitkorea.or.kr/cms/resource/62/3431862_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 183, 'http://tong.visitkorea.or.kr/cms/resource/64/3431864_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=183 AND image_url='http://tong.visitkorea.or.kr/cms/resource/64/3431864_image2_1.jpg');
UPDATE place SET description='인천업사이클에코센터는 도심 속에서 환경소양 교육, 3R(감량, 재사용, 재활용) 생활 교육, 자연생태 체험을 통합적으로 제공하는 환경문화 교육·체험 시설이다. 에코센터는 "에코 플랫폼 구축을 통한 자원순환 사회 구현"이라는 비전을 바탕으로, 지속 가능한 공동체 실현과 환경 실천 문화를 확산하는 것을 목표로 하고 있다. 이를 위해 수요자 맞춤형 자원순환 교육 서비스를 확대하고, 물품 공유센터, 새활용 알맹가게, 캔·페트 수거기 등 시민들이 직접 이용 가능한 플랫폼을 운영하여 생활 속 자원순환 실천을 장려한다. 또한, 업사이클 기업 육성과 소재은행 확대를 통해 순환경제를 활성화하며, 시민들이 체감할 수 있는 쓰레기 ZERO 문화를 확산하고 있다. 나아가, 탄소중립을 위한 선도적인 자원순환 플랫폼을 구축하여 지역사회 환경 보호와 지속 가능한 발전을 위한 기반을 마련하고 있습니다. 에코센터는 시민, 기업, 단체와 협력하여 자원순환 사회를 향한 실질적인 변화를 만들어가고 있다.' WHERE place_id=183 AND (description IS NULL OR description='');
-- [184] 신석구 사택 터
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 184, 'http://tong.visitkorea.or.kr/cms/resource/98/3384998_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=184 AND image_url='http://tong.visitkorea.or.kr/cms/resource/98/3384998_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 184, 'http://tong.visitkorea.or.kr/cms/resource/99/3384999_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=184 AND image_url='http://tong.visitkorea.or.kr/cms/resource/99/3384999_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 184, 'http://tong.visitkorea.or.kr/cms/resource/00/3385000_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=184 AND image_url='http://tong.visitkorea.or.kr/cms/resource/00/3385000_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 184, 'http://tong.visitkorea.or.kr/cms/resource/01/3385001_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=184 AND image_url='http://tong.visitkorea.or.kr/cms/resource/01/3385001_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 184, 'http://tong.visitkorea.or.kr/cms/resource/02/3385002_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=184 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/3385002_image2_1.JPG');
UPDATE place SET description='민족대표 33인 가운데 한 사람인 신석구가 1919년 3·1운동 당시 살던 곳이다. 신석구(1875~1950)는 1907년 기독교에 입교하여 이듬해 세례를 받고 협성신학교에 입학하여 신학을 공부했다. 경기도와 강원도 등지에서 전도사로 일하다가 1917년 9월 남감리교연회에서 목사 안수를 받았다. 1918년 11월부터 서울 수표교교회 담임 목사로 재임하던 중 오화영의 권유를 받고 3·1운동 민족대표로 참여했다. 1919년 3월 1일 태화관에서 독립선언서를 낭독하고 일경에 붙잡혀 2년 8개월 간 옥고를 치렀다. 1938년 7월 신사참배 반대투쟁을 일으켜 옥고를 치렀으며, 1945년 5월 일제가 강요한 전승기원 예배와 일장기 계양 등을 거부하는 활동을 펼치다 연행되어 감옥에서 해방을 맞았다. 1949년 4월 19일 진남포 반공비밀결사 사건의 주모자로 체포되어 평양형무소에서 옥고를 치르다가 6·25전쟁 때 희생되었다.' WHERE place_id=184 AND (description IS NULL OR description='');
-- [186] 봉하마을
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 186, 'http://tong.visitkorea.or.kr/cms/resource/53/3580653_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=186 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/3580653_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 186, 'http://tong.visitkorea.or.kr/cms/resource/46/3580646_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=186 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3580646_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 186, 'http://tong.visitkorea.or.kr/cms/resource/47/3580647_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=186 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3580647_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 186, 'http://tong.visitkorea.or.kr/cms/resource/48/3580648_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=186 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/3580648_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 186, 'http://tong.visitkorea.or.kr/cms/resource/49/3580649_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=186 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/3580649_image2_1.jpg');
UPDATE place SET description='봉하마을은 봉화산 봉수대 아래에 있는 마을이라 하여 봉하마을이라고 불린다. 어린 시절 개구리와 가재를 잡던 마을을 복원시켜 아이들에게 살아있고 아름다운 생태계를 물려주고 싶었던 故 노무현 대통령의 꿈이었다. 대통령은 봉하마을 사저 옆에 있는 생가에서 태어나 유년시절을 보냈으며, 소년시절에는 읍내의 초등학교와 중학교까지 한 시간 남짓을 걸어서 다녔다. 부산에 나가 공부한 고등학교 시절과 군 복무시간을 제외하고, 신혼생활과 제대 후 고시공부도 마을에서 했다. 마을사람 대부분은 오랫동안 봉화마을에 살던 분들이며, 노 대통령과 많은 인연과 추억이 있다.' WHERE place_id=186 AND (description IS NULL OR description='');
-- [188] 운천저수지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 188, 'http://tong.visitkorea.or.kr/cms/resource/13/3367313_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=188 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3367313_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 188, 'http://tong.visitkorea.or.kr/cms/resource/10/3367310_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=188 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3367310_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 188, 'http://tong.visitkorea.or.kr/cms/resource/11/3367311_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=188 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/3367311_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 188, 'http://tong.visitkorea.or.kr/cms/resource/12/3367312_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=188 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/3367312_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 188, 'http://tong.visitkorea.or.kr/cms/resource/14/3367314_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=188 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3367314_image2_1.jpg');
UPDATE place SET description='운천저수지는 1951년 마륵동 농경지에 용수를 공급하기 위해 길이 420m의 제방을 쌓았고 금호, 상무지구의 대규모 택지개발 후 악취와 해충 문제로 매립될 위기에 처했으나 1995년부터 오, 폐수를 차단하고 맑은 물을 공급하여 저수지와 주변 공간을 자연생태공원으로 조성하였다. 저수지를 두른 산책로와 조형물, 분수, 지압로, 정자 등을 이용할 수 있다. 자연을 만끽할 수 있는 공간으로 재탄생한 이곳은 현재 시민들의 산책 명소로 알려져 있다.' WHERE place_id=188 AND (description IS NULL OR description='');
-- [189] 풍암호수
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 189, 'http://tong.visitkorea.or.kr/cms/resource/72/3367472_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=189 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3367472_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 189, 'http://tong.visitkorea.or.kr/cms/resource/67/3367467_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=189 AND image_url='http://tong.visitkorea.or.kr/cms/resource/67/3367467_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 189, 'http://tong.visitkorea.or.kr/cms/resource/68/3367468_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=189 AND image_url='http://tong.visitkorea.or.kr/cms/resource/68/3367468_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 189, 'http://tong.visitkorea.or.kr/cms/resource/69/3367469_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=189 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3367469_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 189, 'http://tong.visitkorea.or.kr/cms/resource/70/3367470_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=189 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3367470_image2_1.jpg');
UPDATE place SET description='1956년 농업용수를 공급하기 위해 축조한 풍암호수는 1975년에 공원으로 지정되었다. 계절에 따라 다양한 꽃이 피며, 이곳에는 장미 140여 종과 2만여 주가 식재되어 있다. 용두동에 자리한 봉황산과 풍암지구를 둘러싼 금당산을 두루 조망할 수 있는 벤치가 마련되어 있으며, 쉼터와 작은 도서관, 야외 공연장 등을 갖춰 자연과 함께 문화를 즐기기에 좋다. 산책로 곳곳에는 시문이 적힌 팻말이 있어 낭만적 분위기를 더한다. (출처 : 전남광주통합특별시 관광 홈페이지)' WHERE place_id=189 AND (description IS NULL OR description='');
-- [190] 계룡 입암저수지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 190, 'http://tong.visitkorea.or.kr/cms/resource/12/2675812_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=190 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/2675812_image2_1.jpg');
UPDATE place SET description='원래 낚시터로 활용되었던 입암저수지에 벤치, 정자 등 휴식공간이 생기며 수변공원으로 다시 태어났다. 저수지를 끼고 걸을 수 있는 둘레길은 965m로 길지 않아 잔잔히 흐르는 물결에 맞춰 걷을 수 있다. 왼쪽으로는 대둔산 오른쪽으로는 계룡산이 보이고, 무엇보다 물에 반영된 메타세콰이아 풍경에 출사지로 유명하다. (출처 : 계룡시 문화관광 홈페이지)' WHERE place_id=190 AND (description IS NULL OR description='');
-- [191] 연산향교
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 191, 'http://tong.visitkorea.or.kr/cms/resource/13/3339113_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=191 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3339113_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 191, 'http://tong.visitkorea.or.kr/cms/resource/14/3339114_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=191 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3339114_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 191, 'http://tong.visitkorea.or.kr/cms/resource/15/3339115_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=191 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3339115_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 191, 'http://tong.visitkorea.or.kr/cms/resource/16/3339116_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=191 AND image_url='http://tong.visitkorea.or.kr/cms/resource/16/3339116_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 191, 'http://tong.visitkorea.or.kr/cms/resource/17/3339117_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=191 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3339117_image2_1.jpg');
UPDATE place SET description='연산향교는 조선 태조 7년(1398)에 창건하여 그간 수차례 중수하여 오늘에 이르고 있다. 지금 남아 있는 건물로는 제사 지내는 공간으로 대성전을 비롯하여 동무, 서무가 있고, 교육 공간으로 강당인 명륜당과 학생들의 기숙사였던 동재, 서재가 있다. 또한 출입구로 홍살문과 내삼문, 외삼문이 남아 있다. 대성전 안쪽에는 공자를 중심으로 5성위(안자, 증자, 자사, 맹자)의 위패를 모시고 있고, 동무와 서무에는 송조 2현과 동국 9현 등 모두 5성 22현 등 27위의 위패를 모시고 있다.' WHERE place_id=191 AND (description IS NULL OR description='');
-- [192] 타임빌라스 수원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 192, 'http://tong.visitkorea.or.kr/cms/resource/79/3427179_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=192 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3427179_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 192, 'http://tong.visitkorea.or.kr/cms/resource/93/3427193_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=192 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3427193_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 192, 'http://tong.visitkorea.or.kr/cms/resource/95/3427195_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=192 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3427195_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 192, 'http://tong.visitkorea.or.kr/cms/resource/96/3427196_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=192 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3427196_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 192, 'http://tong.visitkorea.or.kr/cms/resource/97/3427197_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=192 AND image_url='http://tong.visitkorea.or.kr/cms/resource/97/3427197_image2_1.jpg');
UPDATE place SET description='2014년 개점한 롯데쇼핑의 복합쇼핑몰. 2024년 5월 30일 롯데백화점 수원점과 롯데몰 수원을 통합하여 ''타임빌라스 수원''으로 명칭이 변경되었다.' WHERE place_id=192 AND (description IS NULL OR description='');
UPDATE place SET description='이곳은 조선시대 초기 명재상이며 청백리의 귀감인 방촌 황희(1369~1452) 선생의 유적지이다. 1392년 고려가 망하자 두문동에 은거하다 조선 조정의 요청으로 관직에 나오게 되었다. 선생은 고려 말기부터 조선 초기의 여러 요직을 두루 거치면서 문물과 제도의 정비에 노력했고, 세종연간에는 19년간 의정부 최고의 관직인 영의정에 재직하면서 세종성세에 가장 큰 업적을 남긴 인물로 평가되고 있다. 문종 2년(1452) 90세를 일기로 세상을 떠나자 탄현면 금승리 선영에 예장하고 세종 묘정에 배향되었다. 유적지 내에는 선생의 유업을 기리기 위해 세조 1년(1455) 후손들에 의해 건립된 황희선생 영당과 선생이 관직에서 물러난 후 여생을 보낸 반구정이 위치하고 있다. 본래 영당의 건물은 6·25전쟁 때 전부 불탔으나, 1962년 후손들이 정면 3칸, 측면 2칸의 초익공양식의 맞배집으로 복원하였고, 영당 내부 중앙에 별도의 감실을 두고 선생의 영정을 모셨다. 반구정은 임진강이 내려다 보이는 기암절벽 위에 위치하는데 예로부터 갈매기가 많이 모여들어 ‘갈매기를 벗 삼는 정자’라는 뜻에서 이름을 지었다. 정자 내부에는 미수 허목의 「반구정기」와 여러 개의 중수기 편액이 있다. 이외에도 월헌공 황맹헌 선생 부조묘, 앙지대, 경모재, 방촌 황희 선생 동상 등이 있으며, 입구에는 황희 선생의 업적과 유품을 전시한 방촌기념관이 있다.' WHERE place_id=193 AND (description IS NULL OR description='');
-- [194] 덕진산성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 194, 'http://tong.visitkorea.or.kr/cms/resource/70/3353870_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=194 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3353870_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 194, 'http://tong.visitkorea.or.kr/cms/resource/71/3353871_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=194 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3353871_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 194, 'http://tong.visitkorea.or.kr/cms/resource/72/3353872_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=194 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3353872_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 194, 'http://tong.visitkorea.or.kr/cms/resource/73/3353873_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=194 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3353873_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 194, 'http://tong.visitkorea.or.kr/cms/resource/74/3353874_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=194 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3353874_image2_1.jpg');
UPDATE place SET description='파주시 군내면에 위치한 덕진산성은 삼국시대에 축조된 것으로 추정되는 산성이다. 덕진산성은 내성과 외성으로 나누어져 있는데, 내성은 최고봉인 해발 85m 봉우리를 중심으로 산 능선을 따라 돌며 표주박 형태로 구축되어 있다. 외성에는 두 개의 문지가 완연하게 남아 있고, 성 위에 담을 낮게 쌓았던 부분이 두 군데 있다. 비교적 낮은 곳에 위치해 있지만, 임진강의 북쪽 해안이고 주변에 높은 산이 없어 넓은 땅이 내려다 보이는 전략적 요충지의 역할을 했을 것으로 보인다. 내성은 성벽의 견고성을 높이기 위해 보축과 함께 계단식 석축공사를 했던 것으로 추정된다. 외성은 토축성으로 성벽은 거친 흙다짐을 하면서 흙을 쌓아 성을 축조하였고, 그 외부에 목책 같은 방어시설을 설치하였던 것으로 보인다. 내성과 달리 외성은 광해군 때 전쟁위기가 고조되면서 매우 급하게 조성되었기 때문에 성벽이 치밀하지 못한 것으로 보인다. 덕진산성은 인근의 임진강변에 위치하고 있는 호로고루나 당포성, 은대리성 등과 함께 임진강 북안에 설치된 중요한 삼국시대 성곽이다. 또한 조선시대에 들어와서도 전략적 가치를 인정받아 외성을 확장·수축하여 사용했던 특수한 사례를 보여주고 있어 학술자료로서의 가치가 매우 큰 성이다.' WHERE place_id=194 AND (description IS NULL OR description='');
-- [195] 세종로공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 195, 'http://tong.visitkorea.or.kr/cms/resource/51/3412051_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=195 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/3412051_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 195, 'http://tong.visitkorea.or.kr/cms/resource/25/2459325_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=195 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/2459325_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 195, 'http://tong.visitkorea.or.kr/cms/resource/26/2459326_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=195 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/2459326_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 195, 'http://tong.visitkorea.or.kr/cms/resource/27/2459327_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=195 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/2459327_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 195, 'http://tong.visitkorea.or.kr/cms/resource/52/3412052_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=195 AND image_url='http://tong.visitkorea.or.kr/cms/resource/52/3412052_image2_1.JPG');
UPDATE place SET description='세종로공원은 우리나라의 중심인 세종로 가로(경복궁-광화문-세종문화회관-청계천-서울광장)에 조성된 녹지공간이다. 노후되고 폐쇄되어 시민 이용에 한계가 있던 곳을 광화문광장과 연계하여 개방형 공원으로 탈바꿈하여 새로운 시민소통의 공간으로 마련하였다. 공원 내에는 그늘집과 나무의자가 곳곳에 설치되어 인근 직장인들에게 도심 속의 휴식공간을 제공한다. 또한, 분수대와 야외 원형 무대, 각종 조형물이 들어서 깔끔하고 정돈된 분위기 속에서 휴식을 취할 수 있다.' WHERE place_id=195 AND (description IS NULL OR description='');
-- [196] 아트파크
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 196, 'http://tong.visitkorea.or.kr/cms/resource/59/3414059_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=196 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3414059_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 196, 'http://tong.visitkorea.or.kr/cms/resource/60/3414060_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=196 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3414060_image2_1.jpg');
UPDATE place SET description='아트파크는 다양한 전시를 통하여 예술 치유를 경험할 수 있는 기회를 제공하고 있다. 아트파크는 문화의 거리 삼청동에 2003년 개관하고 2020년 팔판동으로 이전했으며, 창의적인 예술 세계를 추구하는 젊은 작가들을 발굴하여 그들의 작품을 기획, 전시하고 있다. 2004년부터 세브란스 병원의 미술 부문을 자문하고 있으며, 이후에 세브란스 아트 스페이스(신촌 세브란스 병원 본관)의 전시도 기획하여 예술 치유와 새롭고 다양한 예술 체험의 기회를 제공하고 있다. 또한 국내외 유명한 아트페어에 참가해 젊은 작가들의 예술 세계를 소개 및 전시하고 있다. 국공립미술관 및 사립 미술관의 설립과 예술품 소장, 미술관 건축, 그리고 작품 설치 등 다양한 문화예술 행사도 기획하고 자문하고 있다.' WHERE place_id=196 AND (description IS NULL OR description='');
-- [197] 임진강 독개다리
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 197, 'http://tong.visitkorea.or.kr/cms/resource/09/3540609_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=197 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3540609_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 197, 'http://tong.visitkorea.or.kr/cms/resource/03/3540603_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=197 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/3540603_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 197, 'http://tong.visitkorea.or.kr/cms/resource/04/3540604_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=197 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/3540604_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 197, 'http://tong.visitkorea.or.kr/cms/resource/05/3540605_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=197 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/3540605_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 197, 'http://tong.visitkorea.or.kr/cms/resource/06/3540606_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=197 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3540606_image2_1.jpg');
UPDATE place SET description='임진강 독개다리는 6·25 전쟁 당시 폭격으로 파괴된 교각을 활용하여 길이 105m, 폭 5m로 전쟁 전 철교의 형태를 재현해 만든 관광형 인도교이다. 과거, 현재, 미래로 구성된 다리를 걸으며 전쟁의 상흔과 평화의 소중함을 느낄 수 있도록 했다. 통일에 대한 염원과 미래지향적 의미를 담아 만든 이곳은 별도 출입 허가 절차 없이 민통선 내 풍광을 자유로이 즐길 수 있는 관광시설이다.' WHERE place_id=197 AND (description IS NULL OR description='');
-- [198] 퇴촌 맛다냐 딸기 체험농장
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 198, 'http://tong.visitkorea.or.kr/cms/resource/28/3082228_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=198 AND image_url='http://tong.visitkorea.or.kr/cms/resource/28/3082228_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 198, 'http://tong.visitkorea.or.kr/cms/resource/29/3082229_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=198 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3082229_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 198, 'http://tong.visitkorea.or.kr/cms/resource/30/3082230_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=198 AND image_url='http://tong.visitkorea.or.kr/cms/resource/30/3082230_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 198, 'http://tong.visitkorea.or.kr/cms/resource/31/3082231_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=198 AND image_url='http://tong.visitkorea.or.kr/cms/resource/31/3082231_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 198, 'http://tong.visitkorea.or.kr/cms/resource/32/3082232_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=198 AND image_url='http://tong.visitkorea.or.kr/cms/resource/32/3082232_image2_1.JPG');
UPDATE place SET description='맛다냐 딸기체험농장은 인공수정이 아닌 벌로 수정하는 친환경농법을 활용하여 수경재배한 딸기를 맛볼 수 있는 곳이다. 부모님을 도와 농사꾼 경력을 오래 쌓아온 청년 농부의 아이디어로 출발한 딸기 농장과 딸기 체험 농장은 쾌적한 환경과 건강하고 당도 높은 딸기를 자랑한다. 파란 대문이 돋보이는 대형 유리온실에서는 딸기를 재료로 한 음료를 판매한다. 온실 옆 야외에 따로 마련된 대형 비닐 하우스에는 보기에도 탐스러운 딸기들이 주렁주렁 열려있다. 체험객에게는 500g 용량의 투명 플라스틱통과 직접 딸기를 딸 수 있는 30분의 시간이 주어진다. 체험 중에 시식도 함께 즐길 수 있다는 점에서 재미를 더한다. 딸기 체험 농장은 12월에 시작해서 이듬해 6월까지만 운영한다. 딸기 수확 체험 외에 다른 다양한 체험 수업도 있으니 아이들과 함께 방문해보는 것도 좋다.' WHERE place_id=198 AND (description IS NULL OR description='');
-- [199] 경안천 습지생태공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 199, 'http://tong.visitkorea.or.kr/cms/resource/08/3527208_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=199 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3527208_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 199, 'http://tong.visitkorea.or.kr/cms/resource/09/3527209_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=199 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3527209_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 199, 'http://tong.visitkorea.or.kr/cms/resource/10/3527210_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=199 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3527210_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 199, 'http://tong.visitkorea.or.kr/cms/resource/11/3527211_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=199 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/3527211_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 199, 'http://tong.visitkorea.or.kr/cms/resource/12/3527212_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=199 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/3527212_image2_1.jpg');
UPDATE place SET description='봄, 여름, 가을에 신록과 갈대가 어우러지는 경안습지생태공원은 일 년 내내 온갖 철새가 노니는 장소이다. 호숫가 갈대숲 사이로 보이는 몇 그루의 나무들이 운치를 더한다. 이곳은 1973년 팔당댐이 건설되면서 일대 농지와 저지대가 물에 잠긴 이후 자연적으로 습지로 변한 독특한 곳이다. 다양한 수생식물과 갖가지 철새와 텃새가 서식하게 되어 이제 조류관찰과 자연학습의 장으로 거듭났다. 공원을 휘휘 돌아 잘 정돈된 산책로가 조성되어 가족단위로 산책 나온 시민들과 자전거를 타는 사람들에게 휴식처를 제공하기도 한다. 습지는 자연정화기능이 탁월해 수질환경개선에 큰 도움이 되고 있어 점차 그 범위를 확대 조성할 계획에 있으며, 이곳 공원은 습지상태 자연 학습장으로 개인 및 단체 관람객들이 많이 찾아오고 있다. (출처 : 광주시 문화관광 홈페이지)' WHERE place_id=199 AND (description IS NULL OR description='');
-- [200] 문화역 서울 284
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 200, 'http://tong.visitkorea.or.kr/cms/resource/89/3413089_image2_1.png', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=200 AND image_url='http://tong.visitkorea.or.kr/cms/resource/89/3413089_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 200, 'http://tong.visitkorea.or.kr/cms/resource/90/3413090_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=200 AND image_url='http://tong.visitkorea.or.kr/cms/resource/90/3413090_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 200, 'http://tong.visitkorea.or.kr/cms/resource/91/3413091_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=200 AND image_url='http://tong.visitkorea.or.kr/cms/resource/91/3413091_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 200, 'http://tong.visitkorea.or.kr/cms/resource/92/3413092_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=200 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/3413092_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 200, 'http://tong.visitkorea.or.kr/cms/resource/93/3413093_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=200 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3413093_image2_1.png');
UPDATE place SET description='문화역 서울 284는 한국 근현대사의 주요 무대이자 교통과 교류의 관문이었던 구 서울역사의 원형을 복원해 만든 복합문화공간이다. 문화·예술의 창작과 교류가 이루어지는 플랫폼으로서 전시, 공연, 워크숍 등 다채로운 프로그램을 진행하고 있다. 1900년 남대문정차장을 시작으로 경성역을 거쳐 서울역이 됐다가 2004년 구역사가 폐쇄되며 새로운 공간으로 변신했다. 건물 외관은 1925년 경성역을 복원해 재현한 것이다. 유럽 르네상스식의 이국적인 외관으로 붉은 벽돌, 화강암 바닥, 인조석을 붙인 벽, 박달나무 바닥으로 이루어져 있어 인기를 끌었다. ◎ 한류의 매력을 만나는 여행 정보 드라마 속 목하(박은빈 분)가 꽃을 들고 기호(채종협 분)를 기다리던 곳이다. 옛 서울역사를 복원한 만큼, 드라마 에서는 삼천포(김성균 분)가 상경한 장소로 등장한다.' WHERE place_id=200 AND (description IS NULL OR description='');
-- [201] 충무공 이순신 동상
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 201, 'http://tong.visitkorea.or.kr/cms/resource/45/3590045_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=201 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3590045_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 201, 'http://tong.visitkorea.or.kr/cms/resource/42/3590042_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=201 AND image_url='http://tong.visitkorea.or.kr/cms/resource/42/3590042_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 201, 'http://tong.visitkorea.or.kr/cms/resource/43/3590043_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=201 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/3590043_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 201, 'http://tong.visitkorea.or.kr/cms/resource/44/3590044_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=201 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3590044_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 201, 'http://tong.visitkorea.or.kr/cms/resource/46/3590046_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=201 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3590046_image2_1.jpg');
UPDATE place SET description='광화문 광장에 위치한 충무공 이순신 장군 동상은 정부의 산하 단체였던 애국선열 조상건립위원회와 서울신문사의 공동주관으로 1968년 4월 27일 건립되었다. 전체 높이 17m(동상 6.5m, 기단 10.5m)의 청동 입상 형태로 건립되었으며, 주변 조형물로는 거북선 모형 1개와 북 2개가 설치되어 있다. 국가의 심장부로 통하는 광화문 네거리에 위치할 애국선열동상의 인물지정에 관해 확인된 바에 따르면 세종로와 태평로가 뻥 뚫려 있어 남쪽 일본의 기운이 너무 강하게 들어오게 되는데, 이를 제어할 필요가 있다던 당시 풍수지리학자들의 주장을 배경으로 세종로 네거리에 일본이 가장 무서워할 인물의 동상, 국가를 수호하는 지킴이의 의미를 지닐 선열조상의 인물로서 왜적을 물리쳐 나라를 구하신 이순신 장군이 결정되었다고 한다. 이 동상의 조각적 특징은 기념비적 상징성에 있다. 형상의 완전한 사실성보다는 그 인물이 지니는 역사적 의미를 강조하는 표현이다. 바다를 지킨 이순신 장군의 업적을 기리기라도 하듯이 이순신 장군 동상 근처에 분수대를 설치하였다.' WHERE place_id=201 AND (description IS NULL OR description='');
UPDATE place SET description='뜨락영농조합법인은 김해시 화포천습지 생태공원 근처 내에 위치하여 아이들과 함께 산책도 하고, 간단한 음료를 즐기면서 가족 단위로 여러 가지 체험학습을 즐길 수 있는 곳이다. 상시 운영하는 체험 프로그램으로는 아이들 간식을 직접 만들 수 있는, 수박떡바 만들기, 쿠키 만들기, 초콜릿 만들기, 꽃공예 만들기 등이 있으며 체험에 필요한 모든 재료와 조리도구가 준비되어 있다. 그 외에도 실외 체험활동으로 매실 따기(매실청 담그기). 옥수수 따기, 감자 캐기, 고구마 캐기, 단감 따기가 있다. 계절별 체험활동은 김치 담그기, 된장·고추장 담그기, 장 담그기 등 다양한 프로그램을 운영하고 있다. 텃밭에서 자라는 채소들의 이름과 생김새에 관해 설명도 들을 수 있고, 철마다 나는 채소 등 매번 다른 작물을 수확해 가지고 갈 수 있어서 아이들에게 좋은 자연학습장이 되는 곳이다. 뜨락 뒤로는 산책로가 조성되어 있으며 인근에 화포천습지 생태박물관도 있으므로 연계해서 방문해 볼 만하다.' WHERE place_id=202 AND (description IS NULL OR description='');
-- [203] 천주교 명례성지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 203, 'http://tong.visitkorea.or.kr/cms/resource/28/3521128_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=203 AND image_url='http://tong.visitkorea.or.kr/cms/resource/28/3521128_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 203, 'http://tong.visitkorea.or.kr/cms/resource/25/3521125_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=203 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3521125_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 203, 'http://tong.visitkorea.or.kr/cms/resource/26/3521126_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=203 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3521126_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 203, 'http://tong.visitkorea.or.kr/cms/resource/27/3521127_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=203 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/3521127_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 203, 'http://tong.visitkorea.or.kr/cms/resource/29/3521129_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=203 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3521129_image2_1.jpg');
UPDATE place SET description='명례성당은 1896년 경남 지역에서 가장 먼저 설립된 천주교회 본당이다. 성당의 초대 주임은 김대건 안드레아, 최양업 토마스에 이어 우리나라의 세 번째 사제인 강성삼 라우렌시오 신부이다. 성당 건물은 1897년 순교자 신석복 마르코의 생가 인근, 현재의 성모동산 부지에 건립되었는데, 1928년 권영조 마르코 신부에 의해 지금의 자리로 옮겨졌다. 그러나 그 성당이 1936년 태풍으로 무너지면서, 무너진 성당의 잔해를 이용해 원형을 축소 복원하여 1938년 성모승천성당으로 봉헌, 현재까지 이어져 오고 있다. 목재 건물인 성전 내부는 남녀 좌석이 구분되어 있고, 전면 벽에 붙은 제대와 십자가, 장미의 성모상과 14처에서 초기 교회의 모습과 신자들의 신앙생활을 느낄 수 있다. 초기 한국 천주교회의 건축 양식을 엿볼 수 있는 몇 안 되는 중요한 건축물이다.' WHERE place_id=203 AND (description IS NULL OR description='');
-- [204] 소이산
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 204, 'http://tong.visitkorea.or.kr/cms/resource/04/3590304_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=204 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/3590304_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 204, 'http://tong.visitkorea.or.kr/cms/resource/98/3590298_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=204 AND image_url='http://tong.visitkorea.or.kr/cms/resource/98/3590298_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 204, 'http://tong.visitkorea.or.kr/cms/resource/99/3590299_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=204 AND image_url='http://tong.visitkorea.or.kr/cms/resource/99/3590299_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 204, 'http://tong.visitkorea.or.kr/cms/resource/00/3590300_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=204 AND image_url='http://tong.visitkorea.or.kr/cms/resource/00/3590300_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 204, 'http://tong.visitkorea.or.kr/cms/resource/01/3590301_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=204 AND image_url='http://tong.visitkorea.or.kr/cms/resource/01/3590301_image2_1.jpg');
UPDATE place SET description='소이산은 362m의 작은 산으로 때 묻지 않은 자연과 넓은 평야를 내려다보는 정상 전망은 그 위용을 자랑한다. 고려 시대부터 외적의 출연을 알리던 제1로 봉수대가 위치한 공간으로 한국전쟁 이전 화려했던 구 철원의 역사를 기억하고 있을 철원역사의 중심이다. 또한 소이산 생태숲 녹색길이 조성되어 있다. 그동안 소이산은 군사 통제구역을 벗어나 지뢰밭과 민간인 통제 구역에 갇혀 수십 년간 사람의 발길을 거부해 왔다. 그러나 육군의 오랜 소이산 개방 협의와 적극적인 협조로 민관군이 하나 되어 지금의 길을 열어놓았다. 지뢰지대의 안전과 지역의 특수성을 고려한 펜스 설치는 자연 그대로의 울창한 산림과 어우러지며 때 묻지 않은 자연 생태계는 찾는 이에게 큰마음의 안식을 주고 있다. (출처 : 철원군 문화관광 홈페이지)' WHERE place_id=204 AND (description IS NULL OR description='');
UPDATE place SET description='철원군 외촌리에 있는 농산물검사소는 1936년에 지어진 건물로 지상 2층, 연면적 135㎡(40.9평) 규모의 시멘트벽돌 조적조 건축물이다. 오르내림 창호, 출입구의 모접기, 기둥 상부와 천장의 모서리 처리 수법 등에서 근대건축의 특징이 많이 나타난다. 이 건축물은 북한 정권 하에서 불순분자 색출, 반공 인사들의 체포 등 만행을 자행하던 검찰청으로 사용되기도 했다고 한다. 일제강점기 원명은 곡물검사소 철원 출장소로서 현재 구 철원 시가지 유적 중에서 거의 완전한 형태로 보존되어 있는 유일한 건물이다.' WHERE place_id=205 AND (description IS NULL OR description='');
-- [206] 대현문화공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 206, 'http://tong.visitkorea.or.kr/cms/resource/80/3456380_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=206 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3456380_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 206, 'http://tong.visitkorea.or.kr/cms/resource/78/3456378_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=206 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3456378_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 206, 'http://tong.visitkorea.or.kr/cms/resource/79/3456379_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=206 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3456379_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 206, 'http://tong.visitkorea.or.kr/cms/resource/82/3456382_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=206 AND image_url='http://tong.visitkorea.or.kr/cms/resource/82/3456382_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 206, 'http://tong.visitkorea.or.kr/cms/resource/84/3456384_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=206 AND image_url='http://tong.visitkorea.or.kr/cms/resource/84/3456384_image2_1.jpg');
UPDATE place SET description='서울에 있는 대현문화공원은 서울특별시 서대문구 대현동에 위치한 공원이다. 이 공원은 지역 주민들에게 자연과 여가 공간을 제공하며, 주변 도심 속에서 휴식을 취할 수 있는 장소로 사랑받고 있다. 대현문화공원은 다양한 산책로와 조경이 잘 어우러진 녹지 공간을 갖추고 있으며, 지역 주민들의 건강과 여가 활동을 지원하기 위한 운동기구와 휴식 공간이 마련되어 있다. 특히, 근린공원이라는 특성상 지역 주민들이 자주 찾는 친근한 장소로 알려져 있다.' WHERE place_id=206 AND (description IS NULL OR description='');
-- [207] 탈영역우정국
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 207, 'http://tong.visitkorea.or.kr/cms/resource/61/3082461_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=207 AND image_url='http://tong.visitkorea.or.kr/cms/resource/61/3082461_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 207, 'http://tong.visitkorea.or.kr/cms/resource/62/3082462_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=207 AND image_url='http://tong.visitkorea.or.kr/cms/resource/62/3082462_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 207, 'http://tong.visitkorea.or.kr/cms/resource/63/3082463_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=207 AND image_url='http://tong.visitkorea.or.kr/cms/resource/63/3082463_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 207, 'http://tong.visitkorea.or.kr/cms/resource/64/3082464_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=207 AND image_url='http://tong.visitkorea.or.kr/cms/resource/64/3082464_image2_1.JPG');
UPDATE place SET description='지하철 6호선 광흥창역 인근 주택가 한가운데에 있는 탈영역우정국은 (구)창전동 우체국 건물을 문화공간으로 탈바꿈시키는 [리이어콜렉티브]의 장기프로젝트이다. 우체국의 옛말인 우정국으로 공간의 이름을 명하고, 소통의 장소인 POST OFFICE의 [POST]의 다른 뜻인 [이후의], 장르와 영역을 벗어난다는 [탈]의미를 합쳐 [탈영역우정국]이라 명칭 하게 되었다. 탈영역우정국은 건물의 임대기간인 10년 동안을 공간 운영 목표로 물리적 플랫폼과 콘텐츠의 연계, 실험을 통해 경계 없는 예술을 지향한다. 우체국 업무공간이었던 1층과 관사로 썼던 2층의 공간이 있으며 1층은 주로 전시장, 공연장, 상영관, 기타 용도로 2층은 전시 및 소규모 프로젝트와 워크숍 등 프로젝트에 따라 다양한 행사가 진행된다. 탈영역우정국은 유휴공간을 재활용한 공간답게 건물 내부의 커다란 금고, 폐기물을 태우던 소각로 등 우체국 시절 흔적들이 고스란히 남아있다. 대관은 행사의 주최자와 기획내용에 따라 일반 대관과 협력 대관으로 차별화하여 진행된다. 탈영역우정국은 영상, 회화, 설치, 공연, 워크숍, 상영, 강연, 토크 등을 통해 미술계뿐만 아니라 퍼포먼스, 실험 사운드, 실험예술 등 다양한 영역 아티스트들의 실험적인 작품들을 꾸준히 선보이며 인근 학생들과 지역 주민들에게 다양한 문화예술을 선보이고 있다. 반려동물들과 함께 갤러리 관람이 가능한 흔치 않은 예술 공간이다.' WHERE place_id=207 AND (description IS NULL OR description='');
-- [208] 왕십리맛골목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 208, 'http://tong.visitkorea.or.kr/cms/resource/01/3444601_image2_1.jpeg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=208 AND image_url='http://tong.visitkorea.or.kr/cms/resource/01/3444601_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 208, 'http://tong.visitkorea.or.kr/cms/resource/99/3444599_image2_1.jpeg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=208 AND image_url='http://tong.visitkorea.or.kr/cms/resource/99/3444599_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 208, 'http://tong.visitkorea.or.kr/cms/resource/00/3444600_image2_1.jpeg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=208 AND image_url='http://tong.visitkorea.or.kr/cms/resource/00/3444600_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 208, 'http://tong.visitkorea.or.kr/cms/resource/02/3444602_image2_1.jpeg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=208 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/3444602_image2_1.jpeg');
UPDATE place SET description='왕십리맛골목은 서울 성동구 왕십리역 인근에 위치한 음식 거리로, 한식, 중식, 일식 등 다양한 요리를 합리적인 가격에 즐길 수 있는 맛집들이 밀집해 있다. 교통이 편리하고 활기찬 분위기를 자랑하며, 직장인과 학생들의 모임 장소로 인기 있는 먹거리 명소이다.' WHERE place_id=208 AND (description IS NULL OR description='');
-- [209] 용두동 쭈꾸미골목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 209, 'http://tong.visitkorea.or.kr/cms/resource/51/3567851_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=209 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/3567851_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 209, 'http://tong.visitkorea.or.kr/cms/resource/50/3567850_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=209 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/3567850_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 209, 'http://tong.visitkorea.or.kr/cms/resource/52/3567852_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=209 AND image_url='http://tong.visitkorea.or.kr/cms/resource/52/3567852_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 209, 'http://tong.visitkorea.or.kr/cms/resource/53/3567853_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=209 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/3567853_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 209, 'http://tong.visitkorea.or.kr/cms/resource/54/3567854_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=209 AND image_url='http://tong.visitkorea.or.kr/cms/resource/54/3567854_image2_1.jpg');
UPDATE place SET description='용두동 쭈꾸미 골목은 쭈꾸미 특화 거리로 지하철 1호선 제기동역 6번 출구 인근에 있다. 1990년대 초부터 형성된 거리로 쭈꾸미를 매운양념과 볶아내는 철판볶음이 인기 메뉴이다. 골목 초입 황금색 쭈꾸미 모형을 시작으로 쭈꾸미 전문점들이 들어서 있다. 오랜 시간 쭈꾸미골목을 찾아온 단골 손님이 많고, 여러 쭈꾸미 전문점들 중 끌리는 가게에 찾아 들어가는 재미도 있다. 고추장으로 매콤하게 양념한 쭈꾸미를 철판에 익혀 먹는 용두동 쭈꾸미는 별미 중의 별미이다.' WHERE place_id=209 AND (description IS NULL OR description='');
UPDATE place SET description='덕수공원은 포항시 수도산 일대에 조성된 공원으로 사계절 등산객과 행락객으로 붐비는 시민 휴식처이다. 조경시설, 체력단련장, 다목적운동장, 게이트볼장 등의 시설이 잘 갖추어져 있어 인근 지역주민의 신체단련과 여가 장소로 많은 사랑을 받고 있다. 이 외에도 공원 일대에 6·25전쟁 때 산화한 호국영령을 기리기 위해 1964년 세운 충혼탑과 위령비가 있다. 또한 관음사, 극락사, 보현사 등의 사찰과 포항사당제를 지내는 포항사관, 단종 때의 충신 모갈거사의 순절사책비 및 모거비가 있다.' WHERE place_id=210 AND (description IS NULL OR description='');
-- [211] 포항해상공원 캐릭터테마파크
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 211, 'http://tong.visitkorea.or.kr/cms/resource/97/3586597_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=211 AND image_url='http://tong.visitkorea.or.kr/cms/resource/97/3586597_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 211, 'http://tong.visitkorea.or.kr/cms/resource/93/3586593_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=211 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3586593_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 211, 'http://tong.visitkorea.or.kr/cms/resource/94/3586594_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=211 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/3586594_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 211, 'http://tong.visitkorea.or.kr/cms/resource/95/3586595_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=211 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3586595_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 211, 'http://tong.visitkorea.or.kr/cms/resource/96/3586596_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=211 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3586596_image2_1.jpg');
UPDATE place SET description='포항해상공원 캐릭터 테마파크는 해양관광도시의 미래로 나아가는 포항의 새로운 랜드마크다. 공원에서는 아이들이 좋아하는 터닝메카드, 헬로카봇, 소피 루비, 가스파드 엣리사 등의 캐릭터를 만날 수 있다. 야간에는 화려한 조명과 함께 물을 뿜는 음악분수도 구경할 수 있다. 공원 주변에는 포항운하, 송도숲 테마거리, 죽도시장이 있다.' WHERE place_id=211 AND (description IS NULL OR description='');
-- [212] 무궁화동산
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 212, 'http://tong.visitkorea.or.kr/cms/resource/24/3399924_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=212 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3399924_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 212, 'http://tong.visitkorea.or.kr/cms/resource/25/3399925_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=212 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3399925_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 212, 'http://tong.visitkorea.or.kr/cms/resource/26/3399926_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=212 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3399926_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 212, 'http://tong.visitkorea.or.kr/cms/resource/54/3399954_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=212 AND image_url='http://tong.visitkorea.or.kr/cms/resource/54/3399954_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 212, 'http://tong.visitkorea.or.kr/cms/resource/55/3399955_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=212 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/3399955_image2_1.JPG');
UPDATE place SET description='무궁화동산은 옛 중앙정보부의 궁정동 안전가옥 터에 마련된 시민휴식공원이다. 본래 이곳은 청와대 구내로 출입이 금지되었던 곳이나 1993년 청와대 앞길이 개방된 뒤 시민공원으로 조성되었다. 태극무늬로 무궁화를 심었으며, 중앙에 궁정동을 상징하는 ‘우물 정’ 자의 분수대가 놓여 있다. 주변에 자연석으로 성곽을 만들고 240m의 산책로 주위에는 화단을 만들어 놓았다. 화단에는 전국 각지의 야생화 7,700그루를 심었으며, 무궁화와 소나무·느티나무 등 수목 13종 1,500여 그루를 심어 놓았다. 군데군데 벤치가 있어 인근 주민들의 휴식처로도 이용된다.' WHERE place_id=212 AND (description IS NULL OR description='');
-- [213] 한국미술관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 213, 'http://tong.visitkorea.or.kr/cms/resource/76/3412076_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=213 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3412076_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 213, 'http://tong.visitkorea.or.kr/cms/resource/77/3412077_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=213 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3412077_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 213, 'http://tong.visitkorea.or.kr/cms/resource/78/3412078_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=213 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3412078_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 213, 'http://tong.visitkorea.or.kr/cms/resource/79/3412079_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=213 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3412079_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 213, 'http://tong.visitkorea.or.kr/cms/resource/80/3412080_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=213 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3412080_image2_1.JPG');
UPDATE place SET description='한국 미술의 다양한 전시를 소화할 수 있는 첨단 시설을 갖춘 1,460㎡의 한국 최대 규모를 갖춘 대형 전시관이다. 넓고 시원한 전시 공간과 시설로 전시 작품을 돋보이게 한다. 최신 장비를 설비하여 작품 감상을 위한 최상의 환경을 구축하였으며 미술에 친근하게 다가갈 수 있는 부대시설도 조성되어 있다. 한국 미술의 중심지이자 대표 화랑가인 인사동의 중심에 위치하여 국내외 관광객은 물론 미술 애호가, 일반인들이 쉽게 방문할 수 있다.' WHERE place_id=213 AND (description IS NULL OR description='');
-- [214] 송하마을
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 214, 'http://tong.visitkorea.or.kr/cms/resource/30/2796030_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=214 AND image_url='http://tong.visitkorea.or.kr/cms/resource/30/2796030_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 214, 'http://tong.visitkorea.or.kr/cms/resource/19/2796019_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=214 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/2796019_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 214, 'http://tong.visitkorea.or.kr/cms/resource/25/2796025_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=214 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/2796025_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 214, 'http://tong.visitkorea.or.kr/cms/resource/35/2796035_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=214 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/2796035_image2_1.jpg');
UPDATE place SET description='송하마을은 평평하고 근방에서 가장 넓은 논이 펼쳐지는 마을로, ‘천평(川坪)들’이라고 하는데, 다른 말로 ‘금환락지(金環洛池)’라 부르고 있다. 금환락지는 옛 선인들이 최고의 명당 자리라고 여겨왔던 곳을 부르던 이름이다. 지리산 둘레길 중에서는 구례 운조루가 금환락지라고 불린다. 이 들판을 둘러싼 옥녀봉은 옥녀가 하늘로 올라가며 금가락지를 빠뜨렸다는 전설이 있다. 덕천강 하류 쪽에서 울며 마을로 들어와도 금환락지를 지나면 풍요와 기쁨으로 웃으며 나간다는 말이 전해진다.' WHERE place_id=214 AND (description IS NULL OR description='');
UPDATE place SET description='지리산 천왕봉 아래 산청군 시천면 사리에 있는 산천재의 뜰에는 남명 조식(曺植, 1501~1572) 선생이 61세이던 명종 16년(1561)에 손수 심은 매화나무가 있다. 산천재는 선생이 학문을 닦고 연구하던 곳으로 명종 16년(1561)에 세웠고, 순조 18년(1818)에 고쳐졌다. 규모는 앞면 2칸, 옆면 2칸이다. 남명 선생은 영남의 퇴계 이황과 쌍벽을 이룰 만큼 호남 학파의 수장이다. 평생 벼슬에 나가지 않았지만 죽어서 사간원(司諫院)과 대사간(大司諫)에 이어 영의정에 추서된 위인이다. 선생은 1501년(연산 7년)에 경상도 삼가현에서 태어나 벼슬길에 나아간 아버지를 따라 서울로 이주하였다가 그 후 의령, 김해, 삼가 등지에서 거주하였다. 선생은 61세가 되던 해에 산청의 덕산으로 이주해 그곳에 서실을 짓고 산천재라 이름하였다.' WHERE place_id=215 AND (description IS NULL OR description='');
UPDATE place SET description='화진해수욕장은 포항시 북구 송라면에 있는 해수욕장이다. 백사장 길이 400m, 폭 100m로 평균 수심은 1.5m이다. 작은 해수욕장이지만 주변에 나무가 많고 바닷물이 깨끗하다. 또한 냇물이 두 군데에서 흘러내려 담수욕도 할 수 있다. 해수욕장 개장은 7월 초부터 8월 말까지다. 마을 번영회에서는 7월부터 11월까지 소나무 숲에 텐트와 캠핑카를 허용하는 캠핑장을 운영하고 있어 화진해수욕장은 해수욕과 캠핑 두 가지를 동시에 즐길 수 있는 최적의 장소이다. 또한 바나나 보트와 제트스키를 탈 수 있는 해양레포츠 시설이 있고 서핑 강습도 진행된다.' WHERE place_id=216 AND (description IS NULL OR description='');
-- [217] 내연산 보경사 시립공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 217, 'http://tong.visitkorea.or.kr/cms/resource/26/3573026_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=217 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3573026_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 217, 'http://tong.visitkorea.or.kr/cms/resource/24/3573024_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=217 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3573024_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 217, 'http://tong.visitkorea.or.kr/cms/resource/25/3573025_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=217 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3573025_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 217, 'http://tong.visitkorea.or.kr/cms/resource/27/3573027_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=217 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/3573027_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 217, 'http://tong.visitkorea.or.kr/cms/resource/29/3573029_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=217 AND image_url='http://tong.visitkorea.or.kr/cms/resource/29/3573029_image2_1.jpg');
UPDATE place SET description='경북 포항시 북구 송라면의 동북쪽에 위치한 내연산(710m)은 12개의 폭포를 간직하고 있는 태백산맥 줄기에 있는 산으로, 그 경관이 아름다워 경북의 금강산 혹은 소금강이라 한다. 원래는 종남산이라 하였으나 신라 진성여왕이 이 산에서 견훤의 난을 피한 뒤로는 내연산이라 부르게 되었다. 문수산(622m), 향로봉(930m), 삿갓봉(718m), 천령산(775m)등의 높은 준봉들로 둘러싸인 내연산 골짜기 청하골은 여느 심산유곡 못지않게 깊고 그윽하고 다양한 형태의 폭포와 소가 많기로 유명하다. 청하골의 12폭포 가운데 가장 경관이 빼어난 곳은 관음폭포(제6폭포)와 연산폭포(제7폭포)이다. 쌍폭인 관음폭포 주변에는 선일대, 신선대, 관음대, 월영대 등의 기암절벽이 장성처럼 둘러져 있고, 폭포수가 만들어 놓은 못 옆에는 커다란 관음굴이 뚫려 있다. 이 굴 안쪽으로 들어가면 한쪽 입구를 가린 채 떨어지는 폭포수 줄기를 볼 수 있다. 관음폭포 위에 걸린 구름다리를 건너면 높이 30m, 길이 40m에 이르는 연산폭포의 위용이 눈에 들어온다. 이는 청하골에서 가장 규모가 큰 폭포인데, 학소대라는 깎아지른 절벽 아래로 커다란 물줄기가 쏟아지는 광경에는 탄성이 절로 나온다. 관음폭포 앞쪽 암벽의 벼룻길을 지나 다시 15분가량 물길을 따라가면 또 하나의 폭포를 만나게 된다. 이 폭포는 숨겨져 있다고 해서 은폭이라 하는데, 가지런한 물줄기가 시퍼런 소로 떨어지는 모습이 사람들의 마음을 차분하게 해 준다. 남쪽으로 2.5㎞ 떨어진 곳에는 유명한 보경사가 있다. 보경사는 신라 진평왕 때 일조대사가 인도에서 가져온 팔면경을 묻고 세웠다는 절로, 경내에는 고려 때 이송로가 지은 원진국사비(보물)와 포항 보경사 승탑(보물)·숙종어필 등이 있다. 보경사를 지나 물길과 나란히 이어지는 등산로를 1.5km쯤 오르면 제1폭포인 쌍생폭포가 나온다. 그리 우람하지는 않지만 두 물길이 양옆으로 나란히 떨어지는 모양이 단아하기 그지없다. 이 폭포를 지나면 잇따라 보현폭포(제2폭포), 삼보폭포(제3폭포), 잠룡폭포(제4폭포), 무봉폭포(제5폭포)가 나타난다. 등산코스로는 보경사를 출발하여 보현암~소금강전망대~은폭포삼거리~선일대~연산폭포~보경사 원점 회귀로 약 7.5㎞로 2시간 40분 소요된다. 이 코스는 내연산의 모든 명소를 돌아볼 수 있으며 제1~7폭포 조망권으로 가장 아름다운 코스이다.' WHERE place_id=217 AND (description IS NULL OR description='');
UPDATE place SET description='남한산성의 남옹성에서 가장 가까이 있는 사찰이다. 남한산성을 쌓고 산성방어를 위해 창건된 9개의 사찰 중 산성 내의 승군을 총 지휘했던 본영사찰이었다. 이 사찰은 1894년 갑오개혁으로 의승방번제가 폐지될 때까지 270년간 수도 한양을 지켜온 호국사찰로 번창했다. 『중정남한지』에는 개원사가 불경을 많이 소장하고 있고, 무게가 200여근이나 나가는 큰 놋그릇이 4개나 있다고 기록하고 있어 그 규모가 매우 컸음을 짐작할 수 있다. 또한 이곳에는 인조 15년(1637) 이래 대장경이 보관되어 왔으나 1907년 일제가 산성 내 무기고와 화약고를 파괴할 때 법당 누각 등 부속건물과 함께 모두 전소되었다. 1976년부터 정비가 이루어져 대각전, 승장조사전, 범종각, 일주문, 천왕문, 요사채 등이 복원되었다. 원래 개원사의 군기고지, 누각지, 종지 등에는 주춧돌이 남아있어 개원사의 규모와 건물의 배치 형태를 짐작하게 한다. 이 사찰에는 남한산성 축성과 산성을 수호했던 승군들이 사용했던 유분 1점과 석장, 옹기, 함지 등의 유물이 보존되어 있어 번창했던 사찰의 영광을 짐작할 수 있게 한다. (출처 : 국가유산청)' WHERE place_id=218 AND (description IS NULL OR description='');
-- [219] 약사사(성남)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 219, 'http://tong.visitkorea.or.kr/cms/resource/65/3541765_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=219 AND image_url='http://tong.visitkorea.or.kr/cms/resource/65/3541765_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 219, 'http://tong.visitkorea.or.kr/cms/resource/64/3541764_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=219 AND image_url='http://tong.visitkorea.or.kr/cms/resource/64/3541764_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 219, 'http://tong.visitkorea.or.kr/cms/resource/66/3541766_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=219 AND image_url='http://tong.visitkorea.or.kr/cms/resource/66/3541766_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 219, 'http://tong.visitkorea.or.kr/cms/resource/67/3541767_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=219 AND image_url='http://tong.visitkorea.or.kr/cms/resource/67/3541767_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 219, 'http://tong.visitkorea.or.kr/cms/resource/68/3541768_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=219 AND image_url='http://tong.visitkorea.or.kr/cms/resource/68/3541768_image2_1.jpg');
UPDATE place SET description='약사사는 경기도 성남시 남한산성 만덕산의 중턱에 위치한 한국불교여래종에 속한 사찰이다. 약사사는 1937년에 숙현대보살(본명 윤봉순)이 현몽을 꾼 후 이창호 할머니의 도움으로 암자터에서 작은 굴을 발견하고 법당을 세워 신앙생황을 시작한 것이 절의 창건이다. 1967년 3월에 인왕스님이 남한선상에 있던 사찰인 한흥사를 재건한 후 1968년 동방교주 약사여래입상을 봉안하고 현재 사찰 이름인 약사사로 변경하였다. 1999년 대웅보전 공사 중에 고려시대 제작된 것으로 추정되는 석탑 부재(하단 기단 면석 2매, 갑석부재 5매, 옥개석 1개)와 기와 편 등이 발견되어 원래 사찰터였음을 확인할 수 있었다. 지장시왕도는 성보전에 봉안되어 있다.' WHERE place_id=219 AND (description IS NULL OR description='');
-- [220] 왜고개
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 220, 'http://tong.visitkorea.or.kr/cms/resource/07/3542807_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=220 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3542807_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 220, 'http://tong.visitkorea.or.kr/cms/resource/08/3542808_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=220 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3542808_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 220, 'http://tong.visitkorea.or.kr/cms/resource/09/3542809_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=220 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3542809_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 220, 'http://tong.visitkorea.or.kr/cms/resource/10/3542810_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=220 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3542810_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 220, 'http://tong.visitkorea.or.kr/cms/resource/13/3542813_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=220 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3542813_image2_1.jpg');
UPDATE place SET description='왜고개는 기와와 벽돌을 구워 공급하던 와서［瓦署］가 있던 데서 유래한 명칭이다. 서울 명동성당과 중림동 약현성당을 지을 때 사용했던 벽돌도 이곳에서 공급해 주었다 전해진다. 이곳은 1866년 병인박해 때 새남터와 서소문에서 순교한 성인들이 매장되었던 유서 깊은 천주교의 성지이다. 또한 왜고개 성지는 1846년 9월 16일 병오박해 때 순교한 한국인 첫 사제 김대건 안드레아 신부의 시신이 모셔졌다가 이장된 역사를 지닌 곳이다.' WHERE place_id=220 AND (description IS NULL OR description='');
-- [221] 후암거실
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 221, 'http://tong.visitkorea.or.kr/cms/resource/59/3542759_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=221 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3542759_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 221, 'http://tong.visitkorea.or.kr/cms/resource/60/3542760_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=221 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3542760_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 221, 'http://tong.visitkorea.or.kr/cms/resource/61/3542761_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=221 AND image_url='http://tong.visitkorea.or.kr/cms/resource/61/3542761_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 221, 'http://tong.visitkorea.or.kr/cms/resource/67/3542767_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=221 AND image_url='http://tong.visitkorea.or.kr/cms/resource/67/3542767_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 221, 'http://tong.visitkorea.or.kr/cms/resource/68/3542768_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=221 AND image_url='http://tong.visitkorea.or.kr/cms/resource/68/3542768_image2_1.jpg');
UPDATE place SET description='후암거실은 서울역 인근에 위치한 곳으로, 나만의 공간에서 영화를 볼 수 있는 아늑한 공간이다. 남산과 서울타워를 바라보며 홈 시어터 시설(4K 지원 빔과 5.1 채널 스피커)이 완비된 공유거실에서 연인이나 친구와 함께 우리만의 시간을 보낼 수 있는 공간이다. 내부는 청록색 소파와 원목의 가구들로 눈길을 이끄는 세련된 인테리어로 꾸며놨다. 후암거실의 1층과 2층은 카페 후암연립으로 간단한 음료와 디저트, 주류를 주문할 수 있다. 남산타워가 한눈에 보이는 야경을 감상하며 조용하고 편안한 시간을 보낼 수 있다.' WHERE place_id=221 AND (description IS NULL OR description='');
-- [222] 고촌이종근기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 222, 'http://tong.visitkorea.or.kr/cms/resource/45/3462945_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=222 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3462945_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 222, 'http://tong.visitkorea.or.kr/cms/resource/44/3462944_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=222 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3462944_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 222, 'http://tong.visitkorea.or.kr/cms/resource/50/3462950_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=222 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/3462950_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 222, 'http://tong.visitkorea.or.kr/cms/resource/52/3462952_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=222 AND image_url='http://tong.visitkorea.or.kr/cms/resource/52/3462952_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 222, 'http://tong.visitkorea.or.kr/cms/resource/53/3462953_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=222 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/3462953_image2_1.jpg');
UPDATE place SET description='고촌이종근기념관은 종근당 창업자인 고촌 이종근 회장의 철학과 업적을 기리고, 실패를 두려워하지 않는 도전의 자세와 검소한 삶 속에서도 어려운 이웃과 함께했던 나눔의 정신을 남기고자 마련된 종근당 창업 전시관이다. 또한 고촌이종근기념관은 국내외 의약사를 함께 관람할 수 있는 제약박물관이기도 하다. 고촌 이종근 회장의 일생과 종근당의 발전 과정, 국내외 의약사의 흐름을 이해할 수 있는 제1전시실과 고촌의 도전과 나눔의 정신 등을 직접 체험해 볼 수 있는 체험관 형식의 제2전시실로 구성되어 있다.' WHERE place_id=222 AND (description IS NULL OR description='');
-- [223] 부산동명불원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 223, 'http://tong.visitkorea.or.kr/cms/resource/69/3070669_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=223 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3070669_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 223, 'http://tong.visitkorea.or.kr/cms/resource/70/3070670_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=223 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3070670_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 223, 'http://tong.visitkorea.or.kr/cms/resource/71/3070671_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=223 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3070671_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 223, 'http://tong.visitkorea.or.kr/cms/resource/72/3070672_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=223 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3070672_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 223, 'http://tong.visitkorea.or.kr/cms/resource/73/3070673_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=223 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3070673_image2_1.JPG');
UPDATE place SET description='용당동 용비산에 자리 잡고 있는 동명불원(東明佛院)은 동명목재 회장 고 강석진씨가 국가번영과 부모의 왕생극락을 빌고 동명의 산업현장에서 일하는 동명 가족들의 행운을 위해 건립했다. 대부분의 우리나라 사찰이 한국 전통 양식인데 비해, 이 절은 동남아시아의 영향을 받아 용마루가 곧게 뻗어 있고 미얀마의 고탑에서 발굴된 부처님의 사리를 모셔 놓았으며, 이곳에 안치된 목조개금불상은 우리나라에서 제일 큰 불상이며, 범종 또한 한국 최대 규모로, 에밀레종보다 더 무겁다고 한다. 동명불종은 종두에 사룡이 새겨져 있는데 종래 볼 수 없었던 독특한 기법이다. 대웅전 내부에는 석가세존을 중심으로 우편에 미륵보살, 좌편에 제화 갈라보살 등 3본을 모셨다. 법당 가운데에는 기둥없는 특수 공법을 사용했고, 내부 천장의 중앙에 용머리를 조화시켰으며 양쪽 벽에는 비천상을 새겼다. 극락전, 나한전, 관음전이 갖추어져 있고, 칠성각, 산신각, 독성각은 2층으로 설계하고 2층에는 불교서적을 진열하고 있다.' WHERE place_id=223 AND (description IS NULL OR description='');
UPDATE place SET description='The, PRIP은 부산 수영구의 독립책방 ‘The, PLACE’를 거점으로, 여행을 주제로 한 체험형 프로그램을 운영하고 있다. 같은 여행이라도 각자의 방식으로 더 깊고 특별한 추억을 남길 수 있도록, 여행의 기억을 직접 담아내는 기념품 만들기 체험을 제공한다. 일반적인 관광 기념품이 아닌, 나만의 손으로 완성한 결과물을 통해 여행의 의미를 오래도록 간직할 수 있다.' WHERE place_id=224 AND (description IS NULL OR description='');
-- [225] 조계사(서울)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 225, 'http://tong.visitkorea.or.kr/cms/resource/13/3533413_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=225 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3533413_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 225, 'http://tong.visitkorea.or.kr/cms/resource/04/3533404_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=225 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/3533404_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 225, 'http://tong.visitkorea.or.kr/cms/resource/05/3533405_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=225 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/3533405_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 225, 'http://tong.visitkorea.or.kr/cms/resource/06/3533406_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=225 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3533406_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 225, 'http://tong.visitkorea.or.kr/cms/resource/07/3533407_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=225 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3533407_image2_1.jpg');
UPDATE place SET description='조계사는 대한불교조계종의 총본산 사찰이다. 조계사의 전신 각황사가 1910년 서울 종로 중심지에 창건된 이래 백여 년 동안 조계사는 ‘한국불교 일번지’라는 상징성으로 불자들의 마음속 의지처가 되어 왔다. 서울 종로 한복판에 자리한 조계사는 시민들에게 도심 속 평화로운 휴식과 여유를 제공하고 있다. 전통을 이어가는 불자들과 도시의 하루를 살아가는 현대인들의 발길 또한 끊이지 않는다. 정갈하게 정돈된 도량은 봄에는 연등, 여름에는 연꽃, 가을에는 국화로 가득 메워진다. 1층의 불교회관은 전시회나 각종 행사 장소로 이용되고 있으며, 이 건물 안에 자리한 불교 신문사에서는 주간으로 불교 신문을 발행하고 있다. 법당 앞에는 1930년에 조성된 7층 석탑이 있다. 석탑 안에는 부처님의 사리가 봉안되어 있는데, 이 사리는 스리랑카의 달마바라 스님이 1914년 한국에 모셔 온 것이다. 이 석탑 양편에는 석등이 자리하고 있으며 법당을 바라보면서 왼편으로 종각이 보인다. 종각에는 큰북과 범종, 운판과 목어가 있어 아침, 저녁 예불 때마다 울린다. 이는 비단 사람들뿐만 아니라 부처님의 깨달음의 말씀을 듣고자 하는 모든 중생들을 위한 것인데 큰북은 네발 달린 짐승을, 범종은 타락하거나 지옥의 고통을 받는 이들을, 운판은 하늘의 날짐승을, 목어는 물에 사는 짐승을 제도하기 위한 것이다.' WHERE place_id=225 AND (description IS NULL OR description='');
-- [226] 서울 동대문 닭한마리 골목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 226, 'http://tong.visitkorea.or.kr/cms/resource/53/2601453_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=226 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/2601453_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 226, 'http://tong.visitkorea.or.kr/cms/resource/52/2601452_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=226 AND image_url='http://tong.visitkorea.or.kr/cms/resource/52/2601452_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 226, 'http://tong.visitkorea.or.kr/cms/resource/54/2601454_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=226 AND image_url='http://tong.visitkorea.or.kr/cms/resource/54/2601454_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 226, 'http://tong.visitkorea.or.kr/cms/resource/55/2601455_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=226 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/2601455_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 226, 'http://tong.visitkorea.or.kr/cms/resource/56/2601456_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=226 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/2601456_image2_1.jpg');
UPDATE place SET description='의류 상가가 꽉꽉 들어찬 빌딩들이 즐비한 동대문, 이제는 아시아 최대의 의류상가 지대라고 해도 지나치지 않는 곳이 동대문 주변 지역이다. 이곳이 지금의 모습을 갖추게 된 뒤에는 동대문 원단시장, 평화시장과 인근에 자리 잡은 시장 사람들이 있었다. 30여 년 전 그들의 허기를 달래주고 마음을 녹여주던 곳이 지금의 닭한마리 골목이었다. 지하철 4호선 동대문역 8, 9번 출구로 나와 직진하다가 기업은행 건물을 끼고 좌회전하면 일방 통행길이다. 그 길 오른쪽에는 오늘도 동대문 종합시장과 인근 시장으로 드나드는 물품을 나르는 택배 오토바이들이 즐비하게 서 있다. 그 길 중간쯤 오른쪽에 보면 덕성각이라는 중국요리집이 보이는데 그 골목으로 들어가면 닭한마리집들이 나온다. 그 골목 닭한마리집들은 짧게는 5년부터 길게는 30년이 넘는 세월 동안 그 자리를 지키고 있다. 그러나 지금의 식당이 있기 전 개인 집에서 닭칼국수를 팔던 시절까지 거슬러 올라가면 그 골목 닭한마리집의 역사는 30년 보다 훨씬 더 전으로 거슬러올라가야 한다. 당시 닭칼국수를 팔던 할머니집을 28년 전에 인수해서 지금까지 닭한마리를 팔던 식당 주인의 이야기를 들어보면 할머니 한 분이 지금의 닭한마리 식의 요리가 아니라 닭고기를 넣고 칼국수를 끓여 팔았다고 한다. 기와 얹은 한옥집 마루와 방에서 손님을 받았다. 지금의 사장은 그 집을 고스란히 인수해서 당시 마당이었던 곳에 홀을 만들고 식탁을 놓았다. 지금 남아 있는 집들 가운데서 가장 오래된 집은 ‘진옥화 닭한마리’이다. 그러나 식당에 불이 났고 2009년 2월 재건축 하였다. 이 집 또한 처음에는 지금의 닭한마리 식의 요리가 아니라 닭칼국수를 팔았다. 그러니까 동대문 닭한마리 골목 요리의 원조는 닭칼국수인 셈이다. 지금도 이 골목 식당 간판이나 현관, 유리창에는 닭칼국수라는 단어가 남아 있다. 또한 닭한마리와 곁들여 먹는 메뉴에 칼국수 사리가 남아 있는 것도 그 증거인 셈이다. 어떻게 보면 칼국수에 닭고기가 들어간 것에서 닭한마리를 육수에 넣고 끓이는 닭요리에 칼국수가 부재료로 들어가는, 주객이 전도된 요리이기도 하다. 닭한마리 요리는 집집마다 거의 비슷하다. 다른 게 있다면 닭고기를 찍어먹는 소스의 맛과 육수, 선택해 넣어 먹을 수 있는 부재료 등이다. 그래서 이 골목 집집마다 소스와 육수의 비빔을 반만 공개한다. 나머지는 비밀이다. 소스의 주재료인 고춧가루가 좋아야 한다. 맵기만한 고춧가루가 아니라 매우면서도 풍부한 미감과 감칠맛을 머금고 있어야 한다. 또한 육수에 들어가는 재료도 엄나무, 인삼 등 집 마다 다르다. 큰 양푼에 육수 가득 붓고 그 속에서 닭한마리를 통째로 끓인다. 닭은 살짝 삶아서 나오지만 식탁 위 간이 가스레인지 위에서도 푹 끓이는 게 좋다. 왜냐하면 감자와 떡, 인삼, 대추, 버섯 등 부재료들의 맛이 우러나 뒤엉켜 한 맛을 내야 하기 때문이다. 그렇게 끓고 나면 고기와 부재료를 건저 먹은 뒤 남은 육수에 칼국수 사리를 넣어 끓여 먹는 것으로 닭한마리의 만찬은 끝난다. 지금도 이 골목 단골의 반 이상이 동대문 주변 시장 사람들이다. 소문이 일본까지 퍼져 일본 여행자들도 꽤 많이 찾는다. 또한 맛골목을 좋아하는 젊은 연인들이 인터넷에서 정보를 얻고 팔품을 팔아 이 골목을 찾는다. 그들의 입맛에 30년 전 닭한마리 요리가 다시 태어나고 있다.' WHERE place_id=226 AND (description IS NULL OR description='');
-- [227] 구 일본우선주식회사 인천지점
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 227, 'http://tong.visitkorea.or.kr/cms/resource/25/3530025_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=227 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3530025_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 227, 'http://tong.visitkorea.or.kr/cms/resource/24/3530024_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=227 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3530024_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 227, 'http://tong.visitkorea.or.kr/cms/resource/26/3530026_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=227 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3530026_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 227, 'http://tong.visitkorea.or.kr/cms/resource/27/3530027_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=227 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/3530027_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 227, 'http://tong.visitkorea.or.kr/cms/resource/28/3530028_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=227 AND image_url='http://tong.visitkorea.or.kr/cms/resource/28/3530028_image2_1.jpg');
UPDATE place SET description='해안동에 있는 구 일본우선(郵船)주식회사 인천지점 건물은 개항 이후 인천의 해운업을 독점했던 일본 운송회사의 사옥이었다. 일본우선(郵船)주식회사는 당시 인천의 해운업을 거의 독점하다시피 했으며 지금도 도쿄에 본사를 두고 해운업을 계속하고 있다. 이 건물은 당시 업무용 건물로서 규모가 큰 편으로 붉은색 지붕에 외벽을 노란색 타일로 처리했다. 이 건물은 지금 남아 있는 우리나라 근대 건축물 중 상당히 오래되었고, 종교시설과 공공시설이 아닌 민간 소유의 건물 중에 원형이 잘 보존되어 있으며 근대문화 유산으로서 보존 가치가 높다. 최근까지도 해운업 관련 회사의 사무실로 이용되었으며 우리나라 해운 산업의 역사를 보여주는 건물로 가치가 있다.' WHERE place_id=227 AND (description IS NULL OR description='');
-- [228] 북성포구
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 228, 'http://tong.visitkorea.or.kr/cms/resource/47/3352647_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=228 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3352647_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 228, 'http://tong.visitkorea.or.kr/cms/resource/45/3352645_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=228 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3352645_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 228, 'http://tong.visitkorea.or.kr/cms/resource/46/3352646_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=228 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3352646_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 228, 'http://tong.visitkorea.or.kr/cms/resource/48/3352648_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=228 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/3352648_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 228, 'http://tong.visitkorea.or.kr/cms/resource/49/3352649_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=228 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/3352649_image2_1.jpg');
UPDATE place SET description='일몰과 야경이 아름다운 곳으로 유명한 북성포구는 인천항의 항만 풍광으로 색다른 일몰을 조망할 수 있는 곳이다. 인천항 부두와 어선들이 늘어선 포구와 한편에 자리 잡은 어시장들의 모습은 이미 사진가들에게는 잘 알려진 포토 스폿이다. 배우 황정민과 이정재가 출연한 영화 촬영지로 알려져 있다. 인근에 월미도, 개항누리길, 제물포구락부, 송월동 동화마을 등이 위치하여 함께 관광할 수 있다.' WHERE place_id=228 AND (description IS NULL OR description='');
-- [229] 문동폭포
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 229, 'http://tong.visitkorea.or.kr/cms/resource/35/3576135_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=229 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/3576135_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 229, 'http://tong.visitkorea.or.kr/cms/resource/36/3576136_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=229 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/3576136_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 229, 'http://tong.visitkorea.or.kr/cms/resource/37/3576137_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=229 AND image_url='http://tong.visitkorea.or.kr/cms/resource/37/3576137_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 229, 'http://tong.visitkorea.or.kr/cms/resource/38/3576138_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=229 AND image_url='http://tong.visitkorea.or.kr/cms/resource/38/3576138_image2_1.jpg');
UPDATE place SET description='문동폭포는 맑은 물이 암벽 위에서 쏟아지는 거제 유일의 폭포이다. "옥녀봉으로부터 굽이쳐 흘러온 물이 여기 문동에 이르러 폭포를 이루었나니 참으로 그 모습이 굉장하고 볼 만하다. 푸른 벼랑이 물러서고 그 앞을 타고 쏟아지는 물줄기는 자못 웅장하고 신비하다. 마치 구름 문이 열리며 그 사이로 하늘이 쏟아져 내리는 것 같다" 라고 거제도에 유배 온 많은 사대부가 이곳을 거닐며 풍경에 반해 시를 남겼다고 한다. 문동저수지를 지나 주차장에 차를 세우고 짙은 숲 내음을 맡으며 잘 정돈된 지압 보도와 시구절이 적힌 몇몇 시비들을 지나 걷다 보면 기대하지 않은 곳에서 시원한 폭포를 만날 수 있다.' WHERE place_id=229 AND (description IS NULL OR description='');
-- [230] 사곡해수욕장
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 230, 'http://tong.visitkorea.or.kr/cms/resource/41/3372141_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=230 AND image_url='http://tong.visitkorea.or.kr/cms/resource/41/3372141_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 230, 'http://tong.visitkorea.or.kr/cms/resource/42/3372142_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=230 AND image_url='http://tong.visitkorea.or.kr/cms/resource/42/3372142_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 230, 'http://tong.visitkorea.or.kr/cms/resource/43/3372143_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=230 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/3372143_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 230, 'http://tong.visitkorea.or.kr/cms/resource/44/3372144_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=230 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3372144_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 230, 'http://tong.visitkorea.or.kr/cms/resource/45/3372145_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=230 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3372145_image2_1.JPG');
UPDATE place SET description='사곡해수욕장은 거제 시내에서 통영 방향으로 10분 가량 이동하면 도착하는 해수욕장으로 백사장이 넓고 수심이 얕아 가족 단위 해수욕장으로 최적의 장소이다. 모래의 결이 고와서 맨발 걷기가 가능한 명소로 알려져 있다. 여름철에는 해수욕뿐만 아니라 일몰이 아름답기로 유명하다. 해수욕장 바로 옆 사곡요트 레저사업장이 있어 도시민에게 힐링과 짜릿함을 동시에 선사하는 최고의 해수욕장이다. 또한 주차장과 가까운 곳에 매점과 화장실, 샤워실 등 편의시설이 잘 갖추어져 있다.' WHERE place_id=230 AND (description IS NULL OR description='');
-- [231] 백범광장(백범 김구선생 동상)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 231, 'http://tong.visitkorea.or.kr/cms/resource/70/3586270_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=231 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3586270_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 231, 'http://tong.visitkorea.or.kr/cms/resource/69/3586269_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=231 AND image_url='http://tong.visitkorea.or.kr/cms/resource/69/3586269_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 231, 'http://tong.visitkorea.or.kr/cms/resource/71/3586271_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=231 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3586271_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 231, 'http://tong.visitkorea.or.kr/cms/resource/72/3586272_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=231 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3586272_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 231, 'http://tong.visitkorea.or.kr/cms/resource/74/3586274_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=231 AND image_url='http://tong.visitkorea.or.kr/cms/resource/74/3586274_image2_1.jpg');
UPDATE place SET description='백범광장은 서울 중구 회현동 남산공원을 올라가는 도중 산 중턱에 위치해 있다. 공원 내에는 독립운동가이자 교육자이자 정치가인 백범 김구선생상이 설립되어 있다. 백범김구선생기념사업협회가 김구선생의 항일구국운동과 통일국가수립을 위해 노력한 애국정신을 기리기 위해 1969년 8월 백범광장 안에 설립한 것이다. 선생이 암살당한 뒤 2달 만에 김구선생동상건립추진위원회가 설립되면서 동상 건립논의가 시작됐다. 장소는 일제강점기 조선신궁 터였던 남산공원으로 정했다. 동상은 국내외 각계에서 보내온 찬조금으로 생존 시 만들어진 석고 흉상을 바탕으로 조각가 김경승과 민복진이 조각하여 높이 10m로 만들었다. 1960년에 결성된 백범김구선생기념사업회의 주도하에 1969년 4월 8일 동상 건립기공식을 열고, 그가 태어난 날인 8월 23일 동상 제막식을 가졌다. 동상 앞으로 넓은 잔디밭이 조성되어 있고 남산으로 오르는 길이 나있다. 이외에도 공원 내에는 삼국을 통일한 김유신 장군의 기마상과 이시영 선생 동상이 있다.' WHERE place_id=231 AND (description IS NULL OR description='');
-- [232] 삼청공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 232, 'http://tong.visitkorea.or.kr/cms/resource/72/3400072_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=232 AND image_url='http://tong.visitkorea.or.kr/cms/resource/72/3400072_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 232, 'http://tong.visitkorea.or.kr/cms/resource/70/3400070_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=232 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3400070_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 232, 'http://tong.visitkorea.or.kr/cms/resource/71/3400071_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=232 AND image_url='http://tong.visitkorea.or.kr/cms/resource/71/3400071_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 232, 'http://tong.visitkorea.or.kr/cms/resource/80/3400080_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=232 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3400080_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 232, 'http://tong.visitkorea.or.kr/cms/resource/81/3400081_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=232 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3400081_image2_1.JPG');
UPDATE place SET description='삼청공원은 서울 종로구 삼청동에 있는 공원으로 1940년 3월에 도시계획공원 제1호로 지정된 공원이다. 서울 시내 중심부에 위치해 있으며 주변에 화랑가, 별미 맛집들이 많아 시민들의 산책 코스로 사랑받고 있다. 호젓한 산책로에 주위 경치가 좋다. 봄이면 벚꽃이 만발하고, 가을이면 단풍이 아름다워서 많은 사람의 발길이 꾸준하다. 고려 충신 정몽주와 그 어머니의 시조비가 있으며, 산책로를 따라 계곡을 올라가면 약수터가 있다. 유아 숲 체험장도 있어서 아이와 함께 방문하기에도 좋다. 삼청공원 안에는 숲속도서관과 카페가 같이 있어서 커피와 함께 여유로운 책 읽기를 즐길 수 있다. 산책로를 따라 걷다 보면 맨발 지압로와 운동기구, 배드민턴장과 테니스장 등 운동할 수 있는 공간이 있고 어린이 놀이터와 매점 등도 있어서 공원을 이용하는 시민들에게 편의를 제공해 준다.' WHERE place_id=232 AND (description IS NULL OR description='');
-- [233] 죽림해수욕장
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 233, 'http://tong.visitkorea.or.kr/cms/resource/47/3039847_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=233 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3039847_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 233, 'http://tong.visitkorea.or.kr/cms/resource/45/3039845_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=233 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3039845_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 233, 'http://tong.visitkorea.or.kr/cms/resource/46/3039846_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=233 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3039846_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 233, 'http://tong.visitkorea.or.kr/cms/resource/49/3039849_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=233 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/3039849_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 233, 'http://tong.visitkorea.or.kr/cms/resource/50/3039850_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=233 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/3039850_image2_1.JPG');
UPDATE place SET description='거제 죽림해수욕장은 백사장 길이는 540m, 폭은 30m로, 거제면에서 서쪽으로 2km 떨어진 죽림마을에 있다. 1980년 대우조선 사원들을 위해 하동 섬진강의 모래를 투석하여 만든 인공 해수욕장이다. 인공으로 만들어져 일반인들에게는 널리 알려지지 않아 비교적 한적한 편이며 오수 해수욕장으로도 알려져 있다. 매년 7월 중순부터 8월 말까지 개장한다. 죽림마을은 조선시대 거제부에서 어해청을 두고 거제만을 지키던 곳이며, 대나무가 많아 대숲게로 부르던 죽림포에는 지금도 대나무가 많은 마을이다. 죽림해수욕장 주변에는 해금강, 구천계곡 등의 명승지와 거제 기성관, 반곡서원, 옥산금성, 폐왕성, 거제향교 등의 문화유적지, 난과 수석 전시장인 자연예술랜드, 노자산 자연휴양림 등 관광지가 많다.' WHERE place_id=233 AND (description IS NULL OR description='');
-- [234] 구천댐(구천저수지)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 234, 'http://tong.visitkorea.or.kr/cms/resource/73/3373673_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=234 AND image_url='http://tong.visitkorea.or.kr/cms/resource/73/3373673_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 234, 'http://tong.visitkorea.or.kr/cms/resource/76/3373676_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=234 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3373676_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 234, 'http://tong.visitkorea.or.kr/cms/resource/78/3373678_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=234 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3373678_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 234, 'http://tong.visitkorea.or.kr/cms/resource/79/3373679_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=234 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3373679_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 234, 'http://tong.visitkorea.or.kr/cms/resource/80/3373680_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=234 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3373680_image2_1.JPG');
UPDATE place SET description='구천댐(구천저수지)은 거제산업기반 개발구역 내의 대우 및 삼성조선소의 확장계획에 따라 추가 소요되는 생활·공업용수와 인근 주민의 생활용수를 공급하기 위한 저수지다. 구천골 북병산 자락을 흐르는 구룡천에 축조된 댐은 1987년에 완공되어 댐의 맑은 물을 상류 측으로 약 5㎞ 떨어진 구천정수장으로 옮겨 정수처리 과정을 거친 깨끗한 수돗물을 1일 약 22,000톤을 생산하여 신현, 아주, 장승포, 대우조선, 삼성조선, 능포, 옥포 등지로 공급하고 있다. 길이 231m, 높이 50m의 담수댐으로 공해가 전혀 없는 맑은 산간지역에 녹색의 짙은 물빛이 원시림처럼 우거진 수림과 어우러져 그림 같은 정경을 그려낸다.' WHERE place_id=234 AND (description IS NULL OR description='');
-- [235] 억새군락지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 235, 'http://tong.visitkorea.or.kr/cms/resource/41/2794841_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=235 AND image_url='http://tong.visitkorea.or.kr/cms/resource/41/2794841_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 235, 'http://tong.visitkorea.or.kr/cms/resource/48/2794848_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=235 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/2794848_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 235, 'http://tong.visitkorea.or.kr/cms/resource/43/2794843_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=235 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/2794843_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 235, 'http://tong.visitkorea.or.kr/cms/resource/44/2794844_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=235 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/2794844_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 235, 'http://tong.visitkorea.or.kr/cms/resource/46/2794846_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=235 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/2794846_image2_1.jpg');
UPDATE place SET description='거제 오수마을 억새군락지는 산촌 간척지에 위치하여 탁 트인 전망의 자연과 고즈넉함이 공존하는 곳이다. 바다와 바다에 인접한 강이 조화를 이루며 멋진 자연경관을 보여준다. 억새밭 주변으로 산책로가 있어 잠시 산책하기에도 좋다. 억새와 갈대가 함께 피어있으며 호수 안에는 물고기들을, 갈대밭 사이로는 많은 새도 볼 수 있는 곳이다. 바다를 매립해 만든 간척지여서 어촌과 농촌의 풍경이 함께 보인다. 거제의 관광지로 인생샷을 얻을 수 있는 포토존으로 널리 알려져 가을에 특히 많은 사람 이 찾는 곳이다.' WHERE place_id=235 AND (description IS NULL OR description='');
-- [236] 옥포수변공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 236, 'http://tong.visitkorea.or.kr/cms/resource/55/3541855_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=236 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/3541855_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 236, 'http://tong.visitkorea.or.kr/cms/resource/56/3541856_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=236 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3541856_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 236, 'http://tong.visitkorea.or.kr/cms/resource/57/3541857_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=236 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3541857_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 236, 'http://tong.visitkorea.or.kr/cms/resource/58/3541858_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=236 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3541858_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 236, 'http://tong.visitkorea.or.kr/cms/resource/59/3541859_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=236 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3541859_image2_1.jpg');
UPDATE place SET description='옥포수변공원은 대우조선 오션플라자와 옥포동 주민센터 근처에 있다. 데크가 깔린 쾌적한 환경에서 시원한 바닷바람과 잔잔한 파도 소리를 들으며 운동을 하거나 산책을 즐기는 사람들이 많은 공원이다. 임진왜란 해전 승전지 기념비가 세워져 있으며 오페라하우스를 닮은 조형물이 있어 공연이나 그늘막으로 활용된다. 낚시를 즐기는 어른들과 자전거를 타는 아이들이 어우러지는 풍경에서 여유가 느껴진다. 근처에 옥포항, 옥포 중앙공원, 옥포대첩기념공원이 있어 공원과 함께 둘러보기 좋다.' WHERE place_id=236 AND (description IS NULL OR description='');
-- [237] 거제자연예술랜드
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 237, 'http://tong.visitkorea.or.kr/cms/resource/70/3521070_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=237 AND image_url='http://tong.visitkorea.or.kr/cms/resource/70/3521070_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 237, 'http://tong.visitkorea.or.kr/cms/resource/57/3521057_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=237 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3521057_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 237, 'http://tong.visitkorea.or.kr/cms/resource/58/3521058_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=237 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3521058_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 237, 'http://tong.visitkorea.or.kr/cms/resource/59/3521059_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=237 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3521059_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 237, 'http://tong.visitkorea.or.kr/cms/resource/60/3521060_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=237 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3521060_image2_1.jpg');
UPDATE place SET description='거제자연예술랜드는 난·수석계의 대표적인 인물이자 시조시인 능곡 이성보, 안해숙 부부가 50여 년 동안 창작, 수집한 풍란, 석부작, 목부작 등 분재와 각종 희귀 수석이 전시되어 있다. 3개의 전시실로 나눠져 있으며, 민속전시관, 야외공원, 목공예전시실 등이 있어 우리나라 최대의 난, 수석, 분재전시관이라 할 수 있다. 미니 장가계, 천 개의 인상석(나한상), 석목부작, 실생산수화, 목공예 등 진귀한 자연예술품들로 조성되어 있어 거제를 찾는 관광객들의 눈길을 사로잡는 곳이다. 특히 미니장가계라 불리는 석림은 사다리를 놓고 하나하나 돌을 쌓아 올려 만든 것으로, 돌과 돌 사이에 식물을 심어 숲을 연상케 하는데 봄철에는 연산홍과 어우러져 장관을 연출한다. 개인이 만든 공원이라고는 믿기지 않을 정도로 웅장하며, 방문객 누구나 감탄이 나올 정도로 화려하고 신비로운 곳이다. 수석과 분재에 관심이 있다면 꼭 들러볼 관광지다.' WHERE place_id=237 AND (description IS NULL OR description='');
-- [238] 산방산(거제)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 238, 'http://tong.visitkorea.or.kr/cms/resource/48/3576148_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=238 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/3576148_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 238, 'http://tong.visitkorea.or.kr/cms/resource/65/1846865_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=238 AND image_url='http://tong.visitkorea.or.kr/cms/resource/65/1846865_image2_1.jpg');
UPDATE place SET description='거제시에 있는 산방산은 산 모양이 산(山)자와 비슷하고 꽃같이 아름답다고 하여 산방산이라는 이름이 붙었다. 정상에서 큰 바위산가 우뚝 솟아 하나의 산봉우리를 이루고 있으므로 삼봉산이라고도 부르기도 하며, 맞은편으로 우두봉이 자리 잡고 있다. 기암괴석이 많고 경치가 아름다워 마치 금강산과 같다는 말을 듣는 곳이며, 가을에는 단풍이 곱게 물들면 푸르른 남해를 배경으로 멀리 보이는 다도해의 많은 섬들과 어울려 절경을 이룬다. 고려 때 의종이 무신의 난을 피해 이곳과 인근에 있던 폐왕성에서 3년간 피난한 적이 있어, 산 곳곳에 의종과 관련된 장소나 전설이 흔하다.' WHERE place_id=238 AND (description IS NULL OR description='');
-- [239] 고바우식당
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 239, 'http://tong.visitkorea.or.kr/cms/resource/44/2839144_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=239 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/2839144_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 239, 'http://tong.visitkorea.or.kr/cms/resource/45/2839145_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=239 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/2839145_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 239, 'http://tong.visitkorea.or.kr/cms/resource/46/2839146_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=239 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/2839146_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 239, 'http://tong.visitkorea.or.kr/cms/resource/47/2839147_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=239 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/2839147_image2_1.png');
UPDATE place SET description='고바우식당은 경상북도 포항시 북구 신흥동 827-9에 있다. TV조선 시사 교양 프로그램 , SBS 시사 교양 프로그램 에 출연한 바 있다. 대표 메뉴는 돼지고기로 요리한 삼겹살 주물럭이다. 이 밖에 한우 석쇠 구이, 오징어 석쇠 구이, 갈치구이, 육회 등도 맛볼 수 있다. 포항 IC에서 가깝고 인근에 포항 해상공원 캐릭터 테마파크, 포항 송도해수욕장이 있다.' WHERE place_id=239 AND (description IS NULL OR description='');
-- [240] 시민제과
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 240, 'http://tong.visitkorea.or.kr/cms/resource/45/2834245_image2_1.png', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=240 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/2834245_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 240, 'http://tong.visitkorea.or.kr/cms/resource/43/2834243_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=240 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/2834243_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 240, 'http://tong.visitkorea.or.kr/cms/resource/44/2834244_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=240 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/2834244_image2_1.png');
UPDATE place SET description='시민제과는 1949년에 개업한 베이커리 카페이다. 현지인들은 물론 타 지역 사람들에게도 오랜 사랑을 받아왔고, 중소벤처기업부 인증 백년가게로 3대째 이어오고 있는 포항시 1호 제과점이다. 대표메뉴는 쫄깃한 빵 안에 고운 팥앙금을 넣은 1949단팥빵과 100% 국내산 찹쌀을 사용하여 만들어 전통적인 맛을 자랑하는 1949찹쌀떡이다. 이 외에도 포항의 특산물인 ‘정구지’로 만든 소가 듬뿍 들어간 추억의 맛인 정구지 퐝과 프랑스 최고급 고메버터와 진하고 고운 팥앙금을 샌드하여 달콤하고 진한 고소한 맛인 검정고무신 등의 빵도 맛볼 수 있다.' WHERE place_id=240 AND (description IS NULL OR description='');
-- [241] THE 신촌s 덮죽
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 241, 'http://tong.visitkorea.or.kr/cms/resource/37/2844637_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=241 AND image_url='http://tong.visitkorea.or.kr/cms/resource/37/2844637_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 241, 'http://tong.visitkorea.or.kr/cms/resource/35/2844635_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=241 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/2844635_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 241, 'http://tong.visitkorea.or.kr/cms/resource/36/2844636_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=241 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/2844636_image2_1.jpg');
UPDATE place SET description='THE 신촌’s 덮죽은 경상북도 포항시 북구 여천동에 있다. SBS 예능 프로그램 백종원의 골목식당에 출연한 바 있다. 외관과 인테리어가 깔끔하며, 내부에는 단체석이 마련되어 있어 각종 모임을 하기 좋다. 대표 메뉴는 소라와 돌문어를 넣어 만든 소문덮죽이다. 시금치와 소고기가 어우러진 시소덮죽도 판다. 두 메뉴 모두 섞지 말고, 죽 위에 토핑을 조금씩 얹어서 먹어야 맛있다. 포항 IC에서 가깝고, 인근에 포항 해상공원 캐릭터 테마파크, 포항 송도해수욕장이 있다.' WHERE place_id=241 AND (description IS NULL OR description='');
-- [242] 임진각샘뜰두부집
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 242, 'http://tong.visitkorea.or.kr/cms/resource/81/2765081_image2_1.jpeg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=242 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/2765081_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 242, 'http://tong.visitkorea.or.kr/cms/resource/79/2765079_image2_1.jpeg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=242 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/2765079_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 242, 'http://tong.visitkorea.or.kr/cms/resource/82/2765082_image2_1.jpeg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=242 AND image_url='http://tong.visitkorea.or.kr/cms/resource/82/2765082_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 242, 'http://tong.visitkorea.or.kr/cms/resource/83/2765083_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=242 AND image_url='http://tong.visitkorea.or.kr/cms/resource/83/2765083_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 242, 'http://tong.visitkorea.or.kr/cms/resource/84/2765084_image2_1.jpeg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=242 AND image_url='http://tong.visitkorea.or.kr/cms/resource/84/2765084_image2_1.jpeg');
UPDATE place SET description='샘뜰두부집은 DMZ 대성동 마을에서 직접 기른 장단콩을 사용하여, 매일 아침 가마솥에서 만든 두부만을 판매한다. 100% 장단콩 두부 전문점으로 두부로 만든 다양한 두부 요리를 맛볼 수 있다. 가장 인기 있는 메뉴는 두부보쌈이다. 가족 단위로 많이 찾는 곳으로 건강한 두부 요리를 즐길 수 있다.' WHERE place_id=242 AND (description IS NULL OR description='');
-- [243] 포비 DMZ
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 243, 'http://tong.visitkorea.or.kr/cms/resource/43/2865443_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=243 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/2865443_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 243, 'http://tong.visitkorea.or.kr/cms/resource/42/2865442_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=243 AND image_url='http://tong.visitkorea.or.kr/cms/resource/42/2865442_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 243, 'http://tong.visitkorea.or.kr/cms/resource/44/2865444_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=243 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/2865444_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 243, 'http://tong.visitkorea.or.kr/cms/resource/45/2865445_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=243 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/2865445_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 243, 'http://tong.visitkorea.or.kr/cms/resource/46/2865446_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=243 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/2865446_image2_1.jpg');
UPDATE place SET description='경기도 파주시 문산읍에 있는 카페이다. 상호 FOURB의 4와 영문자 B를 합친 심볼이 카페 곳곳에 보인다. 카페 앞 철조망 너머 북한을 볼 수 있는 곳이다. 시멘트벽이 있고, 큰 통창이 있어 밖 풍경이 시원하게 보여, 주변 자연과 하나 된 느낌이다. 테이블은 없고 긴 의자가 몇 개 놓여 있는 작은 카페이다. 경의선 마지막 역인 도라산역으로 가는 기찻길이 철조망 옆으로 있다.' WHERE place_id=243 AND (description IS NULL OR description='');
-- [244] 단박왕돈까스
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 244, 'http://tong.visitkorea.or.kr/cms/resource/77/2851977_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=244 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/2851977_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 244, 'http://tong.visitkorea.or.kr/cms/resource/75/2851975_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=244 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/2851975_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 244, 'http://tong.visitkorea.or.kr/cms/resource/76/2851976_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=244 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/2851976_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 244, 'http://tong.visitkorea.or.kr/cms/resource/78/2851978_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=244 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/2851978_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 244, 'http://tong.visitkorea.or.kr/cms/resource/79/2851979_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=244 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/2851979_image2_1.jpg');
UPDATE place SET description='단박왕돈까스는 4, 6호선 삼각지역 8번 출구 인근에 있다. 매장 앞 도로변에 주차가 가능하다. 이 식당의 주메뉴는 돈가스다. 대표 메뉴로는 함박스테이크, 돈가스, 생선가스를 한 번에 맛볼 수 금왕정식이 있고, 이외에 안심가스, 생선가스, 치즈가스, 함박스테이크 등이 있다. 고기는 두껍지도 않으면서 잘 튀겨져서 느끼하지 않고 먹기 좋다.' WHERE place_id=244 AND (description IS NULL OR description='');
-- [245] 식물학 아이파크몰점
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 245, 'http://tong.visitkorea.or.kr/cms/resource/40/3443040_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=245 AND image_url='http://tong.visitkorea.or.kr/cms/resource/40/3443040_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 245, 'http://tong.visitkorea.or.kr/cms/resource/44/3443044_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=245 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3443044_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 245, 'http://tong.visitkorea.or.kr/cms/resource/45/3443045_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=245 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3443045_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 245, 'http://tong.visitkorea.or.kr/cms/resource/46/3443046_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=245 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3443046_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 245, 'http://tong.visitkorea.or.kr/cms/resource/47/3443047_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=245 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3443047_image2_1.jpg');
UPDATE place SET description='식물에 관심이 많아지고 플랜트 인테리어가 하나의 트랜드로 떠오르는 지금 바쁜일상과 스트레스, 불안정속에 안정을 찾고 싶은 사람들의 안식처, 우리는 그 정점에서 맑은 공기와 퀄리티 높은 스페셜티 커피를 즐길 수 있는 그린스페이스로 마음을 정화하고 휴식과 서비스가 존재하는 공간을 만들기 위해 끊임없이 연구하는 자세로 식물학을 만들어가고 있다.' WHERE place_id=245 AND (description IS NULL OR description='');
-- [246] 쌍대포소금구이 본점
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 246, 'http://tong.visitkorea.or.kr/cms/resource/48/2855548_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=246 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/2855548_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 246, 'http://tong.visitkorea.or.kr/cms/resource/47/2855547_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=246 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/2855547_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 246, 'http://tong.visitkorea.or.kr/cms/resource/49/2855549_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=246 AND image_url='http://tong.visitkorea.or.kr/cms/resource/49/2855549_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 246, 'http://tong.visitkorea.or.kr/cms/resource/50/2855550_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=246 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/2855550_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 246, 'http://tong.visitkorea.or.kr/cms/resource/51/2855551_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=246 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/2855551_image2_1.jpg');
UPDATE place SET description='쌍대포소금구이 본점은 4호선 숙대입구역 10번 출구 인근에 있다. 다양한 고기 부위와 함께 한방껍데기, 양념돼지갈비 등 특별한 메뉴를 제공한다. 일삼꼬들(한정판매)과 같은 특수 부위 고기가 맛있으며, 고기와 찌개를 함께 즐기면 더욱 풍성한 식사를 즐길 수 있다.' WHERE place_id=246 AND (description IS NULL OR description='');
-- [247] 열정도쭈꾸미
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 247, 'http://tong.visitkorea.or.kr/cms/resource/11/2860011_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=247 AND image_url='http://tong.visitkorea.or.kr/cms/resource/11/2860011_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 247, 'http://tong.visitkorea.or.kr/cms/resource/08/2860008_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=247 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/2860008_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 247, 'http://tong.visitkorea.or.kr/cms/resource/09/2860009_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=247 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/2860009_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 247, 'http://tong.visitkorea.or.kr/cms/resource/10/2860010_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=247 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/2860010_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 247, 'http://tong.visitkorea.or.kr/cms/resource/12/2860012_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=247 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/2860012_image2_1.jpg');
UPDATE place SET description='열정도쭈꾸미는 서울시 용산구 원효로에 있는 주꾸미 요리 전문점이다. 지하철 1호선 남영역 1번 출구 열정도 문화거리 부근에 있다. 매장은 옛 가옥을 리모델링해 사용하고 있으며 손글씨로 써 놓은 간판이 인상적이다. 내부는 넓고 시멘트벽이나 천장에 노란색 전선을 그대로 보이게 한 미완성 인테리어도 이 집의 콘셉트다. 철판 주꾸미가 인기 메뉴이며 기본 콩나물과 양배추샐러드, 깻잎 등이 밑반찬으로 나오고 리필도 가능하다. 사이드 메뉴로 치즈 계란찜과 사리들이 있어 추가해서 먹으면 좋다.' WHERE place_id=247 AND (description IS NULL OR description='');
-- [248] 한입소반
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 248, 'http://tong.visitkorea.or.kr/cms/resource/02/2851002_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=248 AND image_url='http://tong.visitkorea.or.kr/cms/resource/02/2851002_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 248, 'http://tong.visitkorea.or.kr/cms/resource/00/2851000_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=248 AND image_url='http://tong.visitkorea.or.kr/cms/resource/00/2851000_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 248, 'http://tong.visitkorea.or.kr/cms/resource/01/2851001_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=248 AND image_url='http://tong.visitkorea.or.kr/cms/resource/01/2851001_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 248, 'http://tong.visitkorea.or.kr/cms/resource/03/2851003_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=248 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/2851003_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 248, 'http://tong.visitkorea.or.kr/cms/resource/04/2851004_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=248 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/2851004_image2_1.jpg');
UPDATE place SET description='한입소반은 서울특별시 용산구 청파동에 있는 김밥전문점으로 지하철 4호선 숙대입구역 8번 출구 인근에 있다. 주차시설이 없어 근처 유료주차장을 이용해야 한다. 대표 메뉴는 묵은지참치김밥, 매콤멸치김밥, 시래기김밥 등이 있고 5줄 이상은 30분 전에, 10줄 이상은 한 시간 전에 예약해 줄 것을 당부하고 있다. TV를 통해 유명 연예인이 소개하면서 더욱 유명해진 곳으로 반찬도 포장 판매한다. 개별 용기에 한 줄씩 포장해 줘서 깔끔하고 주문에 맞는 스티커를 붙여준다. 인근에 식민지역사박물관, 효창공원, 백범 김구 묘역이 있어 연계 관광을 할 수 있다.' WHERE place_id=248 AND (description IS NULL OR description='');
-- [249] 오복함흥냉면
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 249, 'http://tong.visitkorea.or.kr/cms/resource/80/2856480_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=249 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/2856480_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 249, 'http://tong.visitkorea.or.kr/cms/resource/76/2856476_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=249 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/2856476_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 249, 'http://tong.visitkorea.or.kr/cms/resource/77/2856477_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=249 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/2856477_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 249, 'http://tong.visitkorea.or.kr/cms/resource/78/2856478_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=249 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/2856478_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 249, 'http://tong.visitkorea.or.kr/cms/resource/79/2856479_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=249 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/2856479_image2_1.jpg');
UPDATE place SET description='오복함흥냉면은 4호선 숙대입구역 6번 출구 먹자골목 바로 앞에 있다. 메뉴는 회냉면, 물냉면, 비빔냉면, 만두, 사리 단출하다. 테이블 위에 식초, 겨자, 양념장, 컵이 세팅되어 있다. 냉면을 시키면 주전자에 갈비탕과 같은 육수를 주는데 짭짤하면서 후추 맛이 난다. 겨울에 계절 메뉴로 갈비탕을 한다. 만두는 직접 만든 수제만두이고 냉면 면도 직접 뽑아 찰기가 있다. 간자미 회가 올라간 회 냉면은 야채도 큼직큼직하게 썰어 들어가 있고 약간의 물기가 있어 비벼 먹기 편하다. 사리를 추가할 수 있고 물은 셀프이다. 식당 인근에 식민지역사박물관, 전쟁기념관이 있어 연계 관광을 할 수 있다.' WHERE place_id=249 AND (description IS NULL OR description='');
-- [250] 후암편백
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 250, 'http://tong.visitkorea.or.kr/cms/resource/96/3578196_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=250 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3578196_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 250, 'http://tong.visitkorea.or.kr/cms/resource/92/3578192_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=250 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/3578192_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 250, 'http://tong.visitkorea.or.kr/cms/resource/93/3578193_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=250 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3578193_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 250, 'http://tong.visitkorea.or.kr/cms/resource/94/3578194_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=250 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/3578194_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 250, 'http://tong.visitkorea.or.kr/cms/resource/95/3578195_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=250 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3578195_image2_1.jpg');
UPDATE place SET description='용산구 후암동에 위치한 후암편백은 세계 사대진미로 꼽히는 이베리코 흑돼지로 편백나무틀로 건강하게 기름기를 뺀 찜요리 전문점이다. 편백 찜뿐만 아니라 이베리코 철판요리, 샤부샤부, 밀푀유나베, 김치우동 등을 맛볼 수 있다. 전체적인 음식을 찌기 때문에 건강식으로도 안성맞춤이다. 간이 세지 않고 담백한 맛이 특징이다.' WHERE place_id=250 AND (description IS NULL OR description='');
-- [251] 용금옥
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 251, 'http://tong.visitkorea.or.kr/cms/resource/95/3083895_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=251 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3083895_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 251, 'http://tong.visitkorea.or.kr/cms/resource/92/3083892_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=251 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/3083892_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 251, 'http://tong.visitkorea.or.kr/cms/resource/94/3083894_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=251 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/3083894_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 251, 'http://tong.visitkorea.or.kr/cms/resource/96/3083896_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=251 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/3083896_image2_1.jpg');
UPDATE place SET description='용금옥은 1932년에 문을 연 추어탕 전문점이다. 이곳은 미꾸라지를 통째로 넣어 끓이는 서울식 추어탕의 원조인 식당이다. 옛 정취가 살아있는 분위기에서 정성껏 끓인 추어탕을 맛볼 수 있는 곳이다. 육수는 양지머리나 곱창을 삶은 국물에 각종 양념을 넣어 끓여내기 때문에 미꾸라지 비린 냄새가 안 나고 육개장에 가까운 얼큰한 맛을 자랑한다. 90년이 넘는 시간 동안 변함없는 맛과 정통의 가치를 지켜오고 있으며, 꾸준히 사랑받는 서울의 숨은 맛집이다.' WHERE place_id=251 AND (description IS NULL OR description='');
-- [252] 산성대가
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 252, 'http://tong.visitkorea.or.kr/cms/resource/90/2855090_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=252 AND image_url='http://tong.visitkorea.or.kr/cms/resource/90/2855090_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 252, 'http://tong.visitkorea.or.kr/cms/resource/91/2855091_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=252 AND image_url='http://tong.visitkorea.or.kr/cms/resource/91/2855091_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 252, 'http://tong.visitkorea.or.kr/cms/resource/92/2855092_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=252 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/2855092_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 252, 'http://tong.visitkorea.or.kr/cms/resource/93/2855093_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=252 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/2855093_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 252, 'http://tong.visitkorea.or.kr/cms/resource/94/2855094_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=252 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/2855094_image2_1.jpg');
UPDATE place SET description='산성대가는 남한산성 성안에 위치한 엄나무 백숙집이다. 한옥 건물에 한적하고 조용한 곳으로 여유롭게 식사를 즐길 수 있다. 내부는 좌식 테이블 룸으로 되어 있어 다양한 인원을 수용할 수 있으며 각종 모임을 할 수 있다. 이 식당은 토종닭, 토종오리 등 모든 재료를 국내산으로 사용한다. 기본 반찬으로 김치와 나물 종류가 정갈하게 나오며 메인 음식이 나오는 데 오래 걸리지 않아 평일에는 예약 없이 방문해도 식사할 수 있다.' WHERE place_id=252 AND (description IS NULL OR description='');
-- [253] 연남동 공방거리
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 253, 'http://tong.visitkorea.or.kr/cms/resource/21/3550521_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=253 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3550521_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 253, 'http://tong.visitkorea.or.kr/cms/resource/18/3550518_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=253 AND image_url='http://tong.visitkorea.or.kr/cms/resource/18/3550518_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 253, 'http://tong.visitkorea.or.kr/cms/resource/19/3550519_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=253 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/3550519_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 253, 'http://tong.visitkorea.or.kr/cms/resource/20/3550520_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=253 AND image_url='http://tong.visitkorea.or.kr/cms/resource/20/3550520_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 253, 'http://tong.visitkorea.or.kr/cms/resource/22/3550522_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=253 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3550522_image2_1.jpg');
UPDATE place SET description='연남동 공방거리는 서울 마포구 연남동에 위치한 예술 문화 공간으로, 경의선 숲길공원 연남동 구간 인근에 자연스럽게 형성된 골목문화거리다. 이곳은 마포 지역의 젊은 예술가들과 수공예 작가들이 모여 자신들만의 창작 활동을 펼치는 공방과 감각적인 편집숍들이 밀집해 있어, 예술과 일상이 자연스럽게 어우러진 공간으로 주목받고 있다. 골목길을 따라 걷다 보면 곳곳에 아기자기한 소품 가게들과 독특한 감성의 인테리어 소품점, 수공예 액세서리나 도자기, 캔들, 가죽 제품 등을 제작·판매하는 공방들을 만날 수 있다.' WHERE place_id=253 AND (description IS NULL OR description='');
-- [254] 공덕동 족발골목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 254, 'http://tong.visitkorea.or.kr/cms/resource/23/3384423_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=254 AND image_url='http://tong.visitkorea.or.kr/cms/resource/23/3384423_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 254, 'http://tong.visitkorea.or.kr/cms/resource/24/3384424_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=254 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3384424_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 254, 'http://tong.visitkorea.or.kr/cms/resource/25/3384425_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=254 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3384425_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 254, 'http://tong.visitkorea.or.kr/cms/resource/26/3384426_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=254 AND image_url='http://tong.visitkorea.or.kr/cms/resource/26/3384426_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 254, 'http://tong.visitkorea.or.kr/cms/resource/27/3384427_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=254 AND image_url='http://tong.visitkorea.or.kr/cms/resource/27/3384427_image2_1.JPG');
UPDATE place SET description='시장 골목 한쪽에서 시작한 족발집이 골목을 이루고 시장 상권보다 오히려 더 활발하게 손님을 모으고 있는 곳이 있다. 그곳이 바로 공덕동 족발골목이다. 30년 전 시장 한쪽에 자리 잡은 2평짜리 식당이 족발골목의 뿌리다. 당시 시장 사람들의 식사를 위해 순댓국을 끓였고 팍팍한 생활을 달랠 술안주로 족발을 만들어 냈다. 순댓국과 족발은 그렇게 시장 사람들의 속과 마음을 달래주면서 점차 식당이 잘 되자 시장 안의 가방 가게가 업종을 변경해 순대와 족발을 팔기 시작했다. 이렇게 주변에 족발집이 늘어나면서 어느덧 이 골목이 족발 골목으로 자리 잡았다. 세월이 흐르며 손님이 시장 사람들에서 학생과 주변 직장인들로 늘어가며, 손님들은 낮에는 나이 드신 분들이 많고 저녁에는 젊은 사람들이 많다. 나이 든 분들은 청춘 시절, 이 골목에서 술잔을 나누었던 추억을 찾아오고 젊은이들은 그들의 추억을 만들며 활기찬 밤 술자리를 만들어 간다. 손님도 세대가 바뀌었지만 이 골목 식당 가운데는 대를 이어 순댓국을 팔고 족발을 만드는 집도 있으니 오래된 옛 맛을 찾아가 보는 것도 괜찮다.' WHERE place_id=254 AND (description IS NULL OR description='');
-- [255] 역전회관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 255, 'http://tong.visitkorea.or.kr/cms/resource/35/3474735_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=255 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/3474735_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 255, 'http://tong.visitkorea.or.kr/cms/resource/36/3474736_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=255 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/3474736_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 255, 'http://tong.visitkorea.or.kr/cms/resource/37/3474737_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=255 AND image_url='http://tong.visitkorea.or.kr/cms/resource/37/3474737_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 255, 'http://tong.visitkorea.or.kr/cms/resource/38/3474738_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=255 AND image_url='http://tong.visitkorea.or.kr/cms/resource/38/3474738_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 255, 'http://tong.visitkorea.or.kr/cms/resource/39/3474739_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=255 AND image_url='http://tong.visitkorea.or.kr/cms/resource/39/3474739_image2_1.jpg');
UPDATE place SET description='역전회관은 1962년에 바싹불고기를 처음 개발한 식당이다. 강한 불로 재빠르게 바싹 구웠다고 하여 바싹불고기라는 이름이 붙여졌다. 매일 아침 마장동에서 불고기의 원재료인 싱싱한 소고기가 배달되어 오고, 개발된 양념레시피 그대로 양념하여 고기에 입혀 숙성일 시킨다. 이후 가장 중요한 요리도구인 석쇠로 육즙이 살아있도록 특유의 불향을 가득 담아 강한 불에 재빠르게 구워낸다. 요리된 바싹불고기는 깻잎과 궁합이 좋아 함께 싸서 먹으면 더욱 맛있다.' WHERE place_id=255 AND (description IS NULL OR description='');
-- [256] 마포전차종점 3·1운동 만세 시위지
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 256, 'http://tong.visitkorea.or.kr/cms/resource/62/3401362_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=256 AND image_url='http://tong.visitkorea.or.kr/cms/resource/62/3401362_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 256, 'http://tong.visitkorea.or.kr/cms/resource/63/3401363_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=256 AND image_url='http://tong.visitkorea.or.kr/cms/resource/63/3401363_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 256, 'http://tong.visitkorea.or.kr/cms/resource/64/3401364_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=256 AND image_url='http://tong.visitkorea.or.kr/cms/resource/64/3401364_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 256, 'http://tong.visitkorea.or.kr/cms/resource/65/3401365_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=256 AND image_url='http://tong.visitkorea.or.kr/cms/resource/65/3401365_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 256, 'http://tong.visitkorea.or.kr/cms/resource/66/3401366_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=256 AND image_url='http://tong.visitkorea.or.kr/cms/resource/66/3401366_image2_1.JPG');
UPDATE place SET description='마포 전차 종점은 현재는 서울 지하철 5호선 마포역에서 마포대교 진입 구간을 이른다. 일제강점기부터 1960년대 말까지 서울 중심부를 오가던 전차의 종점이 있던 곳이다. 이 장소는 역사적으로 중요한 지점으로 1919년 3월 1일 오후 8시경 약 1,000명의 군중이 모여 독립 만세 시위를 벌인 3·1 운동 만세 시위지이다. 1919년 3월 1일, 태화관에서 독립선언식을 거행하고 오후 2시 탑골공원 팔각정에서 독립선언서가 낭독되며 수천 명의 학생들이 대한독립만세를 외치며 시위행진을 벌였다. 종로에서 광교, 시청 앞, 남대문 등지를 거쳐 해가 질 무렵 오후 8시에 이곳 마포 전차 종점에 집결하여 다시 한번 크게 독립만세를 외쳤고, 연희전문학교 부근에서는 오후 11경까지 해산하지 않고 시위행진은 계속되었다. 단 한 건의 폭동도 발생하지 않고 평화적이고 비폭력적인 방법으로 우리 민족의 독립 의지를 표명했던 곳이다. 현재는 현재 마포전차종점은 3·1운동의 역사적 의미를 기리기 위해 기념비와 유적지가 남아 있으며, 많은 사람들이 이곳을 방문해 역사적 기억을 되새긴다.' WHERE place_id=256 AND (description IS NULL OR description='');
-- [257] 봉원사(서울)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 257, 'http://tong.visitkorea.or.kr/cms/resource/22/3509922_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=257 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3509922_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 257, 'http://tong.visitkorea.or.kr/cms/resource/16/3509916_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=257 AND image_url='http://tong.visitkorea.or.kr/cms/resource/16/3509916_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 257, 'http://tong.visitkorea.or.kr/cms/resource/17/3509917_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=257 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3509917_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 257, 'http://tong.visitkorea.or.kr/cms/resource/18/3509918_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=257 AND image_url='http://tong.visitkorea.or.kr/cms/resource/18/3509918_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 257, 'http://tong.visitkorea.or.kr/cms/resource/21/3509921_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=257 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3509921_image2_1.jpg');
UPDATE place SET description='천년고찰이자 전통사찰인 봉원사는 2011년 전통사찰로 등록되었으며, 2014년 7월에 아미타괘불도와 범종이 서울특별시 유형문화유산으로 등록되었다. 신행단체로는 관음회, 화엄회, 합창단, 민화회의 법회가 정기적으로 열리고 있으며 부설기관으로는 영산재 보존회, 영산재연수원, (사)한국불교영산재보존회와 범패교육기관인 옥천범음대학이 설립되어 운영되고 있다. 도심 속에 위치한 봉원사는 전통불교의식인 영산재를 국내는 물론 국외로 홍보하며 또한 계승발전을 통해 전통불교의 총본산의 위상에 걸림이 없고, 생활 속에서의 불심증장 도량이 되도록 노력하고 있다.' WHERE place_id=257 AND (description IS NULL OR description='');
-- [258] 양화진외국인선교사묘원(외인묘지)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 258, 'http://tong.visitkorea.or.kr/cms/resource/36/3384436_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=258 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/3384436_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 258, 'http://tong.visitkorea.or.kr/cms/resource/30/3384430_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=258 AND image_url='http://tong.visitkorea.or.kr/cms/resource/30/3384430_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 258, 'http://tong.visitkorea.or.kr/cms/resource/31/3384431_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=258 AND image_url='http://tong.visitkorea.or.kr/cms/resource/31/3384431_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 258, 'http://tong.visitkorea.or.kr/cms/resource/32/3384432_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=258 AND image_url='http://tong.visitkorea.or.kr/cms/resource/32/3384432_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 258, 'http://tong.visitkorea.or.kr/cms/resource/33/3384433_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=258 AND image_url='http://tong.visitkorea.or.kr/cms/resource/33/3384433_image2_1.JPG');
UPDATE place SET description='양화진외국인선교사묘원은 복음의 씨앗으로 이 땅에서 헌신한 선교사님들이 안장되어 있다. 남아공, 뉴질랜드, 덴마크, 독일, 미국 등 15개국 417명의 선교사가 안장되어 있다. 이들은 일제 암흑기 한민족을 위해 자신의 인생을 헌신했던 선교사들로 당시 세상에서 덜 알려졌던 나라에 복음의 빛을 나누기 위해서 왔다. 선교활동 및 한국 사회사업의 유공자들로서 병원과 학교의 설립과 같은 사회제도뿐만 아니라, 신분제와 남존여비 관습의 철폐와 같은 무형의 정신세계에서도 한국민에게 심대한 영향을 끼쳤다. 선교사들을 통해 뿌려진 복음의 씨앗들은 오늘날 한국교회와 사회 전반에 걸쳐서 많은 열매를 맺고 있다. 대표적인 선교사로는 배설, 헐버트, 언더우드, 아펜젤러 등이 있다. 현재 한국기독교 100주년 기념교회가 관리하고 있으며, 묘지기념관 교회에 200여 명의 외국인들이 주일마다 예배드리는 교회가 세워져 있다.' WHERE place_id=258 AND (description IS NULL OR description='');
-- [259] 홍제천인공폭포
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 259, 'http://tong.visitkorea.or.kr/cms/resource/45/3452745_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=259 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3452745_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 259, 'http://tong.visitkorea.or.kr/cms/resource/55/3452755_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=259 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/3452755_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 259, 'http://tong.visitkorea.or.kr/cms/resource/57/3452757_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=259 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3452757_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 259, 'http://tong.visitkorea.or.kr/cms/resource/58/3452758_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=259 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3452758_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 259, 'http://tong.visitkorea.or.kr/cms/resource/59/3452759_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=259 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3452759_image2_1.jpg');
UPDATE place SET description='홍제천인공폭포는 도심 속 오아시스와 같은 곳이다. 25m 높이의 시원한 인공폭포가 쏟아져 내리는 장관은 더위를 식히고 일상의 피로를 잊게 해주는 멋진 풍경을 선사한다. 폭포 주변에는 산책로, 벤치, 카페 등 다양한 편의시설이 마련되어 있어 여유로운 시간을 보낼 수 있다.' WHERE place_id=259 AND (description IS NULL OR description='');
-- [260] 서울색공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 260, 'http://tong.visitkorea.or.kr/cms/resource/77/3544377_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=260 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3544377_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 260, 'http://tong.visitkorea.or.kr/cms/resource/78/3544378_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=260 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3544378_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 260, 'http://tong.visitkorea.or.kr/cms/resource/79/3544379_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=260 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3544379_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 260, 'http://tong.visitkorea.or.kr/cms/resource/80/3544380_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=260 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3544380_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 260, 'http://tong.visitkorea.or.kr/cms/resource/81/3544381_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=260 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3544381_image2_1.jpg');
UPDATE place SET description='서울 색공원은 마포대교 교각과 둔치 사이의 하부공간에 색을 주제로 조성된 시민공원(약 9,000㎡)이다. 서울시 색채환경 개선 및 고유한 도시이미지 형성을 위하여 개발한 서울색을 공공공간에 적용하여 서울 색공원(Seoul Color Park)을 조성하여 한강을 찾는 시민들에게 휴식 공간과 동시에 일상적 디자인 체험의 기회도 제공하고 있다. 서울 색공원은 한강의 물결을 형상화한 서울색 조형물, 서울 대표색 10을 활용한 서울색 바코드 그래픽 및 벤치 등이 설치되어 있다. 공공시설물은 거리에 통합되어 쾌적해 보이도록 기와진회색과 돌담회색 등을 적용하고, 가로에서 눈에 잘 보여야 하는 것에는 단청빨간색, 꽃담황토색, 남산초록색 등과 그 계열색들을 적용하여 가로경관의 이미지를 체계적으로 개선해가고 있다. 또한, 서울시립미술관과 서울역사박물관에도 서울색을 활용한 조형물 및 작품이 설치되어 있다.' WHERE place_id=260 AND (description IS NULL OR description='');
-- [261] 고미정
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 261, 'http://tong.visitkorea.or.kr/cms/resource/06/2900306_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=261 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/2900306_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 261, 'http://tong.visitkorea.or.kr/cms/resource/03/2900303_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=261 AND image_url='http://tong.visitkorea.or.kr/cms/resource/03/2900303_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 261, 'http://tong.visitkorea.or.kr/cms/resource/04/2900304_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=261 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/2900304_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 261, 'http://tong.visitkorea.or.kr/cms/resource/05/2900305_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=261 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/2900305_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 261, 'http://tong.visitkorea.or.kr/cms/resource/07/2900307_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=261 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/2900307_image2_1.jpg');
UPDATE place SET description='서대문구 연희동에 있는 한식 전문점이다. 연세대학교 북문 아래쪽에 있으며, 건물 2층과 3층을 사용하는 식당이다. 이천쌀밥정식, 고사리소불고기정식, 간장게장정식 등 정갈한 한식 메뉴를 제공한다. 특히 간장게장은 적당히 달달하고 깊은 맛이 일품이며, 제육볶음은 고기가 두툼하고 맵지도 않으면서 돼지불백 느낌이 나는 인기 메뉴다.' WHERE place_id=261 AND (description IS NULL OR description='');
-- [262] 카페작은숲
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 262, 'http://tong.visitkorea.or.kr/cms/resource/46/2893046_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=262 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/2893046_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 262, 'http://tong.visitkorea.or.kr/cms/resource/43/2893043_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=262 AND image_url='http://tong.visitkorea.or.kr/cms/resource/43/2893043_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 262, 'http://tong.visitkorea.or.kr/cms/resource/44/2893044_image2_1.jpeg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=262 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/2893044_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 262, 'http://tong.visitkorea.or.kr/cms/resource/45/2893045_image2_1.jpeg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=262 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/2893045_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 262, 'http://tong.visitkorea.or.kr/cms/resource/47/2893047_image2_1.jpeg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=262 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/2893047_image2_1.jpeg');
UPDATE place SET description='카페 작은 숲은 경기도 광주시 산성리에 있는 디저트 카페이다. 국청사로 올라가는 길에 있는 2층 건물로 매장 앞에 주차장이 있어 편하게 주차할 수 있다. 통창이 있어 개방감이 좋은 1층 내부에는 곳곳에 큰 나무 화분들이 배치되어 있고 원목 테이블과 의자, 테이블보와 꽃병으로 아기자기하게 꾸며져 있다. 대표 메뉴는 아인슈페너, 아몬드 귀리 라테, 아메리카노 등이지만 다양한 커피와 허브티, 에이드 등의 음료가 있다. 눈이 즐거운 레몬 케이크, 갸또 쇼콜라, 파운드케이크 등의 다양한 디저트도 준비되어 있다. 1인 1 음료 주문이 원칙이고 그 경우 아메리카노 리필을 저렴한 가격에 할 수 있다. 실내, 실외 구분 없이 반려동물을 동반할 수 있는 것이 장점이지만 목줄 착용은 필수이다. ※ 반려동물 동반가능' WHERE place_id=262 AND (description IS NULL OR description='');
-- [263] 오복손두부
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 263, 'http://tong.visitkorea.or.kr/cms/resource/58/2750658_image2_1.png', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=263 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/2750658_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 263, 'http://tong.visitkorea.or.kr/cms/resource/59/2750659_image2_1.png', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=263 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/2750659_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 263, 'http://tong.visitkorea.or.kr/cms/resource/60/2750660_image2_1.png', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=263 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/2750660_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 263, 'http://tong.visitkorea.or.kr/cms/resource/61/2750661_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=263 AND image_url='http://tong.visitkorea.or.kr/cms/resource/61/2750661_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 263, 'http://tong.visitkorea.or.kr/cms/resource/62/2750662_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=263 AND image_url='http://tong.visitkorea.or.kr/cms/resource/62/2750662_image2_1.png');
UPDATE place SET description='경기도 광주 남한산성에 있는 40년 동안 매일 아침에 주먹 두부를 직접 만드는 두부 전문집이다. 완성된 두부는 면포에 싸서 굳히는데, 그 모습이 마치 주먹 모양처럼 생겼다하여 주먹 두부라고 한다. 이 주먹두부가 두부 요리에 들어간다. 이 집의 대표메뉴는 두부 전골이다. 주먹 두부를 주문하면 흰 접시에 주먹 두부와 신김치, 간장을 함께 주는데 궁합이 서로 잘 맞는다. 순두부 백반, 산채비빔밥, 토종닭백숙, 토종닭볶음탕 등의 메뉴도 있다.' WHERE place_id=263 AND (description IS NULL OR description='');
UPDATE place SET description='고두리는 용산에 위치한 국산콩 두부요리 전문점이다. 고두리의 고는 높을 고(高) 자로 고랭지 배추로 담근 김치를 사용하기 때문에 사계절 내내 아삭한 식감의 국내산 김치를 맛볼 수 있다. 두는 콩 두(豆 )자이다. 파주 장단콩으로 직접 빚은 두부와 신선한 재료들로 색다른 요리를 매일 같이 준비한다. 갓 나온 두부는 따끈하고 입안에서 살살 녹는 맛이다. 리는 마을 리(里) 자로 김치와 두부가 맛있는 마을로 브랜드를 의미한다. 이곳에는 시원한 국물의 맑은 전골, 얼큰한 국물의 얼큰 전골, 그리고 점심에는 순두부 메뉴들도 준비되어 있다. 이외에도 특별한 메뉴인 루콜라 콩 비지 전, 순두부 계란찜 등이 다양하게 준비되어 있다.' WHERE place_id=264 AND (description IS NULL OR description='');
UPDATE place SET description='용산구에 위치한 중식집 주사부는 TV프로그램 ‘생활의 달인’에서 탕수육 달인으로 인정 받은 탕수육 맛집이다. 탕수육 달인이 이전에는 특밥, 깐쇼새우가 맛있기로 유명했다. 세월감이 느껴지는 오래된 중식당이지만 가게 내부는 깔끔하고 쾌적하다. 주사부는 메뉴가 다양하고 어떤 메뉴를 시켜도 맛있다. 대표 메뉴로는 탕수육, 난자완스, 라조육, 양장피 등이 있다.' WHERE place_id=265 AND (description IS NULL OR description='');
UPDATE place SET description='서울시 서대문구 창천동에 위치한 중식당이다. 처음 개업할 때 신촌의 허름한 뒷골목에 위치했으며, 한국인의 입맛을 자극하는 매운 홍합요리로 입소문을 타고 유명해진 곳이다. 완차이라는 식당 이름은 매운 중국음식점이라 표방한 것인데, 매운맛을 좋아하는 사람이라면 꼭 한 번쯤 찾아갈 만한 곳이다. 중국 쓰촨성에서 맛본 정통의 매운맛이라기보다는 한국식 매운맛이 가미되어 오히려 중국 정통 사천요리보다 더 맛이 좋다. 완차이는 이미 많은 입소문을 타서 인지, 항상 자리가 차 있어서 20분은 족히 기다려야 음식들과 만날 수가 있다. 가장 인기가 있는 것은 아주 매운 홍합 볶음. 빨간 실내 분위기와 큰 접시 위의 빨간 홍합 볶음을 여기저기에서 볼 수가 있다. 홍합을 마른 홍고추와 마늘, 그리고 매운 소스로 볶은 것이다. 굴짬뽕 또한 얼큰하고 시원한 맛에 사람들이 자주 찾는다. 또한 이 집은 기본적으로 자장면도 맛있다. 까만 자장소스가 깔끔하고 고소하다.' WHERE place_id=266 AND (description IS NULL OR description='');
UPDATE place SET description='연남동에 위치한 유기농 쌀 가루로 만든 맛있는 디저트 카페이다. 애견 동반이 가능하여 경의선 숲길공원을 따라 산책 후 들리기 좋은 위치에 있으며, 밀가루가 아닌 쌀가루로 만든 홀케이크로 입소문을 타고 있다. 케이크뿐 아니라 모든 커피 종류는 디카페인으로도 주문이 가능하여 일부러 찾아오는 방문객들도 많다. 홀케이크 예약은 카카오 채널을 통해 문의가 가능하다.' WHERE place_id=267 AND (description IS NULL OR description='');
UPDATE place SET description='꼬숑돈가스는 아담한 한옥 분위기의 돈가스 전문점이다. 돈가스 1장과 샐러드, 장국, 밥, 단무지로 구성된 돈가스 메뉴는 가격이 택시 기본요금보다도 저렴하다. 엄청나게 저렴한 가격이라 물은 기본 제공이 되지 않고 별도구매 해야 하며, 신용카드 사용은 불가하고 현금으로만 주문이 가능하다. 가격과는 반비례로 돈가스의 고기 두께는 두툼한 편이라 이곳을 찾는 방문객들이 많은 편이다.' WHERE place_id=268 AND (description IS NULL OR description='');
UPDATE place SET description='족탕은 30년 역사답게 맛의 노하우를 가지고 있는 선미정의 으뜸 메뉴이다. 오후 10시경부터 다음날 새벽까지 우족을 고아 뼈는 발라내고 고기는 양념에 묻혀 별도로 준다. 워낙 오래 고아 살덩이가 입에서 살살 녹는다. 국물은 파와 마늘, 소금으로만 간을 해서 밥을 말아먹는데 고소하면서도 깊은 맛이 난다. 매장 내부에 단체석이 있어 단체로 이용이 가능하며 주차는 식당 바로 앞에 있는 무료 공영주차장을 이용하면 된다.' WHERE place_id=269 AND (description IS NULL OR description='');
-- [270] 아키라커피 본점
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 270, 'http://tong.visitkorea.or.kr/cms/resource/39/2848739_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=270 AND image_url='http://tong.visitkorea.or.kr/cms/resource/39/2848739_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 270, 'http://tong.visitkorea.or.kr/cms/resource/36/2848736_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=270 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/2848736_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 270, 'http://tong.visitkorea.or.kr/cms/resource/37/2848737_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=270 AND image_url='http://tong.visitkorea.or.kr/cms/resource/37/2848737_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 270, 'http://tong.visitkorea.or.kr/cms/resource/38/2848738_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=270 AND image_url='http://tong.visitkorea.or.kr/cms/resource/38/2848738_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 270, 'http://tong.visitkorea.or.kr/cms/resource/40/2848740_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=270 AND image_url='http://tong.visitkorea.or.kr/cms/resource/40/2848740_image2_1.jpg');
UPDATE place SET description='아키라커피 본점은 인천역 인근에 있다. 이곳은 차이나타운에 있는 일본풍의 감성 카페다. 이곳 외벽에는 아키라커피와 카페의 시그니처 로고인 파랑새 문양이 보인다. 이곳에 들어가는 대문 안으로는 하얀 돌이 깔린 마당이 있다. 마당에는 테이블과 정원이 꾸며져 있다. 카운터 양쪽으로는 룸 2개가 있다. 이곳의 대표 메뉴는 아메리카노, 에스프레소다. 그 외 메뉴는 라테, 플랫화이트, 오렌지파운드케이크, 포르투갈 에그타르트, 로투스 더블 초코 쿠키 등이 있다. 이 카페 인근에는 한미수교백주년기념탑, 자유공원, 맥아더장군동상, 인천광역시역사자료관 등이 있어 연계 관광을 할 수 있다.' WHERE place_id=270 AND (description IS NULL OR description='');
UPDATE place SET description='수연생오리구이는 인천 연수구에 위치한 생오리구이 전문점이다. 가정집을 방문한 듯한 주택 건물에 홀도 방석을 깔고 앉는 좌식 테이블과 식탁 테이블을 갖췄다. 직각으로 크고 넓은 창이 있어서 동네를 모두 조망할 수 있는 개방감도 좋다. 생오리구이와 소양구이 등 생고기 구이를 판매한다. 2층 단독 건물과 넓은 주차장을 갖추고 있어 각종 단체 모임을 하기에도 좋다.' WHERE place_id=271 AND (description IS NULL OR description='');
-- [272] 대산미술관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 272, 'http://tong.visitkorea.or.kr/cms/resource/83/3077383_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=272 AND image_url='http://tong.visitkorea.or.kr/cms/resource/83/3077383_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 272, 'http://tong.visitkorea.or.kr/cms/resource/35/2824535_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=272 AND image_url='http://tong.visitkorea.or.kr/cms/resource/35/2824535_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 272, 'http://tong.visitkorea.or.kr/cms/resource/36/2824536_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=272 AND image_url='http://tong.visitkorea.or.kr/cms/resource/36/2824536_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 272, 'http://tong.visitkorea.or.kr/cms/resource/84/3077384_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=272 AND image_url='http://tong.visitkorea.or.kr/cms/resource/84/3077384_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 272, 'http://tong.visitkorea.or.kr/cms/resource/85/3077385_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=272 AND image_url='http://tong.visitkorea.or.kr/cms/resource/85/3077385_image2_1.jpg');
UPDATE place SET description='대산미술관은 소외된 농촌지역의 미술관을 모토로 화가 김홍 선생의 유언을 받들어 ‘순수한 작가와 미술학도들, 그리고 삶의 여유를 찾고 싶은 사람들의 영원한 안식처’로 창원 지역을 비롯한 낙후된 지역과 소외된 농촌지역의 문화 공간 활성화 및 문화 예술 발전에 기여하고자 1999년 1월 창원시 대산면 낙동강변 유등리 마을에 설립되었다. 주요 활동으로는 현대미술의 조사 연구, 미술작품의 수집 및 보존, 전시기획과 개최 등 종합적이고 다양한 정보를 제공함으로써 창원지역의 문화 수요에 부응하여 문화적 서비스를 제공하고 문화소외계층에 예술 활동의 참여 기회를 마련하고 있다. 미술관 소장품은 섬유미술, 염색공예, 서양화, 한국화, 판화, 서예, 조각에 이르기까지 국내 작가 작품 500여 점을 소장하고 있으며 해마다 6회 이상 특별전과 기획전을 열어 지역민들에게 다양한 볼거리를 제공하고 있다.' WHERE place_id=272 AND (description IS NULL OR description='');
-- [273] 빗돌배기마을
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 273, 'http://tong.visitkorea.or.kr/cms/resource/24/3576724_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=273 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3576724_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 273, 'http://tong.visitkorea.or.kr/cms/resource/09/3576709_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=273 AND image_url='http://tong.visitkorea.or.kr/cms/resource/09/3576709_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 273, 'http://tong.visitkorea.or.kr/cms/resource/10/3576710_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=273 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3576710_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 273, 'http://tong.visitkorea.or.kr/cms/resource/15/3576715_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=273 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3576715_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 273, 'http://tong.visitkorea.or.kr/cms/resource/16/3576716_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=273 AND image_url='http://tong.visitkorea.or.kr/cms/resource/16/3576716_image2_1.jpg');
UPDATE place SET description='빗돌배기마을의 빗돌은 마을에 위치한 조그만 동산이 빗돌이라는 돌로 이루어져 있고 배기는 아래라는 뜻의 순우리말로, 빗돌로 이루어진 동산 아래에 마을을 이루었다고 하여 빗돌배기마을이라는 이름이 붙여졌다. 빗돌배기마을은 제철 농산물 수확체험, 벼 교실 논 학교, 먹거리체험, 타기체험, 생태체험, 전래전통놀이체험, 만들기체험 등의 농촌을 주제로 다양한 체험교육프로그램을 제공한다. 빗돌배기마을에서는 농촌의 가치를 재발견하고, 자연이 주는 건강을 느끼며 감사하는 마음을 가질 수 있다.' WHERE place_id=273 AND (description IS NULL OR description='');
-- [274] 카페 산
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 274, 'http://tong.visitkorea.or.kr/cms/resource/97/2854997_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=274 AND image_url='http://tong.visitkorea.or.kr/cms/resource/97/2854997_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 274, 'http://tong.visitkorea.or.kr/cms/resource/94/2854994_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=274 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/2854994_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 274, 'http://tong.visitkorea.or.kr/cms/resource/95/2854995_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=274 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/2854995_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 274, 'http://tong.visitkorea.or.kr/cms/resource/96/2854996_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=274 AND image_url='http://tong.visitkorea.or.kr/cms/resource/96/2854996_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 274, 'http://tong.visitkorea.or.kr/cms/resource/98/2854998_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=274 AND image_url='http://tong.visitkorea.or.kr/cms/resource/98/2854998_image2_1.jpg');
UPDATE place SET description='카페 산은 남한산성 내에 있어 대형카페이다. 카페로 들어오는 입구에 넓은 전용 주차장이 있어 주차를 편하게 할 수 있다. 붉은 벽돌 건물로 1층 매장은 오픈형 천장에 넓은 창으로 되어 있어 주변 경관을 볼 수 있다. 2층과 3층은 단체 예약을 할 수 있으며, 야외 테라스 포함 카페 내부는 모두 금연 구역이다. 흡연자들을 위한 흡연구역은 주차장 입구 옆에 준비되어 있다. 전문 베이커리 카페는 아니지만 다양한 빵과 케이크, 커피와 차가 준비되어 있고 1인 1 음료 주문해야 한다. 이 카페는 내부, 외부 모두 반려동물 동반이 안 되며 외부 음식 반입 금지되어 있다. 카페 산은 조용한 산속의 봄, 여름, 가을, 겨울의 아름다운 풍경을 볼 수 있는 곳이다.' WHERE place_id=274 AND (description IS NULL OR description='');
-- [275] 장경사(경기)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 275, 'http://tong.visitkorea.or.kr/cms/resource/56/3336256_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=275 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3336256_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 275, 'http://tong.visitkorea.or.kr/cms/resource/47/3336247_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=275 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3336247_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 275, 'http://tong.visitkorea.or.kr/cms/resource/48/3336248_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=275 AND image_url='http://tong.visitkorea.or.kr/cms/resource/48/3336248_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 275, 'http://tong.visitkorea.or.kr/cms/resource/50/3336250_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=275 AND image_url='http://tong.visitkorea.or.kr/cms/resource/50/3336250_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 275, 'http://tong.visitkorea.or.kr/cms/resource/51/3336251_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=275 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/3336251_image2_1.jpg');
UPDATE place SET description='장경사는 유네스코 세계유산에 등재된 남한산성 내에 자리하고 있는 사찰이다. 남한산성이 만들어지던 때 같이 세워졌다. 임진왜란 때 크게 활약한 승군[僧軍 ; 전쟁과 같은 나라의 위기 때 만들어지는 승려로 구성된 군대]은 전쟁 후에도 국가의 필요에 따라 여러 일을 맡았다. 남한산성 역시 당시 북쪽에서 새롭게 힘을 키우던 후금[後金]을 대비하여 벽암 각성스님을 책임자로 하여 승군을 동원해 서울의 남쪽에 새로 쌓은 대규모 산성이다. 이때 전국에서 올라온 승군이 머무를 수 있도록 하기 위해 이전부터 있던 망월사와 옥정사 외에 산성 안에 7개의 사찰을 함께 세우게 되는데, 장경사도 이 중 하나이다. 승군은 공사가 끝난 후에도 산성을 관리하고 지키는 역할을 맡게 되어 9개 사찰에 머물면서 승려이자 군인으로 생활하였다. 1907년 일제에 의해 대한제국의 군대가 해산될 때 남한산성의 군사시설과 함께 승군의 주둔지인 사찰도 대부분 파괴되었지만, 장경사는 이를 피한 유일한 사찰이었다. 1975년 화재로 큰 피해를 입었지만 복구되어 지금에 이르고 있다. 장경사는 남한산성 동문인 좌익문에서 북쪽방향 망월봉 중간쯤 되는 곳에 위치하여, 산성의 제1암문을 통해서 사람들이 많이 드나들었다. 이 사찰에는 석가모니불을 모신 대웅전을 중심으로 승려가 머무르며 생활하는 공간인 요사와 문루인 진남루, 작은 불전인 칠성각이 남아 있다. 승군이 주둔하였던 곳이기에 예불을 드리는 불전보다 요사의 규모가 큰 것이 특징이다. (출처 : 국가유산청 홈페이지)' WHERE place_id=275 AND (description IS NULL OR description='');
-- [276] 단대동 닭죽촌
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 276, 'http://tong.visitkorea.or.kr/cms/resource/23/3551823_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=276 AND image_url='http://tong.visitkorea.or.kr/cms/resource/23/3551823_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 276, 'http://tong.visitkorea.or.kr/cms/resource/19/3551819_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=276 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/3551819_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 276, 'http://tong.visitkorea.or.kr/cms/resource/20/3551820_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=276 AND image_url='http://tong.visitkorea.or.kr/cms/resource/20/3551820_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 276, 'http://tong.visitkorea.or.kr/cms/resource/21/3551821_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=276 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3551821_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 276, 'http://tong.visitkorea.or.kr/cms/resource/22/3551822_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=276 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3551822_image2_1.jpg');
UPDATE place SET description='단대동 닭죽촌은 성남시 수정구 단대동 일대 닭죽을 주로 판매하는 식당이 집중 조성된 곳이다. 원래 이 닭죽촌은 성남에서 들어가는 남한산성 유원지 입구에 조성되어 있었는데, 인근에 광주대단지가 조성됨에 따라 이주한 사람들이 양계장을 운영하며 닭죽 판매를 시작했다. 남한산성 정비사업으로 유원지 입구 닭죽촌은 철거되었고, 성남시는 일부 식당을 현재의 단대동으로 이전해 운영하도록 했다. 이곳의 닭죽은 다른 지역의 백숙과 달리 찹쌀, 인삼, 대추, 밤 등을 듬뿍 넣고 끓여 몸보신하기 좋아 인기를 누렸다.' WHERE place_id=276 AND (description IS NULL OR description='');
-- [277] 창작농성골
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 277, 'http://tong.visitkorea.or.kr/cms/resource/06/3032306_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=277 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/3032306_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 277, 'http://tong.visitkorea.or.kr/cms/resource/07/3032307_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=277 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3032307_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 277, 'http://tong.visitkorea.or.kr/cms/resource/08/3032308_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=277 AND image_url='http://tong.visitkorea.or.kr/cms/resource/08/3032308_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 277, 'http://tong.visitkorea.or.kr/cms/resource/10/3032310_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=277 AND image_url='http://tong.visitkorea.or.kr/cms/resource/10/3032310_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 277, 'http://tong.visitkorea.or.kr/cms/resource/12/3032312_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=277 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/3032312_image2_1.jpg');
UPDATE place SET description='벚꽃길로 익숙한 농성동 하정웅미술관 대로변 맞은편으로 조금만 발걸음을 옮기면 향수를 자극하는 옛 동네가 자리한다. 마을 사랑방 역할을 하는 ‘점방’이 아직도 존재하고 350년이 넘는 당산나무가 자리를 지키고 있는 마을이다. 긴 역사만큼이나 노후화한 이 마을은 ‘창작농성골’이라는 이름과 함께 옷을 새롭게 갈아입었다. 창작농성골은 농성동 68-23 일원으로 지난 2018년 도시재생뉴딜사업 ‘우리 동네 살리기’ 유형에 선정, 3년 간의 사업을 마치고 새로운 모습을 갖추게 됐다. 사업 이전 마을 골목 이곳저곳은 노후화해 고령의 주민들의 안전사고나 범죄 가능성이 항상 도사리고 있기도 했다. 마을 골목골목을 채운 그림들은 페인팅이 아닌 타일 등으로 벽과 바닥을 꾸며 이 마을만의 독특한 풍경을 만든 것은 물론 변색되지 않고 유지와 관리가 수월하도록 했다. 벚꽃이 한창 필 무렵 창작농성골은 옛 주택가가 아닌, 시원한 커피 한잔 들고서 산책하기에 좋은 공간으로 탈바꿈되었다. 곳곳이 따뜻하고 아기자기함으로 넘쳐나고 평소라면 그냥 지나쳤을 골목에도 각기 다른 볼거리와 재미가 담겨있어 숨은 그림 찾기를 하는 기분이 든다. 특히 공영주차장 위쪽으로 조성된, 마을을 내려다볼 수 있는 작은 전망대는 숨겨진 나만의 명소를 찾은 기분을 선물할 것이다.' WHERE place_id=277 AND (description IS NULL OR description='');
-- [278] 경국사(서울)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 278, 'http://tong.visitkorea.or.kr/cms/resource/81/3571681_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=278 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3571681_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 278, 'http://tong.visitkorea.or.kr/cms/resource/75/3571675_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=278 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3571675_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 278, 'http://tong.visitkorea.or.kr/cms/resource/76/3571676_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=278 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3571676_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 278, 'http://tong.visitkorea.or.kr/cms/resource/77/3571677_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=278 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3571677_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 278, 'http://tong.visitkorea.or.kr/cms/resource/79/3571679_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=278 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3571679_image2_1.jpg');
UPDATE place SET description='경국사는 1325년에 자정율사가 창건하였는데, 삼각산의 청봉 아래 있다고 하여 ‘청암사’라 하였다고 전한다. 창건주인 자정율사는 계율에 정통하였을 뿐만 아니라 법화(法華), 유식(唯識) 등에서도 조예가 깊었으며, 계율과 법화경(法華經) 관음신앙을 승상 하던 사찰이었다. 그리고 1349년 태고보우(太古普愚) 국사가 중국에 가서 석옥청공(石屋淸珙) 스님으로부터 법을 전해 받고 귀국하여 경국사에 공민왕의 청을 받아 금란가사와 주장자(拄杖子)를 하사 받고 국사가 되었다. 1545년, 명종조(明宗朝) 때 왕모(王母)인 문정왕후(文定王后)가 불사를 하면서 국가의 경사스러움을 끊어지지 않도록 기원하는 뜻에서 ‘경국사’로 개칭하였다. 그 후 1698년에는 연화승성 스님이 절을 중수하고 천태성전을 세웠다. 천태성전은 독성을 모신 전각으로 이때 기록한 『천태성전상량문』이 지금도 전한다. 전 대통령 이승만도 경국사에 주석하던 보경스님이 인품에 감화되어 전 닉슨 부통령과 함께 참배한 일화도 전한다. 1977년부터 1985년까지 보경 금어 큰스님이 뒤를 이어 주지 소임을 맡은 지관 대종사(전 조계종 총무원장)가 경국사를 현재의 모습으로 탈바꿈시켰으며, 2005년에 한국불교의 율풍 진작에 헌신했던 자운대율사의 계주원명사리탑(戒珠圓明舍利塔)도 이곳에 세워졌다.' WHERE place_id=278 AND (description IS NULL OR description='');
-- [347] 산띠
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 347, 'http://tong.visitkorea.or.kr/cms/resource/81/3567681_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=347 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3567681_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 347, 'http://tong.visitkorea.or.kr/cms/resource/76/3567676_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=347 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3567676_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 347, 'http://tong.visitkorea.or.kr/cms/resource/77/3567677_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=347 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3567677_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 347, 'http://tong.visitkorea.or.kr/cms/resource/78/3567678_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=347 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3567678_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 347, 'http://tong.visitkorea.or.kr/cms/resource/79/3567679_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=347 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3567679_image2_1.jpg');
UPDATE place SET description='홍대에 유일한 정통 네팔, 인도 음식점인 산띠(Shanti)는 산스크리트어로 마음의 평화라는 뜻이다. 산띠의 가장 큰 특징은 경력 15년 이상의 인도, 네팔인 요리사로 구성되어 있어 네팔의 맛 그대로 느낄 수 있다. 산띠의 코스 중 가장 인기 있는 ''Shanti Couple'' 코스는 상큼한 그린 샐러드와 함께 향신료를 발라 탄두리에서 직접 구운 탄두리 치킨 반 마리, 인도 전통 빵인 난과 카레 모두를 맛볼 수 있다. 특히 카레는 치킨, 양고기, 야채, 시푸드 등이 준비되어 있어 더욱 다양한 맛을 즐길 수 있다. 향이 가득한 음식 뒤에 나오는 후식 또한 인기다. 다즐링에서 직접 가져온 차로 만든 짜이(밀크 티)와 인도 요구르트 음료 라씨는 이국적인 음식의 맛을 오래도록 간직할 수 있게 도와준다. 이색적인 음식을 찾는 사람들에게 인기있는 곳이다.' WHERE place_id=347 AND (description IS NULL OR description='');
-- [348] 시도 플레이스
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 348, 'http://tong.visitkorea.or.kr/cms/resource/80/2849780_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=348 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/2849780_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 348, 'http://tong.visitkorea.or.kr/cms/resource/84/2849784_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=348 AND image_url='http://tong.visitkorea.or.kr/cms/resource/84/2849784_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 348, 'http://tong.visitkorea.or.kr/cms/resource/86/2849786_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=348 AND image_url='http://tong.visitkorea.or.kr/cms/resource/86/2849786_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 348, 'http://tong.visitkorea.or.kr/cms/resource/87/2849787_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=348 AND image_url='http://tong.visitkorea.or.kr/cms/resource/87/2849787_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 348, 'http://tong.visitkorea.or.kr/cms/resource/89/2849789_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=348 AND image_url='http://tong.visitkorea.or.kr/cms/resource/89/2849789_image2_1.jpg');
UPDATE place SET description='‘미니멀에 기반하여 매일 다른 옷, 다양한 실루엣을 연출할 수 있는 옷장을 모두가 가질 수 있게 하자’ 라는 생각에서 시도의 브랜드 스토리가 시작되었다. 복식에 대한 끝없는 고민을 통해 원단, 봉제, 마감의 완성도를 높여 시즌이 지나도 여전히 빛날 수 있는 컬렉션을 전개한다. 시도플레이스는 마냥 옷가게는 아니다. 1층에는 카페가 있고 2층에는 매장이 있는데 이뿐아니라 cd플레이어를 청음할 수 있는 곳부터 책이 놓여진 포토존까지 그야말로 복합문화공간이라고 할 수 있다.' WHERE place_id=348 AND (description IS NULL OR description='');
-- [349] 라멘트럭 상수본점
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 349, 'http://tong.visitkorea.or.kr/cms/resource/04/2853804_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=349 AND image_url='http://tong.visitkorea.or.kr/cms/resource/04/2853804_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 349, 'http://tong.visitkorea.or.kr/cms/resource/05/2853805_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=349 AND image_url='http://tong.visitkorea.or.kr/cms/resource/05/2853805_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 349, 'http://tong.visitkorea.or.kr/cms/resource/06/2853806_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=349 AND image_url='http://tong.visitkorea.or.kr/cms/resource/06/2853806_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 349, 'http://tong.visitkorea.or.kr/cms/resource/07/2853807_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=349 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/2853807_image2_1.jpg');
UPDATE place SET description='상수역 4번 출구에서 도보 1분 거리에 있는 라멘전문점이다. 강변북로 방향으로 돌아내려 오면 있는 첫 번째 골목에 있다. 라멘이 대표 메뉴이다. 그 외에도 신라멘, 수제교자, 미니차슈동 등 다양한 메뉴를 즐길 수 있다. 근처 카페가 많고 방탈출카페, 노래방, 보드게임카페, 만화카페 등 놀거리가 많아 데이트하기 좋다.' WHERE place_id=349 AND (description IS NULL OR description='');
UPDATE place SET description='인천광역시 무의동에 위치한 팔미도 등대는 1903년에 세워진 국내 현존최고(最古)의 근대식 등대이다. 1950년 9월 인천상륙작전 당시 연합군함대를 인천으로 진입할 수 있도록 인도하여 6·25전쟁의 국면을 일시에 뒤바꾸는 데 기여한 역사적, 상징적 가치가 있다. 팔미도 등대는 점등 당시에는 석유 백열등으로 불빛을 밝혀왔으나 1954년 8월 발동발전기를 설치해 전기등으로 교체했으며, 1991년 9월에는 태양광 발전 시설을 설치했다. 또한 1963년 12월 무선표지국을 설치하였으며, 1999년 8월에는 위성 항법 보정 시스템까지 설치해 운영을 시작했다. 정교한 위치 정보를 제공하는 등 항로 표지 분야의 과학 기술 발전을 선도해 왔다. 2003년 퇴역할 때까지 100년 동안 인천항을 드나드는 선박의 길잡이 역할을 담당했다. 옛 등대 옆에는 2003년에 순수 국내기술로 지어진 새 등대가 나란히 세워져 있다. 지상 3층 규모의 팔미도 등대역사관에서는 인천상륙작전 성공에 기여한 팔미도 등대의 역사를 영상 자료 등을 볼 수 있으며, 우리나라 전체 등대 역사도 둘러볼 수 있다.' WHERE place_id=354 AND (description IS NULL OR description='');
-- [355] 낙동강승전기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 355, 'https://tong.visitkorea.or.kr/cms/resource/62/3348462_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=355 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/3348462_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 355, 'https://tong.visitkorea.or.kr/cms/resource/63/3348463_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=355 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/3348463_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 355, 'https://tong.visitkorea.or.kr/cms/resource/64/3348464_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=355 AND image_url='https://tong.visitkorea.or.kr/cms/resource/64/3348464_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 355, 'https://tong.visitkorea.or.kr/cms/resource/65/3348465_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=355 AND image_url='https://tong.visitkorea.or.kr/cms/resource/65/3348465_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 355, 'https://tong.visitkorea.or.kr/cms/resource/66/3348466_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=355 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/3348466_image2_1.jpg');
UPDATE place SET description='낙동강에는 겨레를 지키는 거룩한 힘이 굽이치고 나라를 통일한 높은 뜻과 슬기가 서려있다. 6.25 동란 때 동족을 배반한 붉은 무리들의 총부리도 이 강줄기에 이르러 여지없이 꺾이고 말았다. 낙동강 승전기념관은 6.25 한국전쟁 당시 조국수호의 마지막 보루였던 낙동강 방어선에서 침략군들을 물리친 역사적 승리를 기리고 당시 조국에 바친 충성스러운 선열들의 얼을 받들며, 국민들의 호국안보 의식을 드높이고자 1979년 6월 25일 개관하였다. 이 기념관은 다시 대구, 경북 시, 도민의 정성 어린 성금으로 세워진 것이다. 특히, 6.25를 겪어보지 못한 전후세대들에게는 전쟁의 참상을 올바로 일깨워 주고 호국안보의식을 고취시켜 줄 정신무장의 수련도장으로 활용되고 있다.' WHERE place_id=355 AND (description IS NULL OR description='');
-- [356] 다부동전적기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 356, 'https://tong.visitkorea.or.kr/cms/resource_photo/82/3494282_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=356 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/82/3494282_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 356, 'https://tong.visitkorea.or.kr/cms/resource_photo/44/3494244_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=356 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/44/3494244_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 356, 'https://tong.visitkorea.or.kr/cms/resource_photo/30/3494230_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=356 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/30/3494230_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 356, 'https://tong.visitkorea.or.kr/cms/resource_photo/45/3494345_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=356 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/45/3494345_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 356, 'https://tong.visitkorea.or.kr/cms/resource_photo/47/3494347_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=356 AND image_url='https://tong.visitkorea.or.kr/cms/resource_photo/47/3494347_image2_1.jpg');
UPDATE place SET description='다부동전적기념관은 6·25전쟁 때 다부동전투가 벌어졌던 곳에 세워진 기념관으로 구국의 정신과 평화의 소중함을 후대에 길이 전하기 위해 1981년 건립되었다. 다부동전투는 1950년 6·25전쟁 당시 가장 치열했던 전투 중 하나로 경상북도 칠곡군 가산면에서 북한군의 대공세를 저지시켜 대구로 진출을 막은 전투이다. 기념관의 주요 소장품으로는 T.T 권총, 45구경 권총, 98K 소총, RPG-2 대전차 로켓 유탄발사기, 60㎜ 박격포 등이 있으며, 배낭, 야전삽, 수통 또한 전시되고 있다. 이 밖에도 다부동전적기념관에는 구국용사충혼비, 구국경찰충혼비, 백선엽 장군 동상 등이 있다.' WHERE place_id=356 AND (description IS NULL OR description='');
-- [357] 주정공장수용소 4·3역사관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 357, 'https://tong.visitkorea.or.kr/cms/resource/06/3386706_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=357 AND image_url='https://tong.visitkorea.or.kr/cms/resource/06/3386706_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 357, 'https://tong.visitkorea.or.kr/cms/resource/07/3386707_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=357 AND image_url='https://tong.visitkorea.or.kr/cms/resource/07/3386707_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 357, 'https://tong.visitkorea.or.kr/cms/resource/08/3386708_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=357 AND image_url='https://tong.visitkorea.or.kr/cms/resource/08/3386708_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 357, 'https://tong.visitkorea.or.kr/cms/resource/09/3386709_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=357 AND image_url='https://tong.visitkorea.or.kr/cms/resource/09/3386709_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 357, 'https://tong.visitkorea.or.kr/cms/resource/10/3386710_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=357 AND image_url='https://tong.visitkorea.or.kr/cms/resource/10/3386710_image2_1.jpg');
UPDATE place SET description='주정공장수용소 4.3역사관은 일제강점기 시절 동양척식회사가 운영하던 큰 규모의 주정공장이 있었던 곳으로, 바로 앞에 항구가 있어 생산된 주정을 바로 내보낼 수 있었다. 태평양전쟁 때에는 제주도 전역에서 수확한 고구마를 이용해 주정을 생산하였다. 이 곳에서 생산한 알코올은 일본군의 연료로 사용되었고, 해방 이후에도 4.3 당시 주정공장이 보유한 창고들이 민간인 수용소로 이용되었다. 그 당시 행방불명된 희생자들을 기리기 위한 역사관이다.' WHERE place_id=357 AND (description IS NULL OR description='');
-- [358] 북촌마을 4·3길
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 358, 'https://tong.visitkorea.or.kr/cms/resource/80/3069280_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=358 AND image_url='https://tong.visitkorea.or.kr/cms/resource/80/3069280_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 358, 'https://tong.visitkorea.or.kr/cms/resource/20/2661520_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=358 AND image_url='https://tong.visitkorea.or.kr/cms/resource/20/2661520_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 358, 'https://tong.visitkorea.or.kr/cms/resource/21/2661521_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=358 AND image_url='https://tong.visitkorea.or.kr/cms/resource/21/2661521_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 358, 'https://tong.visitkorea.or.kr/cms/resource/22/2661522_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=358 AND image_url='https://tong.visitkorea.or.kr/cms/resource/22/2661522_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 358, 'https://tong.visitkorea.or.kr/cms/resource/81/3069281_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=358 AND image_url='https://tong.visitkorea.or.kr/cms/resource/81/3069281_image2_1.jpg');
UPDATE place SET description='북촌마을은 제주시 조천읍 동쪽 끝에 자리한 해변 마을이다. 일제강점기에는 치열한 항일운동이 펼쳐졌고, 해방 이후에는 주민 자치 활동이 매우 활발했던 곳이다. 그러나 1949년 1월 17일, 세계적으로도 유례를 찾기 힘든 비극적인 대규모 민간인 학살 사건이 이곳에서 자행되었다. 이로 인해 주민 300여 명이 희생당하는 등 북촌마을은 제주 4·3사건 최대의 피해 마을 중 하나로 남게 되었다. 마을에는 4·3사건 당시 도민들이 겪은 통한의 역사에 공감하고 인권과 평화의 소중함을 되새기기 위해 역사 교육의 장으로서 북촌마을 4·3길을 조성하였다. 4·3길의 출발이자 도착 지점인 너븐숭이 4·3기념관에서는 4·3길 무료 해설 서비스를 신청할 수 있다.' WHERE place_id=358 AND (description IS NULL OR description='');
-- [359] 신촌 4·3 성터
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 359, 'https://tong.visitkorea.or.kr/cms/resource/79/3389779_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=359 AND image_url='https://tong.visitkorea.or.kr/cms/resource/79/3389779_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 359, 'https://tong.visitkorea.or.kr/cms/resource/73/3389773_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=359 AND image_url='https://tong.visitkorea.or.kr/cms/resource/73/3389773_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 359, 'https://tong.visitkorea.or.kr/cms/resource/74/3389774_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=359 AND image_url='https://tong.visitkorea.or.kr/cms/resource/74/3389774_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 359, 'https://tong.visitkorea.or.kr/cms/resource/75/3389775_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=359 AND image_url='https://tong.visitkorea.or.kr/cms/resource/75/3389775_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 359, 'https://tong.visitkorea.or.kr/cms/resource/76/3389776_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=359 AND image_url='https://tong.visitkorea.or.kr/cms/resource/76/3389776_image2_1.jpg');
UPDATE place SET description='신촌리는 제주4·3 당시 신촌회담이 열렸던 곳으로, 조천 만세운동 등 항일운동의 역사를 간직한 조천읍의 작은 바닷가 마을이다. 신촌리 4·3 성터는 제주4·3 당시 돌을 쌓아 만든 성터로, 당시의 역사적 흔적을 살펴볼 수 있는 곳이다. 신촌리 4·3 성터는 닭머르해안 인근에 위치하며, 닭머르해안길은 해안누리길 50코스에 포함되어 있다. 해안길을 따라 주변 경관을 살펴보며 산책할 수 있으며, 역사적 흔적과 해안 풍경을 함께 둘러볼 수 있다.' WHERE place_id=359 AND (description IS NULL OR description='');
-- [360] 춘천지구 전적비(춘천지구 전적기념관)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 360, 'http://tong.visitkorea.or.kr/cms/resource/75/3466475_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=360 AND image_url='http://tong.visitkorea.or.kr/cms/resource/75/3466475_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 360, 'http://tong.visitkorea.or.kr/cms/resource/76/3466476_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=360 AND image_url='http://tong.visitkorea.or.kr/cms/resource/76/3466476_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 360, 'http://tong.visitkorea.or.kr/cms/resource/77/3466477_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=360 AND image_url='http://tong.visitkorea.or.kr/cms/resource/77/3466477_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 360, 'http://tong.visitkorea.or.kr/cms/resource/78/3466478_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=360 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3466478_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 360, 'http://tong.visitkorea.or.kr/cms/resource/79/3466479_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=360 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3466479_image2_1.JPG');
UPDATE place SET description='한국전쟁 때 국군 6사단이 치열한 방어전을 펼쳤던 춘천지구 전투를 기념하기 위해 만든 전적비이다. 춘천 지구에 배치된 6사단 7연대는 SU-76 자주포 부대를 앞세우고 침입한 적 2사단을 춘천의 북쪽 관문인 소양강교를 중심으로 소양강 방어선을 구축하고 굳세게 막았다. 대전차 포 중대의 소대장인 심일 소위가 대전차 포와 육탄 공격으로 적 자주포를 파괴한 것도 바로 이때였다. 홍천 지구에 배치된 6사단 2연대도 자주포 부대와 T-34 전차를 선두로 침입한 적 12사단을 막아 싸웠다. 홍천 북쪽 말고개를 중심으로 방어선을 구축하고, 특공대를 편성해 적 자주포와 전차를 파괴하며 효과적인 지연전을 수행한 것이다. 춘천·홍천 지구의 방어전은 서울에 북한군이 진입한 6월 28일까지도 성공적으로 지속됐다. 그러다가 육본의 지시로 부대 편제를 유지한 채 28일 오후부터 원주 방면으로 후퇴했다. 적의 파상적인 공격을 막아내며 치명적인 타격을 가한 춘천·홍천 지구 전투는 6·25전쟁 개전 직후 아군이 방어전에서 승리한 유일한 전투였다. 이로써 북한군의 기습 공격 전략에 일대 타격을 줬을 뿐만 아니라 아군의 주력이 한강 방어선을 형성하고, 유엔군이 참전할 수 있는 시간적 여유를 벌어준 매우 귀중한 승전이었다.' WHERE place_id=360 AND (description IS NULL OR description='');
-- [361] 독립기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 361, 'https://tong.visitkorea.or.kr/cms/resource/21/3450021_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=361 AND image_url='https://tong.visitkorea.or.kr/cms/resource/21/3450021_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 361, 'https://tong.visitkorea.or.kr/cms/resource/18/3450018_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=361 AND image_url='https://tong.visitkorea.or.kr/cms/resource/18/3450018_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 361, 'https://tong.visitkorea.or.kr/cms/resource/19/3450019_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=361 AND image_url='https://tong.visitkorea.or.kr/cms/resource/19/3450019_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 361, 'https://tong.visitkorea.or.kr/cms/resource/25/3450025_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=361 AND image_url='https://tong.visitkorea.or.kr/cms/resource/25/3450025_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 361, 'https://tong.visitkorea.or.kr/cms/resource/26/3450026_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=361 AND image_url='https://tong.visitkorea.or.kr/cms/resource/26/3450026_image2_1.jpg');
UPDATE place SET description='독립기념관은 항일투쟁의 역사를 제대로 정립하고, 이를 널리 알리려는 목적으로 건립되었다. 상설전시관은 겨레의 뿌리, 겨레의 시련, 겨레의 함성, 평화누리, 나라 되찾기, 새로운 나라 총 6개로 구성되어 있다. 각 전시관을 차례로 둘러보며 우리 민족의 찬란한 역사와 자주독립을 향한 선열들의 고귀한 희생정신을 되새길 수 있다. 함께하는 독립운동 체험관은 우리나라를 되찾기 위한 독립운동의 역사를 쉽고 재미있게 배우면서 나라 사랑의 마음을 느껴보는 체험관으로, 영아부터 성인까지 모든 세대가 함께할 수 있다. 무궁화, 태극기 등 나라 상징물로 꾸며진 공간에서 오감을 자극하는 놀이를 즐길 수 있다. ◎ 한류의 매력을 만나는 여행 정보 - 예능 K 컬처를 만든 한국인의 뜨거운 역사를 만날 수 있는 독립기념관은 멀리서도 장엄함을 드러내는 겨레의 탑과 동양 최대 기와 건물인 겨레의 집이다. 에이티즈가 골든벨을 했던 통일 염원의 동산과 통일의 종까지 둘러보며 K-spirit을 느껴볼 것을 추천한다.' WHERE place_id=361 AND (description IS NULL OR description='');
-- [362] 서대문형무소역사관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 362, 'https://tong.visitkorea.or.kr/cms/resource/29/3520329_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=362 AND image_url='https://tong.visitkorea.or.kr/cms/resource/29/3520329_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 362, 'https://tong.visitkorea.or.kr/cms/resource/56/3509056_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=362 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/3509056_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 362, 'https://tong.visitkorea.or.kr/cms/resource/57/3509057_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=362 AND image_url='https://tong.visitkorea.or.kr/cms/resource/57/3509057_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 362, 'https://tong.visitkorea.or.kr/cms/resource/62/3509062_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=362 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/3509062_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 362, 'https://tong.visitkorea.or.kr/cms/resource/63/3509063_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=362 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/3509063_image2_1.jpg');
UPDATE place SET description='서대문형무소는 대한제국 말 일제의 강압으로 세워진 이래, 80여 년간 격동의 근현대 수난사를 간직한 현장이자 대표적인 일제 탄압기관이었다. 1908년 10월 21일 경성감옥이라는 이름으로 문을 연 뒤 일제에 국권을 빼앗기자, 이에 항거하는 민족독립운동이 전국에서 거세게 일어났고 일제는 수많은 애국지사를 체포·투옥했다. 수용 인원이 늘어나자 마포 공덕동에 새 감옥을 지으면서, 기존 감옥은 1912년 9월 3일 서대문감옥으로 명칭이 바뀌었다. 이후 수많은 민족 수난의 역사를 거쳐 1992년 8월 15일 현재의 서대문독립공원으로 개원하였다. 서대문구에서는 1995년부터 서대문독립공원 사적지에 대한 성역화 사업을 추진하였다. 조국 독립을 위해 일제에 맞서다 투옥되어 모진 고문과 탄압 끝에 순국하신 애국선열들의 넋을 기리고, 후손들에게 자주독립 정신을 일깨워 주는 역사의 산 교육장으로 삼고자 이곳을 새롭게 단장하여 서대문형무소역사관으로 개관하였다.' WHERE place_id=362 AND (description IS NULL OR description='');
UPDATE place SET description='해남 우수영관광지에 위치한 명량대첩해전사 기념전시관은 명량대첩에 대해 알리고 승리를 기념하기 위해 건립한 곳이다. 명량대첩 현장인 울돌목을, 역사의 산 교육장이자 호국을 주제로 한 관광지로 운영하기 위하여 우수영관광지 내에 지하 1층 지상 3층 규모로 조성하였다. 명량대첩해전 전시관의 건물 외형은 판옥선을 본뜬 건물이다. 전시 공간은 크게 진입부인 웰컴존과 상설전시실, 기획전시실, 영상실, 옥외공간으로 나누어지며, 1층, 3층, 2층, 1층 순서로 관람한다. 1층에는 난중일기의 명량해전 역사 속으로 들어가는 웰컴존과 4D 입체영상관, 울둘목 영상실과 쉼터 등이 있으며 3층에는 세계의 해전사와 역사에 기록된 명량대첩, 시대별 배 등이 전시되어 있고 2층에는 노 젓기 체험과 그 당시 사용했던 무기류 등을 볼 수 있다. 건물 전시 공간 내부의 콘텐츠는 옛 역사를 구현하는 방식으로 설정했으며 건물 형태의 가장 중요한 것은 현장성을 느껴 볼 수 있도록 연출 구성했다. 특히 외부공간에서도 현장을 볼 수 있도록 3층 전망시설을 갖추어 울돌목의 지리 지형을 관람할 수 있도록 설계되어 있다. 아이들에게도 흥미로운 우리의 역사 이야기를 잘 살펴볼 수 있는 유익한 공간이다.' WHERE place_id=363 AND (description IS NULL OR description='');
-- [364] 명량해협 울돌목
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 364, 'https://tong.visitkorea.or.kr/cms/resource/08/3382008_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=364 AND image_url='https://tong.visitkorea.or.kr/cms/resource/08/3382008_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 364, 'https://tong.visitkorea.or.kr/cms/resource/09/3382009_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=364 AND image_url='https://tong.visitkorea.or.kr/cms/resource/09/3382009_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 364, 'https://tong.visitkorea.or.kr/cms/resource/10/3382010_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=364 AND image_url='https://tong.visitkorea.or.kr/cms/resource/10/3382010_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 364, 'https://tong.visitkorea.or.kr/cms/resource/11/3382011_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=364 AND image_url='https://tong.visitkorea.or.kr/cms/resource/11/3382011_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 364, 'https://tong.visitkorea.or.kr/cms/resource/12/3382012_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=364 AND image_url='https://tong.visitkorea.or.kr/cms/resource/12/3382012_image2_1.JPG');
UPDATE place SET description='울돌목은 이충무공의 3대 해전 중의 하나인 명량대첩지로 잘 알려진 서해의 길목으로, 해남과 진도 간의 좁은 해협을 이루며 바다의 폭은 한강 너비 정도의 294m 내외이다. 물길이 암초에 부딪혀 튕겨 나오는 소리가 매우 커 바다가 우는 것 같다고 하여 울돌목이라고도 불린다. 물살이 빠르고 거품물이 용솟음쳐 배가 운항하기 힘든 곳이다. 바다라기보다는 홍수진 강물로 보이며, 물길이 소용돌이쳤다가 솟아오르면서 세차게 흘러내려 그 소리가 해협을 뒤흔든다. 이는 해협의 폭이 좁은 데다가 해구가 깊은 절벽을 이루고 있어 흐르는 물살이 이에 부딪쳤다가 솟아오르기 때문이다. 이런 지형적 환경을 이용하여 정유재란 당시 명량해전에서 이순신장군은 13척의 배를 가지고 130여 척으로 공격해 오는 왜군을 상대로 대승을 거두었다. 울돌목 해변가에는 데크길과 수변공원이 조성되어 있고 울돌목 스카이워크를 설치하여 바다 위를 걸으며 울돌목의 바닷물살과 소용돌이를 발밑으로 실감할 수 있다.' WHERE place_id=364 AND (description IS NULL OR description='');
-- [365] 해남 명량대첩비
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 365, 'http://tong.visitkorea.or.kr/cms/resource/82/3391982_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=365 AND image_url='http://tong.visitkorea.or.kr/cms/resource/82/3391982_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 365, 'http://tong.visitkorea.or.kr/cms/resource/78/3391978_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=365 AND image_url='http://tong.visitkorea.or.kr/cms/resource/78/3391978_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 365, 'http://tong.visitkorea.or.kr/cms/resource/79/3391979_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=365 AND image_url='http://tong.visitkorea.or.kr/cms/resource/79/3391979_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 365, 'http://tong.visitkorea.or.kr/cms/resource/80/3391980_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=365 AND image_url='http://tong.visitkorea.or.kr/cms/resource/80/3391980_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 365, 'http://tong.visitkorea.or.kr/cms/resource/81/3391981_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=365 AND image_url='http://tong.visitkorea.or.kr/cms/resource/81/3391981_image2_1.JPG');
UPDATE place SET description='임진왜란(1592∼1598) 당시 명량대첩(1597)을 승리로 이끈 이순신의 공을 기념하기 위하여 세운 비이다. 일제강점기 때 경복궁에 옮겨졌던 것을 1947년에 해남의 해안지역(문내면 학동리 1186-7번지)으로 옮겨 세웠다가 2011년 3월 원 설립지인 현재의 위치로 이전하게 되었다. 이 석비는 직사각형의 비 받침 위에 비 몸돌을 꽂고, 그 위로 구름과 용을 장식한 머릿돌을 얹은 형태이다. 비문에는 선조 30년(1597) 이순신장군이 진도 벽파정에 진을 설치하고 우수영과 진도 사이 좁은 바다의 빠른 물살을 이용하여 왜적의 대규모 함대를 무찌른 상황을 자세히 기록하였는데, 철천량해전 이후 수습한 10여 척의 배로 왜적함대 500척을 격파하였다고 기록되어 있으며, 아울러 명량대첩이 갖는 의미 및 장군의 충의에 대해서도 기록되어 있다. 비문은 1686년에 쓰였으나 비가 세워진 것은 2년 뒤인 1688년으로, 전라우도수군절도사 박신주가 건립하였다. (출처 : 국가유산청)' WHERE place_id=365 AND (description IS NULL OR description='');
-- [366] 연평도
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 366, 'https://tong.visitkorea.or.kr/cms/resource/62/4056062_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=366 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/4056062_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 366, 'https://tong.visitkorea.or.kr/cms/resource/59/4056059_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=366 AND image_url='https://tong.visitkorea.or.kr/cms/resource/59/4056059_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 366, 'https://tong.visitkorea.or.kr/cms/resource/60/4056060_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=366 AND image_url='https://tong.visitkorea.or.kr/cms/resource/60/4056060_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 366, 'https://tong.visitkorea.or.kr/cms/resource/61/4056061_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=366 AND image_url='https://tong.visitkorea.or.kr/cms/resource/61/4056061_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 366, 'https://tong.visitkorea.or.kr/cms/resource/63/4056063_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=366 AND image_url='https://tong.visitkorea.or.kr/cms/resource/63/4056063_image2_1.jpg');
UPDATE place SET description='연평도는 인천광역시 옹진군 연평면에 위치한 섬으로, 대연평도와 소연평도로 이루어져 있다. 대연평도는 연평도의 주된 섬으로, 면적이 넓고 주요 주민들이 거주하는 곳이다. 이곳은 자연경관이 뛰어나며, 특히 해안선이 아름답고, 다양한 해양 생물들을 관찰할 수 있는 곳이다. 대연평도는 바다와 산이 어우러져 있어 하이킹과 산책을 즐기기에도 적합하다. 소연평도는 대연평도에서 가까운 작은 섬으로, 조용하고 한적한 분위기를 자랑한다. 소연평도는 자연 그대로의 풍경을 유지하고 있어, 편안한 휴식과 자연 탐방을 원하는 이들에게 이상적인 장소이다. 두 섬 모두 해양 스포츠와 갯벌 체험 등 다양한 액티비티를 즐길 수 있으며, 일출과 일몰이 아름다워 많은 관광객들이 방문한다. 연평도는 자연과 평화로운 분위기를 만끽할 수 있는 최적의 여행지이다.' WHERE place_id=366 AND (description IS NULL OR description='');
-- [367] 서해수호관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 367, 'http://tong.visitkorea.or.kr/cms/resource/19/3543919_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=367 AND image_url='http://tong.visitkorea.or.kr/cms/resource/19/3543919_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 367, 'http://tong.visitkorea.or.kr/cms/resource/20/3543920_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=367 AND image_url='http://tong.visitkorea.or.kr/cms/resource/20/3543920_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 367, 'http://tong.visitkorea.or.kr/cms/resource/21/3543921_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=367 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3543921_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 367, 'http://tong.visitkorea.or.kr/cms/resource/23/3543923_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=367 AND image_url='http://tong.visitkorea.or.kr/cms/resource/23/3543923_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 367, 'http://tong.visitkorea.or.kr/cms/resource/24/3543924_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=367 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3543924_image2_1.JPG');
UPDATE place SET description='서해수호관은 2011년 해군 2함대사령부 내에 개관하여 서해 북방한계선(NLL)을 지키다 전사한 55명의 희생정신을 기리고 국민에게 안보의 중요성을 알리고 있다. 관람객은 참-357호정, 천안함 등 실물 군함과 유품, 당시 사용된 군 장비를 통해 생생한 현장을 체험할 수 있으며 해군 장병의 해설로 이해를 돕는다. 1층 NLL과 해전실에서는 NLL의 역사와 북한과의 주요 해전, 전사자들의 희생을 다양한 자료를 통해 전시하고 있다. 2층 천안함실은 피격 당시 상황과 구조·수색·인양 작전 과정을 영상과 디오라마로 재현하였고, 북한 어뢰 추진장치와 시뮬레이션 자료를 통해 도발 사실을 확인할 수 있다. 천안함 기념관은 군함 내부를 재현한 전시 공간과 전사자들의 유품을 통해 당시 승조원들의 생활과 희생을 추모할 수 있도록 구성되어 있다.' WHERE place_id=367 AND (description IS NULL OR description='');
UPDATE place SET description='도라산전망대는 송악산 OP 폐쇄에 따라 대체 신설되었으며, 북한의 생활을 바라볼 수 있는 남측의 최북단 전망대이다. 수십대의 망원경이 설치되어 있어 개성의 송악산, 김일성 동상, 기정동, 개성시 변두리, 금암골(협동농장)등을 망원경을 통해 바라볼 수 있으며, 날씨가 맑은 날에는 개성공단까지 볼 수 있다. 도라산전망대는 민간인 통제구역 안에 위치하고 있기 때문에 일반 승용차의 출입이 제한된다. DMZ관광을 하기 위해서는 비무장지대 연계관광을 이용하는 것이 편리하다. 2018년 10월 신축이전된 도라산전망대는 실향민과 남북분단 현장을 보러 오는 외국인 관광객 등 연간 80여만 명 이상의 방문객이 찾는 명소이다. 또한 민간인 통제구역을 방문하기 위해서는 신분증을 필참 해야 하므로 잊지 말고 준비해야 한다. (출처 : 파주 DMZ 평화관광)' WHERE place_id=368 AND (description IS NULL OR description='');
-- [369] 파주 임진각(평화누리공원)
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 369, 'https://tong.visitkorea.or.kr/cms/resource/31/3497231_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=369 AND image_url='https://tong.visitkorea.or.kr/cms/resource/31/3497231_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 369, 'https://tong.visitkorea.or.kr/cms/resource/32/3497232_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=369 AND image_url='https://tong.visitkorea.or.kr/cms/resource/32/3497232_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 369, 'https://tong.visitkorea.or.kr/cms/resource/33/3497233_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=369 AND image_url='https://tong.visitkorea.or.kr/cms/resource/33/3497233_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 369, 'https://tong.visitkorea.or.kr/cms/resource/34/3497234_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=369 AND image_url='https://tong.visitkorea.or.kr/cms/resource/34/3497234_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 369, 'https://tong.visitkorea.or.kr/cms/resource/35/3497235_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=369 AND image_url='https://tong.visitkorea.or.kr/cms/resource/35/3497235_image2_1.jpg');
UPDATE place SET description='임진각은 한국전쟁과 민족 분단의 아픔을 간직한 대한민국의 대표적인 평화관광지이다. 이곳에는 임진강지구 전적비, 미국군 참전비 등 다양한 전적비가 설치되어 있다. 한국전쟁 전 북쪽 신의주까지 운행하던 기차가 전시되어 있다. 임진철교는 두 개의 철교 중 하나가 전쟁 중 파괴되어 교각만 남아 있다. 망배단은 실향민들이 북녘 가족을 향해 절을 올리는 장소이다. 임진각은 이산가족의 아픔이 서린 장소로 알려져 있다. 매년 수백만 명의 내·외국인이 임진각을 방문한다. 이곳은 통일을 염원하는 통일안보관광지로 기능하고 있다. 전시관, 평화누리, 평화 곤돌라, 6·25 전쟁 납북자 기념관 등의 시설이 갖추어져 있다. 임진각 일대는 총 14만 평 규모의 대규모 관광지로 조성되어 있다.' WHERE place_id=369 AND (description IS NULL OR description='');
-- [370] 일본군 위안부 역사관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 370, 'https://tong.visitkorea.or.kr/cms/resource/01/4097101_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=370 AND image_url='https://tong.visitkorea.or.kr/cms/resource/01/4097101_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 370, 'https://tong.visitkorea.or.kr/cms/resource/23/3527323_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=370 AND image_url='https://tong.visitkorea.or.kr/cms/resource/23/3527323_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 370, 'https://tong.visitkorea.or.kr/cms/resource/26/3527326_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=370 AND image_url='https://tong.visitkorea.or.kr/cms/resource/26/3527326_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 370, 'https://tong.visitkorea.or.kr/cms/resource/99/4097099_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=370 AND image_url='https://tong.visitkorea.or.kr/cms/resource/99/4097099_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 370, 'https://tong.visitkorea.or.kr/cms/resource/00/4097100_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=370 AND image_url='https://tong.visitkorea.or.kr/cms/resource/00/4097100_image2_1.jpg');
UPDATE place SET description='일본군 위안부 역사관은 1998년 8월 14일 광주시 나눔의 집에 개관한 세계 최초의 전쟁 성노예 주제의 인권 박물관이다. 일본군의 전쟁 범죄 행위를 고발하고, 피해자 할머니들의 명예 회복을 위하여, 후손들에게 역사 교육의 장으로 활용하기 위하여 세워졌다. 제1역사관은 지상 2층~지하 1층, 제2역사관은 지상 2층의 규모로 일반 시민과 뜻있는 일본인들의 성금을 모아 건립되었다. 제1역사관은 총 5개의 전시공간으로 이루어져 있다. 각각 역사의 장, 증언의 장, 체험의 장, 기록의 장, 고발의 장으로 구성되어 있다. 제3전시공간인 체험의 장에서는 당시 피해자의 방이 재현되어 있고 그곳에서 쓰인 물건들도 함께 전시되어 있다. 화면으로 만나는 피해자 할머니의 증언은 당시 아픔을 전해 준다. 제4전시공간인 기록의 장에서는 김학순 할머니의 1991년 육성 증언을 들을 수 있다. 이 증언을 시작으로 일본군 성 노예제를 세상에 알리기 위해 많은 피해자들이 세상에 외치기 시작했다. 제2역사관은 일본군 ''위안부'' 피해자 할머니들의 유품 전시관과 그림 전시관으로 구분되어 있다. 피해 할머니들을 기억하기 위한 유품 전시장과 나눔의 집에서 보유하고 있는 할머니들의 그림 300여 점 중 20여 점을 선정하여 전시한 그림 전시장을 둘러볼 수 있다. 또한 피해 할머니들을 기억하고 추모할 수 있는 추모관과 피해 할머니들의 넋을 기리는 추모 공원이 조성되어 있다. 추모 공원에는 살아생전 고향 땅을 밟아 보지 못하고 돌아가신 할머니들의 넋이 노란 나비가 되어 귀향한 듯, 노란 포스트잇을 채운 추모의 글들이 벽면을 가득 채우고 있다. 야외 전시공간에는 일본군 성노예 피해자를 상징하는 최초의 소녀상 ‘못다 핀 꽃’과 돌아가신 피해자 할머니들의 흉상이 전시되어 있다. 한편, 나눔의 집은 태평양 전쟁 말기, 일제에 의해 성적인 희생을 강요당했던 일본군 ‘위안부’ 피해자들이 모여 살고 있는 곳이다. 1992년 10월 서울특별시 마포구 서교동에 처음으로 개소하였으며, 1995년 12월 현재의 위치로 이전하여 생활관과 역사관, 법당, 수련관 등의 노인 주거 복지 시설을 신축하였다.' WHERE place_id=370 AND (description IS NULL OR description='');
-- [371] 남한산성행궁
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 371, 'http://tong.visitkorea.or.kr/cms/resource/56/3350256_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=371 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3350256_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 371, 'http://tong.visitkorea.or.kr/cms/resource/57/3350257_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=371 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3350257_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 371, 'http://tong.visitkorea.or.kr/cms/resource/58/3350258_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=371 AND image_url='http://tong.visitkorea.or.kr/cms/resource/58/3350258_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 371, 'http://tong.visitkorea.or.kr/cms/resource/59/3350259_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=371 AND image_url='http://tong.visitkorea.or.kr/cms/resource/59/3350259_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 371, 'http://tong.visitkorea.or.kr/cms/resource/60/3350260_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=371 AND image_url='http://tong.visitkorea.or.kr/cms/resource/60/3350260_image2_1.jpg');
UPDATE place SET description='임금이 서울의 궁궐을 떠나 도성 밖으로 행차하는 경우 임시로 거처하는 곳을 행궁이라 한다. 남한산성 행궁은 전쟁이나 내란 등 유사시 후방의 지원군이 도착할 때까지 한양 도성의 궁궐을 대신할 피난처로 사용하기 위하여 조선 인조 4년(1626)에 건립되었다. 실제로 인조 14년(1636) 병자호란이 발생하자 인조는 남한산성으로 피난하여 47일간 항전하였다. 이후에도 숙종, 영조, 정조, 철종, 고종 등이 여주, 이천 등의 능행길에 머물러 이용하였다. 남한산성 행궁은 1909년까지 잘 남아 있었으나 일제강점기에 일본에 의해 훼손되었다. 1999년부터 발굴 조사를 시작하여 2002년에 상궐에 해당하는 내행전을 준공하고 2004년 행궁 좌전을 준공하였다. (출처 : 경기도남한산성세계유산센터 홈페이지)' WHERE place_id=371 AND (description IS NULL OR description='');
-- [372] 서울 삼전도비
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 372, 'http://tong.visitkorea.or.kr/cms/resource/47/3401547_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=372 AND image_url='http://tong.visitkorea.or.kr/cms/resource/47/3401547_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 372, 'http://tong.visitkorea.or.kr/cms/resource/44/3401544_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=372 AND image_url='http://tong.visitkorea.or.kr/cms/resource/44/3401544_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 372, 'http://tong.visitkorea.or.kr/cms/resource/45/3401545_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=372 AND image_url='http://tong.visitkorea.or.kr/cms/resource/45/3401545_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 372, 'http://tong.visitkorea.or.kr/cms/resource/46/3401546_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=372 AND image_url='http://tong.visitkorea.or.kr/cms/resource/46/3401546_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 372, 'http://tong.visitkorea.or.kr/cms/resource/51/3401551_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=372 AND image_url='http://tong.visitkorea.or.kr/cms/resource/51/3401551_image2_1.JPG');
UPDATE place SET description='병자호란 때 청에 패배해 굴욕적인 강화협정을 맺고, 청태종의 요구에 따라 그의 공덕을 적은 비석이다. 조선 인조 17년(1639)에 세워진 비석으로 높이 3.95m, 폭 1.4m이고, 제목은 ‘대청황제공덕비［大淸皇帝功德碑］’로 되어있다. 조선 전기까지 조선에 조공을 바쳐오던 여진족은 명나라가 어지러운 틈을 타 급속히 성장하여 후금을 건국하고, 더욱더 세력을 확장하여 조선을 침략하는 등 압력을 행사하면서 조선과의 관계가 원만하지 못하였다. 나라의 이름을 청으로 바꾼 여진족이 조선에게 신하로서의 예를 갖출 것을 요구하자 두 나라의 관계가 단절되었다. 결국 인조 14년(1636) 청나라 태종은 10만의 군사를 이끌고 직접 조선에 쳐들어와 병자호란을 일으켰다. 남한산성에 머물며 항전하던 인조가 결국 청나라의 군대가 머물고 있는 한강가의 삼전도 나루터에서 항복을 하면서 부끄러운 강화협정을 맺게 되었다. 병자호란이 끝난 뒤 청태종은 자신의 공덕을 새긴 기념비를 세우도록 조선에 강요했고 그 결과 삼전도비가 세워졌다. 비문은 이경석이 짓고 글씨는 오준이 썼으며, ‘대청황제공덕비’라는 제목은 여이징이 썼다. 비석 앞면의 왼쪽에는 몽골글자, 오른쪽에는 만주글자, 뒷면에는 한자로 쓰여져 있어 만주어 및 몽골어를 연구하는데도 중요한 자료이다. (출처 : 송파구청 홈페이지)' WHERE place_id=372 AND (description IS NULL OR description='');
-- [373] 아차산성
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 373, 'https://tong.visitkorea.or.kr/cms/resource/57/3514057_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=373 AND image_url='https://tong.visitkorea.or.kr/cms/resource/57/3514057_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 373, 'https://tong.visitkorea.or.kr/cms/resource/54/3514054_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=373 AND image_url='https://tong.visitkorea.or.kr/cms/resource/54/3514054_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 373, 'https://tong.visitkorea.or.kr/cms/resource/55/3514055_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=373 AND image_url='https://tong.visitkorea.or.kr/cms/resource/55/3514055_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 373, 'https://tong.visitkorea.or.kr/cms/resource/56/3514056_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=373 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/3514056_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 373, 'https://tong.visitkorea.or.kr/cms/resource/58/3514058_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=373 AND image_url='https://tong.visitkorea.or.kr/cms/resource/58/3514058_image2_1.jpg');
UPDATE place SET description='광진구 광장동 한강 북쪽에 있는 해발 285m의 아차산에 있는 아차산성은 백제가 하남위례성에 도읍하였을 때 고구려의 침입을 물리치기 위해 쌓은 큰 성이다. 또한 고구려 장수왕이 한강 하류의 남쪽에 있었던 백제의 왕성을 빼앗고 개로왕을 사로잡아 아차산성으로 끌고 와서 죽인 곳이기도 하다. 한강 하류를 장악한 신라에 있어서도 고구려를 공격하는 전진기지가 되었다. 이러한 신라의 야망을 미리 막기 위하여 싸움터로 나왔던 고구려의 온달 장군이 신라군의 화살에 맞아 전사한 곳이라고도 한다. 이때 사랑하는 남편 온달장군의 주검을 거두기 위하여 천 리 길도 마다치 않고 달려온 고구려 평강공주의 슬픈 사랑 노래가 한강을 따라 천여 년을 흐른 곳이 아차산성이다. 아차산성의 1차, 2차 발굴에서 고구려 성벽과 건물터, 연못터가 확인되었고, 2015년부터 이뤄진 발굴을 통해 신라의 배수구 시설과 고구려 토기 등 삼국시대 유물이 다량 발견됐다. 아차산성 남벽 90m 외벽에선 신라 건축의 특징인 외벽 보축 시설과 출수구(3곳), 내벽에선 입수구(2곳)가 발견됐으며, 감시 초소인 망대지에선 내외성벽을 비롯한 치성과 방대형 시설 등이 확인됐다.' WHERE place_id=373 AND (description IS NULL OR description='');
-- [374] 고구려대장간마을
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 374, 'https://tong.visitkorea.or.kr/cms/resource/35/4075435_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=374 AND image_url='https://tong.visitkorea.or.kr/cms/resource/35/4075435_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 374, 'https://tong.visitkorea.or.kr/cms/resource/31/4075431_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=374 AND image_url='https://tong.visitkorea.or.kr/cms/resource/31/4075431_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 374, 'https://tong.visitkorea.or.kr/cms/resource/32/4075432_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=374 AND image_url='https://tong.visitkorea.or.kr/cms/resource/32/4075432_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 374, 'https://tong.visitkorea.or.kr/cms/resource/33/4075433_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=374 AND image_url='https://tong.visitkorea.or.kr/cms/resource/33/4075433_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 374, 'https://tong.visitkorea.or.kr/cms/resource/34/4075434_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=374 AND image_url='https://tong.visitkorea.or.kr/cms/resource/34/4075434_image2_1.jpg');
UPDATE place SET description='아차산 자락에 위치한 고구려대장간마을은 지난 2008년 개장한 이래, 2009년 4월 공립박물관으로써 등록을 마쳤으며, 지금까지 아차산 출토 고구려 유물을 상설전시하고, 정기적으로 교체전시를 마련하는 등 명실공히 고구려 전문박물관으로 자리 잡고 있다. 또한, 어린이들뿐 아니라 청소년, 성인, 다문화, 문화소외계층에게 우리 역사를 재미있고 신나게 학습할 수 있도록 고구려 역사와 관련된 체험 학습프로그램을 운영하여 큰 호응을 받고 있다. (출처 : 고구려대장간마을 홈페이지)' WHERE place_id=374 AND (description IS NULL OR description='');
-- [375] 충주고구려비전시관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 375, 'http://tong.visitkorea.or.kr/cms/resource/07/3535007_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=375 AND image_url='http://tong.visitkorea.or.kr/cms/resource/07/3535007_image2_1.jpg');
UPDATE place SET description='충주고구려비전시관은 2012년에 개관한 곳으로 국내 유일의 고구려 석비인 충주 고구려비를 볼 수 있는 곳이다. 충주는 계립령과 죽령, 남한강 등이 있어 전략적 요충지였는데 고구려가 충주를 70여 년의 오랜 시간 동안 점령했던 흔적을 보여주는 것이 바로 충주 고구려비이다. 오래전부터 가금면 용전리 입석마을 앞에 서 있었던 충주 고구려비는 1979년에 이르러서야 그 가치가 세상에 알려졌고 그 후 장수왕이 남한강 유역을 공략한 후 세운 기념비로 추정되어 고구려비로 명명됐다. 이 밖에도 충주고구려비전시관은 충주 고구려비뿐만 아니라 고구려의 유산, 설화, 생활상 그리고 안악 3호 분, 광개토대왕 비, 충주 고구려비의 발견 과정 등도 소개하고 있다.' WHERE place_id=375 AND (description IS NULL OR description='');
-- [376] 방동리 고구려고분
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 376, 'https://tong.visitkorea.or.kr/cms/resource/60/182160_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=376 AND image_url='https://tong.visitkorea.or.kr/cms/resource/60/182160_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 376, 'https://tong.visitkorea.or.kr/cms/resource/61/182161_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=376 AND image_url='https://tong.visitkorea.or.kr/cms/resource/61/182161_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 376, 'https://tong.visitkorea.or.kr/cms/resource/62/182162_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=376 AND image_url='https://tong.visitkorea.or.kr/cms/resource/62/182162_image2_1.jpg');
UPDATE place SET description='춘천 방동리 고구려 고분은 강원특별자치도 춘천시 서면 방동리에 위치한 고분으로 일명 ‘춘성 방돌리고분’이라고도 한다. 1985년 9월 13일 강원특별자치도의 문화재자료로 지정되었다. 고려의 개국공신인 신숭겸 장군 무덤 남쪽에 있는 2기의 무덤이다. 이 2기의 고분은 1981년에 김원룡 교수가 발견했고 1993년에 한림대학교 박물관이 발굴하였다. 발견 당시 이미 2기 모두 돌방의 바닥까지 도굴당해 천장부가 완전히 파괴되었고 고분의 앞에는 천장을 덮었던 대형의 괴석들이 흩어져 있었다. 방동리 무덤은 고구려 무덤의 후기 양식이 지방화된 형태로 춘천 지방이 신라가 북상하기 이전인 6세기 중엽에는 고구려 영역이었음을 보여주는 중요한 유적이다. 방동리 고구려고분의 돌방무덤은 남한지역에서는 흔히 볼 수 없는 양식으로 통구나 평양 지역의 고구려 돌방무덤에서 흔히 보이는 형식이다. 방동리 고분의 형태를 통해본 연대는 대체로 6세기 중엽을 상한으로 하고 있으며 모줄임의 고구려식 돌방무덤의 남한지역 분포가 확인된 최초의 예이다.' WHERE place_id=376 AND (description IS NULL OR description='');
-- [377] 장사상륙작전 전승기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 377, 'http://tong.visitkorea.or.kr/cms/resource/17/3572817_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=377 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3572817_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 377, 'http://tong.visitkorea.or.kr/cms/resource/12/3572812_image2_1.jpeg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=377 AND image_url='http://tong.visitkorea.or.kr/cms/resource/12/3572812_image2_1.jpeg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 377, 'http://tong.visitkorea.or.kr/cms/resource/13/3572813_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=377 AND image_url='http://tong.visitkorea.or.kr/cms/resource/13/3572813_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 377, 'http://tong.visitkorea.or.kr/cms/resource/14/3572814_image2_1.png', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=377 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3572814_image2_1.png');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 377, 'http://tong.visitkorea.or.kr/cms/resource/15/3572815_image2_1.png', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=377 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3572815_image2_1.png');
UPDATE place SET description='장사상륙작전 전승기념관은 국내 최초이자 유일하게 바다 위에 건립한 호국전시관으로, 인천상륙작전의 성공에 토대를 마련한 장사상륙작전을 기억하고 호국영웅들의 희생과 숭고한 뜻을 기리고자 건립되었다. 2025년 콘텐츠 리뉴얼로 기존의 정적인 전시에서 미디어아트 등을 활용한 참여형 전시를 통해 1950년 9월 13일부터 19일까지, 6일간의 치열했던 전쟁 당시의 상황을 생생히 전달하고 있다. 전시해설은 오전 10시부터 오후 4시까지 30분 간격으로 진행된다. 단, 10명 이상인 단체의 전시해설이 예약될 경우, 단체 전시해설이 우선된다. 아울러 관람객이 많은 날에는 전승기념관 사정에 따라 변동 또는 취소될 수 있다.' WHERE place_id=377 AND (description IS NULL OR description='');
-- [378] 한산도
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 378, 'https://tong.visitkorea.or.kr/cms/resource/51/3513751_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=378 AND image_url='https://tong.visitkorea.or.kr/cms/resource/51/3513751_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 378, 'https://tong.visitkorea.or.kr/cms/resource/52/3513752_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=378 AND image_url='https://tong.visitkorea.or.kr/cms/resource/52/3513752_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 378, 'https://tong.visitkorea.or.kr/cms/resource/53/3513753_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=378 AND image_url='https://tong.visitkorea.or.kr/cms/resource/53/3513753_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 378, 'https://tong.visitkorea.or.kr/cms/resource/54/3513754_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=378 AND image_url='https://tong.visitkorea.or.kr/cms/resource/54/3513754_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 378, 'https://tong.visitkorea.or.kr/cms/resource/56/3513756_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=378 AND image_url='https://tong.visitkorea.or.kr/cms/resource/56/3513756_image2_1.jpg');
UPDATE place SET description='통영시 남동쪽에 있는 섬으로 통영항에서 뱃길로 2㎞ 정도 떨어져 있는 한산도는 동쪽에는 비산도, 송도, 좌도가 있고, 남쪽에는 추봉도가 있는 한산면의 본섬으로, 면을 이루는 29개 유무인도 가운데 가장 큰 섬이다. 임진왜란 때 이 충무공께서 한산에서 왜적을 일거에 괴멸시킨 청사에 빛나는 한산대첩을 이루었음은 물론, 최초의 조선삼도 수군 통제영인 한산진이 설치됐던 섬이다. 섬 자체가 민족 자긍의 역사와 구국의 혼이 서린 성역으로 많은 관광객이 제승당을 참배한다. 한산도에는 남쪽 최고봉인 망산(293.1m)이 있다. 해안지역은 대부분 암석으로 이루어져 있어 드나듦이 심하고, 전체적으로 절벽과 기암괴석이 많이 있다. 서북쪽 어귀에는 한산만이 있고, 섬과 미륵도 사이에는 한산 해협이 펼쳐져 있다. 한려해상국립공원의 시발점이자 세계적인 해전 가운데 하나로 꼽히는 한산대첩이 있었던 곳으로, 각종 문화재로는 충무공이 삼도수군통제영을 한산도로 옮기면서 지은 제승당과 이충무공유적지 등의 사적이 있다.' WHERE place_id=378 AND (description IS NULL OR description='');
-- [379] 제승당
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 379, 'http://tong.visitkorea.or.kr/cms/resource/14/3558314_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=379 AND image_url='http://tong.visitkorea.or.kr/cms/resource/14/3558314_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 379, 'http://tong.visitkorea.or.kr/cms/resource/15/3558315_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=379 AND image_url='http://tong.visitkorea.or.kr/cms/resource/15/3558315_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 379, 'http://tong.visitkorea.or.kr/cms/resource/16/3558316_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=379 AND image_url='http://tong.visitkorea.or.kr/cms/resource/16/3558316_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 379, 'http://tong.visitkorea.or.kr/cms/resource/17/3558317_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=379 AND image_url='http://tong.visitkorea.or.kr/cms/resource/17/3558317_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 379, 'http://tong.visitkorea.or.kr/cms/resource/18/3558318_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=379 AND image_url='http://tong.visitkorea.or.kr/cms/resource/18/3558318_image2_1.jpg');
UPDATE place SET description='통영시 한산면에 위치한 제승당은 ''승리를 만드는 곳''이라는 뜻으로 이순신 장군의 집무실이었다. 이순신 장군의 사령부가 있던 곳으로 참모들과 전략, 전술, 작전을 협의하던 곳이다. 본래 이순신 장군이 가는 곳마다 기거하던 운주당이 있던 터였으나 1740년 비석을 세우고 제승당이라 이름하였다 전해진다. 이곳을 본거지로 당포해전, 한산도대첩에서 크게 승리하였다. 교과서에서 읽던 역사의 현장에서 이순신 장군과 휘하 참모들이 대면해야 했던 시간을 상상해 보며 자부심과 긍지를 새길 수 있는 곳이다.' WHERE place_id=379 AND (description IS NULL OR description='');
-- [380] 통영 한산도 이충무공 유적
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 380, 'http://tong.visitkorea.or.kr/cms/resource/24/3557924_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=380 AND image_url='http://tong.visitkorea.or.kr/cms/resource/24/3557924_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 380, 'http://tong.visitkorea.or.kr/cms/resource/21/3557921_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=380 AND image_url='http://tong.visitkorea.or.kr/cms/resource/21/3557921_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 380, 'http://tong.visitkorea.or.kr/cms/resource/22/3557922_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=380 AND image_url='http://tong.visitkorea.or.kr/cms/resource/22/3557922_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 380, 'http://tong.visitkorea.or.kr/cms/resource/23/3557923_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=380 AND image_url='http://tong.visitkorea.or.kr/cms/resource/23/3557923_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 380, 'http://tong.visitkorea.or.kr/cms/resource/25/3557925_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=380 AND image_url='http://tong.visitkorea.or.kr/cms/resource/25/3557925_image2_1.jpg');
UPDATE place SET description='한산도의 이충무공 유적지는 통영시 한산면 두억리 제승당 일원에 조성된 건물, 비석, 동산문화재 광장, 조경물 등과 풍치림야를 통칭한다. 임진왜란 때 충무공 이순신이 본영을 설치하고 삼도수군통제사(三道水軍統制使)의 직무를 수행하던 곳으로 1963년 1월 21일 사적으로 지정되었다. 정유재란으로 한산 진영이 불타버리고 폐허가 된 지 142년만인 1739년 제107대 통제사가 이곳에 유허비를 세우고 운주당 옛터에 예대로 집을 짓고, 제승당이라는 친필현판을 걸었다. 그 후 1760년 이충무공의 후손 이태상제121대 통제사가 낡은 건물을 중수하면서 유허비를 손질하고 비각을 뒤로 옮겨 세웠다. 40여 년 후 대대적인 정화 사업을 벌여 지금의 모습을 갖추게 되었다. 이곳 유적지에는 충무사, 제승당, 수루, 한산정 등 당우를 비롯하여 비각 5동과 5개문, 기타 부속건물이 있다.' WHERE place_id=380 AND (description IS NULL OR description='');
-- [381] 안중근의사기념관
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 381, 'https://tong.visitkorea.or.kr/cms/resource/43/4062343_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=381 AND image_url='https://tong.visitkorea.or.kr/cms/resource/43/4062343_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 381, 'https://tong.visitkorea.or.kr/cms/resource/64/3504764_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=381 AND image_url='https://tong.visitkorea.or.kr/cms/resource/64/3504764_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 381, 'https://tong.visitkorea.or.kr/cms/resource/65/3504765_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=381 AND image_url='https://tong.visitkorea.or.kr/cms/resource/65/3504765_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 381, 'https://tong.visitkorea.or.kr/cms/resource/66/3504766_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=381 AND image_url='https://tong.visitkorea.or.kr/cms/resource/66/3504766_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 381, 'https://tong.visitkorea.or.kr/cms/resource/39/4062339_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=381 AND image_url='https://tong.visitkorea.or.kr/cms/resource/39/4062339_image2_1.jpg');
UPDATE place SET description='안중근은 개항 이후 나라가 일제의 침략으로 참담하게 국권을 피탈당하고 있을 때 불타는 애국심으로 침략의 원흉 이토 히로부미를 처단하여 조국이 나아가야 할 길을 밝힌 민족의 영웅이다. 일제 식민 지배의 상징이었던 남산 조선신궁 터에 1970년 개관하고 2010년 새로이 건립되었다. 안중근의사기념관은 안중근의 숭고한 희생정신과 평화 사상을 널리 선양하여 국민의 나라 사랑 정신을 함양하고 인류 평화에 이바지하는데 그 소임이 있다. 안중근의사기념관은 단지 동맹을 상징하는 12개의 유리 기둥을 묶은 형태의 건물로 지하 2층, 지상 2층으로 구성되었다. 전시실에는 그의 출생부터 순국에 이르기까지의 전 생애와 안중근 의사가 생중에 남긴 유품들을 전시하고 있다. 특히 전체가 보물로 지정된 안중근의사 유묵은 그의 우국충절을 다시 한번 느끼게 한다. 그밖에 안중근 의사 초상화와 관련 사진, 건국 공로 훈장과 서한, 공판 당시 신문 보도 내용, 유명 인사 휘호 등도 전시되었다. 민족의 정신을 일깨우기 위해 학생들을 위한 교육도 준비되어 있고 성인을 위한 아카데미 교육도 진행된다.' WHERE place_id=381 AND (description IS NULL OR description='');
UPDATE place SET description='온달 산성은 남한강변의 해발 427m의 성산에 축성된 길이 682m(외측), 532m(내측), 높이 6m 내지 8m로 축성되어 반월형 석성으로 원형이 잘 보존되고 있으며 사적으로 지정되어 있다. 영춘을 돌아 흐르는 남한강 남안의 산에, 길이 70cm, 너비 40cm, 두께 5cm 크기의 얄팍한 돌로 축성한 성으로, 약 100m 정도가 붕괴된 것 외에는 대체로 현존한다. 동 ·남 ·북 3문[門]과 수구[水口]가 지금도 남아 있다. 성내에는 우물이 있었다고 전하나 지금은 매몰되어 물이 조금 나올 정도이며, 곳곳에서 삼국시대 및 고려 때의 토기 조각을 볼 수 있다. 이곳은 고구려가 삼국통일을 이루었다면 현재 중국의 양자강과 황하강 주변이 우리의 땅이 되었을 것이라는 역사적 회고와 바보 온달이 자기 수련의 노력을 통한 명장으로의 변신과 홀어머니에 대한 효행심 그리고 평강공주의 내조의 힘 등을 통하여 현대의 우리들에게 많은 교훈과 의미를 주는 유서 깊은 곳으로 온달동굴과 더불어 학생들의 고적 답사지로 각광을 받고 있다. 온달 산성의 지명과 전설에 의하면, 영토 확장 경쟁이 치열했던 삼국시대에 한강을 차지하기 위한 전초기지로서 고구려와 신라 사이에 영유권을 둘러싸고 전투가 치열하였던 곳으로 알려지고 있으며 고구려 평원왕의 사위 바보온달 장군의 무용담과 함께 평강공주와의 사랑 이야기가 전해오면서 붙여진 이름이다. 고구려 평원왕[平原王]의 사위 온달이 신라군의 침입 때 이 성을 쌓고 싸우다가 전사하였다고 삼국사기 열전 제45에 기록되어 있다.' WHERE place_id=382 AND (description IS NULL OR description='');
UPDATE place SET description='충렬사는 1606년 선조의 명으로 건립하였으며, 충무공 이순신의 신위를 모시고 삼도수군통제영에서 관리하던 사당이다. 고종 때 시행된 서원철폐 시 충무공 이순신 사당 중 유일하게 폐쇄되지 않고 존속된 정통 사당이며, 창건 이후 현재까지 전통 유교 홀기에 따라 제례(춘계향사, 추계향사)를 봉행하고 있다. 1663년에 충렬사란 사액 받고, 보물인 통영충렬사 팔사품과 경상남도 유형문화유산 통영 충렬묘비를 소장하고 있다. 충렬사에는 수군진법 훈련도인 수조도병풍, 삼도수군통제사 신관호(신헌)가 제작한 팔사품도병풍, 정조 19년 발간된 충무공전서, 1733년 작성된 제향홀기 등의 유물을 소장 및 전시하고 있다.' WHERE place_id=383 AND (description IS NULL OR description='');
-- [384] 남해 관음포 이충무공 유적
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 384, 'http://tong.visitkorea.or.kr/cms/resource/55/3553955_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=384 AND image_url='http://tong.visitkorea.or.kr/cms/resource/55/3553955_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 384, 'http://tong.visitkorea.or.kr/cms/resource/53/3553953_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=384 AND image_url='http://tong.visitkorea.or.kr/cms/resource/53/3553953_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 384, 'http://tong.visitkorea.or.kr/cms/resource/54/3553954_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=384 AND image_url='http://tong.visitkorea.or.kr/cms/resource/54/3553954_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 384, 'http://tong.visitkorea.or.kr/cms/resource/56/3553956_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=384 AND image_url='http://tong.visitkorea.or.kr/cms/resource/56/3553956_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 384, 'http://tong.visitkorea.or.kr/cms/resource/57/3553957_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=384 AND image_url='http://tong.visitkorea.or.kr/cms/resource/57/3553957_image2_1.jpg');
UPDATE place SET description='남해대교에서 섬의 한가운데를 향해 들어오면 관음포 이충무공전몰유허가 자리 잡고 있다. 일명 ‘이락사’라고도 불리는 이곳은 노량해전을 승리로 이끌고 전사한 이순신장군의 유해가 맨 처음 육지에 오른 곳이다. 이락사 앞 뜰에는 충무공 순국 400주년을 기념하기 위한 유언비가 역사를 증명하듯 하늘을 향해 솟아있다. 이락사가 순국 성지로서의 모습을 갖추기 시작한 것은 장군이 전사한 지 234년이 지난 1832년이었다. 이순신장군의 8대손으로 통제사가 된 이항권이 이곳에 나라를 지켰던 장군을 기리는 유허비와 비각을 세웠다고 한다. 이곳에는 전국 어디에서도 보기 힘든 사철 푸른 육송이 404년 전의 그날을 되살려내고 있었다. 많게는 10개에서 적게는 4개의 가지가 땅에서 바로 가지를 벌린 채 오솔길을 이루고 있다.' WHERE place_id=384 AND (description IS NULL OR description='');
-- [385] 남해 충렬사
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 385, 'http://tong.visitkorea.or.kr/cms/resource/94/3561794_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=385 AND image_url='http://tong.visitkorea.or.kr/cms/resource/94/3561794_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 385, 'http://tong.visitkorea.or.kr/cms/resource/91/3561791_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=385 AND image_url='http://tong.visitkorea.or.kr/cms/resource/91/3561791_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 385, 'http://tong.visitkorea.or.kr/cms/resource/92/3561792_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=385 AND image_url='http://tong.visitkorea.or.kr/cms/resource/92/3561792_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 385, 'http://tong.visitkorea.or.kr/cms/resource/93/3561793_image2_1.jpg', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=385 AND image_url='http://tong.visitkorea.or.kr/cms/resource/93/3561793_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 385, 'http://tong.visitkorea.or.kr/cms/resource/95/3561795_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=385 AND image_url='http://tong.visitkorea.or.kr/cms/resource/95/3561795_image2_1.jpg');
UPDATE place SET description='벚꽃터널 길을 지나 노량마을로 들어오면 충무공 이순신 장군이 관음포에서 전사한 후 시신을 잠시 모셨던 충렬사가 있다. 충렬사의 가장 큰 특징은 이순신 장군의 가묘가 남아 있다는 점이다. 1598년 11월 19일 돌아가신 충무공의 유해는 충렬사 자리에 안치되어 있다가, 1599년 2월 11일 전라 땅 고금도를 거쳐 외가 동네인 아산 현충사 자리에 운구되어 안장되었다. 충렬사는 장군이 돌아가신지 34년이 지난 1633년에 한 칸의 초사를 건립하여 제사를 봉행하면서 시작된다. 남해 충렬사는 규모가 작지만 1973년 6월 11일, 사적으로 지정되어 보호받고 있으며, 우암 송시열이 짓고 송준길이 쓴 이충무공묘비와 사우 그리고 충무공비와 충민공비, 내삼문, 외삼문을 모두 갖추고 있으며 청해루와 장군의 가묘가 잘 정리되어 있다.' WHERE place_id=385 AND (description IS NULL OR description='');
-- [386] 태봉국 궁예왕 역사공원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 386, 'https://tong.visitkorea.or.kr/cms/resource/78/4090478_image2_1.JPG', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=386 AND image_url='https://tong.visitkorea.or.kr/cms/resource/78/4090478_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 386, 'https://tong.visitkorea.or.kr/cms/resource/76/4090476_image2_1.JPG', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=386 AND image_url='https://tong.visitkorea.or.kr/cms/resource/76/4090476_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 386, 'https://tong.visitkorea.or.kr/cms/resource/77/4090477_image2_1.JPG', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=386 AND image_url='https://tong.visitkorea.or.kr/cms/resource/77/4090477_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 386, 'https://tong.visitkorea.or.kr/cms/resource/79/4090479_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=386 AND image_url='https://tong.visitkorea.or.kr/cms/resource/79/4090479_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 386, 'https://tong.visitkorea.or.kr/cms/resource/80/4090480_image2_1.JPG', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=386 AND image_url='https://tong.visitkorea.or.kr/cms/resource/80/4090480_image2_1.JPG');
UPDATE place SET description='태봉국 궁예왕 역사공원은 후삼국시대 궁예가 철원을 도읍으로 삼았던 역사를 바탕으로 조성된 역사문화공간이다. 철원은 905년부터 918년까지 태봉국의 수도였으며, 공원에서는 현재 비무장지대 안에 남아 있는 철원성과 궁예의 발자취를 다양한 전시와 모형으로 살펴볼 수 있다. 궁예왕 국가표준영정을 봉안한 궁예선양관과 태봉국 역사체험관, 방문자센터가 마련되어 있으며, 철원성 미니어처는 궁궐과 관청, 민가, 시장 등 당시 도성의 모습을 축소해 재현한다. 역사공원은 DMZ 접경지역의 민간인통제지역에 위치하여 개인차량으로 이동이 불가능하며, 반드시 태봉열차를 타고 이동해야 한다. 철원역사문화공원에서 출발하는 태봉열차를 이용해 이동할 수 있으며, 역사교육과 DMZ 평화관광을 함께 경험하는 공간으로 활용된다. 인근의 철원역사문화공원과 노동당사, 소이산 모노레일을 연계하면 철원의 고대사와 근현대사를 함께 둘러볼 수 있다.' WHERE place_id=386 AND (description IS NULL OR description='');
-- [387] 국립서울현충원
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 387, 'https://tong.visitkorea.or.kr/cms/resource/71/3531571_image2_1.jpg', true, 0
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=387 AND image_url='https://tong.visitkorea.or.kr/cms/resource/71/3531571_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 387, 'https://tong.visitkorea.or.kr/cms/resource/69/3531569_image2_1.jpg', false, 1
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=387 AND image_url='https://tong.visitkorea.or.kr/cms/resource/69/3531569_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 387, 'https://tong.visitkorea.or.kr/cms/resource/70/3531570_image2_1.jpg', false, 2
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=387 AND image_url='https://tong.visitkorea.or.kr/cms/resource/70/3531570_image2_1.jpg');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 387, 'https://tong.visitkorea.or.kr/cms/resource/72/3531572_image2_1.JPG', false, 3
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=387 AND image_url='https://tong.visitkorea.or.kr/cms/resource/72/3531572_image2_1.JPG');
INSERT INTO place_image (place_id, image_url, is_primary, sort_order)
SELECT 387, 'https://tong.visitkorea.or.kr/cms/resource/73/3531573_image2_1.jpg', false, 4
WHERE NOT EXISTS (SELECT 1 FROM place_image WHERE place_id=387 AND image_url='https://tong.visitkorea.or.kr/cms/resource/73/3531573_image2_1.jpg');
UPDATE place SET description='국립현충원은 나라와 민족을 위해 순국한 영령들이 안장된 국립묘지이다. 국가유공자의 유골, 유해를 안장하고, 그 충과 의를 기리기 위해 설립되었다. 국립서울현충원은 관악산 기슭의 공작봉을 주봉으로 하여 동작의 능선이 병풍 치듯 3면을 감싸고 있으며 앞에는 한강이 굽이쳐 돌고 있다. 1955년 국군묘지로 창설되어 1965년 국립묘지로 승격되었으며, 기관 명칭은 1996.6.1 국립묘지관리소에서 국립 현충원으로 변경하였다. 43만여 평의 이곳 성역에는 구한 말의 의병들을 위시하여 조국 광복을 위하여 투쟁하신 애국지사, 나라의 발전과 민족의 번영을 위해 평생을 바치신 국가 유공자, 위기에 처한 나라를 구하다 장렬히 산화하신 국군 장병과 경찰관, 예비군 등 165,000여 순국선열과 호국영령들이 잠들어 있다. 영령은 현충탑 내 위패 봉안관과 묘역에, 무명용사는 납골당에 안장되어 있다. 경내에는 현충탑, 현충문, 충성분수대, 현충지, 현충관, 사진 전시관, 유품전시관, 각 시도 공원 등이 설치되어 있다. 매년 6월 6일 현충일에는 거국적인 추념 행사가 거행되며, 2023년 3월부터 주중 및 토요일에 유가족과 참배객의 교통편의 제공을 위하여 현충원 주요 묘역 및 충혼당을 순환하는 셔틀버스를 운행한다.' WHERE place_id=387 AND (description IS NULL OR description='');

COMMIT;

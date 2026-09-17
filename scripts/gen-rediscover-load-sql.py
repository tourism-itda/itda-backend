"""재발굴 검증 결과 중 채택할 것만 골라 적재 SQL 을 만든다."""
import json, sys
sys.stdout.reconfigure(encoding="utf-8")
base = r"C:\Users\User\Desktop\claude max\관광데이터\build\route-probe"

# (content_id, 장소명) 단위로 채택 여부를 사람이 판단한 목록.
# 검증을 통과했다고 전부 쓰지 않는다 — 실재 확인과 작품 관련성은 다른 문제다.
ACCEPT = {
    (11658, "전쟁기념관"),          # 한국전쟁 전반 전시. 작품 배경과 직접 연결
    (79553, "백마고지전적지"),      # 애록고지는 가상이나 백마고지는 실제 고지전 현장
    (242452, "봉하마을"),           # 주인공 실제 모델의 생가·묘역
    (760497, "한국기원"),           # 조훈현·이창호가 실제 대국한 곳
}
# 제외 사유를 남긴다.
REJECT_NOTE = {
    (11658, "대구역"): "기차역은 여행 목적지가 아니다",
    (919207, "전쟁기념관"): "12·12 를 전쟁기념관에 엮는 것은 시대 일치에 가깝다",
    (1296404, "김포국제공항 국내선"): "공항은 여행 목적지가 아니다",
}


def q(s):
    return "NULL" if s is None else "'" + str(s).replace("'", "''") + "'"


def region_of(addr):
    return addr.strip().split()[0][:50] if addr else None


data = json.load(open(base + r"\rediscover-verified.json", encoding="utf-8"))["results"]
lines = ["-- 재발굴 앵커 중 사람이 채택한 것만 적재한다.",
         "SET client_encoding TO 'UTF8';", "\\set ON_ERROR_STOP on", "BEGIN;"]
n = 0
skipped = []
for row in data:
    cid = row["content_id"]
    order = 0
    for a in row["anchors"]:
        key = (cid, a["name"])
        if key not in ACCEPT:
            skipped.append((row["title"], a["name"], REJECT_NOTE.get(key, "채택 목록에 없음")))
            continue
        order += 1
        n += 1
        src = a["source"]
        if src == "TOUR_API":
            match = f"source='TOUR_API' AND external_id={q(a['external_id'])}"
            ext, kakao = q(a["external_id"]), "NULL"
        else:
            match = f"source='KAKAO' AND kakao_place_id={q(a['external_id'])}"
            ext, kakao = "NULL", q(a["external_id"])
        lines.append(f"""
-- [{cid}] {row['title']} -> {a['name']} ({src})
WITH existing AS (SELECT place_id FROM place WHERE {match} LIMIT 1),
ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT {q(a['name'])}, '관광지', {a['lat']}, {a['lng']}, {q(a['address'])}, {q(region_of(a['address']))},
         'SPOT', {q(src)}, {ext}, {kakao}, false
  WHERE NOT EXISTS (SELECT 1 FROM existing) RETURNING place_id),
target AS (SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT {cid}, t.place_id, {order} FROM target t
WHERE NOT EXISTS (SELECT 1 FROM content_place cp
                  WHERE cp.content_id = {cid} AND cp.place_id = t.place_id);""")

lines.append("\nCOMMIT;")
path = base + r"\load-rediscovered.sql"
open(path, "w", encoding="utf-8").write("\n".join(lines))
print(f"채택 {n}건 -> {path}\n")
print("제외한 것:")
for t, nm, why in skipped:
    print(f"  {t} / {nm} — {why}")

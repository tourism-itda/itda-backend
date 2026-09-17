"""검증된 앵커를 place + content_place 로 적재하는 SQL 을 만든다. 멱등하게 쓴다."""
import json, sys
sys.stdout.reconfigure(encoding="utf-8")
base = r"C:\Users\User\Desktop\claude max\관광데이터\build\route-probe"

dry = {r["content_id"]: r for r in json.load(open(base + r"\discovery-dryrun.json", encoding="utf-8"))["results"]}
ready = json.load(open(base + r"\overlap.json", encoding="utf-8"))["ready"]

TYPE_LABEL = {"12": "관광지", "14": "문화시설", "39": "음식점"}


def q(s):
    if s is None:
        return "NULL"
    return "'" + str(s).replace("'", "''") + "'"


def region_of(addr):
    if not addr:
        return None
    return addr.strip().split()[0][:50]


ONLY = sys.argv[1] if len(sys.argv) > 1 else "TOUR_API"   # TOUR_API | KAKAO

lines = [f"-- 검증된 작품 관련 명소를 content_place 에 적재한다 (출처: {ONLY}).",
         "-- 각 앵커는 관광공사 TourAPI 또는 카카오 지도에서 실재와 좌표를 확인했다.",
         "-- 이미 있는 place/매핑은 건드리지 않는다(멱등).",
         "SET client_encoding TO 'UTF8';",
         "\\set ON_ERROR_STOP on",
         "BEGIN;"]
count_anchor = 0
for cid in ready:
    row = dry[cid]
    printed_header = False
    for order, a in enumerate(row["anchors"], start=1):
        src = a["source"]
        if src != ONLY:
            continue
        if not printed_header:
            lines.append(f"\n-- [{cid}] {row['title']}")
            printed_header = True
        count_anchor += 1
        label = TYPE_LABEL.get(a.get("content_type_id"), "관광지") if src == "TOUR_API" else "관광지"
        if src == "TOUR_API":
            match = f"source='TOUR_API' AND external_id={q(a['external_id'])}"
            ext, kakao = q(a["external_id"]), "NULL"
        else:
            match = f"source='KAKAO' AND kakao_place_id={q(a['external_id'])}"
            ext, kakao = "NULL", q(a["external_id"])

        lines.append(f"""WITH existing AS (
  SELECT place_id FROM place WHERE {match} LIMIT 1
), ins AS (
  INSERT INTO place (name, category, latitude, longitude, address, region,
                     place_type, source, external_id, kakao_place_id, night_open)
  SELECT {q(a['name'])}, {q(label)}, {a['lat']}, {a['lng']}, {q(a['address'])}, {q(region_of(a['address']))},
         'SPOT', {q(src)}, {ext}, {kakao}, false
  WHERE NOT EXISTS (SELECT 1 FROM existing)
  RETURNING place_id
), target AS (
  SELECT place_id FROM existing UNION ALL SELECT place_id FROM ins
)
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT {cid}, t.place_id, {order} FROM target t
WHERE NOT EXISTS (
  SELECT 1 FROM content_place cp WHERE cp.content_id = {cid} AND cp.place_id = t.place_id
);""")

lines.append("\nCOMMIT;")
lines.append("\\echo '--- 적재 후 확인 ---'")
lines.append("SELECT count(*) AS content_place행, count(DISTINCT content_id) AS 매핑된작품 FROM content_place;")

path = base + rf"\load-anchors-{ONLY.lower()}.sql"
open(path, "w", encoding="utf-8").write("\n".join(lines))
works = len({cid for cid in ready if any(a["source"] == ONLY for a in dry[cid]["anchors"])})
print(f"[{ONLY}] 작품 {works}편 / 앵커 {count_anchor}건 -> {path}")

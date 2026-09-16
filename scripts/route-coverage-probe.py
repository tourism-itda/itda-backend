"""앵커 커버리지 측정 — 읽기 전용 GET만. DB 접속 없이 공개 API로 작품→인물→장소를 센다.

이전 inventory.json 은 인물 95명 중 66명이 실패로 남아 있다(4-way 병렬 + 20s 타임아웃).
이 스크립트는 실패한 인물만 순차로 재시도해 기존 결과에 합친다. 성공분은 다시 부르지 않는다.

콘텐츠 상세 GET 은 수집을 트리거할 수 있으므로 여전히 호출하지 않는다.

  python scripts/route-coverage-probe.py
"""
import json
import math
import sys
import time
import urllib.request
from collections import defaultdict
from itertools import combinations
from pathlib import Path

BASE = "https://api.itda-travel.com"
OUT = Path(__file__).resolve().parents[1] / "build" / "route-probe"
INVENTORY = OUT / "inventory.json"

ATTEMPTS = 3
PAUSE_SECONDS = 0.4      # 서버가 병렬 호출에 502 를 뱉었으므로 순차 + 간격을 둔다
BACKOFF_SECONDS = 2.0


def get(path):
    last = None
    for attempt in range(ATTEMPTS):
        try:
            request = urllib.request.Request(
                BASE + path,
                headers={"User-Agent": "ItdaCoverageProbe/1.0", "Accept": "application/json"})
            with urllib.request.urlopen(request, timeout=30) as response:
                return json.load(response)
        except Exception as exc:
            last = {"probe_error": type(exc).__name__,
                    "status": getattr(exc, "code", None), "path": path}
            if attempt < ATTEMPTS - 1:
                time.sleep(BACKOFF_SECONDS * (attempt + 1))
    return last


def succeeded(entry):
    return isinstance(entry.get("contents"), list) and isinstance(entry.get("places"), list)


def refetch(persons):
    """실패한 인물만 다시 부른다. 성공분은 그대로 둔다."""
    pending = [p for p in persons if not succeeded(p)]
    print(f"재조회 대상 {len(pending)}명 / 전체 {len(persons)}명", flush=True)

    for index, entry in enumerate(pending, start=1):
        pid = entry["person"]["person_id"]
        name = entry["person"]["name"]
        if not isinstance(entry.get("contents"), list):
            entry["contents"] = get(f"/api/explore/persons/{pid}/contents")
            time.sleep(PAUSE_SECONDS)
        if not isinstance(entry.get("places"), list):
            entry["places"] = get(f"/api/explore/persons/{pid}/places")
            time.sleep(PAUSE_SECONDS)

        mark = "OK" if succeeded(entry) else "FAIL"
        works = len(entry["contents"]) if isinstance(entry["contents"], list) else "-"
        spots = len(entry["places"]) if isinstance(entry["places"], list) else "-"
        print(f"  [{index}/{len(pending)}] {mark} {name}(id={pid}) 작품={works} 장소={spots}", flush=True)
    return persons


def in_korea(place):
    return 33 <= place["latitude"] <= 39 and 124 <= place["longitude"] <= 132


def haversine(a, b):
    x1, y1, x2, y2 = map(math.radians,
                         (a["longitude"], a["latitude"], b["longitude"], b["latitude"]))
    h = math.sin((y2 - y1) / 2) ** 2 + math.cos(y1) * math.cos(y2) * math.sin((x2 - x1) / 2) ** 2
    return 6371000 * 2 * math.asin(min(1, math.sqrt(h)))


def report(contents, persons):
    titles = {c["content_id"]: c["title"] for c in contents}
    ok = [p for p in persons if succeeded(p)]
    print(f"\n인물 {len(persons)}명 중 조회 성공 {len(ok)}명. 실패분은 '연결 0건'이 아니라 미확인이다.")

    anchors_all = defaultdict(dict)
    anchors_kr = defaultdict(dict)
    persons_of = defaultdict(set)
    for entry in ok:
        for work in entry["contents"]:
            # 인물별 작품 목록은 camelCase(contentId), 콘텐츠 목록은 snake_case(content_id) 다.
            cid = work.get("contentId", work.get("content_id"))
            if cid not in titles:
                continue
            persons_of[cid].add(entry["person"]["name"])
            for place in entry["places"]:
                anchors_all[cid][place["place_id"]] = place
                if in_korea(place):
                    anchors_kr[cid][place["place_id"]] = place

    def bucket(table):
        counts = {0: 0, 1: 0, 2: 0}
        for cid in titles:
            counts[min(len(table.get(cid, {})), 2)] += 1
        return counts

    total = len(titles)
    b_all, b_kr = bucket(anchors_all), bucket(anchors_kr)
    print(f"\n공개 작품 {total}편 기준 앵커 확보량")
    print(f"  전체 좌표   0곳 {b_all[0]:>3} / 1곳 {b_all[1]:>3} / 2곳이상 {b_all[2]:>3}")
    print(f"  국내 좌표만 0곳 {b_kr[0]:>3} / 1곳 {b_kr[1]:>3} / 2곳이상 {b_kr[2]:>3}")

    overseas = [cid for cid in titles
                if anchors_all.get(cid) and not anchors_kr.get(cid)]
    if overseas:
        print(f"\n해외 장소만 잡혀 국내 루트 불가: {len(overseas)}편")
        for cid in overseas[:10]:
            names = [p["name"] for p in anchors_all[cid].values()][:4]
            print(f"   {cid} {titles[cid]} — {names} (인물 {sorted(persons_of[cid])})")

    spread = []
    for cid in titles:
        places = list(anchors_kr.get(cid, {}).values())
        if len(places) >= 2:
            widest = max(haversine(a, b) for a, b in combinations(places, 2))
            spread.append((widest, cid, titles[cid], len(places)))
    if spread:
        spread.sort(reverse=True)
        tight = sum(1 for s in spread if s[0] <= 25000)
        print(f"\n국내 앵커 2곳 이상 {len(spread)}편 — 25km 이내로 모임 {tight}편 / 흩어짐 {len(spread) - tight}편")
        for widest, cid, title, n in spread[:8]:
            print(f"   {round(widest / 1000):>4}km  {cid} {title} ({n}곳)")

    (OUT / "coverage-summary.json").write_text(json.dumps({
        "published_total": total,
        "persons_resolved": len(ok),
        "persons_total": len(persons),
        "anchors_all_coords": b_all,
        "anchors_korea_only": b_kr,
        "overseas_only_contents": overseas,
        "per_content": {str(cid): sorted(p["name"] for p in anchors_kr.get(cid, {}).values())
                        for cid in titles},
    }, ensure_ascii=False, indent=2), encoding="utf-8")
    print(f"\n저장: {OUT / 'coverage-summary.json'}")


def main():
    sys.stdout.reconfigure(encoding="utf-8")
    inventory = json.loads(INVENTORY.read_text(encoding="utf-8"))
    persons = refetch(inventory["persons"])
    inventory["persons"] = persons
    INVENTORY.write_text(json.dumps(inventory, ensure_ascii=False, indent=2), encoding="utf-8")
    report(inventory["contents"], persons)


if __name__ == "__main__":
    main()

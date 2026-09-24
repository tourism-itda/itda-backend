"""공개 작품 전부에 대해 루트 생성을 시도해 성공률과 품질 지표를 낸다.

앱이 떠 있어야 한다(기본 localhost:8081). 루트 생성은 일반 명소를 place 에 저장하므로
운영 DB 에 붙여 돌리면 place 행이 늘어난다 — 사용자가 호출할 때와 같은 동작이다.

  python scripts/route-sweep.py [http://localhost:8081]
"""
import json
import sys
import time
import urllib.error
import urllib.request
from pathlib import Path

BASE = sys.argv[1] if len(sys.argv) > 1 else "http://localhost:8081"
OUT = Path(__file__).resolve().parents[1] / "build" / "route-probe"


def get_json(url):
    with urllib.request.urlopen(url, timeout=30) as r:
        return json.load(r)


def plan(content_id):
    req = urllib.request.Request(
        BASE + "/api/itineraries/route",
        data=json.dumps({"content_id": content_id}).encode(),
        headers={"Content-Type": "application/json"}, method="POST")
    try:
        with urllib.request.urlopen(req, timeout=120) as r:
            return json.load(r), None
    except urllib.error.HTTPError as e:
        return None, f"HTTP {e.code}"
    except Exception as e:
        return None, type(e).__name__


def main():
    sys.stdout.reconfigure(encoding="utf-8")
    contents = get_json(BASE + "/api/contents?page=0&limit=200").get("data", [])
    print(f"공개 작품 {len(contents)}편에 대해 루트 생성을 시도한다\n")

    ok, hidden, error = [], [], []
    for i, c in enumerate(contents, start=1):
        cid, title = c["content_id"], c["title"]
        route, err = plan(cid)
        if route:
            spots = [s for s in route["slots"] if s.get("place")]
            related = sum(1 for s in spots if s.get("filled_by") != "GENERAL")
            ok.append({
                "content_id": cid, "title": title,
                "spots": route["spot_count"], "related": related,
                "general": len(spots) - related,
                "source": route.get("anchor_source"),
                "road_verified": route.get("road_verified"),
                "within_limits": route.get("within_time_limits"),
                "distance_m": route.get("travel_distance_meters"),
                "duration_s": route.get("travel_duration_seconds"),
            })
            mark = "OK " if route.get("within_time_limits") else "TIME"
            print(f"[{i:>3}/{len(contents)}] {mark} {title} — 관련{related}+일반{len(spots)-related}, "
                  f"실측={route.get('road_verified')}", flush=True)
        elif err == "HTTP 404":
            hidden.append({"content_id": cid, "title": title})
            print(f"[{i:>3}/{len(contents)}] 숨김 {title}", flush=True)
        else:
            error.append({"content_id": cid, "title": title, "error": err})
            print(f"[{i:>3}/{len(contents)}] 오류 {title} — {err}", flush=True)
        time.sleep(0.2)

    total = len(contents)
    verified = sum(1 for r in ok if r["road_verified"])
    within = sum(1 for r in ok if r["within_limits"])
    rel2 = sum(1 for r in ok if r["related"] >= 2)
    three = sum(1 for r in ok if r["spots"] == 3)

    print("\n" + "=" * 62)
    print(f"공개 작품            {total}편")
    print(f"  루트 생성 성공     {len(ok)}편 ({len(ok) * 100 // total}%)")
    print(f"  숨김(관련 명소 0)  {len(hidden)}편")
    print(f"  오류               {len(error)}편")
    print(f"\n성공한 {len(ok)}편의 품질")
    print(f"  명소 3곳 꽉 채움   {three}편")
    print(f"  관련 2곳 + 일반 1  {rel2}편 / 관련 1곳 + 일반 2  {len(ok) - rel2}편")
    print(f"  길찾기 실측 성공   {verified}편")
    print(f"  시간 제한 통과     {within}편")
    if error:
        print("\n오류 목록:")
        for e in error:
            print(f"  {e['content_id']} {e['title']} — {e['error']}")

    OUT.mkdir(parents=True, exist_ok=True)
    (OUT / "route-sweep.json").write_text(
        json.dumps({"ok": ok, "hidden": hidden, "error": error}, ensure_ascii=False, indent=2),
        encoding="utf-8")
    print(f"\n저장: {OUT / 'route-sweep.json'}")


if __name__ == "__main__":
    main()

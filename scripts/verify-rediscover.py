"""서브에이전트가 낸 후보를 실제 API 로 검증한다. discovery-dryrun 의 검증 로직을 그대로 쓴다.

  python scripts/verify-rediscover.py
"""
import importlib.util
import json
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "build" / "route-probe"

# 검증 규칙(표기 변형·이름 일치·지역 별칭)을 한 곳에만 두기 위해 기존 스크립트를 불러 쓴다.
spec = importlib.util.spec_from_file_location("dryrun", Path(__file__).with_name("discovery-dryrun.py"))
dryrun = importlib.util.module_from_spec(spec)
spec.loader.exec_module(dryrun)


def main():
    sys.stdout.reconfigure(encoding="utf-8")
    env = dryrun.load_env()
    tour_key = env["PUBLIC_DATA_API_KEY"]
    kakao_key = env.get("KAKAO_MAP_REST_KEY") or env.get("KAKAO_REST_API_KEY")

    works = []
    for n in (1, 2, 3):
        path = OUT / f"rediscover-{n}.json"
        if path.exists():
            works.extend(json.loads(path.read_text(encoding="utf-8")))
        else:
            print(f"경고: {path.name} 없음")

    results = []
    calls = {"tour": 0, "kakao": 0}
    for work in works:
        accepted = []
        traces = []
        for cand in work.get("candidates", []):
            keyword = cand["keyword"]
            region = (cand.get("region") or "").strip()
            if cand.get("confidence", 0) < dryrun.MIN_CONFIDENCE:
                traces.append(f"    -- {keyword}: 확신도 미달")
                continue

            def pick(hits):
                rejected = None
                for hit in hits:
                    if not dryrun.in_korea(hit["lat"], hit["lng"]):
                        continue
                    if any(dryrun.haversine(hit["lat"], hit["lng"], a["lat"], a["lng"])
                           < dryrun.DUPLICATE_RADIUS_M for a in accepted):
                        continue
                    if not dryrun.name_matches(keyword, hit["name"]):
                        rejected = (hit, "이름 불일치")
                        continue
                    if not dryrun.region_matches(region or None, hit.get("address")):
                        rejected = (hit, f"지역 불일치(기대 '{region}')")
                        continue
                    return hit, None
                return None, rejected

            picked, rejected, source = None, None, "TOUR_API"
            for variant in dryrun.keyword_variants(keyword):
                for type_id in ("12", "14", None):
                    hits, _ = dryrun.tour_search(tour_key, variant, type_id)
                    calls["tour"] += 1
                    time.sleep(0.15)
                    picked, rejected = pick(hits)
                    if picked:
                        break
                if picked:
                    break

            if not picked and kakao_key:
                hits, _ = dryrun.kakao_search(kakao_key, keyword)
                calls["kakao"] += 1
                time.sleep(0.15)
                kp, kr = pick(hits)
                if kp:
                    picked, source = kp, "KAKAO"
                else:
                    rejected = kr or rejected

            if picked:
                picked.update(source=source, relation_type=cand["relationType"], reason=cand["reason"])
                accepted.append(picked)
                traces.append(f"    OK {keyword} -> [{source}] {picked['name']} ({picked['address']})")
            elif rejected:
                traces.append(f"    XX {keyword}: {rejected[1]} — '{rejected[0]['name']}'")
            else:
                traces.append(f"    -- {keyword}: 결과 없음")

        results.append({"content_id": work["content_id"], "title": work["title"], "anchors": accepted})
        print(f"[{len(accepted)}곳] {work['title']}")
        for line in traces:
            print(line)

    got = sum(1 for r in results if r["anchors"])
    print(f"\n대상 {len(results)}편 — 앵커 확보 {got}편, 0곳 {len(results) - got}편")
    print(f"호출 TourAPI {calls['tour']} / 카카오 {calls['kakao']}")
    (OUT / "rediscover-verified.json").write_text(
        json.dumps({"results": results}, ensure_ascii=False, indent=2), encoding="utf-8")
    print(f"저장: {OUT / 'rediscover-verified.json'}")


if __name__ == "__main__":
    main()

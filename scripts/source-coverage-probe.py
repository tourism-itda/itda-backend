"""일반 명소·식당·카페를 어느 소스가 얼마나 채우는지 실측한다. 읽기 전용 GET.

'일반 명소는 관광공사 TourAPI 에서만 가져온다'는 결정이 실제로 성립하는지 확인하는 것이 목적이다.
앵커 주변 5/10/15km 에서 TourAPI 와 카카오가 각각 몇 건을 돌려주는지 총건수로 비교한다.

키는 프로젝트 루트 .env 에서 읽는다. 코드·결과 파일에 키를 쓰지 않는다.

  python scripts/source-coverage-probe.py
"""
import json
import sys
import time
import urllib.parse
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "build" / "route-probe"

TOUR_BASE = "https://apis.data.go.kr/B551011/KorService2/"
KAKAO_CATEGORY = "https://dapi.kakao.com/v2/local/search/category.json"

RADII = [5000, 10000, 15000]

# 어제 실측으로 25km 이내에 앵커가 모인다고 확인된 작품들에서 뽑은 앵커.
# 도심(경복궁)부터 지방 소도시(외암민속마을)까지 섞어야 커버리지 편차가 드러난다.
ANCHORS = [
    ("경복궁 (서울 도심)",          37.5788, 126.9770),
    ("수원 화성 (경기 도시)",        37.2881, 127.0139),
    ("화성 융건릉 (경기 외곽)",      37.2117, 126.9910),
    ("세종대왕릉 (여주 지방)",       37.3081, 127.6029),
    ("현충사 (아산 지방)",          36.7936, 126.9486),
    ("외암민속마을 (아산 농촌)",     36.7302, 127.0166),
]

# TourAPI contentTypeId
TOUR_TYPES = {"관광지(12)": "12", "문화시설(14)": "14", "음식점(39)": "39"}
# 카카오 category_group_code
KAKAO_TYPES = {"관광명소(AT4)": "AT4", "음식점(FD6)": "FD6", "카페(CE7)": "CE7"}


def load_env():
    env = {}
    path = ROOT / ".env"
    if not path.exists():
        raise SystemExit(".env 가 없습니다. PUBLIC_DATA_API_KEY 와 KAKAO_MAP_REST_KEY 가 필요합니다.")
    for line in path.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        env[key.strip()] = value.strip()
    if not env.get("PUBLIC_DATA_API_KEY"):
        raise SystemExit(".env 에 PUBLIC_DATA_API_KEY 가 없습니다.")
    return env


def fetch(url, headers=None):
    request = urllib.request.Request(url, headers=headers or {})
    request.add_header("User-Agent", "ItdaSourceProbe/1.0")
    request.add_header("Accept", "application/json")
    try:
        with urllib.request.urlopen(request, timeout=20) as response:
            body = response.read().decode("utf-8", errors="replace")
    except Exception as exc:
        return {"probe_error": type(exc).__name__, "status": getattr(exc, "code", None)}
    if not body.strip().startswith("{"):
        # 관광API 는 키 오류·초과 시 XML 을 돌려준다.
        return {"probe_error": "NonJsonResponse", "snippet": body[:200]}
    try:
        return json.loads(body)
    except Exception:
        return {"probe_error": "JsonDecodeError", "snippet": body[:200]}


def tour_count(key, lat, lng, radius, content_type_id):
    params = {
        "serviceKey": key, "MobileOS": "ETC", "MobileApp": "itda", "_type": "json",
        "mapX": lng, "mapY": lat, "radius": radius,
        "contentTypeId": content_type_id, "arrange": "E",
        "numOfRows": 1, "pageNo": 1,
    }
    data = fetch(TOUR_BASE + "locationBasedList2?" + urllib.parse.urlencode(params))
    if "probe_error" in data:
        return None, data["probe_error"]
    header = data.get("response", {}).get("header", {})
    if header.get("resultCode") not in ("0000", "00"):
        return None, header.get("resultMsg", "unknown")
    return data.get("response", {}).get("body", {}).get("totalCount", 0), None


def kakao_count(key, lat, lng, radius, code):
    if not key:
        return None, "NoKey"
    params = {"category_group_code": code, "x": lng, "y": lat,
              "radius": radius, "size": 1, "page": 1}
    data = fetch(KAKAO_CATEGORY + "?" + urllib.parse.urlencode(params),
                 headers={"Authorization": "KakaoAK " + key})
    if "probe_error" in data:
        return None, data["probe_error"]
    return data.get("meta", {}).get("total_count", 0), None


def main():
    sys.stdout.reconfigure(encoding="utf-8")
    env = load_env()
    tour_key = env["PUBLIC_DATA_API_KEY"]
    # 로그인 앱 키(KAKAO_REST_API_KEY)는 카카오맵이 꺼져 있어 403 이 난다. 지도 앱 키를 쓴다.
    kakao_key = env.get("KAKAO_MAP_REST_KEY") or env.get("KAKAO_REST_API_KEY")

    results = []
    calls = 0
    for label, lat, lng in ANCHORS:
        print(f"\n=== {label} ===", flush=True)
        header = "  반경    " + "".join(f"{name:>14}" for name in
                                        list(TOUR_TYPES) + list(KAKAO_TYPES))
        print(header)
        row_for_anchor = {"anchor": label, "lat": lat, "lng": lng, "radii": {}}

        for radius in RADII:
            cells = []
            counts = {}
            for name, type_id in TOUR_TYPES.items():
                count, error = tour_count(tour_key, lat, lng, radius, type_id)
                calls += 1
                time.sleep(0.2)
                counts[name] = count if error is None else f"ERR:{error}"
                cells.append(f"{counts[name]:>14}")
            for name, code in KAKAO_TYPES.items():
                count, error = kakao_count(kakao_key, lat, lng, radius, code)
                calls += 1
                time.sleep(0.2)
                counts[name] = count if error is None else f"ERR:{error}"
                cells.append(f"{counts[name]:>14}")
            print(f"  {radius // 1000:>2}km  " + "".join(cells), flush=True)
            row_for_anchor["radii"][radius] = counts
        results.append(row_for_anchor)

    OUT.mkdir(parents=True, exist_ok=True)
    (OUT / "source-coverage.json").write_text(
        json.dumps({"anchors": results, "total_calls": calls}, ensure_ascii=False, indent=2),
        encoding="utf-8")
    print(f"\n총 호출 {calls}회. 저장: {OUT / 'source-coverage.json'}")


if __name__ == "__main__":
    main()

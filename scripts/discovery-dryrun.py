"""앵커 발굴 파이프라인의 검증 단계를 실제 TourAPI 로 돌려 본다. 읽기 전용 GET.

LLM 발굴 단계는 이번에는 사람(Claude 구독 세션)이 대신 수행해
build/route-probe/llm-candidates.json 에 후보를 만들어 두었다.
이 스크립트는 그 뒤의 검증 단계 — AnchorDiscoveryService.verify() 와 같은 로직 — 만 실행한다.

  1순위 TourAPI searchKeyword2 (contentTypeId=12 관광지 → 0건이면 14 문화시설)
  2순위 카카오 키워드 검색 (TourAPI 0건일 때만)
  공통   국내 좌표 범위 확인, 같은 작품 내 250m 중복 제거, confidence >= 3

목적은 "69편 중 실제로 몇 편이 앵커를 얻는가" 를 배치 실행 전에 확인하는 것이다.

  python scripts/discovery-dryrun.py
"""
import json
import math
import sys
import time
import urllib.parse
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "build" / "route-probe"
TOUR_BASE = "https://apis.data.go.kr/B551011/KorService2/searchKeyword2?"
KAKAO_KEYWORD = "https://dapi.kakao.com/v2/local/search/keyword.json"

MIN_CONFIDENCE = 3
DUPLICATE_RADIUS_M = 250
SEARCH_LIMIT = 5


def load_env():
    env = {}
    for line in (ROOT / ".env").read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if line and not line.startswith("#") and "=" in line:
            key, value = line.split("=", 1)
            env[key.strip()] = value.strip()
    return env


def haversine(lat1, lng1, lat2, lng2):
    x1, y1, x2, y2 = map(math.radians, (lng1, lat1, lng2, lat2))
    h = math.sin((y2 - y1) / 2) ** 2 + math.cos(y1) * math.cos(y2) * math.sin((x2 - x1) / 2) ** 2
    return 6371000 * 2 * math.asin(min(1, math.sqrt(h)))


def in_korea(lat, lng):
    return 33 <= lat <= 39 and 124 <= lng <= 132


# 관광API 주소는 정식 명칭('경상남도')을 쓰고 우리 기대값은 약칭('경남')이라 그대로 비교하면 어긋난다.
REGION_ALIASES = {
    "서울": ["서울"], "인천": ["인천"], "대전": ["대전"], "대구": ["대구"],
    "부산": ["부산"], "울산": ["울산"], "광주": ["광주"], "세종": ["세종"],
    "경기": ["경기"], "강원": ["강원"], "제주": ["제주"],
    "충남": ["충남", "충청남도"], "충북": ["충북", "충청북도"],
    "전남": ["전남", "전라남도"], "전북": ["전북", "전라북도"],
    "경남": ["경남", "경상남도"], "경북": ["경북", "경상북도"],
}


def region_matches(expected, address):
    if not expected:
        return True
    return any(alias in (address or "") for alias in REGION_ALIASES.get(expected, [expected]))


def name_matches(keyword, name):
    """관광API 는 제목 부분일치라 '이화장'이 '한국자이화장품'에 걸린다.

    검색어가 결과 이름의 <b>앞이나 뒤에 붙어 있어야</b> 같은 장소로 본다.
    관광API 는 '서울 운현궁' 처럼 지역을 앞에 붙여 등록하는 경우가 많아 접두만 보면 정상 결과를 버린다.
    반대로 이름 한가운데 우연히 낀 경우는 다른 장소다.

    통과: '자유공원(인천)', '남한산성 수어장대', '창덕궁 낙선재', '서울 운현궁'
    거부: '한국자이화장품'(검색어 '이화장' 이 가운데 낌)
    '박진전쟁기념관' 은 접미가 걸려 통과하지만 뒤의 지역 검사에서 걸러진다.
    """
    def norm(s):
        return (s or "").replace(" ", "").replace("·", "").replace(".", "")

    n_keyword, n_name = norm(keyword), norm(name)
    if not n_keyword or not n_name:
        return False
    return (n_name.startswith(n_keyword)
            or n_name.endswith(n_keyword)
            or ("(" + n_keyword) in n_name)


def keyword_variants(keyword):
    """관광API 는 제목을 그대로 문자열 비교한다. 표기 차이 하나로 0건이 나오므로 변형을 시도한다.

    실제로 '제주4.3평화공원' 은 0건이지만 '제주4·3평화공원'(가운뎃점)은 잡힌다.
    """
    variants = [keyword]
    for made in (keyword.replace(".", "·"), keyword.replace(" ", ""),
                 keyword.replace("·", " "), keyword.replace(".", " ")):
        if made not in variants:
            variants.append(made)
    return variants


def tour_search(key, keyword, content_type_id):
    params = {"serviceKey": key, "MobileOS": "ETC", "MobileApp": "itda", "_type": "json",
              "keyword": keyword, "numOfRows": SEARCH_LIMIT, "pageNo": 1}
    if content_type_id:
        params["contentTypeId"] = content_type_id
    try:
        with urllib.request.urlopen(TOUR_BASE + urllib.parse.urlencode(params), timeout=20) as r:
            body = r.read().decode("utf-8", errors="replace")
    except Exception as exc:
        return [], f"ERR:{type(exc).__name__}"
    if not body.strip().startswith("{"):
        return [], "ERR:NonJson"
    data = json.loads(body)
    header = data.get("response", {}).get("header", {})
    if header.get("resultCode") not in ("0000", "00"):
        return [], f"ERR:{header.get('resultMsg')}"
    items = data.get("response", {}).get("body", {}).get("items")
    if not items or items == "":
        return [], None
    item = items.get("item", [])
    if isinstance(item, dict):
        item = [item]
    found = []
    for it in item:
        try:
            lat, lng = float(it.get("mapy")), float(it.get("mapx"))
        except (TypeError, ValueError):
            continue
        found.append({"name": it.get("title"), "lat": lat, "lng": lng,
                      "address": it.get("addr1"), "external_id": str(it.get("contentid")),
                      "content_type_id": str(it.get("contenttypeid"))})
    return found, None


def kakao_search(key, keyword):
    params = {"query": keyword, "size": SEARCH_LIMIT}
    request = urllib.request.Request(KAKAO_KEYWORD + "?" + urllib.parse.urlencode(params),
                                     headers={"Authorization": "KakaoAK " + key})
    try:
        with urllib.request.urlopen(request, timeout=20) as r:
            data = json.loads(r.read().decode("utf-8"))
    except Exception as exc:
        return [], f"ERR:{type(exc).__name__}"
    found = []
    for doc in data.get("documents", []):
        try:
            lat, lng = float(doc["y"]), float(doc["x"])
        except (TypeError, ValueError, KeyError):
            continue
        found.append({"name": doc.get("place_name"), "lat": lat, "lng": lng,
                      "address": doc.get("address_name"), "external_id": doc.get("id"),
                      "category": doc.get("category_name")})
    return found, None


def main():
    sys.stdout.reconfigure(encoding="utf-8")
    env = load_env()
    tour_key = env.get("PUBLIC_DATA_API_KEY")
    # 로그인 앱 키(KAKAO_REST_API_KEY)는 카카오맵이 꺼져 있어 403 이 난다. 지도 앱 키를 쓴다.
    kakao_key = env.get("KAKAO_MAP_REST_KEY") or env.get("KAKAO_REST_API_KEY")
    if not tour_key:
        raise SystemExit(".env 에 PUBLIC_DATA_API_KEY 가 필요합니다.")

    works = json.loads((OUT / "llm-candidates.json").read_text(encoding="utf-8"))
    regions = json.loads((OUT / "candidate-regions.json").read_text(encoding="utf-8"))
    results = []
    calls = {"tour": 0, "kakao": 0}

    for work in works:
        accepted = []
        traces = []
        for cand in work["candidates"]:
            keyword = cand["keyword"]
            if cand["confidence"] < MIN_CONFIDENCE:
                traces.append(f"    - {keyword}: 확신도 미달({cand['confidence']}) 건너뜀")
                continue

            expected_region = regions.get(keyword)

            def pick_valid(hits):
                """검증을 통과하는 첫 결과. 통과가 없으면 (None, 탈락사유) 를 돌려준다."""
                rejected_local = None
                for hit in hits:
                    if not in_korea(hit["lat"], hit["lng"]):
                        continue
                    if any(haversine(hit["lat"], hit["lng"], a["lat"], a["lng"]) < DUPLICATE_RADIUS_M
                           for a in accepted):
                        continue
                    # 부분일치 오탐 방어: '이화장'이 '한국자이화장품'에 걸리는 것을 막는다.
                    if not name_matches(keyword, hit["name"]):
                        rejected_local = (hit, "이름 불일치")
                        continue
                    # 동명 장소 방어: '자유공원'은 인천과 안양에 둘 다 있다.
                    if not region_matches(expected_region, hit.get("address")):
                        rejected_local = (hit, f"지역 불일치(기대 '{expected_region}')")
                        continue
                    return hit, None
                return None, rejected_local

            source = "TOUR_API"
            error = None
            picked, rejected = None, None
            # 표기 변형 x 콘텐츠타입(관광지 -> 문화시설 -> 전체) 순으로 넓혀 간다.
            for variant in keyword_variants(keyword):
                for type_id in ("12", "14", None):
                    hits, error = tour_search(tour_key, variant, type_id)
                    calls["tour"] += 1
                    time.sleep(0.15)
                    picked, rejected = pick_valid(hits)
                    if picked:
                        break
                if picked:
                    break

            # 관광API 가 결과를 주긴 했으나 전부 검증 탈락한 경우에도 카카오로 넘어가야 한다.
            # '이화장' 은 관광API 가 '한국자이화장품' 을 돌려주는데, 0건이 아니라는 이유로
            # 폴백을 건너뛰면 카카오에 있는 진짜 이화장을 영영 못 찾는다.
            if not picked and kakao_key:
                kakao_hits, kakao_error = kakao_search(kakao_key, keyword)
                calls["kakao"] += 1
                time.sleep(0.15)
                kakao_picked, kakao_rejected = pick_valid(kakao_hits)
                if kakao_picked:
                    picked, source, error = kakao_picked, "KAKAO", None
                else:
                    rejected = kakao_rejected or rejected
                    if kakao_error:
                        error = (error or "") + "/" + kakao_error

            if picked:
                picked["source"] = source
                picked["relation_type"] = cand["relationType"]
                picked["reason"] = cand["reason"]
                accepted.append(picked)
                traces.append(f"    OK  {keyword} -> [{source}] {picked['name']} ({picked['address']})")
            elif rejected:
                hit, why = rejected
                traces.append(f"    XX  {keyword}: {why}로 거부 — "
                              f"'{hit['name']}' ({hit['address']})")
            else:
                traces.append(f"    --  {keyword}: 검증 실패 {error or '(결과 없음)'}")

        results.append({"content_id": work["content_id"], "title": work["title"],
                        "anchors": accepted})
        mark = f"{len(accepted)}곳" if accepted else "0곳"
        print(f"[{mark:>3}] {work['title']}", flush=True)
        for line in traces:
            print(line, flush=True)

    total = len(results)
    got = sum(1 for r in results if r["anchors"])
    two_plus = sum(1 for r in results if len(r["anchors"]) >= 2)
    by_source = {"TOUR_API": 0, "KAKAO": 0}
    for r in results:
        for a in r["anchors"]:
            by_source[a["source"]] += 1

    print("\n" + "=" * 60)
    print(f"대상 {total}편")
    print(f"  앵커 1곳 이상 확보 : {got}편 ({got * 100 // total}%)")
    print(f"  앵커 2곳 이상      : {two_plus}편")
    print(f"  여전히 0곳         : {total - got}편")
    print(f"앵커 출처 — TourAPI {by_source['TOUR_API']}건 / 카카오 {by_source['KAKAO']}건")
    print(f"호출 — TourAPI {calls['tour']}회 / 카카오 {calls['kakao']}회")

    (OUT / "discovery-dryrun.json").write_text(
        json.dumps({"results": results, "calls": calls}, ensure_ascii=False, indent=2),
        encoding="utf-8")
    print(f"저장: {OUT / 'discovery-dryrun.json'}")


if __name__ == "__main__":
    main()

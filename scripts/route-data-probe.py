"""Read-only live-data inventory. No content-detail GETs that can trigger collection."""
import concurrent.futures
import json
from pathlib import Path
import urllib.request

BASE = "https://api.itda-travel.com"
OUT = Path(__file__).resolve().parents[1] / "build" / "route-probe"

def get(path):
    try:
        request = urllib.request.Request(BASE + path, headers={"User-Agent": "ItdaRouteProbe/1.0", "Accept": "application/json"})
        with urllib.request.urlopen(request, timeout=20) as response:
            return json.load(response)
    except Exception as exc:
        return {"probe_error": type(exc).__name__, "status": getattr(exc,"code",None), "path": path}

def main():
    OUT.mkdir(parents=True, exist_ok=True)
    contents = []
    for page in range(5):
        data = get(f"/api/contents?page={page}&limit=100")
        if "probe_error" in data:
            raise RuntimeError(data)
        contents.extend(data["data"])
        if len(contents) >= data["total"]:
            break
    persons = get("/api/explore/persons")
    if not isinstance(persons, list):
        raise RuntimeError(persons)
    def person_links(p):
        pid = p["person_id"]
        return {"person": p, "contents": get(f"/api/explore/persons/{pid}/contents"),
                "places": get(f"/api/explore/persons/{pid}/places")}
    with concurrent.futures.ThreadPoolExecutor(max_workers=4) as pool:
        links = list(pool.map(person_links, persons))
    result = {"contents": contents, "persons": links}
    (OUT / "inventory.json").write_text(json.dumps(result, ensure_ascii=False, indent=2), encoding="utf-8")
    visible_ids = {c["content_id"] for c in contents}
    coverage = {}
    for row in links:
        if not isinstance(row["contents"], list) or not isinstance(row["places"], list):
            continue
        for c in row["contents"]:
            if c["contentId"] in visible_ids:
                entry = coverage.setdefault(c["contentId"], {"title": c["title"], "persons": [], "places": {}})
                entry["persons"].append(row["person"]["name"])
                for place in row["places"]:
                    entry["places"][place["place_id"]] = place["name"]
    print(json.dumps({"visible":len(contents), "persons":len(persons), "linked_contents":len(coverage),
        "with_place_candidates":sum(bool(c["places"]) for c in coverage.values()),
        "errors":sum(not isinstance(r[k],list) for r in links for k in ("contents","places")),
        "coverage":coverage},ensure_ascii=False))

if __name__ == "__main__":
    main()

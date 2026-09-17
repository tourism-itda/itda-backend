"""Bounded GET-only Kakao experiment. Key is read from stdin, never written.

Input build/route-probe/samples.json: [{title,content_id,anchor:{name,x,y}}].
Output is an engineering sample, not an approved historical relation or published route.
"""
import itertools
import json
import math
import sys
import urllib.parse
import urllib.request
from collections import Counter
from pathlib import Path

OUT = Path(__file__).resolve().parents[1] / "build" / "route-probe"
LIMITS = {"keyword": 10, "category": 50, "WALK": 30, "CAR": 30}
counts = Counter()
cache = {}
key = ""

def api(kind, path, params):
    url = path + "?" + urllib.parse.urlencode(params)
    if url in cache:
        return cache[url]
    if counts[kind] >= LIMITS[kind]:
        raise RuntimeError("Call budget exceeded: " + kind)
    counts[kind] += 1
    req = urllib.request.Request(url, headers={"Authorization": "KakaoAK " + key,
        "User-Agent": "ItdaRouteProbe/1.0", "Accept":"application/json"})
    try:
        with urllib.request.urlopen(req, timeout=15) as response:
            data = json.load(response)
    except Exception as exc:
        data = {"probe_error": type(exc).__name__, "http_status":getattr(exc,"code",None)}
    cache[url] = data
    return data

def distance(a, b):
    x1,y1,x2,y2 = map(math.radians,(float(a['x']),float(a['y']),float(b['x']),float(b['y'])))
    h = math.sin((y2-y1)/2)**2+math.cos(y1)*math.cos(y2)*math.sin((x2-x1)/2)**2
    return 6371000*2*math.asin(min(1,math.sqrt(h)))

def category(center, code, radius):
    return api("category", "https://dapi.kakao.com/v2/local/search/category.json",
        {"category_group_code":code,"x":center['x'],"y":center['y'],"radius":int(radius),"sort":"distance","size":15})

def route(mode,a,b):
    if mode == 'WALK':
        data = api(mode,"https://dapi.kakao.com/v2/routing/walk",
            {"start_x":a['x'],"start_y":a['y'],"end_x":b['x'],"end_y":b['y']})
        p = data.get('route',{}).get('properties',{})
        return {"status":data.get('status',data.get('probe_error')),"distance_m":p.get('totalDistance'),"duration_s":p.get('totalTime')}
    data = api(mode,"https://apis-navi.kakaomobility.com/v1/directions",
        {"origin":f"{a['x']},{a['y']}","destination":f"{b['x']},{b['y']}","summary":"true"})
    r = (data.get('routes') or [{}])[0]
    p = r.get('summary',{})
    return {"status":"OK" if r.get('result_code') == 0 else r.get('result_msg',data.get('probe_error')),
        "distance_m":p.get('distance'),"duration_s":p.get('duration')}

def name(p):
    return p.get('place_name',p.get('name',''))

def run_sample(sample,mode):
    before = counts.copy()
    anchor = sample['anchor']
    response = {"documents": sample['general_places']} if 'general_places' in sample else category(anchor,'AT4',2000 if mode=='WALK' else 10000)
    if '--audit-candidates' in sys.argv:
        return {"title":sample['title'],"mode":mode,"documents":response.get('documents',[]),"calls":dict(counts-before)}
    candidates = []
    for p in response.get('documents',[]):
        # Skip subfeatures sharing the anchor's name and near-identical entrances.
        if name(anchor) in name(p) or distance(anchor,p)<250:
            continue
        if any(distance(p,q)<250 for q in candidates):
            continue
        candidates.append(p)
    combinations=[]
    for a,b in itertools.combinations(candidates[:10],2):
        if distance(a,b)<250:
            continue
        for order in itertools.permutations([anchor,a,b]):
            lengths=[distance(order[i],order[i+1]) for i in range(2)]
            if max(lengths) > (1500 if mode=='WALK' else 25000) or sum(lengths)>(5000 if mode=='WALK' else 40000):
                continue
            combinations.append((sum(lengths),order))
    combinations.sort(key=lambda item:item[0])
    best=None
    attempts=0
    # A short list, not all-pairs route API calls. Each mode gets <= 6 leg lookups.
    for _,order in combinations[:3]:
        attempts+=1
        legs=[route(mode,order[i],order[i+1]) for i in range(2)]
        if any(r['status']!='OK' or r['distance_m'] is None or r['duration_s'] is None for r in legs):
            continue
        valid=(max(r['distance_m'] for r in legs)<=1500 and sum(r['distance_m'] for r in legs)<=5000) if mode=='WALK' else (max(r['duration_s'] for r in legs)<=2400 and sum(r['duration_s'] for r in legs)<=4800)
        best={"places":[{"name":name(p),"x":p['x'],"y":p['y'],"role":"RELATED_CANDIDATE" if p is anchor else "GENERAL_CANDIDATE"} for p in order],
            "legs":legs,"travel_distance_m":sum(r['distance_m'] for r in legs),"travel_duration_s":sum(r['duration_s'] for r in legs),"within_limits":valid}
        if valid:
            break
    result={"title":sample['title'],"content_id":sample['content_id'],"mode":mode,
        "anchor":name(anchor),"general_candidates":len(candidates),"category_error":response.get('probe_error'),
        "combination_attempts":attempts,"route":best}
    if best and best['within_limits']:
        allowance=300 if mode=='WALK' else 3000
        ellipses=[]
        for i in range(2):
            a,b=best['places'][i:i+2]
            direct=distance(a,b)
            center={"x":(float(a['x'])+float(b['x']))/2,"y":(float(a['y'])+float(b['y']))/2}
            groups={}
            for code in ['FD6','CE7']:
                data=category(center,code,min(20000,max(500,math.ceil((direct+allowance)/2))))
                inside=[p for p in data.get('documents',[]) if distance(a,p)+distance(p,b)-direct<=allowance]
                groups[code]={"first_page_count":len(data.get('documents',[])),"ellipse_count":len(inside),
                    "examples":[name(p) for p in inside[:3]],"error":data.get('probe_error')}
            ellipses.append({"from":name(a),"to":name(b),"allowance_m":allowance,"groups":groups})
        result['ellipses']=ellipses
    result['calls'] = dict(counts-before)
    return result

def main():
    global key
    sys.stdout.reconfigure(encoding='utf-8')
    key=sys.stdin.readline().strip()
    if not key:
        raise RuntimeError('REST key required on stdin')
    samples=json.loads((OUT/'samples.json').read_text(encoding='utf-8-sig'))
    if len(samples)>3:
        raise RuntimeError('At most three samples per run')
    results=[]
    for sample in samples:
        if sample.get('anchor_query'):
            def search(query):
                data=api('keyword','https://dapi.kakao.com/v2/local/search/keyword.json',{'query':query,'size':15,
                    'x':sample['anchor']['x'],'y':sample['anchor']['y'],'radius':20000})
                docs=data.get('documents',[])
                eligible=[p for p in docs if not any(t in p.get('category_name','') for t in ['주차장','관리,운영','음식점','카페'])
                    and distance(sample['anchor'],p)<20000]
                if not eligible:
                    print(json.dumps({'unresolved_query':query,'candidates':docs},ensure_ascii=False),flush=True)
                    raise RuntimeError('No eligible place result for '+query)
                return eligible[0]
            original=sample['anchor']
            sample['anchor']=search(sample['anchor_query'])
            sample['general_places']=[search(q) for q in sample['general_queries']]
            print(json.dumps({'resolved_title':sample['title'],'seed_coordinate_offset_m':round(distance(original,sample['anchor'])),
                'resolved_places':[{'name':name(p),'category':p.get('category_name'),'address':p.get('address_name'),'x':p['x'],'y':p['y']} for p in [sample['anchor']]+sample['general_places']]},ensure_ascii=False),flush=True)
            (OUT/'resolved-samples.json').write_text(json.dumps(samples,ensure_ascii=False,indent=2),encoding='utf-8')
        for mode in ['WALK','CAR']:
            results.append(run_sample(sample,mode))
            print(json.dumps(results[-1],ensure_ascii=False),flush=True)
            target='candidate-audit.json' if '--audit-candidates' in sys.argv else 'sample-results.json'
            (OUT/target).write_text(json.dumps({"results":results,"calls":dict(counts)},ensure_ascii=False,indent=2),encoding='utf-8')
    print(json.dumps({"total_calls":dict(counts)},ensure_ascii=False))

if __name__=='__main__':
    main()

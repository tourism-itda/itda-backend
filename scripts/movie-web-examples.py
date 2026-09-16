"""Two web-researched route examples; GET only, API key from stdin."""
import json
import sys
from pathlib import Path
import importlib.util

spec=importlib.util.spec_from_file_location('probe',Path(__file__).with_name('route-sample-probe.py'))
probe=importlib.util.module_from_spec(spec)
spec.loader.exec_module(probe)

def main():
    sys.stdout.reconfigure(encoding='utf-8')
    probe.key=sys.stdin.readline().strip()
    if not probe.key:
        raise RuntimeError('REST key required on stdin')
    plans=[
        {'title':'사도','content_id':315439,'region':'화성','queries':[
            ('화성 융건릉','융','HISTORICAL'),('화성 용주사','용주사','HISTORICAL'),('동탄호수공원','동탄호수공원','GENERAL')]},
        {'title':'천문: 하늘에 묻는다','content_id':569267,'region':'아산','queries':[
            ('아산장영실과학관','장영실과학관','EXHIBIT'),('외암민속마을','외암','GENERAL'),('아산 신정호','신정','GENERAL')]}
    ]
    output=[]
    outdir=Path(__file__).resolve().parents[1]/'build'/'movie-web-examples'
    outdir.mkdir(parents=True,exist_ok=True)
    for plan in plans:
        places=[]
        for query,match,role in plan['queries']:
            data=probe.api('keyword','https://dapi.kakao.com/v2/local/search/keyword.json',{'query':query,'size':15})
            docs=data.get('documents',[])
            allowed=[p for p in docs if match in p['place_name'] and plan['region'] in p.get('address_name','')
                     and any(t in p.get('category_name','') for t in ['관광,명소','공원','문화시설','절,사찰','정보화,체험마을'])
                     and not any(t in p.get('category_name','') for t in ['주차장','음식점','카페','관리,운영','교통,수송'])
                     and not any(t in p['place_name'] for t in ['민속관','피크닉장','주차장','화장실','매표소','대여소'])]
            if not allowed:
                print(json.dumps({'failed_query':query,'documents':docs},ensure_ascii=False),flush=True)
                raise RuntimeError('No unambiguous tourist place: '+query)
            if match == '외암':
                allowed=[p for p in allowed if p['place_name'].replace(' ','') in ['외암민속마을','아산외암마을','외암마을']]
                if not allowed:
                    print(json.dumps({'unresolved_main_place':query,'documents':docs},ensure_ascii=False),flush=True)
                    raise RuntimeError('Village main location required')
            place=allowed[0]
            place['role']=role
            places.append(place)
        result={'title':plan['title'],'content_id':plan['content_id'],'mode':'CAR','places':places,'segments':[]}
        for a,b in zip(places,places[1:]):
            car=probe.route('CAR',a,b)
            if car['status']!='OK':
                raise RuntimeError('Route failed')
            direct=probe.distance(a,b)
            center={'x':(float(a['x'])+float(b['x']))/2,'y':(float(a['y'])+float(b['y']))/2}
            segment={'from':a['place_name'],'to':b['place_name'],'car':car,'allowance_m':3000,'nearby':{}}
            for category in ['FD6','CE7']:
                response=probe.category(center,category,min(20000,max(500,int((direct+3000)/2)+1)))
                inside=[p for p in response.get('documents',[]) if probe.distance(a,p)+probe.distance(p,b)-direct<=3000]
                segment['nearby'][category]={'count_in_first_page':len(inside),'examples':[p['place_name'] for p in inside[:3]],'error':response.get('probe_error')}
            result['segments'].append(segment)
        result['total_distance_m']=sum(s['car']['distance_m'] for s in result['segments'])
        result['total_duration_s']=sum(s['car']['duration_s'] for s in result['segments'])
        output.append(result)
        print(json.dumps(result,ensure_ascii=False),flush=True)
        (outdir/'results.json').write_text(json.dumps({'examples':output,'calls':dict(probe.counts)},ensure_ascii=False,indent=2),encoding='utf-8')
    print(json.dumps({'calls':dict(probe.counts)}))

if __name__=='__main__':
    main()

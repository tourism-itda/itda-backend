# 콘텐츠 ↔ 장소 매핑(content_place) 시드 후보를 만드는 스크립트.
#
# docs/콘텐츠-장소_매핑_설계.md 3장(층 1 · 앵커 만들기)을 그대로 구현한 것이다.
# 설계 문서가 "🔴 남은 블로커 — 시드 데이터 없음"으로 남겨둔 5번 항목
# (콘텐츠 15~20편 앵커 채우기)을 반자동으로 처리한다.
#
# ── 왜 두 단계인가 ────────────────────────────────────────────────────────
# 설계 문서 3-4의 근거 등급 규칙상 **노출 기준은 B 이상이고 B는 사람 검수가 필수**다.
# 그래서 이 스크립트는 DB에 직접 쓰지 않는다. 후보 CSV를 뱉고(discover),
# 사람이 approve 열을 채운 뒤(검수), 승인된 행만 SQL로 바꾼다(build-sql).
# 자동으로 밀어넣으면 틀린 매핑이 그대로 사용자에게 노출된다.
#
# ── 사용법 ────────────────────────────────────────────────────────────────
#   # 1) 후보 뽑기 (관광API 호출). 처음엔 -MaxContents 로 호출량을 확인할 것.
#   .\seed-content-places.ps1 -Mode discover -ApiKey "발급키" -MaxContents 5 -OutCsv candidates.csv
#
#   # 2) candidates.csv 를 열어 approve 열에 Y 를 적는다 (사람 검수)
#
#   # 3) 승인된 행만 SQL 로
#   .\seed-content-places.ps1 -Mode build-sql -InCsv candidates.csv -OutSql seed.sql
#
# ── 검색어를 어디서 얻는가 ────────────────────────────────────────────────
# 경로 A(작품 제목)는 콘텐츠 목록만으로 자동으로 돈다.
# 경로 B/C(인물명)는 content_person 이 채워져 있으면 자동이지만,
# **2026-09-09 기준 운영 DB 의 content_person 은 0건**이라 지금은 안 돈다.
# 그때까지는 -KeywordCsv 로 인물명·지명을 손으로 준다:
#
#   .\seed-content-places.ps1 -Mode discover -ApiKey "발급키" -KeywordCsv keywords.csv
#
# keywords.csv 는 content_id,keyword 두 열이면 되고, 앵커가 0건인 작품 목록은
# discover 가 <OutCsv>.keywords-template.csv 로 뽑아 준다.
#
# ── 주의 ──────────────────────────────────────────────────────────────────
# content_place 는 박세현 소유 엔티티라 컬럼을 늘리지 않았다(설계 문서 7장 "팀 합의 필요").
# 생성되는 SQL 은 기존 컬럼(content_id, place_id, recommend_order)만 채운다.
# 근거·등급은 CSV 에만 남으므로, 검수한 CSV 를 저장소에 함께 커밋해 감사 흔적을 남길 것.

[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [ValidateSet('discover', 'build-sql')]
    [string]$Mode,

    # discover 전용. 공공데이터포털 서비스키(인코딩/디코딩 어느 쪽이든 됨).
    [string]$ApiKey,

    # 콘텐츠·인물 목록을 읽어올 itda-backend. 로컬이면 http://localhost:8080
    [string]$ApiBase = 'https://api.itda-travel.com',

    [string]$OutCsv = 'seed-candidates.csv',
    [string]$InCsv,
    [string]$OutSql = 'seed-content-places.sql',

    # 경로 B/D 검색어를 손으로 주는 파일. 열: content_id, keyword (한 줄에 하나).
    # content_person 이 채워지면 인물명은 자동으로 잡히므로 그때는 지명만 남기면 된다.
    # 템플릿은 -Mode discover 가 <OutCsv>.keywords-template.csv 로 같이 뽑아 준다.
    [string]$KeywordCsv,

    # 콘텐츠 하나당 CSV 에 실을 **후보** 수. 저장할 앵커 수가 아니다.
    # 검수자가 고를 여지를 남겨야 하므로 넉넉히 뽑고, 실제 승인은 2~3건만 하면 된다
    # (설계 문서 2장 "작품당 1~3곳이면 충분").
    [int]$MaxCandidatesPerContent = 12,

    # 0 이면 전부. 처음 돌릴 때는 5 정도로 줄여 호출량을 확인하는 걸 권장한다.
    [int]$MaxContents = 0
)

$ErrorActionPreference = 'Stop'
$KorBase = 'https://apis.data.go.kr/B551011/KorService2'

# 설계 문서 3-3 하드필터: 32(숙박)·38(쇼핑) 제외, 12·14·15·25 허용.
$AllowedContentTypes = @('12', '14', '15', '25')

$ContentTypeLabel = @{
    '12' = '관광지'; '14' = '문화시설'; '15' = '축제공연행사'
    '25' = '여행코스'; '28' = '레포츠'; '32' = '숙박'; '38' = '쇼핑'; '39' = '음식점'
}

# ─────────────────────────────────────────────────────────────────────────
# 공통 유틸
# ─────────────────────────────────────────────────────────────────────────

function Write-Step([string]$Message) {
    Write-Host ('  ' + $Message) -ForegroundColor DarkGray
}

# Windows PowerShell 5.1 은 응답 헤더에 charset 이 없으면 본문을 ISO-8859-1 로 디코딩한다.
# 그대로 두면 한글이 전부 깨지고, 그 깨진 문자열이 그대로 관광API 검색어로 나간다.
# 반드시 원본 바이트를 UTF-8 로 직접 디코딩할 것.
function Get-ResponseText($Response) {
    return [System.Text.Encoding]::UTF8.GetString($Response.RawContentStream.ToArray())
}

function Get-Json([string]$Uri) {
    try {
        $resp = Invoke-WebRequest -Uri $Uri -UseBasicParsing -TimeoutSec 30
    }
    catch {
        Write-Host ('  [HTTP 실패] {0}' -f $_.Exception.Message) -ForegroundColor Red
        return $null
    }
    $text = Get-ResponseText $resp
    if ([string]::IsNullOrWhiteSpace($text)) { return $null }
    try { return $text | ConvertFrom-Json } catch { return $null }
}

# ─────────────────────────────────────────────────────────────────────────
# 관광API (KorService2)
# ─────────────────────────────────────────────────────────────────────────

$script:TourCallCount = 0

function Invoke-Tour {
    param([string]$Op, [hashtable]$Params)

    $qs = "serviceKey=$script:EncodedKey&MobileOS=ETC&MobileApp=itda&_type=json"
    foreach ($k in $Params.Keys) {
        $qs += ('&{0}={1}' -f $k, [System.Uri]::EscapeDataString([string]$Params[$k]))
    }

    $script:TourCallCount++
    try {
        $resp = Invoke-WebRequest -Uri ("$KorBase/$Op" + '?' + $qs) -UseBasicParsing -TimeoutSec 25
    }
    catch {
        $msg = $_.Exception.Message
        if ($msg -match '\(403\)') {
            # 공공데이터포털은 미등록·만료·오타 키를 전부 403 으로 돌려준다.
            Write-Host '  [403] 서비스키가 유효하지 않다. PUBLIC_DATA_API_KEY 를 확인할 것.' -ForegroundColor Red
        }
        else {
            Write-Host ('  [HTTP 실패] {0} {1}' -f $Op, $msg) -ForegroundColor Red
        }
        return @()
    }

    $text = Get-ResponseText $resp
    if (-not $text.TrimStart().StartsWith('{')) {
        # 키가 틀렸거나 일일 한도를 넘기면 JSON 이 아니라 XML 에러가 온다.
        $head = $text.Substring(0, [Math]::Min(300, $text.Length))
        Write-Host ('  [JSON 아님 - 키 오류 또는 한도 초과] {0}' -f $head) -ForegroundColor Red
        return @()
    }

    $json = $text | ConvertFrom-Json
    $header = $json.response.header
    if ($header.resultCode -ne '0000' -and $header.resultCode -ne '00') {
        Write-Host ('  [API 오류] {0} resultCode={1} {2}' -f $Op, $header.resultCode, $header.resultMsg) -ForegroundColor Red
        return @()
    }

    $items = $json.response.body.items
    # 결과가 0건이면 items 가 빈 문자열("")로 온다 — 객체가 아니다.
    if ($null -eq $items -or $items -is [string]) { return @() }

    $item = $items.item
    if ($null -eq $item) { return @() }
    # 1건이면 배열이 아니라 단일 객체로 온다.
    return @($item)
}

function Search-TourKeyword {
    param([string]$Keyword, [string]$ContentTypeId, [int]$Limit = 20)

    if ([string]::IsNullOrWhiteSpace($Keyword)) { return @() }

    $p = @{ keyword = $Keyword; numOfRows = $Limit; pageNo = 1 }
    if ($ContentTypeId) { $p['contentTypeId'] = $ContentTypeId }
    return Invoke-Tour -Op 'searchKeyword2' -Params $p
}

function Get-CourseStops {
    param([string]$CourseContentId)
    return Invoke-Tour -Op 'detailInfo2' -Params @{
        contentId = $CourseContentId; contentTypeId = '25'; numOfRows = 50; pageNo = 1
    }
}

# detailCommon2 는 contentId 하나만 받는다. 나머지를 넘기면 호출 자체가 실패한다
# (PlaceController 주석 / docs/tourism_api_guide.md 참고).
function Get-TourDetail {
    param([string]$ContentId)
    $items = Invoke-Tour -Op 'detailCommon2' -Params @{ contentId = $ContentId }
    if ($items.Count -eq 0) { return $null }
    return $items[0]
}

# ─────────────────────────────────────────────────────────────────────────
# 후보 수집 (discover)
# ─────────────────────────────────────────────────────────────────────────

# 제목이 걸린 장소 이름에 이런 말까지 박혀 있으면
# "작품 때문에 생긴 곳"이라는 근거가 관광API 데이터 안에서 닫힌다.
$FilmingWords = @('세트', '촬영장', '촬영지', '테마파크', '파크', '드라마', '영화')

<#
경로별 근거 등급 (설계 문서 3-4).

경로 A 를 무조건 A등급으로 주면 안 된다 — searchKeyword2 는 **제목 부분일치**라
「인천」처럼 제목이 그냥 지명인 작품은 이름만 겹치는 장소가 쏟아진다
(인천대공원·인천차이나타운이 A등급 앵커가 되는 식).

그렇다고 자동으로 C 로 떨어뜨릴 수도 없다. 「명량」→ 명량대첩해전사기념전시관 은
똑같은 제목 일치인데 이쪽은 진짜 관련 장소다. **둘을 문자열 규칙으로는 못 가른다.**

그래서 가르지 않는다. 판정을 사람에게 넘기는 게 B 등급의 존재 이유다:

  이름에 촬영지 관용어까지 있다  → A  (예: 「대장금」 → 용인 대장금파크. 자동 통과)
  그 외 제목 일치                → B  (사람이 보고 판단)

C 는 문서 정의상 "LLM 주장만, 교차검증 실패"라 이 스크립트에선 나오지 않는다 —
모든 후보가 searchKeyword2 로 실재가 확인된 것들이기 때문이다.

경로 B(인물명)가 A인 건 content_person 이라는 **큐레이션된 연결**이 전제일 때다.
사람이 -KeywordCsv 로 넣은 지명은 그 전제가 없으므로 경로 D(B등급)로 따로 센다.
#>
function Resolve-Grade {
    param([string]$Path, [string]$PlaceName, [string]$Keyword)

    if ($Path -like 'A-*') {
        foreach ($w in $FilmingWords) {
            if ($PlaceName -like "*$w*") { return 'A' }
        }
        return 'B'
    }
    if ($Path -like 'B-*') { return 'A' }   # content_person 기반
    if ($Path -like 'C-*') { return 'A' }   # 관광공사 큐레이션 코스
    return 'B'                              # 경로 D — 사람이 준 지명
}

<#
같은 B 등급 안에서의 정렬 순서.

전쟁·사극 콘텐츠에서는 **지명이 제목보다 훨씬 정확한 신호**다.
「인천」의 제목 일치(인천대공원)보다 지명 일치(인천상륙작전기념관)가 먼저 보여야
검수자가 위에서부터 훑다가 좋은 걸 만난다. 이 순서가 검수 품질을 결정한다.
#>
$PathRank = @{ 'C-여행코스' = 0; 'B-인물' = 1; 'D-지명' = 2; 'A-제목' = 3 }

function ConvertTo-Candidate {
    param($Item, [string]$Path, [string]$RelationType, [string]$Keyword)

    $Grade = Resolve-Grade -Path $Path -PlaceName ([string]$Item.title) -Keyword $Keyword
    $typeId = [string]$Item.contenttypeid
    $lat = 0.0; $lng = 0.0
    if ($Item.mapy) { [double]::TryParse([string]$Item.mapy, [ref]$lat) | Out-Null }
    if ($Item.mapx) { [double]::TryParse([string]$Item.mapx, [ref]$lng) | Out-Null }

    $addr = [string]$Item.addr1
    $region = ''
    if ($addr) { $region = ($addr -split '\s+')[0] }

    $category = $ContentTypeLabel[$typeId]
    if (-not $category) { $category = '기타' }

    # 설계 문서 3-3: 좌표가 없으면 동선 계산이 불가능해 탈락, firstimage 가 없으면
    # 탈락이 아니라 등급 강등(지방 사적지는 이미지가 자주 빈다).
    $note = ''
    if ($Grade -eq 'C') {
        # 설계 문서 3-4: C 는 "DB에 넣되 노출 안 함". 승인 기본값이 아님을 눈에 보이게 적어 둔다.
        $note = '이름만 겹침 — 작품 관련 근거 없음. 승인 전 반드시 확인'
    }
    if ($lat -eq 0.0 -or $lng -eq 0.0) {
        if ($note) { $note += ' / ' }
        $note += '좌표없음(detailCommon2 보강 필요)'
    }
    if (-not $Item.firstimage) {
        if ($Grade -eq 'A') { $Grade = 'B' }
        if ($note) { $note += ' / ' }
        $note += '대표이미지 없음'
    }

    return [pscustomobject]@{
        approve         = ''
        content_id      = ''
        content_title   = ''
        path            = $Path
        grade           = $Grade
        relation_type   = $RelationType
        keyword         = $Keyword
        external_id     = [string]$Item.contentid
        name            = [string]$Item.title
        content_type_id = $typeId
        category        = $category
        address         = $addr
        region          = $region
        latitude        = $lat
        longitude       = $lng
        first_image     = [string]$Item.firstimage
        recommend_order = 0
        note            = $note
    }
}

# 설계 문서 3-3: 200m 이내 중복 병합. 같은 궁궐의 여러 문이 별도 contentId 로 등록돼 있다.
function Test-TooClose {
    param($Candidate, $Existing)

    foreach ($e in $Existing) {
        if ($e.external_id -eq $Candidate.external_id) { return $true }
        if ($e.latitude -eq 0.0 -or $Candidate.latitude -eq 0.0) { continue }

        $dLat = ($e.latitude - $Candidate.latitude) * 111000.0
        $dLng = ($e.longitude - $Candidate.longitude) * 111000.0 * [Math]::Cos($e.latitude * [Math]::PI / 180.0)
        if ([Math]::Sqrt($dLat * $dLat + $dLng * $dLng) -lt 200.0) { return $true }
    }
    return $false
}

function Invoke-Discover {

    if (-not $ApiKey) { throw 'discover 모드에는 -ApiKey 가 필요하다.' }

    # 공공데이터포털은 인코딩/디코딩 두 형태로 키를 보여준다. 정확히 한 번만 인코딩되게 맞춘다.
    $key = $ApiKey
    if ($key.Contains('%')) { $key = [System.Uri]::UnescapeDataString($key) }
    $script:EncodedKey = [System.Uri]::EscapeDataString($key)

    Write-Host ''
    Write-Host '[1/3] 콘텐츠 · 검색어 수집' -ForegroundColor Cyan

    # 콘텐츠 목록이 기준이다. 경로 A(작품 제목)는 이것만으로 돌아간다.
    $contents = @{}
    $page = 0
    while ($true) {
        $resp = Get-Json ("{0}/api/contents?page={1}&limit=100" -f $ApiBase, $page)
        if ($null -eq $resp -or $null -eq $resp.data -or @($resp.data).Count -eq 0) { break }
        foreach ($c in @($resp.data)) {
            $cid = [string]$c.content_id
            if ($contents.ContainsKey($cid)) { continue }
            # 검색어 출처를 나눠 담는다. 출처가 곧 근거 등급이라 섞으면 등급을 못 매긴다.
            $contents[$cid] = [pscustomobject]@{
                ContentId   = $cid
                Title       = [string]$c.title
                PersonNames = New-Object System.Collections.ArrayList   # content_person → 경로 B/C
                Manual      = New-Object System.Collections.ArrayList   # -KeywordCsv  → 경로 D
            }
        }
        $page++
        if ($page -gt 50) { break }   # 안전장치
    }
    Write-Step ('콘텐츠 {0}편' -f $contents.Count)

    if ($contents.Count -eq 0) { throw "콘텐츠 목록을 가져오지 못했다: $ApiBase" }

    # 경로 B/C 의 검색어 출처 ①: content_person (있으면 자동).
    # 2026-09-09 기준 운영 DB 의 content_person 은 비어 있어 0건이 정상이다.
    $linkedCount = 0
    $persons = Get-Json "$ApiBase/api/explore/persons"
    foreach ($p in @($persons)) {
        $linked = Get-Json ("$ApiBase/api/explore/persons/{0}/contents" -f $p.person_id)
        foreach ($c in @($linked)) {
            $cid = [string]$c.content_id
            if (-not $contents.ContainsKey($cid)) { continue }
            [void]$contents[$cid].PersonNames.Add([string]$p.name)
            $linkedCount++
        }
    }
    Write-Step ('인물 {0}명 / content_person 연결 {1}건' -f @($persons).Count, $linkedCount)

    # 경로 B/D 의 검색어 출처 ②: 사람이 적어 준 키워드 CSV (인물명·지명).
    if ($KeywordCsv) {
        if (-not (Test-Path $KeywordCsv)) { throw "키워드 파일이 없다: $KeywordCsv" }
        $manual = 0
        foreach ($row in @(Import-Csv -Path $KeywordCsv -Encoding UTF8)) {
            $cid = [string]$row.content_id
            if (-not $contents.ContainsKey($cid)) { continue }
            if ([string]::IsNullOrWhiteSpace($row.keyword)) { continue }
            [void]$contents[$cid].Manual.Add($row.keyword.Trim())
            $manual++
        }
        Write-Step ('수동 키워드 {0}건 ({1})' -f $manual, $KeywordCsv)
    }
    else {
        Write-Step '수동 키워드 없음 — 경로 A(작품 제목)만 돈다. -KeywordCsv 로 인물·지명을 주면 경로 B/C/D 도 돈다.'
    }

    $targets = @($contents.Values | Sort-Object ContentId)
    if ($MaxContents -gt 0 -and $targets.Count -gt $MaxContents) {
        $targets = $targets[0..($MaxContents - 1)]
    }
    Write-Step ('탐색 대상 {0}편' -f $targets.Count)

    Write-Host ''
    Write-Host '[2/3] 앵커 후보 탐색 (설계 문서 3-1 경로 A~C)' -ForegroundColor Cyan

    $rows = New-Object System.Collections.ArrayList

    foreach ($t in $targets) {
        Write-Host ('  · {0} ({1})' -f $t.Title, $t.ContentId) -ForegroundColor White

        $accepted = New-Object System.Collections.ArrayList
        $persons = @($t.PersonNames | Select-Object -Unique)
        $manuals = @($t.Manual | Select-Object -Unique)

        # 경로 A — 작품 제목 직접. 세트장·테마파크는 이름에 작품명이 박혀 있다.
        # 등급은 Resolve-Grade 가 이름을 보고 매긴다 (제목이 흔한 낱말이면 C).
        $found = @()
        $found += Search-TourKeyword -Keyword $t.Title -Limit 20 |
            ForEach-Object { ConvertTo-Candidate $_ 'A-제목' 'FILMING' $t.Title }

        # 경로 B — 인물명. 사극의 주 경로. 축제·기념관·여행코스 제목에 인물명이 들어간다.
        foreach ($n in $persons) {
            $found += Search-TourKeyword -Keyword $n -Limit 20 |
                ForEach-Object { ConvertTo-Candidate $_ 'B-인물' 'HISTORICAL' $n }
        }

        # 경로 D — 사람이 준 사건·지명. 「인천」→ 인천상륙작전기념관·월미도 처럼
        # **제목보다 지명이 훨씬 정확한 신호**인 경우가 많다 (전쟁·사극 콘텐츠에서 특히).
        foreach ($n in $manuals) {
            $found += Search-TourKeyword -Keyword $n -Limit 20 |
                ForEach-Object { ConvertTo-Candidate $_ 'D-지명' 'HISTORICAL' $n }
        }

        # 경로 C — 여행코스(25). 관광공사가 이미 큐레이션해 둔 코스라 품질이 가장 높다.
        # 인물명뿐 아니라 지명으로도 잘 잡힌다("남한산성" 등).
        foreach ($n in ($persons + $manuals)) {
            $courses = Search-TourKeyword -Keyword $n -ContentTypeId '25' -Limit 5
            foreach ($course in $courses) {
                foreach ($stop in (Get-CourseStops -CourseContentId ([string]$course.contentid))) {
                    $sub = [string]$stop.subcontentid
                    if (-not $sub) { continue }

                    # 코스 구성 장소는 이름만 온다. 좌표·주소는 detailCommon2 로 채운다.
                    $detail = Get-TourDetail -ContentId $sub
                    if ($null -eq $detail) { continue }

                    $cand = ConvertTo-Candidate $detail 'C-여행코스' 'HISTORICAL' ('{0} / {1}' -f $n, $course.title)
                    if (-not $cand.name) { $cand.name = [string]$stop.subname }
                    $found += $cand
                }
            }
        }

        # 하드필터 + 중복 병합 후, 근거가 강한 순으로 정렬해 CSV 에 싣는다.
        # **여기서 2~3건으로 자르지 않는다** — 자르면 검수자가 고를 여지가 없어지고,
        # 잘린 자리에 하필 쓰레기만 남으면 그 작품은 통째로 앵커 0건이 된다.
        $gradeRank = @{ 'A' = 0; 'B' = 1; 'C' = 2 }
        $found = @($found | Sort-Object `
            @{ Expression = { $gradeRank[$_.grade] } }, `
            @{ Expression = { $PathRank[$_.path] } })

        foreach ($c in $found) {
            if ($accepted.Count -ge $MaxCandidatesPerContent) { break }
            if ($AllowedContentTypes -notcontains $c.content_type_id) { continue }
            if (Test-TooClose -Candidate $c -Existing $accepted) { continue }

            $c.content_id = $t.ContentId
            $c.content_title = $t.Title
            # 제안 순번일 뿐이다. 최종 순번은 build-sql 이 승인된 행만 보고 다시 매긴다.
            $c.recommend_order = $accepted.Count + 1
            [void]$accepted.Add($c)
        }

        if ($accepted.Count -eq 0) {
            # 설계 문서 5장 "앵커가 0곳인 작품" — 실패도 기록해야 다음에 뭘 보강할지 안다.
            Write-Host '    후보 0건 — 경로 D(사건·지명) 수동 보강 대상' -ForegroundColor Yellow
            [void]$rows.Add([pscustomobject]@{
                approve = ''; content_id = $t.ContentId; content_title = $t.Title
                path = '없음'; grade = ''; relation_type = ''; keyword = (($persons + $manuals) -join ';')
                external_id = ''; name = ''; content_type_id = ''; category = ''
                address = ''; region = ''; latitude = 0; longitude = 0; first_image = ''
                recommend_order = 0; note = '후보 0건 — 경로 D(지명) 수동 입력 필요'
            })
        }
        else {
            $byGrade = $accepted | Group-Object grade | Sort-Object Name |
                ForEach-Object { '{0}:{1}' -f $_.Name, $_.Count }
            Write-Host ('    후보 {0}건 ({1})' -f $accepted.Count, ($byGrade -join ' ')) -ForegroundColor Green
            foreach ($a in $accepted) { [void]$rows.Add($a) }
        }
    }

    Write-Host ''
    Write-Host '[3/3] 후보 CSV 저장' -ForegroundColor Cyan
    $rows | Export-Csv -Path $OutCsv -NoTypeInformation -Encoding UTF8
    Write-Step ('{0} — {1}행 / 관광API 호출 {2}회' -f $OutCsv, $rows.Count, $script:TourCallCount)

    # 앵커가 0건인 작품은 경로 D(사건·지명)를 사람이 넣어 줘야 한다. 그 입력 파일을 미리 깎아 둔다.
    $blank = @($rows | Where-Object { $_.path -eq '없음' })
    if ($blank.Count -gt 0) {
        $tmplPath = $OutCsv + '.keywords-template.csv'
        $blank |
            Select-Object @{n = 'content_id'; e = { $_.content_id }},
                          @{n = 'content_title'; e = { $_.content_title }},
                          @{n = 'keyword'; e = { '' }} |
            Export-Csv -Path $tmplPath -NoTypeInformation -Encoding UTF8
        Write-Step ('앵커 0건 {0}편 → 키워드 템플릿: {1}' -f $blank.Count, $tmplPath)
    }

    Write-Host ''
    Write-Host '다음 단계: CSV 의 approve 열에 Y 를 적어 검수한 뒤' -ForegroundColor Yellow
    Write-Host ("  .\seed-content-places.ps1 -Mode build-sql -InCsv $OutCsv") -ForegroundColor Yellow
}

# ─────────────────────────────────────────────────────────────────────────
# SQL 생성 (build-sql)
# ─────────────────────────────────────────────────────────────────────────

function ConvertTo-SqlLiteral([string]$Value) {
    if ([string]::IsNullOrEmpty($Value)) { return 'NULL' }
    return "'" + $Value.Replace("'", "''") + "'"
}

function Invoke-BuildSql {

    if (-not $InCsv) { throw 'build-sql 모드에는 -InCsv 가 필요하다.' }
    if (-not (Test-Path $InCsv)) { throw "파일이 없다: $InCsv" }

    $all = @(Import-Csv -Path $InCsv -Encoding UTF8)
    $approved = @($all | Where-Object { $_.approve -match '^\s*[YyOo]' -and $_.external_id })

    Write-Host ''
    Write-Host ('검수 결과: 전체 {0}행 중 승인 {1}행' -f $all.Count, $approved.Count) -ForegroundColor Cyan

    if ($approved.Count -eq 0) {
        Write-Host 'approve 열에 Y 가 적힌 행이 없다. 검수 먼저.' -ForegroundColor Yellow
        return
    }

    # 좌표가 없으면 DetourFilter/SpotScorer 가 거리 계산을 못 해 루트에서 쓸 수 없다.
    $noCoords = @($approved | Where-Object { [double]$_.latitude -eq 0 -or [double]$_.longitude -eq 0 })
    if ($noCoords.Count -gt 0) {
        Write-Host ('  좌표 없는 승인 행 {0}건은 건너뛴다:' -f $noCoords.Count) -ForegroundColor Yellow
        foreach ($n in $noCoords) { Write-Host ('    - {0}' -f $n.name) -ForegroundColor Yellow }
        $approved = @($approved | Where-Object { [double]$_.latitude -ne 0 -and [double]$_.longitude -ne 0 })
    }

    # 설계 문서 3-4: "노출 기준은 B 이상". C 를 승인했다면 의도한 것인지 되묻는다.
    $cGrade = @($approved | Where-Object { $_.grade -eq 'C' })
    if ($cGrade.Count -gt 0) {
        Write-Host ('  ⚠ C등급 {0}건이 승인돼 있다 (노출 기준은 B 이상):' -f $cGrade.Count) -ForegroundColor Yellow
        foreach ($c in $cGrade) { Write-Host ('    - {0} ← {1}' -f $c.name, $c.content_title) -ForegroundColor Yellow }
    }

    # recommend_order 는 CSV 값을 믿지 않고 콘텐츠별로 다시 매긴다.
    # 검수자가 중간 행을 건너뛰고 승인하면 순번에 구멍이 나기 때문이다(1,4,7 → 1,2,3).
    foreach ($g in ($approved | Group-Object content_id)) {
        $i = 1
        foreach ($row in $g.Group) {
            $row.recommend_order = $i
            $i++
        }
    }

    $sb = New-Object System.Text.StringBuilder
    $null = $sb.AppendLine('-- 콘텐츠 ↔ 장소 매핑 시드')
    $null = $sb.AppendLine(('-- 생성: {0} / 출처: {1} (사람 검수 통과분만)' -f (Get-Date -Format 'yyyy-MM-dd HH:mm'), $InCsv))
    $null = $sb.AppendLine('--')
    $null = $sb.AppendLine('-- place 는 (source, external_id) 로 중복을 막고, content_place 는 PK 로 막는다.')
    $null = $sb.AppendLine('-- 여러 번 실행해도 같은 결과가 되도록 전부 NOT EXISTS 가드를 걸었다.')
    $null = $sb.AppendLine('')
    $null = $sb.AppendLine('BEGIN;')
    $null = $sb.AppendLine('')

    foreach ($r in $approved) {
        $null = $sb.AppendLine(('-- [{0}/{1}] {2} → {3}  (근거 {4}등급, 키워드: {5})' -f `
            $r.content_id, $r.content_title, $r.path, $r.name, $r.grade, $r.keyword))

        $null = $sb.AppendLine(@"
INSERT INTO place (name, category, description, latitude, longitude, address, region,
                   place_type, source, external_id, night_open)
SELECT $(ConvertTo-SqlLiteral $r.name), $(ConvertTo-SqlLiteral $r.category), NULL,
       $($r.latitude), $($r.longitude), $(ConvertTo-SqlLiteral $r.address), $(ConvertTo-SqlLiteral $r.region),
       'SPOT', 'TOUR_API', $(ConvertTo-SqlLiteral $r.external_id), false
WHERE NOT EXISTS (
    SELECT 1 FROM place WHERE source = 'TOUR_API' AND external_id = $(ConvertTo-SqlLiteral $r.external_id)
);
"@)

        $null = $sb.AppendLine(@"
INSERT INTO content_place (content_id, place_id, recommend_order)
SELECT $($r.content_id), p.place_id, $($r.recommend_order)
FROM place p
WHERE p.source = 'TOUR_API' AND p.external_id = $(ConvertTo-SqlLiteral $r.external_id)
  AND EXISTS (SELECT 1 FROM content c WHERE c.content_id = $($r.content_id))
  AND NOT EXISTS (
      SELECT 1 FROM content_place cp WHERE cp.content_id = $($r.content_id) AND cp.place_id = p.place_id
  );
"@)
        $null = $sb.AppendLine('')
    }

    $null = $sb.AppendLine('COMMIT;')

    Set-Content -Path $OutSql -Value $sb.ToString() -Encoding UTF8
    Write-Host ('SQL 생성: {0} ({1}건)' -f $OutSql, $approved.Count) -ForegroundColor Green
    Write-Host ''
    Write-Host '적용 예:' -ForegroundColor Yellow
    Write-Host ("  psql `"$env:DATABASE_URL`" -f $OutSql") -ForegroundColor Yellow
}

# ─────────────────────────────────────────────────────────────────────────

switch ($Mode) {
    'discover'  { Invoke-Discover }
    'build-sql' { Invoke-BuildSql }
}

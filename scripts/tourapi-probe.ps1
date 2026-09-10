# TourAPI(KorService2) 키워드 검색이 실제로 어떻게 동작하는지 확인하는 탐침 스크립트.
#
# 사용법:
#   .\tourapi-probe.ps1 -ApiKey "발급받은키"
#
# 확인하려는 것:
#   1. searchKeyword2 가 title 부분일치인가 (문서 예시로는 그렇게 보임)
#   2. "세트장" / "촬영지" 관용어로 전국 촬영지를 훑을 수 있는가  <- 핵심
#   3. 작품 제목("대장금")으로 바로 검색되는가
#   4. 인물명("정조" / "이순신")으로 검색되는가                    <- 아마 안 될 것
#   5. detailCommon2 의 overview 에 작품 언급이 실제로 들어있는가
#
# API 호출은 총 10회 내외라 일일 한도에 부담이 없다.

param(
    [Parameter(Mandatory = $true)][string]$ApiKey
)

$ErrorActionPreference = 'Continue'
$base = 'https://apis.data.go.kr/B551011/KorService2'
$tarBase = 'https://apis.data.go.kr/B551011/TarRlteTarService1'

# 공공데이터포털은 인코딩/디코딩 두 형태로 키를 보여준다. 정확히 한 번만 인코딩되게 맞춘다.
$key = $ApiKey
if ($key.Contains('%')) { $key = [System.Uri]::UnescapeDataString($key) }
$key = [System.Uri]::EscapeDataString($key)

$script:CallCount = 0

function Invoke-Tour {
    param([string]$Op, [hashtable]$Params, [string]$BaseUrl = $base)

    $qs = "serviceKey=$key&MobileOS=ETC&MobileApp=itda&_type=json"
    foreach ($k in $Params.Keys) {
        $qs += ('&{0}={1}' -f $k, [System.Uri]::EscapeDataString([string]$Params[$k]))
    }

    $script:CallCount++
    try {
        $resp = Invoke-WebRequest -Uri ("$BaseUrl/$Op" + '?' + $qs) -UseBasicParsing -TimeoutSec 20
    }
    catch {
        Write-Host ('  [HTTP 실패] {0}' -f $_.Exception.Message) -ForegroundColor Red
        return $null
    }

    $text = $resp.Content
    if (-not $text.TrimStart().StartsWith('{')) {
        # 키가 틀렸거나 트래픽 초과면 XML 에러가 온다.
        $head = $text.Substring(0, [Math]::Min(300, $text.Length))
        Write-Host ('  [JSON 아님 - 키 오류 또는 한도 초과] {0}' -f $head) -ForegroundColor Red
        return $null
    }

    $json = $text | ConvertFrom-Json
    $header = $json.response.header
    if ($header.resultCode -ne '0000' -and $header.resultCode -ne '00') {
        Write-Host ('  [API 오류] resultCode={0} resultMsg={1}' -f $header.resultCode, $header.resultMsg) -ForegroundColor Red
        return $null
    }
    return $json.response.body
}

function Get-Items {
    param($Body)
    if ($null -eq $Body) { return @() }
    $items = $Body.items.item
    if ($null -eq $items) { return @() }
    if ($items -isnot [array]) { return @($items) }
    return $items
}

function Test-Keyword {
    param(
        [string]$Keyword,
        [string]$ContentTypeId = '',
        [int]$Show = 10
    )

    Write-Host ''
    Write-Host ('=== searchKeyword2  keyword="{0}"  contentTypeId="{1}" ===' -f $Keyword, $ContentTypeId) -ForegroundColor Cyan

    $p = @{ keyword = $Keyword; numOfRows = 30; pageNo = 1 }
    if ($ContentTypeId -ne '') { $p['contentTypeId'] = $ContentTypeId }

    $body = Invoke-Tour -Op 'searchKeyword2' -Params $p
    if ($null -eq $body) { return @() }

    $items = Get-Items $body
    Write-Host ('  totalCount = {0}' -f $body.totalCount) -ForegroundColor Yellow
    if ($items.Count -eq 0) {
        Write-Host '  (결과 없음)' -ForegroundColor DarkGray
        return @()
    }

    $i = 0
    foreach ($it in $items) {
        $i++
        if ($i -gt $Show) { break }
        $img = '이미지없음'
        if ($it.firstimage) { $img = '이미지있음' }
        Write-Host ('  {0,2}. [{1,-8}] {2}' -f $i, $it.contentid, $it.title)
        Write-Host ('       cat3={0}  {1}  {2}' -f $it.cat3, $img, $it.addr1) -ForegroundColor DarkGray
    }
    if ($items.Count -gt $Show) {
        Write-Host ('  ... 외 {0} 건 (이번 페이지)' -f ($items.Count - $Show)) -ForegroundColor DarkGray
    }
    return $items
}

function Test-Related {
    param(
        [string]$Keyword,
        [string]$AreaCd,       # 법정동 시도코드. KorService2 의 areaCode 와 다르다 (11=서울, 41=경기, 51=강원)
        [int]$Show = 10
    )

    Write-Host ''
    Write-Host ('=== TarRlteTar/searchKeyword1  keyword="{0}"  areaCd={1} ===' -f $Keyword, $AreaCd) -ForegroundColor Cyan

    $body = Invoke-Tour -BaseUrl $tarBase -Op 'searchKeyword1' -Params @{
        keyword   = $Keyword
        areaCd    = $AreaCd
        numOfRows = 20
        pageNo    = 1
    }
    if ($null -eq $body) { return }

    $items = Get-Items $body
    Write-Host ('  totalCount = {0}' -f $body.totalCount) -ForegroundColor Yellow
    if ($items.Count -eq 0) {
        Write-Host '  (결과 없음)' -ForegroundColor DarkGray
        return
    }

    $i = 0
    foreach ($it in $items) {
        $i++
        if ($i -gt $Show) { break }
        Write-Host ('  {0,2}위. {1}  ({2} {3})' -f $it.rlteRank, $it.rlteTatsNm, $it.rlteRegnNm, $it.rlteSignguNm)
        Write-Host ('       {0} > {1} > {2}' -f $it.rlteCtgryLclsNm, $it.rlteCtgryMclsNm, $it.rlteCtgrySclsNm) -ForegroundColor DarkGray
    }
}

function Test-Overview {
    param(
        [string]$ContentId,
        [string]$Title,
        [string[]]$LookFor
    )

    Write-Host ''
    Write-Host ('=== detailCommon2  contentId={0}  ({1}) ===' -f $ContentId, $Title) -ForegroundColor Cyan

    $body = Invoke-Tour -Op 'detailCommon2' -Params @{
        contentId    = $ContentId
        defaultYN    = 'Y'
        overviewYN   = 'Y'
        addrinfoYN   = 'Y'
        mapinfoYN    = 'Y'
        firstImageYN = 'Y'
    }
    $items = Get-Items $body
    if ($items.Count -eq 0) {
        Write-Host '  (조회 실패)' -ForegroundColor Red
        return
    }

    $overview = $items[0].overview
    if (-not $overview) {
        Write-Host '  overview 없음' -ForegroundColor Red
        return
    }

    $clean = ($overview -replace '<[^>]*>', ' ') -replace '\s+', ' '
    Write-Host ('  overview 길이 {0}자' -f $clean.Length) -ForegroundColor Yellow
    Write-Host ('  "{0}"' -f $clean.Substring(0, [Math]::Min(400, $clean.Length))) -ForegroundColor DarkGray

    Write-Host '  -- 작품 관련 단어 포함 여부 --'
    foreach ($w in $LookFor) {
        if ($clean.Contains($w)) {
            Write-Host ('   O  "{0}" 포함' -f $w) -ForegroundColor Green
        }
        else {
            Write-Host ('   X  "{0}" 없음' -f $w) -ForegroundColor DarkGray
        }
    }
}

# ────────────────────────────────────────────────────────────────────────
Write-Host ''
Write-Host '################  TourAPI 키워드 검색 탐침  ################' -ForegroundColor Magenta

Write-Host ''
Write-Host '### [1] 관용어로 촬영지를 훑을 수 있는가 (이 계획의 핵심) ###' -ForegroundColor Magenta
$set1 = Test-Keyword -Keyword '세트장'  -ContentTypeId '12'
$null  = Test-Keyword -Keyword '촬영지'  -ContentTypeId '12'
$null  = Test-Keyword -Keyword '드라마'  -ContentTypeId '12'
$null  = Test-Keyword -Keyword '민속촌'  -ContentTypeId '12'

Write-Host ''
Write-Host '### [2] 작품 제목으로 바로 검색되는가 ###' -ForegroundColor Magenta
$null = Test-Keyword -Keyword '대장금' -Show 5
$null = Test-Keyword -Keyword '태조왕건' -Show 5

Write-Host ''
Write-Host '### [3] 인물명으로 검색되는가 (아마 title 에 있는 것만) ###' -ForegroundColor Magenta
$null = Test-Keyword -Keyword '이순신' -Show 5
$null = Test-Keyword -Keyword '정조'   -Show 5

Write-Host ''
Write-Host '### [4] 지명은 확실히 되는가 (대조군) ###' -ForegroundColor Magenta
$null = Test-Keyword -Keyword '남한산성' -Show 5

Write-Host ''
Write-Host '### [6] 연관 관광지 - 이 공모전의 핵심 데이터 ###' -ForegroundColor Magenta
# 관광지명으로 부르는 건 정상 용법. 작품명으로도 되는지가 진짜 궁금한 것.
Test-Related -Keyword '창덕궁' -AreaCd '11'      # 대조군 - 이건 되어야 정상
Test-Related -Keyword '한국민속촌' -AreaCd '41'   # 사극 단골 촬영지
Test-Related -Keyword '대장금' -AreaCd '41'      # 작품명. 되면 판이 바뀐다
Test-Related -Keyword '수원화성' -AreaCd '41'     # 역사 배경지

Write-Host ''
Write-Host '### [5] overview 에 작품 언급이 실제로 들어있는가 ###' -ForegroundColor Magenta
if ($set1.Count -gt 0) {
    Test-Overview -ContentId $set1[0].contentid -Title $set1[0].title `
        -LookFor @('드라마', '영화', '촬영', '세트')
}
else {
    Write-Host '  [1]에서 세트장 결과가 없어 건너뜁니다.' -ForegroundColor DarkGray
}

Write-Host ''
Write-Host ('################  끝. API 호출 {0}회  ################' -f $script:CallCount) -ForegroundColor Magenta
Write-Host ''
Write-Host '판정 기준:' -ForegroundColor White
Write-Host '  [1] 세트장/촬영지 totalCount 가 수십 건 이상 -> 발견 경로 성립. 이대로 진행.'
Write-Host '  [1] 이 0~2건 -> 관용어 훑기 불가. 앵커는 LLM+사람으로만 만들어야 함.'
Write-Host '  [3] 이 0건 -> 인물명 검색은 폐기 (예상된 결과).'
Write-Host '  [5] 에서 "드라마"/"촬영" 이 포함 -> overview 를 근거로 쓸 수 있음 (전수 아닌 후보 한정).'
Write-Host ''
Write-Host '  [6] 이 가장 중요하다:' -ForegroundColor Yellow
Write-Host '      창덕궁/수원화성이 나온다        -> 연관관광지를 추천 엔진 중심으로 쓸 수 있다.'
Write-Host '      "대장금"(작품명)까지 나온다     -> 앵커 없이 콘텐츠명만으로 추천 가능. 설계가 크게 단순해진다.'
Write-Host '      전부 0건이거나 areaCd 오류      -> areaCd 를 바꿔 재시도 (11=서울 41=경기 51=강원).'

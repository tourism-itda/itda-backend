# 잇다 백엔드 — GitHub push 스크립트
# 조직(tourism-itda) 초대 수락 + GitHub 인증이 끝난 뒤 이 스크립트 하나만 실행하면 됩니다.
# 실행: powershell -ExecutionPolicy Bypass -File .\push-to-github.ps1

$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot

Write-Output "== 1. 리모트 접근 확인 =="
git ls-remote origin | Select-Object -First 5
if (-not $?) { throw "리모트 접근 실패 — 조직 초대 또는 인증을 먼저 확인하세요." }

Write-Output "`n== 2. 제외 파일 확인 (아래 목록은 절대 올라가면 안 됨) =="
git -c core.quotepath=false status --short --ignored | Select-String -Pattern '^!!'

Write-Output "`n== 3. push (feat/place-itinerary) =="
git push -u origin feat/place-itinerary

Write-Output "`n== 완료 =="
Write-Output "PR 생성: https://github.com/tourism-itda/itda-backend/compare/feat/place-itinerary?expand=1"

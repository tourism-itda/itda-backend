# 협업 가이드

## 브랜치 전략

```
main
 └── dev
      ├── feat/기능명
      ├── fix/버그명
      └── chore/작업명
```

- **main**: 배포용 브랜치. 직접 커밋 금지.
- **dev**: 통합 브랜치. 모든 PR은 dev로 머지.
- **feat/fix/chore**: 작업 단위 브랜치. dev에서 분기하고 dev로 머지.

## 작업 플로우

1. `dev` 브랜치에서 작업 브랜치 생성
   ```bash
   git checkout dev
   git pull origin dev
   git checkout -b feat/기능명
   ```
2. 작업 후 커밋
3. GitHub에서 `feat/기능명 → dev` PR 생성
4. 리뷰 후 머지

## 커밋 메시지 컨벤션

```
feat: 새로운 기능 추가
fix: 버그 수정
chore: 빌드, 설정, 의존성 변경
refactor: 기능 변경 없는 코드 개선
docs: 문서 수정
```

## PR 규칙

- PR 대상 브랜치는 반드시 **dev**
- main으로 직접 PR 금지
- PR 제목은 커밋 컨벤션과 동일한 형식 사용

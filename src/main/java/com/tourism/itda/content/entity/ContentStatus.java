package com.tourism.itda.content.entity;

/**
 * 콘텐츠 노출 상태.
 *
 * PUBLISHED: 우리 DB의 실존 인물 또는 실제 장소(왕조)와 매칭이 확인된 콘텐츠. 사용자에게 노출한다.
 * PENDING  : 매칭에 실패한 콘텐츠. 사용자에게 노출하지 않고 보류한다.
 *            Person/Place DB가 확장되면 재검증 배치로 PUBLISHED 승격 대상이 된다.
 */
public enum ContentStatus {
    PUBLISHED,
    PENDING
}

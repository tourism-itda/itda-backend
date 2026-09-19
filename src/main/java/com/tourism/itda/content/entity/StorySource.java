package com.tourism.itda.content.entity;

/**
 * 줄거리(storyBody 등)가 어떻게 생성되었는지 나타내는 출처 라벨.
 *
 * CHRONICLE     : 국사편찬위원회 연표(인물 재위기간 또는 검수된 사건 구간)를 근거로 생성. "실제 역사 연표 기반".
 * AI_GENERATED  : 매칭된 연표가 없어 영화 정보만으로 생성한 이야기. "AI 창작 이야기".
 */
public enum StorySource {
    CHRONICLE,
    AI_GENERATED
}

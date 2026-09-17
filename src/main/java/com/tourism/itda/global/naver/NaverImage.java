package com.tourism.itda.global.naver;

/**
 * 네이버 이미지 검색 결과 한 건.
 *
 * @param title        검색어가 {@code <b>} 태그로 강조된 채 온다. {@link NaverSearchClient} 에서 태그를 벗겨 담는다.
 * @param imageUrl     원본 이미지 URL
 * @param thumbnailUrl 썸네일 URL. 원본이 죽어 있을 때의 대안으로만 쓴다.
 * @param width        원본 가로 픽셀. 0 이면 응답에 없었다는 뜻이다.
 * @param height       원본 세로 픽셀. 0 이면 응답에 없었다는 뜻이다.
 */
public record NaverImage(
        String title,
        String imageUrl,
        String thumbnailUrl,
        int width,
        int height) {

    /**
     * 카드에 쓸 만한 크기인가.
     *
     * <p>크기를 못 받았으면(0) 통과시킨다 — 크기 미상이라는 이유로 버리면
     * 쓸 수 있는 사진까지 같이 날아간다.
     */
    public boolean isLargeEnough(int minWidth, int minHeight) {
        if (width == 0 || height == 0) {
            return true;
        }
        return width >= minWidth && height >= minHeight;
    }
}

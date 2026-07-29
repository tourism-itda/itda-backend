package com.itda.common.exception;

/**
 * 공통 에러 응답 포맷. 팀 전원이 동일하게 파싱한다. (TODO 4번 합의사항)
 * 예: { "code": "NOT_FOUND", "message": "장소를 찾을 수 없습니다." }
 */
public record ErrorResponse(String code, String message) {
}

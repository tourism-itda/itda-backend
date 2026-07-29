package com.itda.common.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * itda.jwt.* 설정 바인딩. 안시현 인증 파트로 교체될 때 이 값들만 팀 규격에 맞추면 된다.
 */
@ConfigurationProperties(prefix = "itda.jwt")
public record JwtProperties(
        String secret,
        String userIdClaim
) {
    public String userIdClaim() {
        return (userIdClaim == null || userIdClaim.isBlank()) ? "userId" : userIdClaim;
    }
}

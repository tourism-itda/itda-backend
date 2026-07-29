package com.itda.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

/**
 * 임시 JWT 발급/검증기 (HS256). 안시현 인증 파트로 교체 예정.
 * 발급은 개발용(DevTokenController)에서만 쓰고, 운영 검증은 이 클래스로 통일한다.
 */
@Slf4j
@Component
public class JwtProvider {

    private final SecretKey key;
    private final String userIdClaim;

    public JwtProvider(JwtProperties properties) {
        this.key = Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
        this.userIdClaim = properties.userIdClaim();
    }

    /** 토큰이 유효하면 userId 반환, 그렇지 않으면 null (익명 처리). */
    public Long parseUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            Object raw = claims.get(userIdClaim);
            if (raw == null) {
                // 팀 합의에 따라 sub 에 담는 경우 대비
                raw = claims.getSubject();
            }
            if (raw == null) {
                return null;
            }
            return Long.valueOf(raw.toString());
        } catch (Exception e) {
            log.debug("invalid jwt: {}", e.getMessage());
            return null;
        }
    }

    /** 개발용 토큰 발급 (local 프로파일 전용). */
    public String issueDevToken(Long userId, Duration ttl) {
        Date now = new Date();
        return Jwts.builder()
                .claim(userIdClaim, userId)
                .subject(String.valueOf(userId))
                .issuedAt(now)
                .expiration(new Date(now.getTime() + ttl.toMillis()))
                .signWith(key)
                .compact();
    }
}

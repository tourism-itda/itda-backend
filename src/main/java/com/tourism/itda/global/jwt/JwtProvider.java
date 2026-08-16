package com.tourism.itda.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
// @Service, @Repository처럼 이 클래스도 Spring Bean으로 등록되게 하는 어노테이션
// (특정 계층에 속하지 않는 범용 컴포넌트에 흔히 씀)
public class JwtProvider {

    private final SecretKey key;
    private final long expirationMs = 1000 * 60 * 60 * 24; // 24시간

    // application.yml에 정의된 값을 주입받음
    public JwtProvider(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String loginId) {
        return Jwts.builder()
                .subject(loginId)              // 토큰의 주인이 누구인지 (보통 식별자)
                .claim("userId", userId)        // 커스텀 데이터 추가 (나중에 꺼내 쓸 값)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)                  // 이 비밀키로 서명 → 위변조 방지
                .compact();
    }

    public String createResetToken(Long userId) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("type", "RESET")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10분
                .signWith(key)
                .compact();
    }

    public Long parseResetToken(String token) {
        var claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();
        if (!"RESET".equals(claims.get("type", String.class))) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        return Long.parseLong(claims.getSubject());
    }
}
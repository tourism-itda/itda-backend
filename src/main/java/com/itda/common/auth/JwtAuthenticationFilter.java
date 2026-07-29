package com.itda.common.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Authorization: Bearer <token> 이 오면 파싱해서 userId 를 request 속성으로 심는다.
 * 토큰이 없거나 invalid 하면 익명(속성 미설정)으로 통과 — 인증 필수 여부는
 * 컨트롤러의 @LoginUser(required=true) 에서 판단한다.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String LOGIN_USER_ID = "loginUserId";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            String token = header.substring(BEARER_PREFIX.length()).trim();
            Long userId = jwtProvider.parseUserId(token);
            if (userId != null) {
                request.setAttribute(LOGIN_USER_ID, userId);
            }
        }
        filterChain.doFilter(request, response);
    }
}

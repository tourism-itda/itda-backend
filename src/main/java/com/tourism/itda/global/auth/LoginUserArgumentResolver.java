package com.tourism.itda.global.auth;

import com.tourism.itda.global.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * {@code @LoginUser Long userId} 파라미터 해석기.
 *
 * <p>userId 는 안시현 파트의 {@code global.jwt.JwtFilter} 가
 * {@code Authentication.getPrincipal()} 에 Long 으로 넣어준다.
 * 이 클래스는 그 값을 꺼내 쓰기만 한다 — 토큰 파싱/검증은 하지 않는다.
 *
 * <p>{@code (Long) authentication.getPrincipal()} 을 컨트롤러마다 반복하는 대신
 * 널 처리와 401 을 한 곳에 모으려고 둔 것이다. dev 의 다른 컨트롤러처럼
 * {@code Authentication} 을 직접 받아도 동작에는 차이가 없다.
 */
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class)
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Long userId = currentUserId();

        LoginUser annotation = parameter.getParameterAnnotation(LoginUser.class);
        boolean required = annotation == null || annotation.required();

        if (userId == null && required) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return userId; // Long or null
    }

    private Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        // JwtFilter 는 principal 에 userId(Long) 를 넣는다.
        // 익명 사용자일 때는 "anonymousUser" 문자열이 들어오므로 타입으로 걸러낸다.
        return authentication.getPrincipal() instanceof Long userId ? userId : null;
    }
}

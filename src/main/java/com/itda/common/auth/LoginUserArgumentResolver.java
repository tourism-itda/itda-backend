package com.itda.common.auth;

import com.itda.common.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @LoginUser Long userId 파라미터 해석기.
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
        Object userId = webRequest.getAttribute(
                JwtAuthenticationFilter.LOGIN_USER_ID, RequestAttributes.SCOPE_REQUEST);

        LoginUser annotation = parameter.getParameterAnnotation(LoginUser.class);
        boolean required = annotation == null || annotation.required();

        if (userId == null && required) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return userId; // Long or null
    }
}

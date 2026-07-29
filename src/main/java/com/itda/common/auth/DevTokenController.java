package com.itda.common.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

/**
 * 개발용 JWT 발급 엔드포인트. local 프로파일에서만 노출된다.
 * 예: GET /dev/token?userId=1  ->  { "token": "eyJ..." }
 * 안시현 인증 파트가 붙으면 이 컨트롤러는 삭제한다.
 */
@Profile({"local", "h2"})
@RestController
@RequiredArgsConstructor
public class DevTokenController {

    private final JwtProvider jwtProvider;

    @GetMapping("/dev/token")
    public Map<String, String> issue(@RequestParam(defaultValue = "1") Long userId) {
        String token = jwtProvider.issueDevToken(userId, Duration.ofDays(7));
        return Map.of("token", token);
    }
}

package com.tourism.itda.config;

import com.tourism.itda.global.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.httpFirewall(httpFirewall());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:5173",
                "https://itda-frontend-peach.vercel.app",
                "https://itda-travel.com",
                "https://www.itda-travel.com",
                "http://localhost:5174"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        // 관리성 배치·수집 API — 프론트에서 쓰지 않고 운영자가 서버 내부/스케줄러로만 돌린다.
                        // 아직 권한(Role) 체계가 없어 로그인한 아무 회원이나 외부 API 대량 호출·데이터 적재
                        // 배치를 트리거할 수 있으므로 HTTP 노출 자체를 막는다. denyAll → 인증 여부와 무관하게 403.
                        // 향후 관리자 화면이 필요해지면 Role 체계로 교체할 것.
                        .requestMatchers(HttpMethod.POST, "/api/contents/collect").denyAll()
                        .requestMatchers(HttpMethod.POST, "/api/contents/backfill-media").denyAll()
                        .requestMatchers("/api/admin/**").denyAll()
                        .requestMatchers("/api/auth/logout").authenticated()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/check-login-id", "/api/users/check-nickname").permitAll()
                        // 관광API 패스스루·장소 조회는 GET 만 공개.
                        // POST /api/places/import 는 place 테이블에 쓰므로 인증 필요 → anyRequest 로 처리.
                        .requestMatchers(HttpMethod.GET, "/api/places/**").permitAll()
                        .requestMatchers("/api/explore/**").permitAll()
                        .requestMatchers("/api/events/**").permitAll()
                        .requestMatchers("/api/contents/*/places").permitAll()
                        // 장소/일정 파트 — 인증 불필요 (저장 전 미리보기·후보 조회)
                        .requestMatchers(HttpMethod.GET, "/api/itineraries/recommend").permitAll()
                        .requestMatchers("/api/itineraries/route/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/itineraries/route").permitAll()
                        // 나머지 /api/itineraries/** (저장·목록·상세·수정·삭제) 는 인증 필요 → anyRequest 로 처리
                        .requestMatchers(HttpMethod.GET, "/api/contents").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contents/credits/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contents/*").permitAll()
                        // POST /api/contents/{contentId}(영화 저장)·/api/contents/collect(수집 트리거)는
                        // 쓰기/부작용 작업이므로 인증 필요 → anyRequest 로 처리.
                        // No.40/41 커뮤니티 목록·상세 — 인증 불필요
                        .requestMatchers(HttpMethod.GET, "/api/community/posts", "/api/community/posts/*").permitAll()
                        // No.43 리뷰 목록 — 인증 선택 (로그인 시에만 is_liked 계산). POST(작성)/좋아요는 인증 필요 → anyRequest 로 처리
                        .requestMatchers(HttpMethod.GET, "/api/itineraries/*/reviews").permitAll()
                        .requestMatchers("/explore/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}

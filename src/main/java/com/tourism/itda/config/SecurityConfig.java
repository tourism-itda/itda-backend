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
                "http://localhost:5174",
                "https://itda-frontend-peach.vercel.app"
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
                        .requestMatchers("/api/auth/logout").authenticated()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/check-login-id", "/api/users/check-nickname").permitAll()
                        .requestMatchers("/api/places/**").permitAll()
                        .requestMatchers("/api/contents/*/places").permitAll()
                        // 장소/일정 파트 — 인증 불필요 (저장 전 미리보기·후보 조회)
                        .requestMatchers(HttpMethod.GET, "/api/itineraries/recommend").permitAll()
                        .requestMatchers("/api/itineraries/route/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/itineraries/route").permitAll()
                        // 나머지 /api/itineraries/** (저장·목록·상세·수정·삭제) 는 인증 필요 → anyRequest 로 처리
                        .requestMatchers(HttpMethod.GET, "/api/contents").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contents/*").permitAll()
                        // No.40/41 커뮤니티 목록·상세 — 인증 불필요
                        .requestMatchers(HttpMethod.GET, "/api/community/posts", "/api/community/posts/*").permitAll()
                        // No.43 리뷰 목록 — 인증 선택 (로그인 시에만 is_liked 계산). POST(작성)/좋아요는 인증 필요 → anyRequest 로 처리
                        .requestMatchers(HttpMethod.GET, "/api/itineraries/*/reviews").permitAll()
                        .requestMatchers("/explore/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}

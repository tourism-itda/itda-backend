package com.tourism.itda.global.config;

import com.tourism.itda.global.tourapi.TourApiProperties;
import com.tourism.itda.planner.route.LlmProperties;
import com.tourism.itda.planner.route.RouteProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 장소·일정 파트가 쓰는 {@code itda.*} 설정을 빈으로 등록한다.
 *
 * <p>메인 클래스에 {@code @ConfigurationPropertiesScan} 을 붙이면 한 줄로 끝나지만,
 * 그건 팀 공용 파일이라 여기서 필요한 것만 명시적으로 등록한다.
 * 새 {@code @ConfigurationProperties} 를 만들면 이 목록에 추가해야 한다.
 */
@Configuration
@EnableConfigurationProperties({
        TourApiProperties.class,
        RouteProperties.class,
        LlmProperties.class
})
public class ItdaPropertiesConfig {
}

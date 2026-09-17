package com.tourism.itda.global.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Map;

/**
 * 공공데이터포털(data.go.kr) 패스스루 호출기.
 *
 * <p><b>서비스마다 서비스키가 다를 수 있다.</b> 포털은 데이터셋 단위로 활용신청을 받으므로,
 * 국문 관광정보(KorService2)와 연관 관광지(TarRlteTarService1)를 서로 다른 계정에서 신청하면
 * 키가 두 개가 된다. 한쪽 키로 다른 서비스를 부르면 <b>403</b> 이 난다.
 *
 * <p>그래서 서비스별로 (URL, 키) 짝을 따로 들고 메서드를 나눠 둔다.
 * {@code tar-rlte-api-key} 를 비우면 {@code api-key} 를 그대로 쓰므로,
 * 한 계정에서 둘 다 신청한 환경에서는 환경변수를 하나만 둬도 된다.
 */
@Component
public class PublicDataClient {

    private final RestClient restClient;

    /** 국문 관광정보(KorService2). */
    private final String baseUrl;
    private final String apiKey;

    /** 연관 관광지(TarRlteTarService1). */
    private final String tarRlteUrl;
    private final String tarRlteApiKey;

    public PublicDataClient(
            @Value("${public-data.base-url}") String baseUrl,
            @Value("${public-data.api-key}") String apiKey,
            @Value("${public-data.tar-rlte-url}") String tarRlteUrl,
            @Value("${public-data.tar-rlte-api-key:}") String tarRlteApiKey
    ) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.tarRlteUrl = tarRlteUrl;
        // 비워 두면 국문 키를 그대로 쓴다 — 한 계정에서 둘 다 신청한 경우.
        this.tarRlteApiKey = (tarRlteApiKey == null || tarRlteApiKey.isBlank()) ? apiKey : tarRlteApiKey;
        this.restClient = RestClient.create();
    }

    /** 국문 관광정보(KorService2) 호출. */
    public String get(String endpoint, Map<String, String> params) {
        return call(baseUrl, apiKey, endpoint, params);
    }

    /** 연관 관광지(TarRlteTarService1) 호출. 국문과 서비스키가 다를 수 있어 경로를 나눴다. */
    public String getRelated(String endpoint, Map<String, String> params) {
        return call(tarRlteUrl, tarRlteApiKey, endpoint, params);
    }

    private String call(String base, String key, String endpoint, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(base + endpoint)
                .queryParam("serviceKey", key)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "itda")
                .queryParam("_type", "json");

        params.forEach(builder::queryParam);

        URI uri = builder.build().encode().toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(String.class);
    }
}

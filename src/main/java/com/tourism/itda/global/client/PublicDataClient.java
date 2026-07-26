package com.tourism.itda.global.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Map;

@Component
public class PublicDataClient {

    private final RestClient restClient;
    private final String apiKey;
    private final String baseUrl;

    public PublicDataClient(
            @Value("${public-data.base-url}") String baseUrl,
            @Value("${public-data.api-key}") String apiKey
    ) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.restClient = RestClient.create();
    }

    public String get(String endpoint, Map<String, String> params) {
        return get(baseUrl, endpoint, params);
    }

    public String get(String customBaseUrl, String endpoint, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(customBaseUrl + endpoint)
                .queryParam("serviceKey", apiKey)
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

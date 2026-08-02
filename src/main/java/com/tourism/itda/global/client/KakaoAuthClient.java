package com.tourism.itda.global.client;

import com.tourism.itda.auth.dto.KakaoTokenResponse;
import com.tourism.itda.auth.dto.KakaoUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class KakaoAuthClient {

    private final RestClient restClient;
    private final String clientId;
    private final String redirectUri;
    private final String tokenUrl;
    private final String userInfoUrl;
    private String clientSecret;

    public KakaoAuthClient(
            @Value("${kakao.client-id}") String clientId,
            @Value("${kakao.redirect-uri}") String redirectUri,
            @Value("${kakao.token-url}") String tokenUrl,
            @Value("${kakao.user-info-url}") String userInfoUrl,
            @Value("${kakao.client-secret}") String clientSecret
    ){
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
        this.clientSecret = clientSecret;
        this.restClient = RestClient.create();
    }

    public String getAccessToken(String code){
        String body = "grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&code=" + code
                + "&client_secret=" + clientSecret;

        return restClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(KakaoTokenResponse.class)
                .getAccessToken();
    }

    public KakaoUserInfo getUserInfo(String accessToken){
        return restClient.get()
                .uri(userInfoUrl)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(KakaoUserInfo.class);
    }
}

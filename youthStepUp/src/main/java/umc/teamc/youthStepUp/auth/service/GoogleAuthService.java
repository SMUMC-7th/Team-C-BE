package umc.teamc.youthStepUp.auth.service;


import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import umc.teamc.youthStepUp.auth.constant.OAUTH_URL;
import umc.teamc.youthStepUp.auth.dto.google.GoogleAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.google.GoogleUserInfoDTO;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    @Value("${google.client_id}")
    private String client_id;
    @Value("${google.redirect_uri}")
    private String redirect_uri;
    @Value("${google.local_redirect_uri}")
    private String local_redirect_uri;
    @Value("${google.client_secret}")
    private String google_client_secret;
    @Value("${google.api_key}")
    private String google_api_key;

    private final String grant_type = "authorization_code";
    private final String code = "code";

    public String getCode() {
        WebClient.create()
                .get()
                .uri(getGoogleCode());
        return getGoogleCode();
    }

    private String getGoogleCode() {
        return OAUTH_URL.GOOGLE_AUTH_URL.getUrl()
                + "?scope=profile"
                + "&response_type=code"
                + "&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri;


    }

    public GoogleAccessTokenDTO getGoogleAccessToken(String code, boolean isLocalhost) {
        WebClient webClient = WebClient.create();
        return webClient
                .post()
                .uri(OAUTH_URL.GOOGLE_TOKEN_URL.getUrl())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, isLocalhost))
                .retrieve()
                .bodyToMono(GoogleAccessTokenDTO.class)
                .block();
    }

    private MultiValueMap<String, String> tokenRequest(String code, boolean isLocalhost) {
        String redirectURL = redirect_uri;
        if (isLocalhost) {
            redirectURL = local_redirect_uri;
        }
        MultiValueMap<String, String> formData
                = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", client_id);
        formData.add("client_secret", google_client_secret);
        formData.add("redirect_uri", redirectURL);
        formData.add("grant_type", grant_type);
        return formData;
    }


    public GoogleUserInfoDTO getUserInfo(String token) {
        String url = OAUTH_URL.GOOGLE_INFO_URL.getUrl() + "?access_token=" + token;
        return WebClient.create()
                .get()
                .uri(url)
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_JSON);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .retrieve()
                .bodyToMono(GoogleUserInfoDTO.class)
                .block();
    }

//    private Map<String, String> googleInfoBody(String token) {
//        Map<String, String> body = new HashMap<>();
//        body.put("idToken", token);
//        return body;
//    }

}

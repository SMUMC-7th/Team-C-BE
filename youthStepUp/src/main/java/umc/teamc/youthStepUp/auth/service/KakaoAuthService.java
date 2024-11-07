package umc.teamc.youthStepUp.auth.service;


import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import umc.teamc.youthStepUp.auth.constant.KAKAO_URL;
import umc.teamc.youthStepUp.auth.dto.KakaoAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    @Value("${kakao.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;
    private final String grant_type = "authorization_code";
    private final String response_type = "code";

    public void getCode() {
        ;
        WebClient.create()
                .get()
                .uri(getAuthUrl());
        System.out.println(getAuthUrl());
    }

    private String getAuthUrl() {
        return KAKAO_URL.KAKAO_AUTH_URL.getUrl()
                + "?response_type=" + response_type
                + "&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri;
    }

    public KakaoAccessTokenDTO getKakaoAccessToken(String code) {
        WebClient webClient = WebClient.create();
        return webClient
                .post()
                .uri(KAKAO_URL.KAKAO_TOKEN_URL.getUrl())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code))
                .retrieve()
                .bodyToMono(KakaoAccessTokenDTO.class)
                .block();
    }

    private MultiValueMap<String, String> tokenRequest(String code) {
        MultiValueMap<String, String> formData
                = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", grant_type);
        formData.add("redirect_uri", redirect_uri);
        formData.add("client_id", client_id);
        return formData;
    }

    public void logout() {
        WebClient.create()
                .get()
                .uri(getLogoutUrl());
    }

    private String getLogoutUrl() {
        return KAKAO_URL.KAKAO_LOGOUT_URL.getUrl()
                + "?client_id=" + client_id
                + "&logout_redirect_uri=" + "/";
    }

    public KakaoUserInfoDTO getUserInfo(String token) {
        return WebClient.create()
                .get()
                .uri(KAKAO_URL.KAKAO_INFO_URL.getUrl())
                .headers(header -> {
                    header.setBearerAuth(token);
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .retrieve()
                .bodyToMono(KakaoUserInfoDTO.class)
                .block();
    }


}

package umc.teamc.youthStepUp.auth.service;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import umc.teamc.youthStepUp.auth.constant.OAUTH_URL;
import umc.teamc.youthStepUp.auth.dto.naver.NaverAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.naver.NaverUserInfoDTO;

@Service
@RequiredArgsConstructor
public class NaverAuthService {
    @Value("${naver.naver_client_id}")
    private String client_id;
    @Value("${naver.naver_redirect_url}")
    private String redirect_uri;
    @Value("${naver.naver_local_redirect_uri}")
    private String local_redirect_uri;
    @Value("${naver.naver_client_secret}")
    private String naver_client_secret;
    private final String grant_type = "authorization_code";

    public String getCode() throws UnsupportedEncodingException {
        WebClient.create()
                .get()
                .uri(getNaverCode());
        return getNaverCode();
    }

    private String getNaverCode() throws UnsupportedEncodingException {
        return OAUTH_URL.NAVER_AUTH_URI.getUrl() + "/oauth2.0/authorize"
                + "?response_type=code"
                + "&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri
                + "&redirect_uri=" + URLEncoder.encode("test", "UTF-8");

    }

    public NaverAccessTokenDTO getNaverAccessToken(String state, String code, boolean isLocalhost) {
        WebClient webClient = WebClient.create();
        return webClient
                .post()
                .uri(OAUTH_URL.NAVER_TOKEN_URL.getUrl())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(state, code, isLocalhost))
                .retrieve()
                .bodyToMono(NaverAccessTokenDTO.class)
                .block();
    }

    private MultiValueMap<String, String> tokenRequest(String state, String code, boolean isLocalhost) {
        String redirectURL = redirect_uri;
        if (isLocalhost) {
            redirectURL = local_redirect_uri;
        }
        MultiValueMap<String, String> formData
                = new LinkedMultiValueMap<>();
        formData.add("grant_type", grant_type);
        formData.add("client_id", client_id);
        formData.add("client_secret", naver_client_secret);
        formData.add("code", code);
        formData.add("state", state);
        return formData;
    }


    public NaverUserInfoDTO getUserInfo(String token) {
        return WebClient.create()
                .get()
                .uri(OAUTH_URL.NAVER_API_URI.getUrl() + "/v1/nid/me")
                .headers(header -> {
                    header.setBearerAuth(token);
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .retrieve()
                .bodyToMono(NaverUserInfoDTO.class)
                .block();
    }


}
package umc.teamc.youthStepUp.auth.service;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import umc.teamc.youthStepUp.auth.constant.TokenConstant;
import umc.teamc.youthStepUp.auth.dto.kakao.KakaoAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.kakao.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    @Value("${kakao.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;
    @Value("${kakao.local_redirect_uri}")
    private String local_redirect_uri;
    private final String grant_type = "authorization_code";
    private final String response_type = "code";
    private final JwtProvider jwtProvider;

    public void getCode() {
        WebClient.create()
                .get()
                .uri(getAuthUrl());
        System.out.println(getAuthUrl());
    }

    private String getAuthUrl() {
        return OAUTH_URL.KAKAO_AUTH_URL.getUrl()
                + "?response_type=" + response_type
                + "&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri;
    }

    public KakaoAccessTokenDTO getKakaoAccessToken(String code, boolean isLocalhost) {
        WebClient webClient = WebClient.create();
        return webClient
                .post()
                .uri(OAUTH_URL.KAKAO_TOKEN_URL.getUrl())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, isLocalhost))
                .retrieve()
                .bodyToMono(KakaoAccessTokenDTO.class)
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
        formData.add("grant_type", grant_type);
        formData.add("redirect_uri", redirectURL);
        formData.add("client_id", client_id);
        return formData;
    }

    public void logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie accessToken = jwtProvider.findCookie(request.getCookies(), TokenConstant.ACCESS_TOKEN.getValue());
        Cookie refreshToken = jwtProvider.findCookie(request.getCookies(), TokenConstant.REFRESH_TOKEN.getValue());
        setCookieClean(accessToken);
        setCookieClean(refreshToken);
        WebClient.create()
                .get()
                .uri(getLogoutUrl());
        response.addCookie(accessToken);
        response.addCookie(refreshToken);
    }

    private static void setCookieClean(Cookie cookie) {
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setPath("/");
    }

    private String getLogoutUrl() {
        return OAUTH_URL.KAKAO_LOGOUT_URL.getUrl()
                + "?client_id=" + client_id
                + "&logout_redirect_uri=" + "/";
    }

    public KakaoUserInfoDTO getUserInfo(String token) {
        return WebClient.create()
                .get()
                .uri(OAUTH_URL.KAKAO_INFO_URL.getUrl())
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
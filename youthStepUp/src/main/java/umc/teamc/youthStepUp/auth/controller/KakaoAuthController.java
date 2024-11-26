package umc.teamc.youthStepUp.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.dto.KakaoAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.auth.service.KakaoAuthService;
import umc.teamc.youthStepUp.auth.success.AuthSuccessCode;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "KakaoAuth", description = "카카오 소셜 로그인 관련 API")
public class KakaoAuthController {
    private final KakaoAuthService kakaoAuthService;
    private final AuthService authService;

    @GetMapping("/auth/kakao-login")
    @Operation(summary = "카카오 로그인 백엔드 테스트 용", description = "프론트는 사용 안 해도 됨")
    public CustomResponse<?> login() {
        kakaoAuthService.getCode();
        return CustomResponse.onSuccess(AuthSuccessCode.AUTH_CODE_SUCCESS);
    }

    @GetMapping("/auth/kakao-oauth")
    @Operation(summary = "카카오 인가 코드로 로그인", description = "카카오 OAuth 인가 코드를 받아 액세스 토큰을 발급받고 로그인한다.")
    public CustomResponse<?> getKakaoCode(
            @Parameter(description = "카카오 인가코드", required = true)
            @RequestParam("code") String code,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        boolean isLocalhost = false;
        if (request.getRequestURI().contains("localhost")) {
            isLocalhost = true;
        }
        KakaoAccessTokenDTO tokenDTO = kakaoAuthService.getKakaoAccessToken(code, isLocalhost);
        KakaoUserInfoDTO userInfoDTO = kakaoAuthService.getUserInfo(tokenDTO.accessToken());
        return CustomResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, authService.login(userInfoDTO, response));
    }

    @GetMapping("/auth/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 수행한다.")
    public CustomResponse<?> logout(HttpServletResponse response, HttpServletRequest request) {
        kakaoAuthService.logout(response, request);
        return CustomResponse.onSuccess(AuthSuccessCode.LOGOUT_SUCCESS);
    }

    @GetMapping("/auth/reissue-token")
    @Operation(summary = "토큰 재발급", description = "만료된 액세스 토큰을 재발급받는다.")
    public CustomResponse<?> reissueToken(
            @Parameter(description = "Refresh 토큰을 쿠키에 넣어 요청", required = true)
            @CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        authService.reissueToken(refreshToken, response);
        return CustomResponse.onSuccess(AuthSuccessCode.ACCESS_TOKEN_REISSUE_SUCCESS);
    }

}
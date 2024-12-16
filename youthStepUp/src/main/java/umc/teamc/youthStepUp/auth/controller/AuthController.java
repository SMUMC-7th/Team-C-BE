package umc.teamc.youthStepUp.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.auth.service.KakaoAuthService;
import umc.teamc.youthStepUp.auth.success.AuthSuccessCode;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "KakaoAuth", description = "카카오 소셜 로그인 관련 API")
public class AuthController {
    private final KakaoAuthService kakaoAuthService;
    private final AuthService authService;

    @GetMapping("/auth/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 수행한다.")
    public CustomResponse<?> logout(HttpServletResponse response, HttpServletRequest request) {
        authService.logout(response, request);
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
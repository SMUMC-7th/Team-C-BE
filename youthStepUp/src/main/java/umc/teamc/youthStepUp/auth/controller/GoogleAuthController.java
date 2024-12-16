package umc.teamc.youthStepUp.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.dto.google.GoogleAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.google.GoogleUserInfoDTO;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.auth.service.GoogleAuthService;
import umc.teamc.youthStepUp.auth.success.AuthSuccessCode;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Tag(name = "GoogleAuth", description = "Google 소셜 로그인 관련 API")
public class GoogleAuthController {
    private final GoogleAuthService googleAuthService;
    private final AuthService authService;

    @GetMapping("/auth/google-login")
    @Operation(summary = "Google 로그인", description = "Google 소셜 로그인 인가 코드 발급을 수행한다.")
    public CustomResponse<?> getGoogleCode() throws UnsupportedEncodingException {
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, googleAuthService.getCode());
    }

    @GetMapping("/auth/google-oauth")
    @Operation(summary = "Google 인가코드 콜백 주소", description = "Google 인가 코드를 받고, 로그인을 수행한다.")
    public CustomResponse<?> googleLogin(@RequestParam("code") String code,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        boolean isLocalhost = authService.isLocalhost(request);
        GoogleAccessTokenDTO tokenDTO = googleAuthService.getGoogleAccessToken(code, isLocalhost);
        GoogleUserInfoDTO userInfoDTO = googleAuthService.getUserInfo(tokenDTO.access_token());
        return CustomResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, authService.login(userInfoDTO, response));
    }
}

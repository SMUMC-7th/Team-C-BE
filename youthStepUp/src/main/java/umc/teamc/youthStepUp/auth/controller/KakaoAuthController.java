package umc.teamc.youthStepUp.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.dto.KakaoAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.LoginResponseDTO;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.auth.service.KakaoAuthService;
import umc.teamc.youthStepUp.auth.success.AuthSuccessCode;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;

@RestController
@RequiredArgsConstructor
public class KakaoAuthController {
    private final KakaoAuthService kakaoAuthService;
    private final AuthService authService;

    @GetMapping("/auth/kakao-login")
    public CustomResponse<?> login() {
        kakaoAuthService.getCode();
        return CustomResponse.onSuccess(AuthSuccessCode.AUTH_CODE_SUCCESS);
    }

    @GetMapping("/auth/kakao-oauth")
    public CustomResponse<?> getKakaoCode(@RequestParam("code") String code) {
        KakaoAccessTokenDTO tokenDTO = kakaoAuthService.getKakaoAccessToken(code);
        KakaoUserInfoDTO userInfoDTO = kakaoAuthService.getUserInfo(tokenDTO.accessToken());
        LoginResponseDTO responseDTO = authService.createNewMember(userInfoDTO);
        return CustomResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, responseDTO);
    }

    @GetMapping("/auth/kakao-logout")
    public CustomResponse<?> logout() {
        kakaoAuthService.logout();
        return CustomResponse.onSuccess(AuthSuccessCode.LOGOUT_SUCCESS);
    }

    @GetMapping("/auth/reissue-token")
    public CustomResponse<?> reissueToken(@RequestHeader("Authorization") String header) {
        kakaoAuthService.reissueToken();
        return CustomResponse.onSuccess(AuthSuccessCode.ACCESS_TOKEN_REISSUE_SUCCESS);
    }

}

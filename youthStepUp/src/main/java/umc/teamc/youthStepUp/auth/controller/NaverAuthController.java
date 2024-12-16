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
import umc.teamc.youthStepUp.auth.dto.naver.NaverAccessTokenDTO;
import umc.teamc.youthStepUp.auth.dto.naver.NaverUserInfoDTO;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.auth.service.NaverAuthService;
import umc.teamc.youthStepUp.auth.success.AuthSuccessCode;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Tag(name = "NaverAuth", description = "네이버 소셜 로그인 관련 API")
public class NaverAuthController {
    private final NaverAuthService naverAuthService;
    private final AuthService authService;

    @GetMapping("/auth/naver-login")
    @Operation(summary = "네이버 로그인", description = "네이버 소셜 로그인 인가 코드 발급을 수행한다.")
    public CustomResponse<?> getNaverCode() throws UnsupportedEncodingException {
        naverAuthService.getCode();
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED);
    }

    @GetMapping("/auth/naver-oauth")
    @Operation(summary = "네이버 인가코드 콜백 주소", description = "네이버 인가 코드를 받고, 로그인을 수행한다.")
    public CustomResponse<?> naverLogin(@RequestParam("state") String state,
                                        @RequestParam("code") String code,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        boolean isLocalhost = authService.isLocalhost(request);
        NaverAccessTokenDTO tokenDTO = naverAuthService.getNaverAccessToken(state, code, isLocalhost);
        NaverUserInfoDTO userInfoDTO = naverAuthService.getUserInfo(tokenDTO.access_token());
        return CustomResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, authService.login(userInfoDTO, response));
    }


}
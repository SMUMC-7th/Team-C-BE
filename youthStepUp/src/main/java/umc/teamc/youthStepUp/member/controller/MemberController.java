package umc.teamc.youthStepUp.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberInitProfileRequestDTO;
import umc.teamc.youthStepUp.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member 관련 API")
public class MemberController {
    private final MemberService memberService;

    //    @GetMapping("/auth/kakao-login")
//    public CustomResponse<?> login() {
//        kakaoAuthService.getCode();
//        return CustomResponse.onSuccess(AuthSuccessCode.AUTH_CODE_SUCCESS);
//    }
    @PostMapping("/member/init-profile")
    @Operation(summary = "프로필 초기 설정", description = "교육, 키워드, 지역, 전공을 받아 초기 프로필을 설정한다.")
    public CustomResponse<?> initProfile(@RequestBody MemberInitProfileRequestDTO dto) {
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, memberService.initProfile(dto));
    }

}

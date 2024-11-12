package umc.teamc.youthStepUp.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberInitProfileRequestDTO;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.service.MemberService;
import umc.teamc.youthStepUp.profile.converter.ProfileConverter;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.service.command.ProfileCommandService;
import umc.teamc.youthStepUp.profile.service.query.ProfileQueryService;

@Tag(name = "프로필 API")
@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final MemberService memberService;

    @PostMapping("/init-profile")
    @Operation(summary = "프로필 초기 설정", description = "나이, 교육, 키워드, 지역, 전공을 받아 초기 프로필을 설정한다.")
    public CustomResponse<?> initProfile(@Parameter(hidden = true) @MemberInfo Long id,
                                         @RequestBody MemberInitProfileRequestDTO dto) {
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, memberService.initProfile(id, dto));
    }

    /**
     * 프로필 조회
     *
     * @return 프로필 정보
     */
    @Operation(summary = "프로필 기본 조회")
    @GetMapping
    public CustomResponse<?> getProfile(@Parameter(hidden = true) @MemberInfo Long id) {
        Member member = profileQueryService.getProfile(id);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileResponse(member));
    }

    /**
     * 프로필 상세 조회
     *
     * @return 프로필 상세 정보
     */
    @Operation(summary = "프로필 상세 조회")
    @GetMapping("/detail")
    public CustomResponse<?> getProfileDetail(@Parameter(hidden = true) @MemberInfo Long id) {
        Member member = profileQueryService.getProfile(id);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileDetailResponse(member));
    }

    /**
     * 프로필 수정
     *
     * @param request 프로필 수정 요청 DTO
     * @return 프로필 수정 결과
     */
    @Operation(summary = "프로필 수정")
    @PatchMapping
    public CustomResponse<?> updateProfile(@Parameter(hidden = true) @MemberInfo Long id,
                                           @RequestBody UpdateProfileRequestDTO request) {
        Member member = profileCommandService.updateProfile(id, request);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileDetailResponse(member));
    }

    /**
     * 회원 탈퇴
     *
     * @return 탈퇴 처리 결과
     */
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public CustomResponse<?> deleteProfile(@Parameter(hidden = true) @MemberInfo Long id,
                                           @RequestBody String name) {
        profileCommandService.deleteProfile(id, name);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }
}



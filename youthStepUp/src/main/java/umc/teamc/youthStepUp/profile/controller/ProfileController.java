package umc.teamc.youthStepUp.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberInitProfileRequestDTO;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.service.MemberService;
import umc.teamc.youthStepUp.profile.converter.ProfileBookmarkConverter;
import umc.teamc.youthStepUp.profile.converter.ProfileConverter;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.service.command.ProfileBookmarkCommandService;
import umc.teamc.youthStepUp.profile.service.command.ProfileCommandService;
import umc.teamc.youthStepUp.profile.service.query.ProfileBookmarkQueryService;
import umc.teamc.youthStepUp.profile.service.query.ProfileQueryService;

@Tag(name = "프로필 API")
@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final ProfileBookmarkQueryService profileBookmarkQueryService;
    private final ProfileBookmarkCommandService profileBookmarkCommandService;
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
     * @param name 유저에게 입력 받은 본인 이름
     * @return 탈퇴 처리 결과
     */
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public CustomResponse<?> deleteProfile(@Parameter(hidden = true) @MemberInfo Long id,
                                           @RequestBody String name) {
        profileCommandService.deleteProfile(id, name);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }


    /**
     * 북마크 목록 조회
     *
     * @return 북마크 목록
     */
    @Operation(summary = "북마크 목록 조회")
    @GetMapping("/bookmarks")
    public CustomResponse<?> getBookmarks(@Parameter(hidden = true) @MemberInfo Long id,
                                          @RequestParam(value = "cursor", defaultValue = "0") Long cursor,
                                          @RequestParam(value = "offset", defaultValue = "10") int offset) {

        Slice<Bookmark> bookmarkList = profileBookmarkQueryService.getBookmarks(cursor, offset, id);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileBookmarkConverter.toBookmarkSliceResponseDTO(bookmarkList));
    }

    /**
     * 특정 북마크 삭제
     *
     * @param bookmarkId 삭제할 북마크의 ID
     * @return 북마크 삭제 결과
     */
    @Operation(summary = "특정 북마크 삭제")
    @DeleteMapping("/bookmarks/{bookmarkId}")
    public CustomResponse<?> deleteBookmark(@Parameter(hidden = true) @MemberInfo Long id,
                                            @PathVariable Long bookmarkId) {
        profileBookmarkCommandService.deleteBookmark(id, bookmarkId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }

}



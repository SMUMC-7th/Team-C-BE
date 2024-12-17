package umc.teamc.youthStepUp.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.firebase.service.FCMService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberInitProfileRequestDTO;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.service.MemberService;
import umc.teamc.youthStepUp.profile.converter.ProfileBookmarkConverter;
import umc.teamc.youthStepUp.profile.converter.ProfileConverter;
import umc.teamc.youthStepUp.profile.dto.request.DeleteMemberRequestDTO;
import umc.teamc.youthStepUp.profile.dto.request.DuplicateCheckRequestDTO;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO;
import umc.teamc.youthStepUp.profile.dto.response.DuplicateCheckResponseDTO;
import umc.teamc.youthStepUp.profile.service.command.ProfileBookmarkCommandService;
import umc.teamc.youthStepUp.profile.service.command.ProfileCommandService;
import umc.teamc.youthStepUp.profile.service.query.ProfileBookmarkQueryService;

@Tag(name = "프로필 API")
@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileBookmarkQueryService profileBookmarkQueryService;
    private final ProfileBookmarkCommandService profileBookmarkCommandService;
    private final MemberService memberService;
    private final FCMService fcmService;

    @PostMapping("/init-profile")
    @Operation(summary = "프로필 초기 설정", description = "닉네임, 나이, 교육, 키워드, 지역, 전공을 받아 초기 프로필을 설정한다.")
    public CustomResponse<?> initProfile(@MemberInfo Member member,
                                         @Valid @RequestBody MemberInitProfileRequestDTO dto) {
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, memberService.initProfile(member, dto));
    }

    @PostMapping("/duplicate-nickname")
    @Operation(summary = "닉네임 중복 검사", description = "현재 닉네임이 존재하는지 확인한다.")
    public CustomResponse<?> duplicateName(@MemberInfo Member member,
                                           @Valid @RequestBody DuplicateCheckRequestDTO dto) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                new DuplicateCheckResponseDTO(memberService.checkDuplicateNickName(member, dto)));
    }

    /**
     * 알림 조회
     */
    @Operation(summary = "알림 조회")
    @GetMapping("/alarm")
    public CustomResponse<?> getProfile(@MemberIdInfo Long memberId,
                                        @RequestParam(value = "cursor", defaultValue = "29302402390319") Long cursorId,
                                        @RequestParam(value = "offset", defaultValue = "10") int offset) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, fcmService.getMyAlarm(memberId, cursorId, offset));
    }

    /**
     * 프로필 조회
     *
     * @return 프로필 정보
     */
    @Operation(summary = "프로필 기본 조회")
    @GetMapping
    public CustomResponse<?> getProfile(@MemberInfo Member member) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileResponse(member));
    }

    /**
     * 프로필 상세 조회
     *
     * @return 프로필 상세 정보
     */
    @Operation(summary = "프로필 상세 조회")
    @GetMapping("/detail")
    public CustomResponse<?> getProfileDetail(@MemberInfo Member member) {
//        Member member = profileQueryService.getProfile();
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileDetailResponse(member));
    }

    /**
     * 프로필 수정
     *
     * @param request 프로필 수정 요청 DTO
     * @return 프로필 수정 결과
     */
    @Operation(summary = "프로필 수정")
    @PutMapping
    public CustomResponse<?> updateProfile(@MemberInfo Member member,
                                           @RequestBody UpdateProfileRequestDTO request) {
        Member updateMember = profileCommandService.updateProfile(member, request);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileDetailResponse(updateMember));
    }

    /**
     * 회원 탈퇴
     *
     * @param name 유저에게 입력 받은 본인 이름
     * @return 탈퇴 처리 결과
     */
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public CustomResponse<?> deleteProfile(@MemberInfo Member member,
                                           @Valid @RequestBody DeleteMemberRequestDTO dto) {
        profileCommandService.deleteProfile(member, dto.nickName());
        return CustomResponse.onSuccess(GeneralSuccessCode.DELETED);
    }


    /**
     * 북마크 목록 조회
     *
     * @return 북마크 목록
     */
    @Operation(summary = "북마크 목록 조회")
    @GetMapping("/bookmarks")
    @Parameters({
            @Parameter(name = "cursor", description = "cursor값을 입력해주세요. 기본값은 0입니다."),
            @Parameter(name = "month", description = "offset값을 입력해주세요. 기본값은 10입니다.")
    })
    public CustomResponse<?> getBookmarks(@MemberIdInfo Long id,
                                          @RequestParam(value = "cursor", defaultValue = "0") Long cursor,
                                          @RequestParam(value = "offset", defaultValue = "10") int offset) {

        Slice<BookmarkResponseDTO> bookmarkList = profileBookmarkQueryService.getBookmarks(cursor, offset, id);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ProfileBookmarkConverter.toBookmarkSliceResponseDTO(bookmarkList));
    }

    /**
     * 특정 북마크 삭제
     *
     * @param bookmarkId 삭제할 북마크의 ID
     * @return 북마크 삭제 결과
     */
    @Operation(summary = "특정 북마크 삭제")
    @DeleteMapping("/bookmarks/{srchPolicyId}")
    @Parameter(name = "srchPolicyId", description = "북마크 되어있는 정책 중 삭제하고자 하는 정책의 srchPolicyId를 입력해주세요.")
    public CustomResponse<?> deleteBookmark(@MemberIdInfo Long id,
                                            @PathVariable String srchPolicyId) {
        profileBookmarkCommandService.deleteBookmark(id, srchPolicyId);
        return CustomResponse.onSuccess(GeneralSuccessCode.DELETED);
    }

}



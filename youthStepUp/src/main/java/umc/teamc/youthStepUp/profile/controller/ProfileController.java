package umc.teamc.youthStepUp.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.converter.BookmarkConverter;
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

    /**
     * 프로필 조회
     *
     * @return 프로필 정보
     */
    @Operation(summary = "프로필 기본 조회")
    @GetMapping
    public CustomResponse<?> getProfile() {
        Long memberId = 1L;//헤더에서 가져오는 구문, @RequestHeader("Authorization") String token매개변수에 이런식으로 가져옴
        Member member = profileQueryService.getProfile(memberId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileResponse(member));
    }

    /**
     * 프로필 상세 조회
     *
     * @return 프로필 상세 정보
     */
    @Operation(summary = "프로필 상세 조회")
    @GetMapping("/detail")
    public CustomResponse<?> getProfileDetail() {
        Long memberId = 1L;//헤더에서 가져오는 구문
        Member member = profileQueryService.getProfile(memberId);
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
    public CustomResponse<?> updateProfile(@RequestBody UpdateProfileRequestDTO request) {
        Long memberId = 1L;
        Member member = profileCommandService.updateProfile(memberId, request);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ProfileConverter.toProfileDetailResponse(member));
    }

    /**
     * 회원 탈퇴
     *
     * @return 탈퇴 처리 결과
     */
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public CustomResponse<?> deleteProfile(@RequestBody String name) {
        Long memberId = 1L;
        profileCommandService.deleteProfile(memberId, name);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }

    /**
     * 북마크 목록 조회
     *
     * @return 북마크 목록
     */
    @Operation(summary = "북마크 목록 조회")
    @GetMapping("/bookmarks")
    public CustomResponse<?> getBookmarks(@RequestParam(value = "cursor", defaultValue = "0") Long cursor,
                                          @RequestParam(value = "offset", defaultValue = "10") int offset) {
        Long memberId = 1L;
        Slice<Bookmark> bookmarkList = profileQueryService.getBookmarks(cursor, offset, memberId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                BookmarkConverter.toBookmarkSliceResponseRecord(bookmarkList));
    }

    /**
     * 특정 북마크 삭제
     *
     * @param bookmarkId 삭제할 북마크의 ID
     * @return 북마크 삭제 결과
     */
    @Operation(summary = "특정 북마크 삭제")
    @DeleteMapping("/bookmarks/{bookmarkId}")
    public CustomResponse<?> deleteBookmark(@PathVariable Long bookmarkId) {
        profileCommandService.deleteBookmark(bookmarkId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }
}

package umc.teamc.youthStepUp.calendar.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.teamc.youthStepUp.calendar.converter.BookmarkConverter;
import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.service.command.CalendarBookmarkCommandService;
import umc.teamc.youthStepUp.calendar.service.query.CalendarBookmarkQueryService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;

import java.util.List;

@Tag(name = "캘린더 API")
@RestController
@RequestMapping("/users/policies")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarBookmarkQueryService bookmarkQueryService;
    private final CalendarBookmarkCommandService bookmarkCommandService;

    /**
     * 해당하는 월에 시작되거나 종료하는 일자가 있는 정책들을 전부 가져옴
     *
     * @param year  연도
     * @param month 월
     * @return 해당 월에 대한 정책 목록
     */
    @Operation(summary = "월 기준 정책 검색")
    @GetMapping("/month")
    public CustomResponse<?> getPoliciesByMonth(
            @RequestParam int year,
            @RequestParam int month) {
        String toMonth = year + "-" + month;
        List<Bookmark> bookmarks = bookmarkQueryService.findByPolicyPeriodMonth(toMonth);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, BookmarkConverter.toBookmarkResponseByMonthListRecord(bookmarks));
    }

    /**
     * 특정 날짜에 해당하는 북마크를 가져옴
     *
     * @param date 조회할 날짜 (yyyy-MM-dd 형식)
     * @return 해당 날짜의 북마크 목록
     */
    @Operation(summary = "일 기준 정책 검색")
    @GetMapping("/date")
    public CustomResponse<?> getBookmarksByDate(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int date) {
        String toDate = year + "-" + month + "-" + date;
        List<Bookmark> bookmarks = bookmarkQueryService.findByPolicyPeriodDate(toDate);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, BookmarkConverter.toBookmarkResponseByDateListRecord(bookmarks));
    }

    /**
     * 정책의 완료 상태를 업데이트
     *
     * @param request 완료 상태 업데이트 요청 DTO
     * @return 정책 완료 상태 업데이트 결과
     */
    @Operation(summary = "정책 완료 여부 수정")
    @PatchMapping("/is-complete")
    public CustomResponse<?> updatePolicyCompletionStatus(@RequestBody UpdateBookmarkCompletionDTO request) {
        Bookmark bookmark = bookmarkCommandService.updateIsCompleted(request.bookmarkId(), request.isComplete());

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, BookmarkConverter.toBookmarkResponseByDateRecord(bookmark));
    }
}
package umc.teamc.youthStepUp.calendar.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;
import umc.teamc.youthStepUp.calendar.converter.BookmarkConverter;
import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO;
import umc.teamc.youthStepUp.calendar.service.command.CalendarBookmarkCommandService;
import umc.teamc.youthStepUp.calendar.service.query.CalendarBookmarkQueryService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.validation.annotation.CheckDay;
import umc.teamc.youthStepUp.validation.annotation.CheckMonth;
import umc.teamc.youthStepUp.validation.annotation.CheckYear;

import java.time.LocalDate;
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
    @Parameters({
            @Parameter(name = "year", description = "연도(year)를 입력해주세요"),
            @Parameter(name = "month", description = "달(month)을 입력해주세요")
    })
    public CustomResponse<?> getPoliciesByMonth(
            @Parameter(hidden = true) @MemberIdInfo Long id,
            @CheckYear @RequestParam int year,
            @CheckMonth @RequestParam int month) {

        LocalDate targetMonth = LocalDate.of(year, month, 1);

        List<BookmarkResponseByMonthDTO> bookmarkPolicies = bookmarkQueryService.findByPolicyPeriodMonth(id, targetMonth);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                BookmarkConverter.toBookmarkResponseByMonthListRecord(bookmarkPolicies));
    }

    /**
     * 특정 날짜에 해당하는 북마크를 가져옴
     *
     * @param date 조회할 날짜 (yyyy-MM-dd 형식)
     * @return 해당 날짜의 북마크 목록
     */
    @Operation(summary = "일 기준 정책 검색")
    @GetMapping("/date")
    @Parameters({
            @Parameter(name = "year", description = "연도(year)를 입력해주세요"),
            @Parameter(name = "month", description = "달(month)을 입력해주세요"),
            @Parameter(name = "date", description = "일(day)을 입력해주세요")
    })
    public CustomResponse<?> getBookmarksByDate(
            @Parameter(hidden = true) @MemberIdInfo Long id,
            @CheckYear @RequestParam int year,
            @CheckMonth @RequestParam int month,
            @CheckDay @RequestParam int date) {

        LocalDate targetDate = LocalDate.of(year, month, date);
        List<BookmarkResponseByDateDTO> bookmarkPolicies = bookmarkQueryService.findByPolicyPeriodDate(id, targetDate);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                BookmarkConverter.toBookmarkResponseByDateListRecord(bookmarkPolicies));
    }

    /**
     * 정책의 완료 상태를 업데이트
     *
     * @param request 완료 상태 업데이트 요청 DTO
     * @return 정책 완료 상태 업데이트 결과
     */
    @Operation(summary = "정책 완료 여부 수정")
    @PatchMapping("/is-complete")
    public CustomResponse<?> updatePolicyCompletionStatus(@Parameter(hidden = true) @MemberIdInfo Long id,
                                                          @RequestBody UpdateBookmarkCompletionDTO request) {
        BookMarkPolicy bookmarkPolicy = bookmarkCommandService.updateIsCompleted(id, request);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                BookmarkConverter.toUpdatedBookmarkIsCompletedRecord(bookmarkPolicy));
    }
}
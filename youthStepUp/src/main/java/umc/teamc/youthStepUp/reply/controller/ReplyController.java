package umc.teamc.youthStepUp.reply.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.firebase.dto.MessagePushServiceRequest;
import umc.teamc.youthStepUp.firebase.service.FCMService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPageListResponseDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPostDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyResponseDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyResponseUpdateDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;
import umc.teamc.youthStepUp.reply.service.command.ReplyCommandService;
import umc.teamc.youthStepUp.reply.service.query.ReplyQueryService;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 API 입니다.")
@RequestMapping("/replies")
@Slf4j
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;
    private final FCMService fcmService;

    @PostMapping("/articles")
    @Operation(method = "POST", summary = "댓글 생성 API")
    public CustomResponse<?> createReply(
            @RequestBody ReplyCreateRequestDTO dto,
            @MemberInfo Member member) {

        ReplyPostDTO replyDTO = replyCommandService.createReplyDTO(dto, member);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, new ReplyResponseDTO(replyDTO.reply()));
    }

    @GetMapping("/articles/{articleId}")
    @Operation(method = "GET", summary = "커뮤니티 글에 따른 댓글 조회 생성 API")
    @Parameters({
            @Parameter(name = "cursorId", description = "cursorId를 입력해주세요. 필수로 입력해야 하는 값은 아닙니다."),
            @Parameter(name = "pageSize", description = "pageSize를 입력해주세요. 기본 값은 10입니다."),
            @Parameter(name = "articleId", description = "댓글을 작성하고자 하는 글의 articleId를 입력해주세요.")
    })
    public CustomResponse<?> getRepliesByArticleId(
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable(name = "articleId") Long articleId,
            @MemberInfo Member member
    ) {
        ReplyPageListResponseDTO replies = replyQueryService.getRepliesByArticleId(articleId, cursorId, pageSize);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }

    @DeleteMapping("/{replyId}")
    @Operation(method = "DELETE", summary = "댓글 삭제 API")
    @Parameter(name = "replyId", description = "삭제하고자 하는 댓글의 replyId를 입력해주세요.")
    public CustomResponse<?> deleteReply(
            @MemberInfo Member member,
            @PathVariable("replyId") Long replyId) {
        replyCommandService.deleteReply(member, replyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, replyId);
    }

    @PutMapping("{replyId}")
    @Operation(method = "PUT", summary = "댓글 수정 API")
    @Parameter(name = "replyId", description = "수정하고자 하는 댓글의 replyId를 입력해주세요.")
    public CustomResponse<?> updateReply(
            @MemberInfo Member member,
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyUpdateRequestDTO dto) {
        Reply reply = replyCommandService.updateReply(member, replyId, dto);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, new ReplyResponseUpdateDTO(reply));
    }
}
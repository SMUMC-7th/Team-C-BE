package umc.teamc.youthStepUp.reply.service.command;

import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPostDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;

public interface ReplyCommandService {

    ReplyPostDTO createReply(ReplyCreateRequestDTO dto, Long memberId);

    Reply updateReply(Long replyId, ReplyUpdateRequestDTO dto);

    void deleteReply(Long replyId);
}

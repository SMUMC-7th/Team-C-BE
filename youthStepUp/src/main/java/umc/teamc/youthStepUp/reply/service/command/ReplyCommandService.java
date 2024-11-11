package umc.teamc.youthStepUp.reply.service.command;

import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;

public interface ReplyCommandService {

    Reply createReply(ReplyCreateRequestDTO dto);

    Reply updateReply(Long replyId, ReplyUpdateRequestDTO dto);

    void deleteReply(Long replyId);
}

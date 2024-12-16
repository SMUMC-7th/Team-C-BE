package umc.teamc.youthStepUp.reply.service.command;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPostDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;

public interface ReplyCommandService {

    ReplyPostDTO createReplyDTO(ReplyCreateRequestDTO dto, Member member);

    Reply updateReply(Long replyId, ReplyUpdateRequestDTO dto);

    void deleteReply(Long replyId);
}

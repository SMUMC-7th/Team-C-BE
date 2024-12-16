package umc.teamc.youthStepUp.reply.service.query;

import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPageListResponseDTO;


public interface ReplyQueryService {

    ReplyPageListResponseDTO getRepliesByArticleId(Long articleId, Long cursorId, int pageSize);
}

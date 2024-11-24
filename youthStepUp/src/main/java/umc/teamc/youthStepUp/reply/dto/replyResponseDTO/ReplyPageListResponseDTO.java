package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.reply.entity.Reply;

import java.util.List;

public record ReplyPageListResponseDTO(
        Boolean hasNext,
        List<ReplyResponseDTO> replyList,
        Long nextCursorId
) {
    public static ReplyPageListResponseDTO from(Slice<Reply> replies) {

        List<ReplyResponseDTO> replyList = replies.getContent()
                .stream()
                .map(ReplyResponseDTO::new)
                .toList();

        Long nextCursorId = replies.hasNext()
                ? replies.getContent()
                .get(replies.getNumberOfElements() - 1)
                .getId() : 0;

        return new ReplyPageListResponseDTO(replies.hasNext(), replyList, nextCursorId);
    }
}

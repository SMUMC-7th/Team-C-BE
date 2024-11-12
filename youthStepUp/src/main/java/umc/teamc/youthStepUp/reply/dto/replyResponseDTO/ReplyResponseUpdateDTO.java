package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import umc.teamc.youthStepUp.reply.entity.Reply;

import java.time.LocalDateTime;

public record ReplyResponseUpdateDTO(
        Long replyId,
        String content,
        LocalDateTime createdAt

) {
    public ReplyResponseUpdateDTO(Reply reply) {
        this(
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt()
        );
    }
}

package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import umc.teamc.youthStepUp.reply.entity.Reply;

import java.time.LocalDateTime;

public record ReplyResponseDTO(
        String nickName,
        String content,
        Long replyId,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt
) {
    public ReplyResponseDTO(Reply reply) {
        this(
                reply.getMember().getNickName(),
                reply.getContent(),
                reply.getId(),
                reply.getCreatedAt()
        );
    }
}

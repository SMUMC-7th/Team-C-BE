package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import umc.teamc.youthStepUp.reply.entity.Reply;

import java.time.LocalDateTime;

public record ReplyResponseUpdateDTO(
        Long replyId,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt

) {
    public ReplyResponseUpdateDTO(Reply reply) {
        this(
                reply.getId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }
}

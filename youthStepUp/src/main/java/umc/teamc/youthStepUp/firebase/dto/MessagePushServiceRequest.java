package umc.teamc.youthStepUp.firebase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import umc.teamc.youthStepUp.firebase.domain.FCMMessage;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPostDTO;

@Builder(access = AccessLevel.PRIVATE)
public record MessagePushServiceRequest(
        String targetToken,
        String title,
        String body
) {
    public static MessagePushServiceRequest of(String token, String title, String body) {
        return MessagePushServiceRequest.builder()
                .targetToken(token)
                .title(title)
                .body(body)
                .build();
    }

    public static MessagePushServiceRequest of(ReplyPostDTO dto) {
        return MessagePushServiceRequest.builder()
                .targetToken(dto.article().getMember().getDeviceToken())
                .title(FCMMessage.REPLY_TITLE.getValue())
                .body(FCMMessage.REPLY_COMMENT.format(dto.reply().getMember().getNickName(), dto.reply().getContent()))
                .build();
    }
}

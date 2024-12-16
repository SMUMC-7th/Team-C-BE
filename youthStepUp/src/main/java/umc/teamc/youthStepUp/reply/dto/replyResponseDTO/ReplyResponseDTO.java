package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import umc.teamc.youthStepUp.reply.entity.Reply;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public record ReplyResponseDTO(
        String nickName,
        String content,
        Long replyId,
        Long parentId,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
        List<ReplyResponseDTO> children,
        String imgUrl
) {
    public ReplyResponseDTO(Reply reply) {
        this(
                reply.getDeletedAt() != null ? "알 수 없음" : reply.getMember().getNickName(),
                reply.getDeletedAt() != null ? "삭제된 메시지 입니다." : reply.getContent(),
                reply.getId(),
                reply.getParentReply() != null ? reply.getParentReply().getId() : null,
                reply.getCreatedAt(),
                reply.getChildrenReply() == null ? new ArrayList<>() : reply.getChildrenReply().stream().map(ReplyResponseDTO::new).toList(),
                reply.getDeletedAt() != null ? "http://img1.kakaocdn.net/thumb/R640x640.q70/?fname=http://t1.kakaocdn.net/account_images/default_profile.jpeg" : reply.getMember().getImgUrl()
        );

    }
    public static List<ReplyResponseDTO> buildReplyTree(List<Reply> replies) {
        Map<Long, ReplyResponseDTO> map = new HashMap<>();
        List<ReplyResponseDTO> rootReplies = new ArrayList<>();

        for (Reply reply : replies) {
            // reply -> responseDTO
            ReplyResponseDTO dto = new ReplyResponseDTO(reply);
            map.put(reply.getId(), dto);

            if (reply.getParentReply() == null) {
                // 첫 댓글
                rootReplies.add(dto);
            } else {
                // 대댓글
                ReplyResponseDTO parentDTO = map.get(reply.getParentReply().getId());
                if (parentDTO != null) {
                    parentDTO.children().add(dto);
                }
            }
        }
        return rootReplies;
    }
}

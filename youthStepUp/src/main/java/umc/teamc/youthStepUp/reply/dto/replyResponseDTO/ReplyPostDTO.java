package umc.teamc.youthStepUp.reply.dto.replyResponseDTO;

import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.reply.entity.Reply;

public record ReplyPostDTO(
        Reply reply,
        Article article
) {
}

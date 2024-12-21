package umc.teamc.youthStepUp.reply.dto.replyRequestDTO;

import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.entity.Reply;

public record ReplyCreateRequestDTO(
        Long articleId,
        String content,
        Long parentId
) {
    public Reply toReply(ReplyCreateRequestDTO dto, Member member, Article article, Reply parentReply) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .member(member)
                .parentReply(parentReply)
                .build();
    }

}

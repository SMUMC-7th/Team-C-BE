package umc.teamc.youthStepUp.reply.dto.replyRequestDTO;

import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.entity.Reply;

public record ReplyCreateRequestDTO(
        Long articleId,
        String content,
        String nickName
) {
    public Reply toReply(ReplyCreateRequestDTO dto, Long memberId, Article article) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .member(Member.builder().nickName(this.nickName).id(memberId).build())
                .build();
    }

}

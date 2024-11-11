package umc.teamc.youthStepUp.reply.dto.replyRequestDTO;

import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.entity.Reply;

public record ReplyCreateRequestDTO(
        Long articleId,
        String content,
        String nickname,
        Long memberId
) {
    public Reply toReply(ReplyCreateRequestDTO dto, Article article) {
        return Reply.builder()
                .article(article)
                .content(dto.content())
                .member(Member.builder().nickName(this.nickname).id(this.memberId()).build())
                .build();
    }

}

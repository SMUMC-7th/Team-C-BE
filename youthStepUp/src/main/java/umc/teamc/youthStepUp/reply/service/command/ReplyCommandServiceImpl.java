package umc.teamc.youthStepUp.reply.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;
import umc.teamc.youthStepUp.reply.error.exceptiion.ReplyErrorCode;
import umc.teamc.youthStepUp.reply.error.exceptiion.ReplyErrorException;
import umc.teamc.youthStepUp.reply.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Reply createReply(ReplyCreateRequestDTO dto, Long memberId) {

        Article article = articleRepository.findById(dto.articleId()).orElseThrow(
                () -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));

        return replyRepository.save(dto.toReply(dto, memberId, article));
    }

    @Override
    public Reply updateReply(Long replyId, ReplyUpdateRequestDTO dto) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new ReplyErrorException((ReplyErrorCode.NOT_FOUND)));
        reply.updateArticle(dto.content());

        return reply;
    }

    @Override
    public void deleteReply(Long replyId) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyErrorException(ReplyErrorCode.NOT_FOUND));
        replyRepository.deleteById(reply.getId());
    }
}

package umc.teamc.youthStepUp.reply.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;
import umc.teamc.youthStepUp.firebase.domain.FCMMessage;
import umc.teamc.youthStepUp.firebase.dto.MessagePushServiceRequest;
import umc.teamc.youthStepUp.firebase.service.FCMService;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyCreateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyRequestDTO.ReplyUpdateRequestDTO;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPostDTO;
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
    private final FCMService fcmService;

    @Override
    public ReplyPostDTO createReplyDTO(ReplyCreateRequestDTO dto, Member member) {

        Article article = articleRepository.findById(dto.articleId()).orElseThrow(
                () -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));

        // 부모 댓글 조회
        Reply parnetReply = dto.parentId() != null ?
                replyRepository.findById(dto.parentId()).orElseThrow(
                        () -> new ReplyErrorException(ReplyErrorCode.NOT_FOUND)
                ) : null;

        Reply reply = replyRepository.save(dto.toReply(dto, member.getId(), article, parnetReply));

        article.incrementReplyCount();

        ReplyPostDTO replyDTO = new ReplyPostDTO(reply, article);
        if (reply.getParentReply() == null) {
            fcmService.pushMessage(replyDTO.article().getMember(), MessagePushServiceRequest.of(replyDTO));
        } else {
            fcmService.pushMessage(
                    replyDTO.reply().getParentReply().getMember(),
                    MessagePushServiceRequest.of(replyDTO.reply().getParentReply().getMember().getDeviceToken(),
                                FCMMessage.REPLY_TITLE.getValue(),
                                FCMMessage.REPLY_COMMENT.format(
                                        reply.getMember().getNickName(),
                                        reply.getContent()
                            )
                    )
            );
        }

        return replyDTO;
    }

    @Override
    public Reply updateReply(Long replyId, ReplyUpdateRequestDTO dto) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new ReplyErrorException((ReplyErrorCode.NOT_FOUND)));
        reply.updateReply(dto.content());
        return reply;
    }

    @Override
    public void deleteReply(Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyErrorException(ReplyErrorCode.NOT_FOUND));

        reply.getArticle().decrementReplyCount();

        reply.delete();
    }
}

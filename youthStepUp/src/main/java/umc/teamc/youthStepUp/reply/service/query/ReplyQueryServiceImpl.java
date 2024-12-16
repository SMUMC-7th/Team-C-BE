package umc.teamc.youthStepUp.reply.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;
import umc.teamc.youthStepUp.reply.dto.replyResponseDTO.ReplyPageListResponseDTO;
import umc.teamc.youthStepUp.reply.entity.Reply;
import umc.teamc.youthStepUp.reply.repository.ReplyRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyPageListResponseDTO getRepliesByArticleId(Long articleId, Long cursorId, int pageSize) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND)
        );

        Pageable pageable = PageRequest.of(0, pageSize);

        Slice<Reply> replySlice = replyRepository.findRepliesByArticleOrderByCreatedAtAsc(articleId, cursorId, pageable);
        return ReplyPageListResponseDTO.from(replySlice);
    }
}

package umc.teamc.youthStepUp.article.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.dto.ArticleRequestDTO;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(dto.toEntity());
    }

    @Override
    public Article updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));

        article.updateArticle(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public Article deleteArticle(Long articleId) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));

        articleRepository.deleteById(articleId);

        return article;
    }
}

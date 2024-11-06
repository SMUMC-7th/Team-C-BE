package umc.teamc.youthStepUp.article.service.command;

import umc.teamc.youthStepUp.article.dto.ArticleRequestDTO;
import umc.teamc.youthStepUp.article.entity.Article;


public interface ArticleCommandService {

    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);

    Article updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto);

    Article deleteArticle(Long articleId);

}

package umc.teamc.youthStepUp.article.service.query;

import org.springframework.data.domain.Page;
import umc.teamc.youthStepUp.article.entity.Article;

public interface ArticleQueryService {

    Article getArticle(Long articleId);

    Page<Article> getArticles(int pageNumber, int pageSize);

}

package umc.teamc.youthStepUp.article.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.article.entity.Article;

public interface ArticleQueryService {

    Article getArticle(Long articleId);

    Slice<Article> getArticles(Long cursorId, int pageSize);

}

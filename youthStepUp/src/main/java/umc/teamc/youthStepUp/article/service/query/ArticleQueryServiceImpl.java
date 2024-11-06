package umc.teamc.youthStepUp.article.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleQueryServiceImpl implements ArticleQueryService{

    private final ArticleRepository articleRepository;

    @Override
    public Article getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));
    }

    @Override
    public Page<Article> getArticles(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return articleRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

}

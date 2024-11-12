package umc.teamc.youthStepUp.article.repository;/*
package umc.teamc.youthStepUp.article.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import umc.teamc.youthStepUp.article.entity.Article;

import java.util.List;

import static umc.teamc.youthStepUp.article.entity.QArticle.article;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Article> findAllArticles(Long cursorId, Pageable pageable) {
        List<Article> articles = jpaQueryFactory
                .selectFrom(article)
                .where(cursorIdCondition(cursorId))
                .orderBy(article.createdAt.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();


        boolean hasNext = articles.size() > pageable.getPageSize();
        if (hasNext) { // 다음 있는가
            articles.remove(articles.size() - 1); // 마지막 데이터 제거
        }

        return new SliceImpl<>(articles, pageable, hasNext);
    }

    private BooleanExpression cursorIdCondition(Long cursorId) {
        return cursorId != null ? article.id.lt(cursorId) : null;
    }
}
*/

package umc.teamc.youthStepUp.article.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> findByIdLessThanOrderByIdDesc(Long cursorId, Pageable pageable);
}

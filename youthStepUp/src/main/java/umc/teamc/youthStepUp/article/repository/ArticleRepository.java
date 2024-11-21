package umc.teamc.youthStepUp.article.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.teamc.youthStepUp.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.deletedAt IS NULL AND a.member.deletedAt IS NULL")
    Slice<Article> findByIdLessThanOrderByIdDesc(Long cursorId, Pageable pageable);
    
}

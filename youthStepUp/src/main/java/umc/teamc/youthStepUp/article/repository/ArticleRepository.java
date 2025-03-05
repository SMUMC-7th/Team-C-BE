package umc.teamc.youthStepUp.article.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.deletedAt IS NULL AND a.member.deletedAt IS NULL AND a.id < :cursorId ORDER BY a.id DESC")
    Slice<Article> findAllByIdLessThanOrderByIdDesc(@Param("cursorId") Long cursorId, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.deletedAt IS NULL AND a.member.deletedAt IS NULL ORDER BY a.id DESC")
    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

}

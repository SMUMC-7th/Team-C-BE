package umc.teamc.youthStepUp.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.teamc.youthStepUp.article.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

//    List<Article> findAllByDeletedAtIsNull(); // 삭제되지 않은 Article만 조회

    Page<Article> findAllByOrderByCreatedAtDesc(Pageable pageable);
}

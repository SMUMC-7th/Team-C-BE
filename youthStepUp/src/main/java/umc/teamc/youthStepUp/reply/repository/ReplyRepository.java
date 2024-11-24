package umc.teamc.youthStepUp.reply.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

//    Slice<Reply> findByIdLessThanOrderByIdDesc(Long cursorId, Pageable pageable);

    @Query("SELECT r" +
            " FROM Reply r" +
            " WHERE r.article.id = :articleId" +
            " AND r.member.deletedAt IS NULL" +
            " AND (:cursorId IS NULL OR r.id > :cursorId)" +
            " ORDER BY r.createdAt ASC")
    Slice<Reply> findRepliesByArticleOrderByCreatedAtAsc(@Param("articleId") Long articleId,
                                                         @Param("cursorId") Long cursorId,
                                                         Pageable pageable);

}



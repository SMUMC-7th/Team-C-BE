package umc.teamc.youthStepUp.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.teamc.youthStepUp.global.common.BaseEntity;
import umc.teamc.youthStepUp.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    /*@Column(name = "deleted_at")
    @ColumnDefault("NULL")
    private LocalDateTime deletedAt; // NULL이 아닌 경우 삭제하는 방식? - soft delete
    // soft delete 처리 메서드 필요함*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

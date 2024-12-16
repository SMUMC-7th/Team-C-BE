package umc.teamc.youthStepUp.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import umc.teamc.youthStepUp.global.entity.BaseEntity;
import umc.teamc.youthStepUp.member.entity.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@SQLDelete(sql = "UPDATE article SET deleted_at = CURRENT_TIME where article_id = ?")
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "count", nullable = false)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void incrementReplyCount() {
        this.count += 1;
    }

    public void decrementReplyCount() {
        if (this.count > 0) {
            this.count -= 1;
        }
    }
}

package umc.teamc.youthStepUp.reply.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.global.entity.BaseEntity;
import umc.teamc.youthStepUp.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "Reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parentReply; // 부모 댓글

    @OneToMany(mappedBy = "parentReply", orphanRemoval = true)
    private List<Reply> childrenReply = new ArrayList<>();

    public void updateReply(String content) {
        this.content = content;
    }

    public void delete() {
        deleteEntity();
    }
}

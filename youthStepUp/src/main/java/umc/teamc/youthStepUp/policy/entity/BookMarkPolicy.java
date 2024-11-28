package umc.teamc.youthStepUp.policy.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.global.entity.BaseEntity;
import umc.teamc.youthStepUp.member.entity.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "book_mark_policy")
public class BookMarkPolicy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "is_completed")
    Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    public void updateIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}

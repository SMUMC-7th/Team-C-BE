package umc.teamc.youthStepUp.domain.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.global.entity.BaseEntity;
import umc.teamc.youthStepUp.member.entity.Member;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_completed")
    private boolean isCompleted;

    //시작,마감시간
    @Column(name = "policy_period")
    private String policyPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    //삭제시간이 필요한가 북마크도 소프트딜리트?
//    private LocalDateTime dateTime;


    public void updateIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}

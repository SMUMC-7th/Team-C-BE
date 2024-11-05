package umc.teamc.youthStepUp.domain.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.global.entity.BaseEntity;

import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Policy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "srch_policy_id")
    private Long srchPolicyId;

    //시작,마감시간
    @Column(name = "policy_period")
    private String policyPeriod;

    @OneToMany(mappedBy = "bookmark", cascade = CascadeType.ALL)
    private List<Bookmark> bookmark;


    //삭제시간이 필요한가 북마크도 소프트딜리트?
//    private LocalDateTime dateTime;


}

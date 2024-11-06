package umc.teamc.youthStepUp.calendar.entity;

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

    @Column(name = "srch_policy_id")
    private Long srchPolicyId;

    //시작,마감시간
    @Column(name = "policy_period")
    private String policyPeriod;

    @OneToMany(mappedBy = "bookmark", cascade = CascadeType.ALL)
    private List<Bookmark> bookmark;


}

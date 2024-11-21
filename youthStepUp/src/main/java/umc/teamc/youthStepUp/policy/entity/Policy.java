package umc.teamc.youthStepUp.policy.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.global.common.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "policy")
public class Policy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "srchPolicyId")
    String srchPolicyId;

    @Column(name = "deadline")
    String deadline;

}

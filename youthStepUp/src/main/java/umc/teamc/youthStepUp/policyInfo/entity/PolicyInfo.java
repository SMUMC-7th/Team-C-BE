package umc.teamc.youthStepUp.policyInfo.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.teamc.youthStepUp.global.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "policy_info")
public class PolicyInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "info_title")
    String title;

    @Column(name = "info_content")
    String content;

}

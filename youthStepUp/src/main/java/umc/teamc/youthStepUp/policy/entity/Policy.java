package umc.teamc.youthStepUp.policy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import umc.teamc.youthStepUp.global.entity.BaseEntity;

import java.time.LocalDate;

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

    @Column(name = "polyBizSjnm")
    String polyBizSjnm;

    @Column(name = "srchPolicyId")
    String srchPolicyId;

    @Column(name = "startDate")
    LocalDate startDate;

    @Column(name = "deadline")
    LocalDate deadline;

}

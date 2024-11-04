package umc.teamc.youthStepUp.global.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 자식 클래스에게 매핑 정보(@ManyToOne, @Column...)를 제공
@EntityListeners(AuditingEntityListener.class) // 엔티티의 생성 및 수정 시간을 자동으로 추적
@Getter
public class BaseEntity {

    // 해당 Column에 생성시간 자동 mapping
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 해당 Column에 수정시간 자동 mapping
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
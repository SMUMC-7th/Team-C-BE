package umc.teamc.youthStepUp.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

public enum Education {
    HIGH_SCHOOL("고등학교 졸업 이하"),
    UNDERGRADUATE("대학교 재학"),
    COLLEGE_GRADUATE("대학교 졸업");
    final String description;
    Education(String description){
        this.description =description;
    }
}

package umc.teamc.youthStepUp.member.entity;

public enum Education {
    HIGH_SCHOOL("고등학교 졸업 이하"),
    UNDERGRADUATE("대학교 재학"),
    COLLEGE_GRADUATE("대학교 졸업");
    final String description;

    Education(String description) {
        this.description = description;
    }

    public static Education toEducation(String description) {
        for (Education education : Education.values()) {
            if (education.description.equals(description)) {
                return education;
            }
        }
        return null;
    }
}

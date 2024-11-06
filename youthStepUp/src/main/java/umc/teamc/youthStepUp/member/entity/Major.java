package umc.teamc.youthStepUp.member.entity;

import java.util.List;

public enum Major {
    IT("IT"),
    ENGINEERING("공학"),
    NATURAL_SCIENCE("자연"),
    BUSINESS("경상"),
    HUMANITIES("인문"),
    ARTS_SPORTS("예체능");
    private final String description;

    Major(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<Major> toMajor(List<String> majors) {
        return majors.stream()
                .map(Major::isEqualTo)
                .toList();
    }

    private static Major isEqualTo(String majors) {
        for (Major major : Major.values()) {
            if (major.description.equals(majors)) {
                return major;
            }
        }
        return null;
    }
}

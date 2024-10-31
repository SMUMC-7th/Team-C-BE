package umc.teamc.youthStepUp.member.entity;

public enum Major {
    IT("IT"),
    ENGINEERING("공학"),
    NATURAL_SCIENCE("자연"),
    BUSINESS("경상"),
    HUMANITIES("인문"),
    ARTS_SPORTS( "예체능");
    private final String description;

    Major(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

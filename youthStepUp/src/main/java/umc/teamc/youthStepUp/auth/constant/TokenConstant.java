package umc.teamc.youthStepUp.auth.constant;

public enum TokenConstant {
    HEADER_PREFIX("Authorization"),
    HEADER_TYPE("Bearer "),
    ACCESS_TOKEN("accessToken"),
    REFRESH_TOKEN("refreshToken");
    private final String value;

    TokenConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

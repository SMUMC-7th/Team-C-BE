package umc.teamc.youthStepUp.auth.constant;

public enum URLConstant {
    LOGIN_URL("https://youthstepup.site/"),
    INIT_URL("https://youthstepup.site/settings"),
    //    INIT_URL("http://localhost:5173/settings"),
    HOME_URL("https://youthstepup.site/home");
    private final String value;

    URLConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

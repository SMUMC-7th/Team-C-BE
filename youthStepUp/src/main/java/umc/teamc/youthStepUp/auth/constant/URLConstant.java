package umc.teamc.youthStepUp.auth.constant;

public enum URLConstant {
    LOGIN_URL("https://youthstepup.vercel.app/"),
    INIT_URL("https://youthstepup.vercel.app/settings"),
    //    INIT_URL("http://localhost:5173/settings"),
    HOME_URL("https://youthstepup.vercel.app/home");
    private final String value;

    URLConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

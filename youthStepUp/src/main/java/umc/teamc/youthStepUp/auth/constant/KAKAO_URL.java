package umc.teamc.youthStepUp.auth.constant;

public enum KAKAO_URL {
    KAKAO_AUTH_URL("https://kauth.kakao.com/oauth/authorize"),
    KAKAO_TOKEN_URL("https://kauth.kakao.com/oauth/token"),
    KAKAO_LOGOUT_URL("https://kauth.kakao.com/oauth/logout"),
    KAKAO_INFO_URL("https://kapi.kakao.com/v2/user/me");
    private final String url;

    public String getUrl() {
        return url;
    }

    KAKAO_URL(String url) {
        this.url = url;
    }
}

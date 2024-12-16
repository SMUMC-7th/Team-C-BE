package umc.teamc.youthStepUp.auth.constant;

public enum OAUTH_URL {
    GOOGLE_AUTH_URL("https://accounts.google.com/o/oauth2/v2/auth"),
    GOOGLE_TOKEN_URL("https://oauth2.googleapis.com/token"),
    GOOGLE_INFO_URL("https://www.googleapis.com/userinfo/v2/me"),
    NAVER_AUTH_URI("https://nid.naver.com"),
    NAVER_API_URI("https://openapi.naver.com"),
    NAVER_TOKEN_URL("https://nid.naver.com/oauth2.0/token"),
    KAKAO_AUTH_URL("https://kauth.kakao.com/oauth/authorize"),
    KAKAO_TOKEN_URL("https://kauth.kakao.com/oauth/token"),
    KAKAO_LOGOUT_URL("https://kauth.kakao.com/oauth/logout"),
    KAKAO_INFO_URL("https://kapi.kakao.com/v2/user/me");
    private final String url;

    public String getUrl() {
        return url;
    }

    OAUTH_URL(String url) {
        this.url = url;
    }
}

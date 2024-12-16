package umc.teamc.youthStepUp.firebase.domain;

public enum FCMMessage {
    RE_REPLY_TITLE("대댓글 알림"),
    REPLY_TITLE("댓글 알림"),
    RE_REPLY_COMMENT("%s님이 대댓글을 달았어요: %s"),
    REPLY_COMMENT("%s님이 댓글을 달았어요: %s");
    private String value;

    public String format(Object... args) {
        return this.value.formatted(args);
    }

    FCMMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

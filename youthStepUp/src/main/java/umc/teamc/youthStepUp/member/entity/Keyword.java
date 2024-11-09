package umc.teamc.youthStepUp.member.entity;

import java.util.List;

public enum Keyword {
    JOBS("023010", "일자리 분야"),
    HOUSING("023020", "주거 분야"),
    EDUCATION("023030", "교육 분야"),
    WELFARE_CULTURE("023040", "복지.문화 분야"),
    PARTICIPATION_RIGHTS("023050", "참여.권리 분야");

    private final String code;
    private final String description;

    Keyword(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static List<Keyword> toKeyword(List<String> keywords) {
        return keywords.stream()
                .map(Keyword::isEqualTo)
                .toList();
    }

    private static Keyword isEqualTo(String keywords) {
        for (Keyword keyWord : Keyword.values()) {
            if (keyWord.description.equals(keywords)) {
                return keyWord;
            }
        }
        return null;
    }
}

package umc.teamc.youthStepUp.member.entity;

import java.util.List;

public enum Region {
    SEOUL("003002001", "서울"),
    BUSAN("003002002", "부산"),
    DAEGU("003002003", "대구"),
    INCHEON("003002004", "인천"),
    GWANGJU("003002005", "광주"),
    DAEJEON("003002006", "대전"),
    ULSAN("003002007", "울산"),
    GYEONGGI("003002008", "경기"),
    GANGWON("003002009", "강원"),
    CHUNGBUK("003002010", "충북"),
    CHUNGNAM("003002011", "충남"),
    JEONBUK("003002012", "전북"),
    JEONNAM("003002013", "전남"),
    GYEONGBUK("003002014", "경북"),
    GYEONGNAM("003002015", "경남"),
    JEJU("003002016", "제주"),
    SEJONG("003002017", "세종");

    private final String code;
    private final String description;

    Region(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static List<Region> toRegion(List<String> regions) {
        return regions.stream()
                .map(Region::isEqualTo)
                .toList();
    }

    private static Region isEqualTo(String regions) {
        for (Region region : Region.values()) {
            if (region.description.equals(regions)) {
                return region;
            }
        }
        return null;
    }

}

package umc.teamc.youthStepUp.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;
import umc.teamc.youthStepUp.member.repository.MemberRepository;
import umc.teamc.youthStepUp.member.service.MemberService;

public class EnumConverterTest {
    private MemberService memberService;
    private MemberRepository memberRepository;
    List<String> educations = List.of("HIGH_SCHOOL", "UNDERGRADUATE");


    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 로그인직후_사용자의_지역정보를_받고_ENUMLIST로_변환한다() {
        List<String> regions = List.of("서울", "부산");
        List<Region> toRegion = Region.toRegion(regions);
        assertThat(toRegion.size()).isEqualTo(2);
        assertThat(toRegion.get(0)).isEqualTo(Region.SEOUL);
        assertThat(toRegion.get(1)).isEqualTo(Region.BUSAN);
    }

    @Test
    void 로그인직후_사용자의_전공정보를_받고_ENUMLIST로_변환한다() {
        List<String> majors = List.of("IT", "예체능");
        List<Major> toMajors = Major.toMajor(majors);
        assertThat(toMajors.size()).isEqualTo(2);
        assertThat(toMajors.get(0)).isEqualTo(Major.IT);
        assertThat(toMajors.get(1)).isEqualTo(Major.ARTS_SPORTS);
    }

    @Test
    void 입력값이_잘못되었을시_null로_변환한다() {
        List<String> majors = List.of("ITE", "예채능", "에체능");
        List<Major> toMajors = Major.toMajor(majors);
        assertThat(toMajors.size()).isEqualTo(3);
        assertThat(toMajors.get(2)).isEqualTo(null);
        assertThat(toMajors.get(0)).isEqualTo(null);
        assertThat(toMajors.get(1)).isEqualTo(null);
    }

    @Test
    void 로그인직후_사용자의_키워드정보를_받고_ENUMLIST로_변환한다() {
        List<String> keywords = List.of("주거 분야", "교육 분야");
        List<Keyword> toKeyWords = Keyword.toKeyword(keywords);
        assertThat(toKeyWords.size()).isEqualTo(2);
        assertThat(toKeyWords.get(0)).isEqualTo(Keyword.HOUSING);
        assertThat(toKeyWords.get(1)).isEqualTo(Keyword.EDUCATION);
    }


}

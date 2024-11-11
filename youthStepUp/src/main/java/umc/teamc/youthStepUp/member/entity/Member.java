package umc.teamc.youthStepUp.member.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamc.youthStepUp.global.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nick_name")
    String nickName;
    @Column(name = "age")
    int age;
    @Column(name = "kakao_id")
    Long kakaoId;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "education")
    Education education;
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    @Column(name = "region")
    @Builder.Default
    List<Region> region = new ArrayList<>();
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    @Column(name = "major")
    @Builder.Default
    List<Major> major = new ArrayList<>();
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    @Column(name = "keyword")
    @Builder.Default
    List<Keyword> keyword = new ArrayList<>();

    public void editNickName(String nickName) {
        this.nickName = nickName;
    }

    public void editAge(int age) {
        this.age = age;
    }

    public void editKakaoId(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void editEducation(Education education) {
        this.education = education;
    }

    public void editRegion(List<Region> region) {
        this.region = region;
    }

    public void editMajor(List<Major> major) {
        this.major = major;
    }

    public void editKeyword(List<Keyword> keyword) {
        this.keyword = keyword;
    }
}

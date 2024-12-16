package umc.teamc.youthStepUp.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import umc.teamc.youthStepUp.global.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Where(clause = "deleted_at IS NULL")
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
    @Column(name = "social_id")
    String socialId;
    @Column(name = "profile_img")
    String imgUrl;
    @Column(name = "device_token")
    String deviceToken;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "education")
    Education education;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "region")
    @Builder.Default
    @ElementCollection
    List<Region> region = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    @Column(name = "major")
    @Builder.Default
    @ElementCollection
    List<Major> major = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    @Column(name = "keyword")
    @Builder.Default
    @ElementCollection
    List<Keyword> keyword = new ArrayList<>();

    public void editNickName(String nickName) {
        this.nickName = nickName;
    }

    public void editAge(int age) {
        this.age = age;
    }


    public void editEducation(Education education) {
        this.education = education;
    }

    public void editRegion(List<Region> region) {
        this.region.clear();
        this.region.addAll(region);
    }

    public void editMajor(List<Major> major) {
        this.major.clear();
        this.major.addAll(major);
    }

    public void editKeyword(List<Keyword> keyword) {
        this.keyword.clear();
        this.keyword.addAll(keyword);
    }

    public void editSocialId(String socialId) {
        this.socialId = socialId;
    }
}
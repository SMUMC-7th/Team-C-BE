package umc.teamc.youthStepUp.member.entity;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nick_name")
    String nickName;
    @Column(name = "age")
    int age;
    @Enumerated(value= EnumType.STRING)
    @Column(name = "education")
    Education education;
    @Enumerated(value= EnumType.STRING)
    @Column(name = "region")
    Region region;
    @Enumerated(value= EnumType.STRING)
    @Column(name = "category")
    Major major;
    @Enumerated(value= EnumType.STRING)
    @Column(name = "keyword")
    Keyword keyword;

}

package umc.teamc.youthStepUp.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "education")
    Education education;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "region")
    List<Region> region;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    List<Major> major;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "keyword")
    List<Keyword> keyword;

}

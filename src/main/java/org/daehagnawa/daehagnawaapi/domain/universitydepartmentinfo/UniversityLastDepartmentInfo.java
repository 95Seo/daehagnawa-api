package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Setter @Getter
@DynamicInsert
@Entity(name = "last_competition_ratio")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UniversityLastDepartmentInfo {

    @Id
    @Comment("기본키")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    // 대학이름
    @Comment("대학 이름")
    @Column(name = "university_name", columnDefinition = "varchar(100)", nullable = false)
    private String universityName;

    // 전형 구분
    @Comment("입학 전형 구분")
    @Column(name = "admission_type", columnDefinition = "varchar(100)", nullable = false)
    private String admissionType;

    // 학과이름
    @Comment("학과 이름")
    @Column(name = "department_name", columnDefinition = "TEXT", nullable = false)
    private String departmentName;

    // 모집인원
    @Comment("모집 인원")
    @Column(name = "recruitment_count", columnDefinition = "TEXT", nullable = false)
    private String recruitmentCount;

    // 지원인원
    @Comment("지원 인원")
    @Column(name = "applicants_count", columnDefinition = "TEXT", nullable = false)
    private String applicantsCount;

    // 경쟁률
    @Comment("경쟁룰")
    @Column(name = "competition_ratio", columnDefinition = "FLOAT", nullable = false)
    private float competitionRatio;

    @Comment("지역")
    @Column(name = "university_area", columnDefinition = "CHAR(2)", nullable = false)
    private String universityArea;

    @Comment("학위")
    @Column(name = "university_degree", columnDefinition = "CHAR(3)", nullable = false)
    private String universityDegree;

    @Comment("입시년도")
    @Column(name = "entrance_exam_year", columnDefinition = "INT", nullable = false)
    private int entranceExamYear;

    @Comment("원서 접수 사이트 URL")
    @Column(name = "reception_url", columnDefinition = "TEXT", nullable = false)
    private String receptionUrl;

    @Comment("크롤링 타입 구분 (uway, jinhak)")
    @Column(name = "type", columnDefinition = "VARCHAR(6)", nullable = false)
    private String type;

    public UniversityLastDepartmentInfo(
            Long id,
            String universityName,
            String admissionType,
            String departmentName,
            String recruitmentCount,
            String applicantsCount,
            float competitionRatio,
            String universityArea,
            String universityDegree,
            int entranceExamYear,
            String receptionUrl,
            String type
    ) {
        this.id = id;
        this.universityName = universityName;
        this.admissionType = admissionType;
        this.departmentName = departmentName;
        this.recruitmentCount = recruitmentCount;
        this.applicantsCount = applicantsCount;
        this.competitionRatio = competitionRatio;
        this.universityArea = universityArea;
        this.universityDegree = universityDegree;
        this.entranceExamYear = entranceExamYear;
        this.receptionUrl = receptionUrl;
        this.type = type;
    }
}

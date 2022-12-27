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

    // 지역
    @Comment("지역")
    @Column(name = "area", columnDefinition = "TEXT", nullable = true)
    private String area;

    // 학위
    @Comment("학위")
    @Column(name = "degree", columnDefinition = "TEXT", nullable = true)
    private String degree;

    @CreatedDate
    @Comment("데이터 생성일")
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Comment("데이터 수정일")
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    public UniversityLastDepartmentInfo(
            Long id,
            String universityName,
            String admissionType,
            String departmentName,
            String recruitmentCount,
            String applicantsCount,
            float competitionRatio,
            String area,
            String degree,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.universityName = universityName;
        this.admissionType = admissionType;
        this.departmentName = departmentName;
        this.recruitmentCount = recruitmentCount;
        this.applicantsCount = applicantsCount;
        this.competitionRatio = competitionRatio;
        this.area = area;
        this.degree = degree;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

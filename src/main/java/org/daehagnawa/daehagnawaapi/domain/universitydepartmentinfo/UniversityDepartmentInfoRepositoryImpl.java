package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.QBatchJobExecution.batchJobExecution;
import static org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.QUniversityDepartmentInfo.universityDepartmentInfo;
import static org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.QUniversityLastDepartmentInfo.universityLastDepartmentInfo;

public class UniversityDepartmentInfoRepositoryImpl implements UniversityDepartmentInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UniversityDepartmentInfoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UniversityDepartmentInfo> departmentSearch(String keyword, Pageable pageable) {
        List<UniversityDepartmentInfo> result = queryFactory
                .selectFrom(universityDepartmentInfo)
                .where(
                        universityDepartmentInfo.universityName.like("%" + keyword + "%")
                                .or(universityDepartmentInfo.departmentName.like("%" + keyword + "%"))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<UniversityLastDepartmentInfo> lastDepartmentSearch(String keyword, Pageable pageable) {
        List<UniversityLastDepartmentInfo> result = queryFactory
                .selectFrom(universityLastDepartmentInfo)
                .where(
                        universityLastDepartmentInfo.universityName.like("%"+keyword+"%")
                                .or(universityLastDepartmentInfo.departmentName.like("%"+keyword+"%"))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public BatchJobExecution get() {
        BatchJobExecution result = queryFactory
                .selectFrom(batchJobExecution)
                .where(batchJobExecution.status.eq("COMPLETED"))
                .orderBy(batchJobExecution.endTime.desc())
                .fetchFirst();

        return result;
    }


}

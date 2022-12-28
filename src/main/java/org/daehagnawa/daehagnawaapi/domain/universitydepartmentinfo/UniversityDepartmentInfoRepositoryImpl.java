package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
    public List<UniversityDepartmentInfo> departmentSearch(String keyword, String area, String degree, Pageable pageable) {
        List<UniversityDepartmentInfo> result = queryFactory
                .selectFrom(universityDepartmentInfo)
                .where(
                        universityDepartmentInfo.universityName.like("%" + keyword + "%")
                        .or(universityDepartmentInfo.departmentName.like("%" + keyword + "%"))
                        .and(universityDepartmentInfo.area.like("%" + area + "%"))
                        .and(universityDepartmentInfo.degree.like("%" + degree + "%"))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(currentDepartmentSort(pageable))
                .fetch();
        return result;
    }

    @Override
    public List<UniversityLastDepartmentInfo> lastDepartmentSearch(String keyword, String area, String degree, Pageable pageable) {
        List<UniversityLastDepartmentInfo> result = queryFactory
                .selectFrom(universityLastDepartmentInfo)
                .where(
                        universityLastDepartmentInfo.universityName.like("%" + keyword + "%")
                        .or(universityLastDepartmentInfo.departmentName.like("%" + keyword + "%"))
                        .and(universityLastDepartmentInfo.area.like("%" + area + "%"))
                        .and(universityLastDepartmentInfo.degree.like("%" + degree + "%"))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(lastDepartmentSort(pageable))
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

    private OrderSpecifier<?> currentDepartmentSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                return new OrderSpecifier(direction, universityDepartmentInfo.competitionRatio);
            }
        }

        return new OrderSpecifier(Order.DESC, universityDepartmentInfo.competitionRatio);
    }

    private OrderSpecifier<?> lastDepartmentSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                return new OrderSpecifier(direction, universityLastDepartmentInfo.competitionRatio);
            }
        }

        return new OrderSpecifier(Order.DESC, universityLastDepartmentInfo.competitionRatio);
    }

}

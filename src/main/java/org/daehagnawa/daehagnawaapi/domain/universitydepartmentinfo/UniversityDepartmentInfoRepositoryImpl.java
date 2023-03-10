package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public List<UniversityDepartmentInfo> departmentSearch(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    ) {
        List<UniversityDepartmentInfo> result = queryFactory
                .selectFrom(universityDepartmentInfo)
                .where(
                        universityDepartmentInfo.universityName.like("%" + universityKeyword + "%")
                        .and(universityDepartmentInfo.departmentName.like("%" + departmentKeyword + "%"))
                        .and(universityDepartmentInfo.universityArea.like("%" + area + "%"))
                        .and(universityDepartmentInfo.universityDegree.like("%" + degree + "%"))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(currentDepartmentSort(pageable))
                .fetch();
        return result;
    }

    @Override
    public List<UniversityLastDepartmentInfo> lastDepartmentSearch(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    ) {
        List<UniversityLastDepartmentInfo> result = queryFactory
                .selectFrom(universityLastDepartmentInfo)
                .where(
                        universityLastDepartmentInfo.universityName.like("%" + universityKeyword + "%")
                        .and(universityLastDepartmentInfo.departmentName.like("%" + departmentKeyword + "%"))
                        .and(universityLastDepartmentInfo.universityArea.like("%" + area + "%"))
                        .and(universityLastDepartmentInfo.universityDegree.like("%" + degree + "%"))
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
        //??????????????? ????????? Pageable ????????? ???????????? null ??? ??????
        if (!pageable.getSort().isEmpty()) {
            //???????????? ?????? ????????? for ???????????? ?????? ????????????
            for (Sort.Order order : pageable.getSort()) {
                // ??????????????? ????????? DESC or ASC ??? ????????????.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // ??????????????? ????????? ?????? ????????? ????????? ????????? ?????? ???????????? ???????????? ??????.
                return new OrderSpecifier(direction, universityDepartmentInfo.competitionRatio);
            }
        }

        return new OrderSpecifier(Order.DESC, universityDepartmentInfo.competitionRatio);
    }

    private OrderSpecifier<?> lastDepartmentSort(Pageable pageable) {
        //??????????????? ????????? Pageable ????????? ???????????? null ??? ??????
        if (!pageable.getSort().isEmpty()) {
            //???????????? ?????? ????????? for ???????????? ?????? ????????????
            for (Sort.Order order : pageable.getSort()) {
                // ??????????????? ????????? DESC or ASC ??? ????????????.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // ??????????????? ????????? ?????? ????????? ????????? ????????? ?????? ???????????? ???????????? ??????.
                return new OrderSpecifier(direction, universityLastDepartmentInfo.competitionRatio);
            }
        }

        return new OrderSpecifier(Order.DESC, universityLastDepartmentInfo.competitionRatio);
    }

}

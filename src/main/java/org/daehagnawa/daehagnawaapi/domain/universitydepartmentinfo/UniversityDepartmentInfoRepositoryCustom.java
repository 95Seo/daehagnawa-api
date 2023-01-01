package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityDepartmentInfoRepositoryCustom {

    List<UniversityDepartmentInfo> departmentSearch(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    );

    List<UniversityLastDepartmentInfo> lastDepartmentSearch(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    );

    BatchJobExecution get();
}

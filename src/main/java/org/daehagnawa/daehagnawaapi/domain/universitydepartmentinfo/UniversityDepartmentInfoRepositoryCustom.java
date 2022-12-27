package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityDepartmentInfoRepositoryCustom {

    List<UniversityDepartmentInfo> departmentSearch(@Param("keyword") String keyword, Pageable pageable);

    List<UniversityLastDepartmentInfo> lastDepartmentSearch(@Param("keyword") String keyword, Pageable pageable);

    BatchJobExecution get();
}

package org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityDepartmentInfoRepository extends JpaRepository<UniversityDepartmentInfo, Long>, UniversityDepartmentInfoRepositoryCustom {

}
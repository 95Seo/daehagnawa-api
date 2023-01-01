package org.daehagnawa.daehagnawaapi.application;

import lombok.RequiredArgsConstructor;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.BatchJobExecution;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.UniversityDepartmentInfoRepository;
import org.daehagnawa.daehagnawaapi.dto.DepartmentInfoListResponseDto;
import org.daehagnawa.daehagnawaapi.dto.LastDepartmentInfoListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UniversityDepartmentInfoService {

    private final UniversityDepartmentInfoRepository departmentRepository;

    public DepartmentInfoListResponseDto getDepartment(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    ) {
        return DepartmentInfoListResponseDto.builder()
                .list(departmentRepository.departmentSearch(
                        universityKeyword,
                        departmentKeyword,
                        area,
                        degree,
                        pageable))
                .endTime(departmentRepository.get().getEndTime())
                .build();
    }

    public LastDepartmentInfoListResponseDto getLastDepartment(
            String universityKeyword,
            String departmentKeyword,
            String area,
            String degree,
            Pageable pageable
    ) {
        return LastDepartmentInfoListResponseDto.builder()
                .list(departmentRepository.lastDepartmentSearch(
                        universityKeyword,
                        departmentKeyword,
                        area,
                        degree,
                        pageable))
                .endTime(null)
                .build();
    }

    public BatchJobExecution get() {
        return departmentRepository.get();
    }
}
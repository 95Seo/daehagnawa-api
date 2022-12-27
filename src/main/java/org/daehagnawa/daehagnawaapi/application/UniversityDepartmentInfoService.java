package org.daehagnawa.daehagnawaapi.application;

import lombok.RequiredArgsConstructor;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.BatchJobExecution;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.UniversityDepartmentInfoRepository;
import org.daehagnawa.daehagnawaapi.dto.DepartmentInfoListResponseDto;
import org.daehagnawa.daehagnawaapi.dto.LastDepartmentInfoListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityDepartmentInfoService {

    private final UniversityDepartmentInfoRepository departmentRepository;

    public DepartmentInfoListResponseDto getDepartment(String keyword, Pageable pageable) {
        return DepartmentInfoListResponseDto.builder()
                .list(departmentRepository.departmentSearch(keyword, pageable))
                .endTime(departmentRepository.get().getEndTime())
//                .endTime(null)
                .build();
    }

    public LastDepartmentInfoListResponseDto getLastDepartment(String keyword, Pageable pageable) {
        return LastDepartmentInfoListResponseDto.builder()
                .list(departmentRepository.lastDepartmentSearch(keyword, pageable))
                .endTime(null)
                .build();
    }

    public BatchJobExecution get() {
        return departmentRepository.get();
    }
}
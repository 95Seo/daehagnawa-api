package org.daehagnawa.daehagnawaapi.presentation;

import lombok.RequiredArgsConstructor;
import org.daehagnawa.daehagnawaapi.application.UniversityDepartmentInfoService;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.BatchJobExecution;
import org.daehagnawa.daehagnawaapi.dto.DepartmentInfoListResponseDto;
import org.daehagnawa.daehagnawaapi.dto.LastDepartmentInfoListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UniversityDepartmentInfoController {
    private final UniversityDepartmentInfoService service;

    @GetMapping(value = "/department")
    public DepartmentInfoListResponseDto getDepartmentList(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        return service.getDepartment(keyword, pageable);
    }

    @GetMapping(value = "/last/department")
    public LastDepartmentInfoListResponseDto getLastDepartmentList(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        return service.getLastDepartment(keyword, pageable);
    }

    @GetMapping(value = "/d")
    public BatchJobExecution get() {
        return service.get();
    }
}
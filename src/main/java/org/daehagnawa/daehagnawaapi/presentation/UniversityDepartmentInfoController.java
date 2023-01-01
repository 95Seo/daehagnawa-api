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
            @RequestParam(value = "universityKeyword", defaultValue = "") String universityKeyword,
            @RequestParam(value = "departmentKeyword", defaultValue = "") String departmentKeyword,
            @RequestParam(value = "universityArea", defaultValue = "") String area,
            @RequestParam(value = "universityDegree", defaultValue = "") String degree,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        System.out.println("universityKeyword = " + universityKeyword);

        System.out.println("departmentKeyword = " + departmentKeyword);

        departmentKeyword = departmentKeyword.replaceAll(" ", "");

        if (area.equals("전체"))
            area = "";

        if (degree.equals("전체"))
            degree = "";

        return service.getDepartment(universityKeyword, departmentKeyword, area, degree, pageable);
    }

    @GetMapping(value = "/last/department")
    public LastDepartmentInfoListResponseDto getLastDepartmentList(
            @RequestParam(value = "universityKeyword", defaultValue = "") String universityKeyword,
            @RequestParam(value = "departmentKeyword", defaultValue = "") String departmentKeyword,
            @RequestParam(value = "universityArea", defaultValue = "") String area,
            @RequestParam(value = "universityDegree", defaultValue = "") String degree,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        departmentKeyword = departmentKeyword.replaceAll(" ", "");

        if (area.equals("전체"))
            area = "";

        if (degree.equals("전체"))
            degree = "";

        return service.getLastDepartment(universityKeyword, departmentKeyword, area, degree, pageable);
    }

    @GetMapping(value = "/d")
    public BatchJobExecution get() {
        return service.get();
    }
}
package org.daehagnawa.daehagnawaapi.dto;

import lombok.Builder;
import lombok.Getter;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.UniversityDepartmentInfo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DepartmentInfoListResponseDto {
    List<UniversityDepartmentInfo> list;
    LocalDateTime endTime;

    @Builder
    public DepartmentInfoListResponseDto(
            List<UniversityDepartmentInfo> list,
            LocalDateTime endTime
    ) {
        this.list = list;
        this.endTime = endTime;
    }
}

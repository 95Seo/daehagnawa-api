package org.daehagnawa.daehagnawaapi.dto;

import lombok.Builder;
import lombok.Getter;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.UniversityDepartmentInfo;
import org.daehagnawa.daehagnawaapi.domain.universitydepartmentinfo.UniversityLastDepartmentInfo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class LastDepartmentInfoListResponseDto {
    List<UniversityLastDepartmentInfo> list;
    LocalDateTime endTime;

    @Builder
    public LastDepartmentInfoListResponseDto(
            List<UniversityLastDepartmentInfo> list,
            LocalDateTime endTime
    ) {
        this.list = list;
        this.endTime = endTime;
    }
}

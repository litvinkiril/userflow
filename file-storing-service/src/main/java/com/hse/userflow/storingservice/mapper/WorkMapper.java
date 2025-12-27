package com.hse.userflow.storingservice.mapper;

import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;
import com.hse.userflow.storingservice.model.Work;

import java.time.LocalDateTime;

public class WorkMapper {
    public static WorkDto toDto(Work work) {
        return WorkDto.builder()
                .name(work.getName())
                .description(work.getDescription())
                .build();
    }

    public static Work toEntity(WorkCreateDto newWork) {
        return Work.builder()
                .name(newWork.getName())
                .description(newWork.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

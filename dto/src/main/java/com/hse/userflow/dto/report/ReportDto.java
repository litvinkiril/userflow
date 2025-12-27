package com.hse.userflow.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Отчет анализа плагиата")
public class ReportDto {
    @Schema(description = "ID работы", example = "123")
    private Integer workId;

    @Schema(description = "Название работы", example = "Лабораторная работа 1")
    private String workName;

    @Schema(description = "ID студента", example = "456")
    private Integer studentId;

    @Schema(description = "Имя студента", example = "Иван Петров")
    private String studentName;

    @Schema(description = "Обнаружен ли плагиат", example = "true")
    private Boolean plagiarismDetected;

    @Schema(description = "Дата и время загрузки", example = "2024-01-15T10:30:00")
    private LocalDateTime uploadedAt;
}

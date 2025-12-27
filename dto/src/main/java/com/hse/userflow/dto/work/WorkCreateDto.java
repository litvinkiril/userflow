package com.hse.userflow.dto.work;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для создания новой учебной работы")
public class WorkCreateDto {
    @NotNull(message = "Название работы не может быть пустым")
    @Size(max = 50, message = "Название работы не должно превышать 50 символов")
    @Schema(
            description = "Название учебной работы",
            example = "Лабораторная работа по математике",
            maxLength = 50,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotNull(message = "Описание работы не может быть пустым")
    @Schema(
            description = "Подробное описание учебной работы",
            example = "Исследование свойств дифференциальных уравнений",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String description;
}

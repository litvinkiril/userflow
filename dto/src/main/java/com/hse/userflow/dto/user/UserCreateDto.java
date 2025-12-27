package com.hse.userflow.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для создания нового пользователя")
public class UserCreateDto {
    @NotNull(message = "Имя пользователя не может быть пустым")
    @Size(max = 50, message = "Имя пользователя не должно превышать 50 символов")
    @Schema(
            description = "Имя пользователя",
            example = "Алексей Сидоров",
            maxLength = 50,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @Email(message = "Некорректный формат email")
    @NotNull(message = "Email не может быть пустым")
    @Schema(
            description = "Электронная почта пользователя",
            example = "alex@university.ru",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email"
    )
    private String email;

}

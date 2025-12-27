package com.hse.userflow.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Информация о пользователе")
public class UserDto {
    @Schema(description = "Имя пользователя", example = "Иван Петров")
    private String name;

    @Schema(description = "Email пользователя", example = "ivan@example.com")
    private String email;
}

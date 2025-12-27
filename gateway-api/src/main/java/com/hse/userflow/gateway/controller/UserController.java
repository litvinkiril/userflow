package com.hse.userflow.gateway.controller;

import com.hse.userflow.dto.user.UserCreateDto;
import com.hse.userflow.dto.user.UserDto;
import com.hse.userflow.gateway.client.UserStorageClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@Tag(name = "User Management", description = "API для управления пользователями")
@RequiredArgsConstructor
public class UserController {
    private final UserStorageClient userStorageClient;

    @Operation(summary = "добавление нового пользователя")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "пользователь успешно добавлен",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "неверные данные в теле запроса"
            )
    })
    @PostMapping
    public UserDto createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового пользователя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserCreateDto.class))
            )
            @Validated @RequestBody UserCreateDto newUser) {
        return userStorageClient.createUser(newUser);
    }


    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает информацию о пользователе по его идентификатору"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь найден",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден"
            )
    })
    @GetMapping("/{userId}")
    public UserDto findUserById(
            @Parameter(description = "ID пользователя", required = true, example = "456")
            @PathVariable Integer userId) {
        return userStorageClient.findUserById(userId);
    }


    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя из системы"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно удален"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден"
            )
    })
    @DeleteMapping("/{userId}")
    public void deleteUserById(
            @Parameter(description = "ID пользователя", required = true, example = "456")
            @PathVariable Integer userId) {
        userStorageClient.deleteUserById(userId);
    }
}

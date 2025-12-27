package com.hse.userflow.gateway.controller;

import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;
import com.hse.userflow.gateway.client.WorkStorageClient;
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
@RequiredArgsConstructor
@RequestMapping("/works")
@Tag(name = "Work Management", description = "API для управления работами")
public class WorkController {
    private final WorkStorageClient workStorageClient;

    @Operation(summary = "добавление новой работы")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "работа успешно добавлена",
                    content = @Content(schema = @Schema(implementation = WorkDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "неверные данные в теле запроса"
            )
    })
    @PostMapping
    public WorkDto addWork(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные новой работы",
                    required = true,
                    content = @Content(schema = @Schema(implementation = WorkCreateDto.class))
            )
            @Validated @RequestBody WorkCreateDto newWork) {
        return workStorageClient.createWork(newWork);
    }

    @DeleteMapping("/{workId}")
    public void deleteWork(
            @Parameter(description = "ID работы", required = true, example = "438")
            @PathVariable Integer workId) {
        workStorageClient.deleteWork(workId);
    }

}

package com.hse.userflow.gateway.controller;

import com.hse.userflow.dto.report.ReportDto;
import com.hse.userflow.gateway.client.ReportClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Analysis Reports", description = "API для получения отчетов анализа плагиата")
@RequestMapping("/reports")
public class ReportController {
    private final ReportClient reportClient;


    @Operation(
            summary = "Получить отчет по работе и студенту",
            description = "Возвращает отчет анализа плагиата для конкретной работы и студента"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Отчет найден",
                    content = @Content(schema = @Schema(implementation = ReportDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Отчет не найден"
            )
    })
    @GetMapping("/works/{workId}/students/{studentId}")
    public ReportDto findReportByWorkIdAndStudentId(
            @Parameter(description = "ID работы", required = true, example = "123")
            @PathVariable Integer workId,

            @Parameter(description = "ID студента", required = true, example = "456")
            @PathVariable Integer studentId) {
        return reportClient.findReportByWorkIdAndStudentId(workId, studentId);
    }


    @Operation(
            summary = "Получить все отчеты по работе",
            description = "Возвращает список всех отчетов анализа плагиата для указанной работы"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список отчетов получен",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReportDto.class)))
            )
    })
    @GetMapping("/works/{workId}")
    public List<ReportDto> findReportsByWorkId(
            @Parameter(description = "ID работы", required = true, example = "123")
            @PathVariable Integer workId) {
        return reportClient.findAllReportsByWorkId(workId);
    }
}

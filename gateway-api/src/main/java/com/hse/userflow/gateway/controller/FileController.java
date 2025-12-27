package com.hse.userflow.gateway.controller;


import com.hse.userflow.dto.file.FileDto;
import com.hse.userflow.gateway.client.FileClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "File Management", description = "API для управления файлами")
@RequestMapping("/files")
public class FileController {
    private final FileClient fileClient;

    @Operation(
            summary = "Загрузить файл работы студента",
            description = "Загружает файл студенческой работы в систему хранения"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно загружен",
                    content = @Content(schema = @Schema(implementation = FileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный формат файла или параметры запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Работа или студент не найден"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера при загрузке файла"
            )
    })
    @PostMapping(value = "/works/{workId}/students/{studentId}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileDto uploadFile(
            @Parameter(description = "ID работы", required = true, example = "123")
            @PathVariable Integer workId,

            @Parameter(description = "ID студента", required = true, example = "456")
            @PathVariable Integer studentId,

            @Parameter(
                    description = "Файл работы (PDF, DOC, DOCX, TXT)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam(value = "file", required = false) MultipartFile file) {
        return fileClient.uploadFile(file, workId, studentId);
    }

    @Operation(
            summary = "Скачать файл по ID",
            description = "Скачивает файл студенческой работы по его идентификатору"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно скачан",
                    content = @Content(mediaType = "application/octet-stream")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Файл не найден"
            )
    })
    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadFile(
            @Parameter(description = "ID файла", required = true, example = "1")
            @PathVariable Integer fileId) {
        return fileClient.downloadFile(fileId);
    }

    @Operation(
            summary = "Удалить файл",
            description = "Удаляет файл из системы хранения"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно удален"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Файл не найден"
            )
    })
    @DeleteMapping("/{fileId}")
    public void deleteFile(
            @Parameter(description = "ID файла", required = true, example = "1")
            @PathVariable Integer fileId) {
        fileClient.deleteFile(fileId);
    }
}


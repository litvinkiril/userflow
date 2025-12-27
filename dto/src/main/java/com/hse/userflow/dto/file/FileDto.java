package com.hse.userflow.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Ответ после загрузки файла")
public class FileDto {
    @Schema(description = "ID файла", example = "1")
    private Integer fileId;
    @Schema(description = "ID файла", example = "1")
    private String fileName;
    @Schema(description = "ID файла", example = "1")
    private LocalDateTime loadedAt;
}

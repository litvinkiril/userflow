package com.hse.userflow.storingservice.mapper;

import com.hse.userflow.dto.file.FileContentDto;
import com.hse.userflow.dto.file.FileDto;
import com.hse.userflow.storingservice.model.File;

import java.util.List;

public class FileMapper {

    public static FileDto toDto(File file) {
        return FileDto.builder()
                .fileId(file.getId())
                .fileName(file.getOriginalFileName())
                .loadedAt(file.getUploadedAt())
                .build();
    }

    public static List<FileDto> toDto(List<File> files) {
        return files.stream().map(FileMapper::toDto).toList();
    }

    public static FileContentDto toContentDto(File file, byte[] content) {
        return FileContentDto.builder()
                .fileId(file.getId())
                .studentId(file.getStudent().getId())
                .userName(file.getStudent().getName())
                .workName(file.getWork().getName())
                .workId(file.getWork().getId())
                .originalFileName(file.getOriginalFileName())
                .mimeType(file.getMimeType())
                .content(content)
                .build();
    }

}

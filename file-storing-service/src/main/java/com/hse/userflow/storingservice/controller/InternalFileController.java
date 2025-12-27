package com.hse.userflow.storingservice.controller;

import com.hse.userflow.dto.file.FileContentDto;
import com.hse.userflow.storingservice.service.S3FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class InternalFileController {
    private final S3FileService fileService;

    @GetMapping("/{fileId}/earlier")
    public List<FileContentDto> findAllEarlierReports(@PathVariable Integer fileId) {
        return fileService.findAllEarlierReports(fileId);
    }

    @GetMapping("/{fileId}")
    public FileContentDto getFileContent(@PathVariable Integer fileId) {
        return fileService.getFileContent(fileId);
    }
}

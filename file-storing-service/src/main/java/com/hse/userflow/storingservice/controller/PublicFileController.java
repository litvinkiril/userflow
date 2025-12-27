package com.hse.userflow.storingservice.controller;

import com.hse.userflow.dto.file.FileDto;
import com.hse.userflow.storingservice.service.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class PublicFileController {
    private final S3FileService fileService;

    @PostMapping("/works/{workId}/students/{studentId}/upload")
    public FileDto upload(@RequestParam("file") MultipartFile file,
                          @PathVariable Integer workId,
                          @PathVariable Integer studentId
    ) {
        return fileService.uploadFile(workId, studentId, file);
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> download(@PathVariable Integer fileId) {
        return fileService.downloadFile(fileId);
    }

    @DeleteMapping("/{fileId}/delete")
    public void delete(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
    }

}

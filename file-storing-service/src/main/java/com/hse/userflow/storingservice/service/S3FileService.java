package com.hse.userflow.storingservice.service;

import com.hse.userflow.dto.file.FileContentDto;
import com.hse.userflow.dto.file.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3FileService {
    FileDto uploadFile(Integer workId, Integer studentId, MultipartFile multipartFile);

    ResponseEntity<byte[]> downloadFile(Integer fileId);

    void deleteFile(Integer fileId);

    List<FileContentDto> findAllEarlierReports(Integer fileId);

    FileContentDto getFileContent(Integer fileId);

}

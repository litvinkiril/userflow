package com.hse.analysis.service.client;

import com.hse.analysis.exception.AnalysisServiceException;
import com.hse.userflow.dto.file.FileContentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStorageClient {
    private final RestTemplate restTemplate;

    @Value("${storing-service.url}")
    private String storingServiceUrl;


    public List<FileContentDto> findAllEarlierReports(int fileId) {
        try {
            String url = storingServiceUrl + "/files/{fileId}/earlier";
            ResponseEntity<FileContentDto[]> response = restTemplate.getForEntity(url, FileContentDto[].class, fileId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<FileContentDto> files = Arrays.asList(response.getBody());
                log.debug("более ранние работы успешно получены");
                return files;
            } else {
                return Collections.emptyList();
            }
        } catch (ResourceAccessException exception) {
            throw new AnalysisServiceException("ошибка сервиса анализа отчетов", exception);
        }
    }

    public FileContentDto getFileContentFromStorage(Integer fileId) {
        try {
            String url = storingServiceUrl + "/files/{fileId}";
            ResponseEntity<FileContentDto> response = restTemplate.getForEntity(url, FileContentDto.class, fileId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                FileContentDto content = response.getBody();
                log.debug("более ранняя работа успешно получена");
                return content;
            } else {
                throw new AnalysisServiceException("не удалось получить данные из сервиса хранения");
            }
        } catch (ResourceAccessException exception) {
            throw new AnalysisServiceException("ошибка сервиса анализа отчетов", exception);
        }
    }

}

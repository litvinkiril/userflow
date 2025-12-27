package com.hse.userflow.gateway.client;

import com.hse.userflow.dto.error.ErrorResponse;
import com.hse.userflow.dto.file.FileDto;
import com.hse.userflow.gateway.exception.GateWayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.hse.userflow.gateway.utils.ErrorParser.toErrorResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileClient {
    private static final String FILES_URI = "/api/files";
    private final WebClient fileStorageWebClient;

    public FileDto uploadFile(MultipartFile file, Integer workId, Integer studentId) {
        try {
            MultiValueMap<String, Object> body = createMultipartBody(file);

            return fileStorageWebClient
                    .post()
                    .uri(uriBuilder -> uriBuilder
                            .path(FILES_URI + "/works/{workId}/students/{studentId}/upload")
                            .build(workId, studentId))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .bodyToMono(FileDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public ResponseEntity<byte[]> downloadFile(Integer fileId) {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(FILES_URI + "/{fileId}", fileId)
                    .accept(MediaType.APPLICATION_OCTET_STREAM)
                    .retrieve()
                    .toEntity(byte[].class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public void deleteFile(Integer fileId) {
        try {
            fileStorageWebClient
                    .delete()
                    .uri(FILES_URI + "/{fileId}", fileId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public FileDto getFileInfo(Integer fileId) {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(FILES_URI + "/{fileId}/info", fileId)
                    .retrieve()
                    .bodyToMono(FileDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public List<FileDto> getFilesByWorkId(Integer workId) {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(FILES_URI + "/works/{workId}", workId)
                    .retrieve()
                    .bodyToFlux(FileDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    private MultiValueMap<String, Object> createMultipartBody(MultipartFile file) {
        if (file == null) {
            throw new IllegalArgumentException("произошла ошибка во время сохранения файла");
        }
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", file.getResource())
                .filename(file.getOriginalFilename())
                .contentType(MediaType.parseMediaType(
                        file.getContentType() != null ?
                                file.getContentType() : "application/octet-stream"));

        return (MultiValueMap<String, Object>) (MultiValueMap<?, ?>) builder.build();
    }

}



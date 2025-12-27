package com.hse.userflow.gateway.client;

import com.hse.userflow.dto.error.ErrorResponse;
import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;
import com.hse.userflow.gateway.exception.GateWayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.hse.userflow.gateway.utils.ErrorParser.toErrorResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class WorkStorageClient {
    private static final String WORKS_URI = "/api/works";
    private final WebClient fileStorageWebClient;

    public WorkDto createWork(WorkCreateDto newWork) {
        try {
            return fileStorageWebClient
                    .post()
                    .uri(WORKS_URI)
                    .bodyValue(newWork)
                    .retrieve()
                    .bodyToMono(WorkDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public void deleteWork(Integer workId) {
        try {
            fileStorageWebClient
                    .delete()
                    .uri(WORKS_URI + "/{workId}", workId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public WorkDto getWorkById(Integer workId) {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(WORKS_URI + "/{workId}", workId)
                    .retrieve()
                    .bodyToMono(WorkDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public List<WorkDto> getAllWorks() {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(WORKS_URI)
                    .retrieve()
                    .bodyToFlux(WorkDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

}

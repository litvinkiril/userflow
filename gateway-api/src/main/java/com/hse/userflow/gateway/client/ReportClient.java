package com.hse.userflow.gateway.client;

import com.hse.userflow.dto.error.ErrorResponse;
import com.hse.userflow.dto.report.ReportDto;
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
public class ReportClient {
    private static final String REPORTS_URI = "/api/reports";
    private final WebClient analysisServiceWebClient;

    public ReportDto findReportByWorkIdAndStudentId(Integer workId, Integer studentId) {
        try {
            return analysisServiceWebClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(REPORTS_URI + "/works/{workId}/students/{studentId}")
                            .build(workId, studentId))
                    .retrieve()
                    .bodyToMono(ReportDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public List<ReportDto> findAllReportsByWorkId(Integer workId) {
        try {
            return analysisServiceWebClient
                    .get()
                    .uri(REPORTS_URI + "/works/{workId}", workId)
                    .retrieve()
                    .bodyToFlux(ReportDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public ReportDto getAnalysisReport(Integer fileId) {
        try {
            return analysisServiceWebClient
                    .get()
                    .uri(REPORTS_URI + "/{fileId}", fileId)
                    .retrieve()
                    .bodyToMono(ReportDto.class)
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public List<ReportDto> getAllPlagiarismReports() {
        try {
            return analysisServiceWebClient
                    .get()
                    .uri(REPORTS_URI + "/plagiarism")
                    .retrieve()
                    .bodyToFlux(ReportDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            final ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }
}

package com.hse.userflow.storingservice.service.client;

import com.hse.userflow.dto.report.AnalysisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class AnalysisClient {
    private final RestTemplate restTemplate;

    @Value("${analysis-service.url}")
    private String analysisServiceUrl;

    public void triggerAnalysisService(AnalysisRequest request) {
        try {
            restTemplate.postForObject(analysisServiceUrl + "/analyze", request, Void.class);
        } catch (Exception e) {
            throw new InternalException("произошла ошибка при обращении к сервису анализа отчетов", e);
        }
    }
}

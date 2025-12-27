package com.hse.analysis.controller;

import com.hse.analysis.service.ReportAnalysisService;
import com.hse.userflow.dto.report.AnalysisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {
    private final ReportAnalysisService analysisService;

    @PostMapping
    public void analyzeReport(@RequestBody AnalysisRequest analysisRequest) {
        analysisService.analyzeReport(analysisRequest);
        log.debug("запрос на анализ файла{}", analysisRequest.getFileId());
    }
}

package com.hse.analysis.controller;

import com.hse.analysis.service.ReportService;
import com.hse.userflow.dto.report.ReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/works/{workId}/students/{studentId}")
    public ReportDto findByWorkIdAndStudentId(@PathVariable Integer workId, @PathVariable Integer studentId) {
        return reportService.findByWorkIdAndStudentId(workId, studentId);
    }

    @GetMapping("/works/{workId}")
    public List<ReportDto> findAllByWorkId(@PathVariable Integer workId) {
        return reportService.findAllByWorkId(workId);
    }


}

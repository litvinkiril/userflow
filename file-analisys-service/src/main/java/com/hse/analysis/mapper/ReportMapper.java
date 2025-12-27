package com.hse.analysis.mapper;

import com.hse.analysis.model.Report;
import com.hse.userflow.dto.report.ReportDto;

import java.util.List;

public class ReportMapper {
    public static ReportDto toDto(Report report) {
        return ReportDto.builder()
                .workId(report.getWorkId())
                .workName(report.getWorkName())
                .studentId(report.getStudentId())
                .studentName(report.getStudentName())
                .plagiarismDetected(report.getPlagiarismDetected())
                .uploadedAt(report.getUploadedAt())
                .build();
    }

    public static List<ReportDto> toDto(List<Report> reports) {
        return reports.stream().map(ReportMapper::toDto).toList();
    }
}

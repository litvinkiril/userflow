package com.hse.analysis.service;

import com.hse.userflow.dto.report.ReportDto;

import java.util.List;

public interface ReportService {
    ReportDto findByWorkIdAndStudentId(Integer studentId, Integer workId);

    List<ReportDto> findAllByWorkId(Integer workId);

}

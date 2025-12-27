package com.hse.analysis.service.impl;

import com.hse.analysis.exception.NotFoundException;
import com.hse.analysis.model.Report;
import com.hse.analysis.repository.ReportRepository;
import com.hse.analysis.service.ReportService;
import com.hse.userflow.dto.report.ReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hse.analysis.mapper.ReportMapper.toDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public List<ReportDto> findAllByWorkId(Integer workId) {
        log.debug("запрос на получение всех отчетов для работы с id{}", workId);
        return toDto(reportRepository.findAllByWorkId(workId));

    }

    @Override
    public ReportDto findByWorkIdAndStudentId(Integer workId, Integer studentId) {
        log.debug("запрос на получение отчета по работе{} студента {}", workId, studentId);
        Report report = reportRepository.findByWorkIdAndStudentId(workId, studentId).orElseThrow(() ->
                new NotFoundException("отчет пользователя " + studentId + " не найден"));
        return toDto(report);
    }
}

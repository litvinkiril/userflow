package com.hse.analysis.repository;

import com.hse.analysis.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findAllByWorkId(Integer workId);

    Optional<Report> findByWorkIdAndStudentId(Integer workId, Integer studentId);
}

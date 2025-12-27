package com.hse.analysis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "work_id", nullable = false)
    private Integer workId;
    @Column(name = "work_name", nullable = false)
    private String workName;
    @Column(name = "student_id", nullable = false)
    private Integer studentId;
    @Column(name = "student_name", nullable = false)
    private String studentName;
    private Boolean plagiarismDetected;
    private LocalDateTime uploadedAt;
}

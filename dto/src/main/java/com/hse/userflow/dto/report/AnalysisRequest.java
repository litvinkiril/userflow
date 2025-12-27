package com.hse.userflow.dto.report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AnalysisRequest {
    private Integer fileId;
    private Integer workId;
    private Integer studentId;
    private String s3Key;
    private LocalDateTime requestTime;
}

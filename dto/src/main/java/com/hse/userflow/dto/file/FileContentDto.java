package com.hse.userflow.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileContentDto {
    private Integer fileId;
    private byte[] content;
    private Integer studentId;
    private String userName;
    private Integer workId;
    private String workName;
    private String originalFileName;
    private String mimeType;

}

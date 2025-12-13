package com.i4o.dms.itldis.reports.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * MIS Report Response DTO
 */
@Data
public class MisReportResponseDto {
    private String reportName;
    private String reportType; // "pdf" or "xls"
    private byte[] reportData;
    private List<Map<String, Object>> reportDataList;
    private Integer totalRecords;
    private Map<String, Object> summary;
}

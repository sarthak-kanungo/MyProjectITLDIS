package com.i4o.dms.itldis.reports.dto;

import lombok.Data;
import java.util.Date;

/**
 * MIS Report Request DTO
 */
@Data
public class MisReportRequestDto {
    private String dealerCode;
    private Date fromDate;
    private Date toDate;
    private String reportType; // "view" or "export"
    private String finYear;
    private Integer month;
    private Integer week;
    private String country;
    private String zone;
    private String state;
    private String ccm;
    private String cce;
    private String dealer;
}

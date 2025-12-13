package com.i4o.dms.itldis.reports.service;

import com.i4o.dms.itldis.reports.dto.MisReportRequestDto;
import com.i4o.dms.itldis.reports.dto.MisReportResponseDto;

/**
 * Report Service Interface
 * Defines all report generation methods
 */
public interface ReportService {
    
    /**
     * Generate MIS Report
     */
    MisReportResponseDto generateMisReport(MisReportRequestDto request);
    
    /**
     * Generate Spares & Lubes Report
     */
    MisReportResponseDto generateSparesLubesReport(MisReportRequestDto request);
    
    /**
     * Generate Spares & Lubes Rolling Report
     */
    MisReportResponseDto generateSparesLubesRollingReport(MisReportRequestDto request);
    
    /**
     * Generate Sale Invoice Report
     */
    MisReportResponseDto generateSaleInvoiceReport(MisReportRequestDto request);
    
    /**
     * Generate Pending PI Confirmation Report
     */
    MisReportResponseDto generatePendingPIReport(MisReportRequestDto request);
    
    /**
     * Generate Order Invoice Detail Report
     */
    MisReportResponseDto generateOrderInvoiceDetailReport(MisReportRequestDto request);
    
    /**
     * Generate GSTR-1 Report
     */
    MisReportResponseDto generateGstr1Report(MisReportRequestDto request);
    
    /**
     * Generate GSTR-2 Report
     */
    MisReportResponseDto generateGstr2Report(MisReportRequestDto request);
}

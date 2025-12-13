package com.i4o.dms.itldis.reports.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.reports.dto.MisReportRequestDto;
import com.i4o.dms.itldis.reports.dto.MisReportResponseDto;
import com.i4o.dms.itldis.reports.service.ReportService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * Report Controller for ITLDIS
 * Handles all report generation requests
 * 
 * @author ITLDIS Team
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/reports")
public class ReportController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    @Autowired
    private ReportService reportService;
    
    /**
     * Initialize MIS Report
     */
    @GetMapping("/mis/init")
    public ResponseEntity<ApiResponse> initMisReport(@RequestParam(required = false) String dealerCode) {
        try {
            // Implementation to be added
            return ResponseEntity.ok(ApiResponse.success("MIS Report initialized"));
        } catch (Exception e) {
            logger.error("Error initializing MIS report", e);
            return ResponseEntity.ok(ApiResponse.error("Error initializing MIS report: " + e.getMessage()));
        }
    }
    
    /**
     * Generate MIS Report
     */
    @PostMapping("/mis/generate")
    public ResponseEntity<ApiResponse> generateMisReport(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generateMisReport(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating MIS report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating MIS report: " + e.getMessage()));
        }
    }
    
    /**
     * Get Spares & Lubes Report
     */
    @PostMapping("/spares-lubes")
    public ResponseEntity<ApiResponse> getSparesLubesReport(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generateSparesLubesReport(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating Spares & Lubes report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating report: " + e.getMessage()));
        }
    }
    
    /**
     * Get Sale Invoice Report
     */
    @PostMapping("/sale-invoice")
    public ResponseEntity<ApiResponse> getSaleInvoiceReport(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generateSaleInvoiceReport(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating Sale Invoice report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating report: " + e.getMessage()));
        }
    }
    
    /**
     * Get Pending PI Confirmation Report
     */
    @PostMapping("/pending-pi")
    public ResponseEntity<ApiResponse> getPendingPIReport(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generatePendingPIReport(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating Pending PI report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating report: " + e.getMessage()));
        }
    }
    
    /**
     * Generate GSTR-1 Report
     */
    @PostMapping("/gstr-1")
    public ResponseEntity<ApiResponse> generateGstr1Report(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generateGstr1Report(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating GSTR-1 report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating report: " + e.getMessage()));
        }
    }
    
    /**
     * Generate GSTR-2 Report
     */
    @PostMapping("/gstr-2")
    public ResponseEntity<ApiResponse> generateGstr2Report(@RequestBody MisReportRequestDto request) {
        try {
            MisReportResponseDto response = reportService.generateGstr2Report(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error generating GSTR-2 report", e);
            return ResponseEntity.ok(ApiResponse.error("Error generating report: " + e.getMessage()));
        }
    }
}

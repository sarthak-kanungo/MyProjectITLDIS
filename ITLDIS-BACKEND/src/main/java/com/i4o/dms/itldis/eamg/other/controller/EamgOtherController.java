package com.i4o.dms.itldis.eamg.other.controller;

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

import com.i4o.dms.itldis.eamg.other.dto.OtherRequestDto;
import com.i4o.dms.itldis.eamg.other.dto.OtherResponseDto;
import com.i4o.dms.itldis.eamg.other.service.EamgOtherService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Other Controller
 * Handles Other EAMG operations
 * Reference: EAMG.Other.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/other")
public class EamgOtherController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgOtherController.class);
    
    @Autowired
    private EamgOtherService eamgOtherService;
    
    /**
     * Add TSN
     * Reference: EAMG_AddTSNAction
     */
    @PostMapping("/add-tsn")
    public ResponseEntity<ApiResponse> addTSN(@RequestBody OtherRequestDto request) {
        try {
            OtherResponseDto response = eamgOtherService.addTSN(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding TSN", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding TSN: " + e.getMessage()));
        }
    }
    
    /**
     * EAMG vs OEM Action
     * Reference: EAMG_EAMG_VS_OMEAction
     */
    @PostMapping("/eamg-vs-oem")
    public ResponseEntity<ApiResponse> eamgVsOEM(@RequestBody OtherRequestDto request) {
        try {
            OtherResponseDto response = eamgOtherService.eamgVsOEM(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing EAMG vs OEM", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing EAMG vs OEM: " + e.getMessage()));
        }
    }
    
    /**
     * EAMG Part vs OEM Part
     * Reference: EAMG_EAMGPart_OEMPartAction
     */
    @PostMapping("/eamg-part-vs-oem-part")
    public ResponseEntity<ApiResponse> eamgPartVsOEMPart(@RequestBody OtherRequestDto request) {
        try {
            OtherResponseDto response = eamgOtherService.eamgPartVsOEMPart(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing EAMG part vs OEM part", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing EAMG part vs OEM part: " + e.getMessage()));
        }
    }
    
    /**
     * Create PDF
     * Reference: CreatePDFAction
     */
    @PostMapping("/create-pdf")
    public ResponseEntity<byte[]> createPDF(@RequestBody OtherRequestDto request) {
        try {
            byte[] pdfData = eamgOtherService.createPDF(request);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"eamg_report.pdf\"")
                    .body(pdfData);
        } catch (Exception e) {
            logger.error("Error creating PDF", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Usage Detail Action
     * Reference: UsageDetailAction
     */
    @GetMapping("/usage-detail")
    public ResponseEntity<ApiResponse> getUsageDetail(@RequestParam String partNo) {
        try {
            OtherResponseDto response = eamgOtherService.getUsageDetail(partNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting usage detail", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting usage detail: " + e.getMessage()));
        }
    }
}

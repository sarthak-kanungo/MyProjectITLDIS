package com.i4o.dms.itldis.salesandpresales.pi.controller;

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

import com.i4o.dms.itldis.salesandpresales.pi.dto.PIRequestDto;
import com.i4o.dms.itldis.salesandpresales.pi.dto.PIResponseDto;
import com.i4o.dms.itldis.salesandpresales.pi.service.PIService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * PI (Proforma Invoice) Controller
 * Handles PI creation and management
 * Reference: action.piAction.CreatePIAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/pi")
public class PIController {
    
    private static final Logger logger = LoggerFactory.getLogger(PIController.class);
    
    @Autowired
    private PIService piService;
    
    /**
     * Create PI
     * Reference: CreatePIAction
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPI(@RequestBody PIRequestDto request) {
        try {
            PIResponseDto response = piService.createPI(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating PI", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating PI: " + e.getMessage()));
        }
    }
    
    /**
     * Get PI List
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getPIList(@RequestParam(required = false) String dealerCode,
                                                 @RequestParam(required = false) String status) {
        try {
            PIResponseDto response = piService.getPIList(dealerCode, status);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting PI list", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting PI list: " + e.getMessage()));
        }
    }
    
    /**
     * Get PI Details
     */
    @GetMapping("/details")
    public ResponseEntity<ApiResponse> getPIDetails(@RequestParam String piNo) {
        try {
            PIResponseDto response = piService.getPIDetails(piNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting PI details", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting PI details: " + e.getMessage()));
        }
    }
    
    /**
     * Export PI
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportPI(@RequestParam String piNo) {
        try {
            byte[] fileData = piService.exportPI(piNo);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"PI_" + piNo + ".pdf\"")
                    .body(fileData);
        } catch (Exception e) {
            logger.error("Error exporting PI", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

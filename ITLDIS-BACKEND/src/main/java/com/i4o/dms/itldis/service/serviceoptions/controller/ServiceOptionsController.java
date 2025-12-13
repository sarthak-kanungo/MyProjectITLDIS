package com.i4o.dms.itldis.service.serviceoptions.controller;

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

import com.i4o.dms.itldis.service.serviceoptions.dto.ServiceOptionsRequestDto;
import com.i4o.dms.itldis.service.serviceoptions.dto.ServiceOptionsResponseDto;
import com.i4o.dms.itldis.service.serviceoptions.service.ServiceOptionsService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * Service Options Controller
 * Handles service options management
 * Reference: action.serviceOptionsAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/service/options")
public class ServiceOptionsController {
    
    private static final Logger logger = LoggerFactory.getLogger(ServiceOptionsController.class);
    
    @Autowired
    private ServiceOptionsService serviceOptionsService;
    
    /**
     * Get Service Options
     * Reference: serviceOptionsAction methods
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getServiceOptions(@RequestParam(required = false) String dealerCode) {
        try {
            ServiceOptionsResponseDto response = serviceOptionsService.getServiceOptions(dealerCode);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting service options", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting service options: " + e.getMessage()));
        }
    }
    
    /**
     * Create/Update Service Option
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveServiceOption(@RequestBody ServiceOptionsRequestDto request) {
        try {
            ServiceOptionsResponseDto response = serviceOptionsService.saveServiceOption(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error saving service option", e);
            return ResponseEntity.ok(ApiResponse.error("Error saving service option: " + e.getMessage()));
        }
    }
}

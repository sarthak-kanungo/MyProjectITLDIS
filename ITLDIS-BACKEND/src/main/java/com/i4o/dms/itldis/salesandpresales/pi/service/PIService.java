package com.i4o.dms.itldis.salesandpresales.pi.service;

import com.i4o.dms.itldis.salesandpresales.pi.dto.PIRequestDto;
import com.i4o.dms.itldis.salesandpresales.pi.dto.PIResponseDto;

/**
 * PI Service Interface
 * Reference: action.piAction.CreatePIAction
 */
public interface PIService {
    
    /**
     * Create PI
     * Reference: CreatePIAction
     */
    PIResponseDto createPI(PIRequestDto request);
    
    /**
     * Get PI List
     */
    PIResponseDto getPIList(String dealerCode, String status);
    
    /**
     * Get PI Details
     */
    PIResponseDto getPIDetails(String piNo);
    
    /**
     * Export PI
     */
    byte[] exportPI(String piNo);
}

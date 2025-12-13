package com.i4o.dms.itldis.service.serviceoptions.service;

import com.i4o.dms.itldis.service.serviceoptions.dto.ServiceOptionsRequestDto;
import com.i4o.dms.itldis.service.serviceoptions.dto.ServiceOptionsResponseDto;

/**
 * Service Options Service Interface
 * Reference: action.serviceOptionsAction
 */
public interface ServiceOptionsService {
    
    /**
     * Get Service Options
     */
    ServiceOptionsResponseDto getServiceOptions(String dealerCode);
    
    /**
     * Save Service Option
     */
    ServiceOptionsResponseDto saveServiceOption(ServiceOptionsRequestDto request);
}

package com.i4o.dms.itldis.service.serviceoptions.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Service Options Response DTO
 */
@Data
public class ServiceOptionsResponseDto {
    private String status;
    private String message;
    private List<ServiceOptionsRequestDto> serviceOptions;
    private Map<String, Object> summary;
}

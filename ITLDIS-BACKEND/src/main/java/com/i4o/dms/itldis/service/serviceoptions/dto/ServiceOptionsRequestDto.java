package com.i4o.dms.itldis.service.serviceoptions.dto;

import lombok.Data;
import java.util.Map;

/**
 * Service Options Request DTO
 */
@Data
public class ServiceOptionsRequestDto {
    private String optionId;
    private String optionName;
    private String description;
    private String dealerCode;
    private String status;
    private Map<String, Object> additionalData;
}

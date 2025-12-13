package com.i4o.dms.itldis.eamg.other.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * EAMG Other Response DTO
 */
@Data
public class OtherResponseDto {
    private String status;
    private String message;
    private List<Map<String, Object>> comparisonData;
    private List<Map<String, Object>> usageDetails;
    private Map<String, Object> summary;
}

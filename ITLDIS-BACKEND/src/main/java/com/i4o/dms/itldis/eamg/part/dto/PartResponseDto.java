package com.i4o.dms.itldis.eamg.part.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Part Response DTO
 */
@Data
public class PartResponseDto {
    private String partNo;
    private String partName;
    private String status;
    private String message;
    private List<Map<String, Object>> partData;
    private Map<String, Object> priceList;
    private Map<String, Object> validationResult;
}

package com.i4o.dms.itldis.eamg.kit.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Kit Response DTO
 */
@Data
public class KitResponseDto {
    private String kitNo;
    private String kitName;
    private String status;
    private String message;
    private List<Map<String, Object>> kitData;
    private Map<String, Object> validationResult;
}

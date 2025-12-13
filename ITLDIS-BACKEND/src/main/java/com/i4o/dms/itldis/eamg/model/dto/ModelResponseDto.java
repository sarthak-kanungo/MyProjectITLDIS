package com.i4o.dms.itldis.eamg.model.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Model Response DTO
 */
@Data
public class ModelResponseDto {
    private String modelNo;
    private String modelName;
    private String status;
    private String message;
    private List<Map<String, Object>> modelData;
    private List<Map<String, Object>> groups;
    private List<Map<String, Object>> aggregates;
    private Map<String, Object> validationResult;
}

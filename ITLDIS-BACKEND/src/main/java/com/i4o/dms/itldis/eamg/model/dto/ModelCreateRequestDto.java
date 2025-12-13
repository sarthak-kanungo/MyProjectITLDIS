package com.i4o.dms.itldis.eamg.model.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Model Create Request DTO
 */
@Data
public class ModelCreateRequestDto {
    private String modelNo;
    private String modelName;
    private String variant;
    private List<Map<String, Object>> aggregates;
    private List<Map<String, Object>> groups;
    private List<Map<String, Object>> components;
    private Map<String, Object> additionalData;
}

package com.i4o.dms.itldis.eamg.tool.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Tool Create Request DTO
 */
@Data
public class ToolCreateRequestDto {
    private String toolNo;
    private String toolName;
    private String description;
    private List<Map<String, Object>> toolItems;
    private Map<String, Object> additionalData;
}

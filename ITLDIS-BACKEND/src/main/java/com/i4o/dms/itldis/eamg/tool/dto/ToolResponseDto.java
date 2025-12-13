package com.i4o.dms.itldis.eamg.tool.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Tool Response DTO
 */
@Data
public class ToolResponseDto {
    private String toolNo;
    private String toolName;
    private String status;
    private String message;
    private List<Map<String, Object>> toolData;
    private Map<String, Object> validationResult;
}

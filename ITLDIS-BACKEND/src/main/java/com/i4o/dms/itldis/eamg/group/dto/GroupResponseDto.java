package com.i4o.dms.itldis.eamg.group.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Group Response DTO
 */
@Data
public class GroupResponseDto {
    private String groupNo;
    private String groupName;
    private String status;
    private String message;
    private List<Map<String, Object>> groupData;
    private List<Map<String, Object>> bomData;
    private Map<String, Object> validationResult;
}

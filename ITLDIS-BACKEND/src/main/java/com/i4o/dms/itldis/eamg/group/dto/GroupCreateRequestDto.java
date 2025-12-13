package com.i4o.dms.itldis.eamg.group.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Group Create Request DTO
 */
@Data
public class GroupCreateRequestDto {
    private String groupNo;
    private String groupName;
    private String description;
    private List<Map<String, Object>> bomItems;
    private String sequence;
    private String fCode;
    private Map<String, Object> additionalData;
}

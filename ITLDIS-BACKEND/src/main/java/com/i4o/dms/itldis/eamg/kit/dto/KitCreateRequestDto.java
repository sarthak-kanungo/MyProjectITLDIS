package com.i4o.dms.itldis.eamg.kit.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Kit Create Request DTO
 */
@Data
public class KitCreateRequestDto {
    private String kitNo;
    private String kitName;
    private String description;
    private List<Map<String, Object>> kitItems;
    private Map<String, Object> additionalData;
}

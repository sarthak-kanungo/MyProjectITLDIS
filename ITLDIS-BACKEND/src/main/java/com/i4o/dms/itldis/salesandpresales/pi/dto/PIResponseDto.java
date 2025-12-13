package com.i4o.dms.itldis.salesandpresales.pi.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * PI Response DTO
 */
@Data
public class PIResponseDto {
    private String status;
    private String message;
    private String piNo;
    private List<PIItemDto> items;
    private List<Map<String, Object>> piList;
    private Map<String, Object> piDetails;
    private Map<String, Object> summary;
}

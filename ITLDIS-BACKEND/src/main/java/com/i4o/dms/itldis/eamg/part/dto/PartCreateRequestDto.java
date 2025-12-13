package com.i4o.dms.itldis.eamg.part.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Part Create Request DTO
 */
@Data
public class PartCreateRequestDto {
    private String partNo;
    private String partName;
    private String description;
    private Double price;
    private String sapPartNo;
    private List<Map<String, Object>> alternateParts;
    private Map<String, Object> additionalData;
}

package com.i4o.dms.itldis.eamg.other.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * EAMG Other Request DTO
 */
@Data
public class OtherRequestDto {
    private String tsn;
    private String partNo;
    private String oemPartNo;
    private String eamgPartNo;
    private String reportType;
    private List<Map<String, Object>> data;
    private Map<String, Object> additionalData;
}

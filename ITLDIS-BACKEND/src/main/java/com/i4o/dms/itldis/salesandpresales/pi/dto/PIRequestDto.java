package com.i4o.dms.itldis.salesandpresales.pi.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PI Request DTO
 */
@Data
public class PIRequestDto {
    private String piNo;
    private String dealerCode;
    private Date piDate;
    private String customerCode;
    private String customerName;
    private List<PIItemDto> items;
    private Double totalAmount;
    private String status;
    private String remarks;
    private Map<String, Object> additionalData;
}

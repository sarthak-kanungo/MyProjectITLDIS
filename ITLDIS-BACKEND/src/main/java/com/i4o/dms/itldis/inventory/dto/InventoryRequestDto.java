package com.i4o.dms.itldis.inventory.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Inventory Request DTO
 */
@Data
public class InventoryRequestDto {
    private String dealerCode;
    private String grnNo;
    private Date grnDate;
    private String orderNo;
    private String supplierCode;
    private List<InventoryItemDto> items;
    private String remarks;
    private Map<String, Object> additionalData;
}

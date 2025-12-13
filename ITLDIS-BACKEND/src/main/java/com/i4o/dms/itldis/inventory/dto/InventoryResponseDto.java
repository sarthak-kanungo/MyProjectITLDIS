package com.i4o.dms.itldis.inventory.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Inventory Response DTO
 */
@Data
public class InventoryResponseDto {
    private String status;
    private String message;
    private List<InventoryItemDto> inventoryItems;
    private List<Map<String, Object>> expOrders;
    private Map<String, Object> summary;
    private String grnNo;
    private String orderNo;
}

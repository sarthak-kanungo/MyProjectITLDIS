package com.i4o.dms.itldis.inventory.dto;

import lombok.Data;

/**
 * Inventory Item DTO
 */
@Data
public class InventoryItemDto {
    private String partNo;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String binLocation;
    private String remarks;
}

package com.i4o.dms.itldis.ecatalog.dto;

import lombok.Data;

/**
 * Cart Item DTO
 */
@Data
public class CartItemDto {
    private String partNo;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}

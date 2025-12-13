package com.i4o.dms.itldis.salesandpresales.pi.dto;

import lombok.Data;

/**
 * PI Item DTO
 */
@Data
public class PIItemDto {
    private String partNo;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String description;
}

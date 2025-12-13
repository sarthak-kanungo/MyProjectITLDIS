package com.i4o.dms.itldis.ecatalog.dto;

import lombok.Data;

/**
 * Price DTO
 */
@Data
public class PriceDto {
    private String partNo;
    private Double unitPrice;
    private Double discount;
    private Double finalPrice;
    private String priceListCode;
    private String currency;
}

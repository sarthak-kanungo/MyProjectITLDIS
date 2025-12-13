package com.i4o.dms.itldis.ecatalog.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * E-Catalog Response DTO
 */
@Data
public class ECatalogResponseDto {
    private String status;
    private String message;
    private List<PartDto> parts;
    private PartDto partDetails;
    private PriceDto priceDetails;
    private List<CartItemDto> cartItems;
    private Map<String, Object> summary;
}

package com.i4o.dms.itldis.ecatalog.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * E-Catalog Request DTO
 */
@Data
public class ECatalogRequestDto {
    private String partNo;
    private String partName;
    private String category;
    private Integer quantity;
    private String sessionId;
    private List<CartItemDto> cartItems;
    private Map<String, Object> additionalData;
}

package com.i4o.dms.itldis.ecatalog.dto;

import lombok.Data;

/**
 * Part DTO
 */
@Data
public class PartDto {
    private String partNo;
    private String partName;
    private String description;
    private String category;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
}

package com.i4o.dms.itldis.ecatalog.service;

import com.i4o.dms.itldis.ecatalog.dto.ECatalogRequestDto;
import com.i4o.dms.itldis.ecatalog.dto.ECatalogResponseDto;

/**
 * E-Catalog Service Interface
 * Reference: viewEcat.comEcat.*, viewEcat.orderEcat.*
 */
public interface ECatalogService {
    
    /**
     * Search Parts
     * Reference: viewEcat.comEcat.*
     */
    ECatalogResponseDto searchParts(String partNo, String partName, String category);
    
    /**
     * Get Part Details
     */
    ECatalogResponseDto getPartDetails(String partNo);
    
    /**
     * Get Price Details
     * Reference: viewEcat.orderEcat.PriceDetails
     */
    ECatalogResponseDto getPriceDetails(String partNo);
    
    /**
     * Add to Cart
     * Reference: viewEcat.orderEcat.Add_2_cart
     */
    ECatalogResponseDto addToCart(ECatalogRequestDto request);
    
    /**
     * View Cart
     * Reference: viewEcat.orderEcat.View_cart
     */
    ECatalogResponseDto viewCart(String sessionId);
    
    /**
     * Export Cart to Excel
     * Reference: viewEcat.orderEcat.ExportCartToExcel
     */
    byte[] exportCartToExcel(String sessionId);
}

package com.i4o.dms.itldis.ecatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.ecatalog.dto.ECatalogRequestDto;
import com.i4o.dms.itldis.ecatalog.dto.ECatalogResponseDto;
import com.i4o.dms.itldis.ecatalog.service.ECatalogService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * E-Catalog Controller
 * Handles part catalog, cart, and order management
 * Reference: viewEcat.comEcat.*, viewEcat.orderEcat.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/ecatalog")
public class ECatalogController {
    
    private static final Logger logger = LoggerFactory.getLogger(ECatalogController.class);
    
    @Autowired
    private ECatalogService eCatalogService;
    
    /**
     * Search Parts
     * Reference: viewEcat.comEcat.*
     */
    @GetMapping("/parts/search")
    public ResponseEntity<ApiResponse> searchParts(@RequestParam(required = false) String partNo,
                                                   @RequestParam(required = false) String partName,
                                                   @RequestParam(required = false) String category) {
        try {
            ECatalogResponseDto response = eCatalogService.searchParts(partNo, partName, category);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error searching parts", e);
            return ResponseEntity.ok(ApiResponse.error("Error searching parts: " + e.getMessage()));
        }
    }
    
    /**
     * Get Part Details
     */
    @GetMapping("/parts/details")
    public ResponseEntity<ApiResponse> getPartDetails(@RequestParam String partNo) {
        try {
            ECatalogResponseDto response = eCatalogService.getPartDetails(partNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting part details", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting part details: " + e.getMessage()));
        }
    }
    
    /**
     * Get Price Details
     * Reference: viewEcat.orderEcat.PriceDetails
     */
    @GetMapping("/parts/price")
    public ResponseEntity<ApiResponse> getPriceDetails(@RequestParam String partNo) {
        try {
            ECatalogResponseDto response = eCatalogService.getPriceDetails(partNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting price details", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting price details: " + e.getMessage()));
        }
    }
    
    /**
     * Add to Cart
     * Reference: viewEcat.orderEcat.Add_2_cart
     */
    @PostMapping("/cart/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody ECatalogRequestDto request) {
        try {
            ECatalogResponseDto response = eCatalogService.addToCart(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding to cart", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding to cart: " + e.getMessage()));
        }
    }
    
    /**
     * View Cart
     * Reference: viewEcat.orderEcat.View_cart
     */
    @GetMapping("/cart/view")
    public ResponseEntity<ApiResponse> viewCart(@RequestParam(required = false) String sessionId) {
        try {
            ECatalogResponseDto response = eCatalogService.viewCart(sessionId);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error viewing cart", e);
            return ResponseEntity.ok(ApiResponse.error("Error viewing cart: " + e.getMessage()));
        }
    }
    
    /**
     * Export Cart to Excel
     * Reference: viewEcat.orderEcat.ExportCartToExcel
     */
    @GetMapping("/cart/export")
    public ResponseEntity<byte[]> exportCartToExcel(@RequestParam(required = false) String sessionId) {
        try {
            byte[] fileData = eCatalogService.exportCartToExcel(sessionId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"cart_export.xlsx\"")
                    .body(fileData);
        } catch (Exception e) {
            logger.error("Error exporting cart", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

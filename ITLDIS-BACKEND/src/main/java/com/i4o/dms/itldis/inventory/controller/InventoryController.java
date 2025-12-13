package com.i4o.dms.itldis.inventory.controller;

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

import com.i4o.dms.itldis.inventory.dto.InventoryRequestDto;
import com.i4o.dms.itldis.inventory.dto.InventoryResponseDto;
import com.i4o.dms.itldis.inventory.service.InventoryService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * Inventory Controller
 * Handles inventory management operations
 * Reference: action.InvtoryAction, action.inventoryEXPAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/inventory")
public class InventoryController {
    
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    
    @Autowired
    private InventoryService inventoryService;
    
    /**
     * Get Inventory List
     * Reference: InvtoryAction methods
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getInventoryList(@RequestParam(required = false) String dealerCode,
                                                        @RequestParam(required = false) String searchTerm) {
        try {
            InventoryResponseDto response = inventoryService.getInventoryList(dealerCode, searchTerm);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting inventory list", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting inventory list: " + e.getMessage()));
        }
    }
    
    /**
     * Create GRN (Goods Receipt Note)
     */
    @PostMapping("/grn/create")
    public ResponseEntity<ApiResponse> createGRN(@RequestBody InventoryRequestDto request) {
        try {
            InventoryResponseDto response = inventoryService.createGRN(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating GRN", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating GRN: " + e.getMessage()));
        }
    }
    
    /**
     * Get Inventory EXP (Export) Orders
     * Reference: inventoryEXPAction
     */
    @GetMapping("/exp/orders")
    public ResponseEntity<ApiResponse> getExpOrders(@RequestParam(required = false) String dealerCode) {
        try {
            InventoryResponseDto response = inventoryService.getExpOrders(dealerCode);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting EXP orders", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting EXP orders: " + e.getMessage()));
        }
    }
    
    /**
     * Create New EXP Order
     * Reference: inventoryEXPAction.createNewEXPOrder
     */
    @PostMapping("/exp/order/create")
    public ResponseEntity<ApiResponse> createExpOrder(@RequestBody InventoryRequestDto request) {
        try {
            InventoryResponseDto response = inventoryService.createExpOrder(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating EXP order", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating EXP order: " + e.getMessage()));
        }
    }
    
    /**
     * Create GRN EXP
     * Reference: inventoryEXPAction.createGRNEXP
     */
    @PostMapping("/exp/grn/create")
    public ResponseEntity<ApiResponse> createGRNExp(@RequestBody InventoryRequestDto request) {
        try {
            InventoryResponseDto response = inventoryService.createGRNExp(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating GRN EXP", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating GRN EXP: " + e.getMessage()));
        }
    }
}

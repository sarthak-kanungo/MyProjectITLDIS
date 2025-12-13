package com.i4o.dms.itldis.inventory.service;

import com.i4o.dms.itldis.inventory.dto.InventoryRequestDto;
import com.i4o.dms.itldis.inventory.dto.InventoryResponseDto;

/**
 * Inventory Service Interface
 * Reference: action.InvtoryAction, action.inventoryEXPAction
 */
public interface InventoryService {
    
    /**
     * Get Inventory List
     * Reference: InvtoryAction methods
     */
    InventoryResponseDto getInventoryList(String dealerCode, String searchTerm);
    
    /**
     * Create GRN (Goods Receipt Note)
     * Reference: InvtoryAction.createGRN
     */
    InventoryResponseDto createGRN(InventoryRequestDto request);
    
    /**
     * Get Inventory EXP Orders
     * Reference: inventoryEXPAction.getExpOrders
     */
    InventoryResponseDto getExpOrders(String dealerCode);
    
    /**
     * Create New EXP Order
     * Reference: inventoryEXPAction.createNewEXPOrder
     */
    InventoryResponseDto createExpOrder(InventoryRequestDto request);
    
    /**
     * Create GRN EXP
     * Reference: inventoryEXPAction.createGRNEXP
     */
    InventoryResponseDto createGRNExp(InventoryRequestDto request);
}

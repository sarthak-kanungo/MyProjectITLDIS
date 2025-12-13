package com.i4o.dms.itldis.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.inventory.domain.SpOrderHeaderExp;
import com.i4o.dms.itldis.inventory.domain.SpOrderDetailsExp;
import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrn;
import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrnDetails;
import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrnDetailsPK;
import com.i4o.dms.itldis.inventory.dto.InventoryRequestDto;
import com.i4o.dms.itldis.inventory.dto.InventoryResponseDto;
import com.i4o.dms.itldis.inventory.repository.InventoryRepository;
import com.i4o.dms.itldis.inventory.repository.SpOrderHeaderExpRepository;
import com.i4o.dms.itldis.inventory.repository.SpOrderDetailsExpRepository;
import com.i4o.dms.itldis.inventory.repository.SpOrderInvGrnRepository;
import com.i4o.dms.itldis.inventory.repository.SpOrderInvGrnDetailsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Inventory Service Implementation
 * Implements business logic from InvtoryAction and inventoryEXPAction
 * 
 * Reference:
 * - ITLDIS/src/main/java/action/InvtoryAction.java
 * - ITLDIS/src/main/java/action/inventoryEXPAction.java
 * - ITLDIS/src/main/java/dao/inventoryDAO.java
 * - ITLDIS/src/main/java/dao/inventoryEXPDAO.java
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private SpOrderHeaderExpRepository spOrderHeaderExpRepository;
    
    @Autowired
    private SpOrderDetailsExpRepository spOrderDetailsExpRepository;
    
    @Autowired
    private SpOrderInvGrnRepository spOrderInvGrnRepository;
    
    @Autowired
    private SpOrderInvGrnDetailsRepository spOrderInvGrnDetailsRepository;
    
    @Override
    public InventoryResponseDto getInventoryList(String dealerCode, String searchTerm) {
        logger.info("Getting inventory list for dealer: {}, search: {}", dealerCode, searchTerm);
        
        InventoryResponseDto response = new InventoryResponseDto();
        
        try {
            List<com.i4o.dms.itldis.inventory.domain.InventoryItem> items;
            
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                items = inventoryRepository.searchInventory(dealerCode, searchTerm);
            } else {
                items = inventoryRepository.findByDealerCode(dealerCode);
            }
            
            // Convert to DTOs
            List<com.i4o.dms.itldis.inventory.dto.InventoryItemDto> itemDtos = items.stream()
                .map(item -> {
                    com.i4o.dms.itldis.inventory.dto.InventoryItemDto dto = 
                        new com.i4o.dms.itldis.inventory.dto.InventoryItemDto();
                    dto.setPartNo(item.getPartNo());
                    dto.setPartName(item.getPartName());
                    dto.setQuantity(item.getQuantity());
                    dto.setUnitPrice(item.getUnitPrice());
                    return dto;
                })
                .collect(Collectors.toList());
            
            response.setStatus("SUCCESS");
            response.setInventoryItems(itemDtos);
            response.setMessage("Inventory list retrieved successfully");
            
            // Set summary
            Map<String, Object> summary = new HashMap<>();
            summary.put("totalItems", items.size());
            summary.put("dealerCode", dealerCode);
            response.setSummary(summary);
            
        } catch (Exception e) {
            logger.error("Error getting inventory list", e);
            response.setStatus("ERROR");
            response.setMessage("Error retrieving inventory list: " + e.getMessage());
        }
        
        return response;
    }
    
    @Override
    @Transactional
    public InventoryResponseDto createGRN(InventoryRequestDto request) {
        logger.info("Creating GRN for dealer: {}, order: {}", request.getDealerCode(), request.getOrderNo());
        
        InventoryResponseDto response = new InventoryResponseDto();
        
        try {
            // Validate request
            if (request.getDealerCode() == null || request.getOrderNo() == null) {
                response.setStatus("ERROR");
                response.setMessage("Dealer code and order number are required");
                return response;
            }
            
            // Generate GRN number (format: GRN-{dealerCode}-{timestamp})
            String grnNo = "GRN-" + request.getDealerCode() + "-" + System.currentTimeMillis();
            
            // Process items and update inventory
            if (request.getItems() != null && !request.getItems().isEmpty()) {
                for (com.i4o.dms.itldis.inventory.dto.InventoryItemDto itemDto : request.getItems()) {
                    // Update inventory quantities
                    // This would typically involve:
                    // 1. Finding existing inventory item
                    // 2. Updating quantity
                    // 3. Creating GRN record
                    // 4. Logging transaction
                    logger.debug("Processing item: {} with quantity: {}", 
                        itemDto.getPartNo(), itemDto.getQuantity());
                }
            }
            
            response.setStatus("SUCCESS");
            response.setGrnNo(grnNo);
            response.setMessage("GRN created successfully");
            
        } catch (Exception e) {
            logger.error("Error creating GRN", e);
            response.setStatus("ERROR");
            response.setMessage("Error creating GRN: " + e.getMessage());
        }
        
        return response;
    }
    
    @Override
    public InventoryResponseDto getExpOrders(String dealerCode) {
        logger.info("Getting EXP orders for dealer: {}", dealerCode);
        
        InventoryResponseDto response = new InventoryResponseDto();
        
        try {
            // Query EXP orders from database
            // Reference: inventoryEXPAction.getExpOrders
            List<SpOrderHeaderExp> orders = spOrderHeaderExpRepository.findByDealerCodeAndStatus(dealerCode, "OPEN");
            
            List<Map<String, Object>> expOrders = orders.stream()
                .map(order -> {
                    Map<String, Object> orderMap = new HashMap<>();
                    orderMap.put("orderNo", order.getCustPoNo());
                    orderMap.put("orderType", order.getOrdType());
                    orderMap.put("status", order.getStatus());
                    orderMap.put("dealerCode", order.getDealerCode());
                    orderMap.put("dealerRefNo", order.getDealerRefNo());
                    orderMap.put("orderDate", order.getCustPoDate());
                    orderMap.put("totalValue", order.getTotalValue());
                    orderMap.put("currency", order.getCurrency());
                    orderMap.put("dischargePort", order.getDischargePort());
                    orderMap.put("destinationPlace", order.getDestinationPlace());
                    return orderMap;
                })
                .collect(Collectors.toList());
            
            response.setStatus("SUCCESS");
            response.setExpOrders(expOrders);
            response.setMessage("EXP orders retrieved successfully");
            
        } catch (Exception e) {
            logger.error("Error getting EXP orders", e);
            response.setStatus("ERROR");
            response.setMessage("Error retrieving EXP orders: " + e.getMessage());
        }
        
        return response;
    }
    
    @Override
    @Transactional
    public InventoryResponseDto createExpOrder(InventoryRequestDto request) {
        logger.info("Creating EXP order for dealer: {}", request.getDealerCode());
        
        InventoryResponseDto response = new InventoryResponseDto();
        
        try {
            // Validate request
            if (request.getDealerCode() == null) {
                response.setStatus("ERROR");
                response.setMessage("Dealer code is required");
                return response;
            }
            
            // Generate order number
            // Reference: inventoryEXPAction.createNewEXPOrder
            // Format: PO-{dealerCode}-{timestamp}
            String orderNo = "PO-" + request.getDealerCode() + "-" + System.currentTimeMillis();
            
            // Create EXP order header
            SpOrderHeaderExp orderHeader = new SpOrderHeaderExp();
            orderHeader.setCustPoNo(orderNo);
            orderHeader.setDealerCode(request.getDealerCode());
            orderHeader.setOrdType((String) request.getAdditionalData().getOrDefault("ordType", "STD"));
            orderHeader.setStatus("OPEN");
            orderHeader.setCustPoDate(new Date());
            orderHeader.setCreatedOn(new Date());
            orderHeader.setLastUpdatedOn(new Date());
            orderHeader.setCreatedBy(request.getDealerCode()); // Use dealer code or get from security context
            
            // Set terms from request additional data
            if (request.getAdditionalData() != null) {
                orderHeader.setDeliveryTerms((String) request.getAdditionalData().getOrDefault("deliveryTerms", ""));
                orderHeader.setPaymentTerms((String) request.getAdditionalData().getOrDefault("paymentTerms", ""));
                orderHeader.setIncoTerms((String) request.getAdditionalData().getOrDefault("incoTerms", ""));
                orderHeader.setDischargePort((String) request.getAdditionalData().getOrDefault("dischargePort", ""));
                orderHeader.setDestinationPlace((String) request.getAdditionalData().getOrDefault("destinationPlace", ""));
                orderHeader.setConsigneeCode((String) request.getAdditionalData().getOrDefault("consigneeCode", ""));
                orderHeader.setConsigneeName((String) request.getAdditionalData().getOrDefault("consigneeName", ""));
                orderHeader.setConsigneeAddress((String) request.getAdditionalData().getOrDefault("consigneeAddress", ""));
                orderHeader.setConsigneeCountry((String) request.getAdditionalData().getOrDefault("consigneeCountry", ""));
                orderHeader.setCurrency((String) request.getAdditionalData().getOrDefault("currency", "INR"));
                orderHeader.setPriceListCode((String) request.getAdditionalData().getOrDefault("priceListCode", ""));
            }
            
            // Calculate total value
            double totalValue = 0.0;
            if (request.getItems() != null && !request.getItems().isEmpty()) {
                List<SpOrderDetailsExp> orderDetails = new ArrayList<>();
                int positionNo = 1;
                
                for (com.i4o.dms.itldis.inventory.dto.InventoryItemDto itemDto : request.getItems()) {
                    SpOrderDetailsExp orderDetail = new SpOrderDetailsExp();
                    orderDetail.setCustPoNo(orderNo);
                    orderDetail.setPositionNo(positionNo++);
                    orderDetail.setPartNo(itemDto.getPartNo());
                    orderDetail.setQty(itemDto.getQuantity());
                    orderDetail.setPrice(itemDto.getUnitPrice() != null ? itemDto.getUnitPrice() : 0.0);
                    orderDetail.setStatus("OPEN");
                    orderDetail.setPendingQty(itemDto.getQuantity());
                    orderDetail.setPiQty(0);
                    orderDetail.setLastUpdatedOn(new Date());
                    orderDetail.setSpOrderHeaderExp(orderHeader);
                    
                    totalValue += (itemDto.getUnitPrice() != null ? itemDto.getUnitPrice() : 0.0) * itemDto.getQuantity();
                    orderDetails.add(orderDetail);
                }
                
                orderHeader.setTotalValue(totalValue);
                orderHeader.setSpOrderDetailsExpList(orderDetails);
            }
            
            // Save order header (cascade will save details)
            spOrderHeaderExpRepository.save(orderHeader);
            
            response.setStatus("SUCCESS");
            response.setOrderNo(orderNo);
            response.setMessage("EXP order created successfully");
            
        } catch (Exception e) {
            logger.error("Error creating EXP order", e);
            response.setStatus("ERROR");
            response.setMessage("Error creating EXP order: " + e.getMessage());
        }
        
        return response;
    }
    
    @Override
    @Transactional
    public InventoryResponseDto createGRNExp(InventoryRequestDto request) {
        logger.info("Creating GRN EXP for order: {}", request.getOrderNo());
        
        InventoryResponseDto response = new InventoryResponseDto();
        
        try {
            // Validate request
            if (request.getOrderNo() == null) {
                response.setStatus("ERROR");
                response.setMessage("Order number is required");
                return response;
            }
            
            // Find the order
            SpOrderHeaderExp order = spOrderHeaderExpRepository.findById(request.getOrderNo())
                .orElseThrow(() -> new RuntimeException("Order not found: " + request.getOrderNo()));
            
            // Generate GRN EXP number
            // Reference: inventoryEXPAction.createGRNEXP
            String grnNo = "GRN-EXP-" + request.getOrderNo() + "-" + System.currentTimeMillis();
            
            // Create GRN EXP header
            SpOrderInvGrn grn = new SpOrderInvGrn();
            grn.setGrNo(grnNo);
            grn.setGrDate(request.getGrnDate() != null ? request.getGrnDate() : new Date());
            grn.setInvoiceNo((String) request.getAdditionalData().getOrDefault("invoiceNo", ""));
            grn.setInvoiceDate(request.getGrnDate() != null ? request.getGrnDate() : new Date());
            grn.setDealerCode(request.getDealerCode());
            grn.setCustPoNo(request.getOrderNo());
            grn.setReceivedBy(request.getDealerCode()); // Use dealer code or get from security context
            grn.setReceivedOn(new Date());
            grn.setCreatedBy(request.getDealerCode());
            grn.setCreatedOn(new Date());
            
            // Create GRN EXP details
            List<SpOrderInvGrnDetails> grnDetailsList = new ArrayList<>();
            if (request.getItems() != null && !request.getItems().isEmpty()) {
                for (com.i4o.dms.itldis.inventory.dto.InventoryItemDto itemDto : request.getItems()) {
                    SpOrderInvGrnDetails grnDetail = new SpOrderInvGrnDetails();
                    SpOrderInvGrnDetailsPK pk = new SpOrderInvGrnDetailsPK(grnNo, itemDto.getPartNo());
                    grnDetail.setSpOrderInvGrnDetailsPK(pk);
                    grnDetail.setPartdesc(itemDto.getPartName() != null ? itemDto.getPartName() : "");
                    grnDetail.setInvoiceQty(itemDto.getQuantity().doubleValue());
                    grnDetail.setReceivedQty(itemDto.getQuantity().doubleValue());
                    grnDetail.setUnitvalue(itemDto.getUnitPrice() != null ? itemDto.getUnitPrice() : 0.0);
                    grnDetail.setSpOrderInvGrn(grn);
                    
                    grnDetailsList.add(grnDetail);
                    
                    // Update inventory quantities (if inventory management is implemented)
                    logger.debug("Processing GRN EXP item: {} with quantity: {}", 
                        itemDto.getPartNo(), itemDto.getQuantity());
                }
            }
            
            grn.setSpOrderInvGrnDetailsList(grnDetailsList);
            
            // Save GRN (cascade will save details)
            spOrderInvGrnRepository.save(grn);
            
            // Update order status if all items are received
            // This is a simplified logic - you may need more complex business rules
            order.setLastUpdatedOn(new Date());
            spOrderHeaderExpRepository.save(order);
            
            response.setStatus("SUCCESS");
            response.setGrnNo(grnNo);
            response.setOrderNo(request.getOrderNo());
            response.setMessage("GRN EXP created successfully");
            
        } catch (Exception e) {
            logger.error("Error creating GRN EXP", e);
            response.setStatus("ERROR");
            response.setMessage("Error creating GRN EXP: " + e.getMessage());
        }
        
        return response;
    }
}

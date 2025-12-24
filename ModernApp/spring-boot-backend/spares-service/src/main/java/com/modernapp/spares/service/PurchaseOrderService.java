package com.modernapp.spares.service;

import com.modernapp.spares.model.PurchaseOrder;
import com.modernapp.spares.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseOrderService {
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public Optional<PurchaseOrder> getPurchaseOrderByPoNo(String poNo) {
        return purchaseOrderRepository.findByPoNo(poNo);
    }

    public List<PurchaseOrder> getPurchaseOrdersByStatus(String status) {
        return purchaseOrderRepository.findByStatus(status);
    }

    public List<PurchaseOrder> searchPurchaseOrdersBySupplierName(String supplierName) {
        return purchaseOrderRepository.findBySupplierNameContainingIgnoreCase(supplierName);
    }

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder poDetails) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found with id: " + id));
        
        purchaseOrder.setSupplierName(poDetails.getSupplierName());
        purchaseOrder.setSupplierCode(poDetails.getSupplierCode());
        purchaseOrder.setTotalAmount(poDetails.getTotalAmount());
        purchaseOrder.setStatus(poDetails.getStatus());
        
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder approvePurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found with id: " + id));
        purchaseOrder.setStatus("APPROVED");
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}


package com.modernapp.spares.controller;

import com.modernapp.spares.model.PurchaseOrder;
import com.modernapp.spares.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spares/purchase-orders")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        Optional<PurchaseOrder> po = purchaseOrderService.getPurchaseOrderById(id);
        return po.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/poNo/{poNo}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderByPoNo(@PathVariable String poNo) {
        Optional<PurchaseOrder> po = purchaseOrderService.getPurchaseOrderByPoNo(poNo);
        return po.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PurchaseOrder>> getPurchaseOrdersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrdersByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PurchaseOrder>> searchPurchaseOrders(@RequestParam String supplierName) {
        return ResponseEntity.ok(purchaseOrderService.searchPurchaseOrdersBySupplierName(supplierName));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder savedPO = purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) {
        try {
            PurchaseOrder updatedPO = purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);
            return ResponseEntity.ok(updatedPO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PurchaseOrder> approvePurchaseOrder(@PathVariable Long id) {
        try {
            PurchaseOrder approvedPO = purchaseOrderService.approvePurchaseOrder(id);
            return ResponseEntity.ok(approvedPO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }
}


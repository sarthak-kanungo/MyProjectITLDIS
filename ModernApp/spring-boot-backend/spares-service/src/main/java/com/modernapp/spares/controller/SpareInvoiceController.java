package com.modernapp.spares.controller;

import com.modernapp.spares.model.SpareInvoice;
import com.modernapp.spares.service.SpareInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spares/invoices")
@CrossOrigin(origins = "*")
public class SpareInvoiceController {

    @Autowired
    private SpareInvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<SpareInvoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpareInvoice> getInvoiceById(@PathVariable Long id) {
        Optional<SpareInvoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/invoiceNo/{invoiceNo}")
    public ResponseEntity<SpareInvoice> getInvoiceByInvoiceNo(@PathVariable String invoiceNo) {
        Optional<SpareInvoice> invoice = invoiceService.getInvoiceByInvoiceNo(invoiceNo);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SpareInvoice>> getInvoicesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(invoiceService.getInvoicesByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpareInvoice>> searchInvoices(@RequestParam String customerName) {
        return ResponseEntity.ok(invoiceService.searchInvoicesByCustomerName(customerName));
    }

    @PostMapping
    public ResponseEntity<SpareInvoice> createInvoice(@RequestBody SpareInvoice invoice) {
        SpareInvoice savedInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpareInvoice> updateInvoice(@PathVariable Long id, @RequestBody SpareInvoice invoice) {
        try {
            SpareInvoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok(updatedInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<SpareInvoice> cancelInvoice(@PathVariable Long id) {
        try {
            SpareInvoice cancelledInvoice = invoiceService.cancelInvoice(id);
            return ResponseEntity.ok(cancelledInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}


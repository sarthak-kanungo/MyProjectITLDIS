package com.modernapp.services.controller;

import com.modernapp.services.model.ServiceInvoice;
import com.modernapp.services.service.ServiceInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services/invoices")
@CrossOrigin(origins = "*")
public class ServiceInvoiceController {

    @Autowired
    private ServiceInvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<ServiceInvoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceInvoice> getInvoiceById(@PathVariable Long id) {
        Optional<ServiceInvoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/invoiceNo/{invoiceNo}")
    public ResponseEntity<ServiceInvoice> getInvoiceByInvoiceNo(@PathVariable String invoiceNo) {
        Optional<ServiceInvoice> invoice = invoiceService.getInvoiceByInvoiceNo(invoiceNo);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jobCardNo/{jobCardNo}")
    public ResponseEntity<ServiceInvoice> getInvoiceByJobCardNo(@PathVariable String jobCardNo) {
        Optional<ServiceInvoice> invoice = invoiceService.getInvoiceByJobCardNo(jobCardNo);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceInvoice>> getInvoicesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(invoiceService.getInvoicesByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceInvoice>> searchInvoices(@RequestParam String customerName) {
        return ResponseEntity.ok(invoiceService.searchInvoicesByCustomerName(customerName));
    }

    @PostMapping
    public ResponseEntity<ServiceInvoice> createInvoice(@RequestBody ServiceInvoice invoice) {
        ServiceInvoice savedInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PostMapping("/generate/{jobCardNo}")
    public ResponseEntity<ServiceInvoice> generateInvoiceFromJobCard(@PathVariable String jobCardNo, @RequestBody ServiceInvoice invoice) {
        ServiceInvoice generatedInvoice = invoiceService.generateInvoiceFromJobCard(jobCardNo, invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceInvoice> updateInvoice(@PathVariable Long id, @RequestBody ServiceInvoice invoice) {
        try {
            ServiceInvoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok(updatedInvoice);
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


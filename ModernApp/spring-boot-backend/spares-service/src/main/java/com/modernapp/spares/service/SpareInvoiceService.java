package com.modernapp.spares.service;

import com.modernapp.spares.model.SpareInvoice;
import com.modernapp.spares.repository.SpareInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpareInvoiceService {
    
    @Autowired
    private SpareInvoiceRepository invoiceRepository;

    public List<SpareInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<SpareInvoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Optional<SpareInvoice> getInvoiceByInvoiceNo(String invoiceNo) {
        return invoiceRepository.findByInvoiceNo(invoiceNo);
    }

    public List<SpareInvoice> getInvoicesByStatus(String status) {
        return invoiceRepository.findByStatus(status);
    }

    public List<SpareInvoice> searchInvoicesByCustomerName(String customerName) {
        return invoiceRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }

    public SpareInvoice saveInvoice(SpareInvoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public SpareInvoice updateInvoice(Long id, SpareInvoice invoiceDetails) {
        SpareInvoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        
        invoice.setCustomerName(invoiceDetails.getCustomerName());
        invoice.setCustomerCode(invoiceDetails.getCustomerCode());
        invoice.setReferenceDocument(invoiceDetails.getReferenceDocument());
        invoice.setDocumentType(invoiceDetails.getDocumentType());
        invoice.setTotalAmount(invoiceDetails.getTotalAmount());
        invoice.setStatus(invoiceDetails.getStatus());
        
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public SpareInvoice cancelInvoice(Long id) {
        SpareInvoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        invoice.setStatus("CANCELLED");
        return invoiceRepository.save(invoice);
    }
}


package com.modernapp.services.service;

import com.modernapp.services.model.ServiceInvoice;
import com.modernapp.services.repository.ServiceInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceInvoiceService {
    
    @Autowired
    private ServiceInvoiceRepository invoiceRepository;

    public List<ServiceInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<ServiceInvoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Optional<ServiceInvoice> getInvoiceByInvoiceNo(String invoiceNo) {
        return invoiceRepository.findByInvoiceNo(invoiceNo);
    }

    public Optional<ServiceInvoice> getInvoiceByJobCardNo(String jobCardNo) {
        return invoiceRepository.findByJobCardNo(jobCardNo);
    }

    public List<ServiceInvoice> getInvoicesByStatus(String status) {
        return invoiceRepository.findByStatus(status);
    }

    public List<ServiceInvoice> searchInvoicesByCustomerName(String customerName) {
        return invoiceRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }

    public ServiceInvoice saveInvoice(ServiceInvoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public ServiceInvoice generateInvoiceFromJobCard(String jobCardNo, ServiceInvoice invoice) {
        invoice.setJobCardNo(jobCardNo);
        invoice.setStatus("GENERATED");
        return invoiceRepository.save(invoice);
    }

    public ServiceInvoice updateInvoice(Long id, ServiceInvoice invoiceDetails) {
        ServiceInvoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        
        invoice.setTotalPartsValue(invoiceDetails.getTotalPartsValue());
        invoice.setTotalLabourCharges(invoiceDetails.getTotalLabourCharges());
        invoice.setTotalAmount(invoiceDetails.getTotalAmount());
        invoice.setStatus(invoiceDetails.getStatus());
        
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}


package com.modernapp.services.repository;

import com.modernapp.services.model.ServiceInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceInvoiceRepository extends JpaRepository<ServiceInvoice, Long> {
    Optional<ServiceInvoice> findByInvoiceNo(String invoiceNo);
    List<ServiceInvoice> findByStatus(String status);
    Optional<ServiceInvoice> findByJobCardNo(String jobCardNo);
    List<ServiceInvoice> findByCustomerNameContainingIgnoreCase(String customerName);
}


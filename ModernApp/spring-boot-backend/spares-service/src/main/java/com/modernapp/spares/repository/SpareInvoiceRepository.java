package com.modernapp.spares.repository;

import com.modernapp.spares.model.SpareInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpareInvoiceRepository extends JpaRepository<SpareInvoice, Long> {
    Optional<SpareInvoice> findByInvoiceNo(String invoiceNo);
    List<SpareInvoice> findByStatus(String status);
    List<SpareInvoice> findByCustomerNameContainingIgnoreCase(String customerName);
}


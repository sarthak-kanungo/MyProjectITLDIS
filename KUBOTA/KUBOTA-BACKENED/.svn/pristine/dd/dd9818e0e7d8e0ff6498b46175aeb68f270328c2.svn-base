package com.i4o.dms.kubota.salesandpresales.sales.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.salesandpresales.sales.invoice.domain.SalesInvoiceCancelRequest;

public interface InvoiceCancellationRepository extends JpaRepository<SalesInvoiceCancelRequest, Long> {

	SalesInvoiceCancelRequest findByInvoiceId(Long invoiceId);
}

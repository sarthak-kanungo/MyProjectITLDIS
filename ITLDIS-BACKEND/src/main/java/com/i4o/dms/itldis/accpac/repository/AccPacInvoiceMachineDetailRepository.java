package com.i4o.dms.itldis.accpac.repository;

import com.i4o.dms.itldis.accpac.domain.AccPacInvoicePartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccPacInvoiceMachineDetailRepository extends JpaRepository<AccPacInvoicePartDetails,Long> {
}

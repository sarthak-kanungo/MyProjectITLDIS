package com.i4o.dms.itldis.spares.purchase.backordercancellation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.spares.purchase.backordercancellation.domain.SPBackOrderCancellationApproval;

public interface SPBackOrderCancellationApprovalRepo extends JpaRepository<SPBackOrderCancellationApproval, Long>{

}

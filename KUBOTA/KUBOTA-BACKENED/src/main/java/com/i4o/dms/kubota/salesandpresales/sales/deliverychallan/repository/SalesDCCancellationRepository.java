package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain.DeliveryChallanCancelRequest;

public interface SalesDCCancellationRepository  extends JpaRepository<DeliveryChallanCancelRequest,Long> {

}

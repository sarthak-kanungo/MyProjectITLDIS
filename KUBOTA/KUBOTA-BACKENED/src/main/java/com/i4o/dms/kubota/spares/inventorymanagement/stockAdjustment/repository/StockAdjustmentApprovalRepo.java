package com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.domain.StockAdjustmentApprovalEntity;

public interface StockAdjustmentApprovalRepo extends JpaRepository<StockAdjustmentApprovalEntity, Long> {

	
}

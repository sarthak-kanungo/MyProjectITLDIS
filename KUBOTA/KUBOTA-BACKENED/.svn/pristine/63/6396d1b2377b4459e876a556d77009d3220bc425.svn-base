package com.i4o.dms.kubota.masters.postatus.repository;

import com.i4o.dms.kubota.masters.postatus.domain.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderStatusRepo extends JpaRepository<PurchaseOrderStatus,Long> {

    @Query(value = "{call sp_getPurchaseOrderStatus()}", nativeQuery = true)
    List<Map<String,Object>> getPurchaseOrderStatus();
}

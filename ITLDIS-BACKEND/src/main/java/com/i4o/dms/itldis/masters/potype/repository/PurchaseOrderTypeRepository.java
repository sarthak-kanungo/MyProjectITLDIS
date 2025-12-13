package com.i4o.dms.itldis.masters.potype.repository;

import com.i4o.dms.itldis.masters.potype.domain.PurchaseOrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderTypeRepository extends JpaRepository<PurchaseOrderType,Long> {

    @Query(value = "{call sp_getPurchaseOrderType()}", nativeQuery = true)
    List<Map<String,Object>> getPurchaseOrderType();
}

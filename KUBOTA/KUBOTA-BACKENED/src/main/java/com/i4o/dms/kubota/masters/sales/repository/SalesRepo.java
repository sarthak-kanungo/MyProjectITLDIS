package com.i4o.dms.kubota.masters.sales.repository;

import com.i4o.dms.kubota.masters.sales.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SalesRepo extends JpaRepository<Sales, Long> {

    @Query(value = "{call sp_getSearchVehicleRegistration(:chassisNo,:customerCode,:page,:size)}", nativeQuery = true)
    List<Map<String, Object>> getSalesSearch(@Param("chassisNo") String chassisNo,
                                             @Param("customerCode") String customerCode,
                                             @Param("page") Integer page,
                                             @Param("size") Integer size);


    Sales getById(Long id);


}

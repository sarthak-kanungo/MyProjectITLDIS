package com.i4o.dms.kubota.masters.spares.dbentities.repository;

import com.i4o.dms.kubota.masters.spares.dbentities.domain.SparesMtDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SparesMtDocumentTypeRepo extends JpaRepository<SparesMtDocumentType,Long> {

    @Query(value="{call sp_spares_mt_document_type_dropdown()}",nativeQuery = true)
    List<Map<String, Object>> getReferenceDocumentTypes();

    @Query(value="{call sp_spares_mt_sales_type_dropdown()}",nativeQuery = true)
    List<Map<String, Object>> getSpareSalesTypeTypes();


}

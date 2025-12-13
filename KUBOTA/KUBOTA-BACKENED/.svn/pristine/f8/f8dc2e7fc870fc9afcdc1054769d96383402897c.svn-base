package com.i4o.dms.kubota.masters.warranty.repository;


import com.i4o.dms.kubota.masters.warranty.domain.WarrantyMtPartFailureCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WarrantyMtPartFailureCodeRepo extends JpaRepository<WarrantyMtPartFailureCode,Long> {

    @Query(value = "{call warranty_mt_autocomplete_part_failure_code(:machineMasterId,:code)}", nativeQuery = true)
    List<Map<String, Object>> autoCompletePartFailureCode(@Param("machineMasterId") Long machineMasterId,@Param("code")String code);

    //Suraj--29/11/2022
    @Query(value = "{call getAllFailureCodeAndDesc(:code)}", nativeQuery = true)
    List<Map<String, Object>> getPartFailureCode(@Param("code")String code);
    //Suraj--29/11/2022
}
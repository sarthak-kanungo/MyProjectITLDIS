package com.i4o.dms.kubota.masters.spares.sparepartmaster.repository;

import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SparesPartMasterRepo extends JpaRepository<SparePartMaster, Long> {

    @Query(value = "{call sp_spares_get_sparepart_details_for_mrc(:itemNumber)}", nativeQuery = true)
    List<Map<String, Object>> getSparePartItemDetailsForMrc(@Param("itemNumber") String itemNumber);

    @Query(value = "{call sp_spares_mt_autocompletePartNumber(:itemNumber,:itemId,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> autoCompleteSparePartNumber(@Param("itemNumber") String itemNumber,
                                                          @Param("itemId") String itemId,
                                                          @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_get_part_details_for_quotation(:itemNumber,:branchId,:state)}", nativeQuery = true)
    Map<String, Object> getSparePartDetailsForQuotation(@Param("itemNumber") String itemNumber,
                                                        @Param("branchId") Long branchId,
                                                        @Param("state") String state);

	//SparePartMasterDto findByItemNo(String itemNo);	//Suraj--19-02-2023
    SparePartMaster findByItemNo(String itemNo);	//Suraj--19-02-2023
}

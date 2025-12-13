package com.i4o.dms.kubota.masters.service.pdi.repository;

import com.i4o.dms.kubota.masters.service.pdi.domain.ServiceMtPdiAggregate;
import com.i4o.dms.kubota.masters.service.pdi.dto.CheckDraftModeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceMtPdiAggregateRepo extends JpaRepository<ServiceMtPdiAggregate, Long> {

    @Query(value = "{call sp_service_pdi_autocomplete_chassisNo(:dealerId,:chassisNo)}", nativeQuery = true)
    List<Map<String, Object>> autoCompleteChassisNo(@Param("dealerId") Long dealerId,
                                                    @Param("chassisNo") String chassisNo);

    @Query(value = "{call sp_service_pdi_get_grnDetails_by_chassisNo(:chassisNo)}", nativeQuery = true)
    Map<String, Object> grnDetailsByChassisNo(@Param("chassisNo") String chassisNo);

    @Query(value = "{call sp_service_pdi_check_chassis_no_draft_mode(:chassisNo,:dealerId)}", nativeQuery = true)
    CheckDraftModeDto servicePdiDraftModeCheck(@Param("chassisNo") String chassisNo, @Param("dealerId") Long dealerId);

}

package com.i4o.dms.kubota.masters.service.psc.repository;

import com.i4o.dms.kubota.masters.service.pdi.domain.ServiceMtPdiAggregate;
import com.i4o.dms.kubota.masters.service.psc.domain.ServiceMtPscAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ServiceMtPscRepository  extends JpaRepository<ServiceMtPscAggregate,Long> {

    @Query(value = "{call sp_service_psc_mt_get_aggregate_and_checkpoint()}",nativeQuery = true)
    List<Map<String, Object>> getAllCheckpoints();
}

package com.i4o.dms.kubota.masters.service.pdi.repository;

import com.i4o.dms.kubota.masters.service.pdi.domain.ServiceMtPdiModelAggregateCheckpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceMtPdiModelAggregateCheckpointMappingRepo extends JpaRepository<ServiceMtPdiModelAggregateCheckpointMapping,Long> {

    @Query(value = "{call sp_service_pdi_get_aggregate_and_checkpoints(:model)}",nativeQuery = true)
    List<Map<String,Object>> getAggregateAndCheckpoints(@Param("model") String model);

}

package com.i4o.dms.kubota.masters.service.reinstallation.repository;

import com.i4o.dms.kubota.masters.service.reinstallation.domain.ServiceMtRiSeriesAggregateCheckpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceMtRiModelAggregateMappingRepo extends JpaRepository<ServiceMtRiSeriesAggregateCheckpointMapping,Long> {

    @Query(value = "{call sp_service_ri_mt_get_aggregate_checkpoint_by_series(:series)}",nativeQuery = true)
    List<Map<String, Object>> getAllReInstallationDetails(@Param("series") String series);
}



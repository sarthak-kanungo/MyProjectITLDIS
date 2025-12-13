package com.i4o.dms.kubota.masters.service.machineinstallation.deliveryInstallation.repository;

import com.i4o.dms.kubota.masters.service.machineinstallation.deliveryInstallation.domain.ServiceMtDiModelAggregateCheckpointMapping;
import com.i4o.dms.kubota.masters.service.pdi.domain.ServiceMtPdiModelAggregateCheckpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceMtDeliveryInstallationRepository extends JpaRepository<ServiceMtDiModelAggregateCheckpointMapping,Long> {

    @Query(value = "{call sp_service_pdc_get_aggregate_and_checkpoints(:model)}",nativeQuery = true)
    List<Map<String, Object>> getAllDeliveryInstallationDetails(@Param("model") String Model);
}

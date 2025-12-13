package com.i4o.dms.kubota.masters.service.machineinstallation.fieldInstallation.repository;

import com.i4o.dms.kubota.masters.service.machineinstallation.fieldInstallation.controller.ServiceMtFieldInstallationController;
import com.i4o.dms.kubota.masters.service.machineinstallation.fieldInstallation.domain.ServiceMtFieldInstallationAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceMtFieldInstallationRepository extends JpaRepository<ServiceMtFieldInstallationAggregate,Long> {

    @Query(value = "{call sp_service_fi_mt_get_aggregate_checkpoint_by_model(:model)}",nativeQuery = true)
    List<Map<String, Object>> getAllFieldInstallationDetails(@Param("model") String Model);
}

package com.i4o.dms.itldis.masters.service.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.i4o.dms.itldis.masters.service.domain.ServiceMtCheckPoint;

public interface CheckpointRepository extends JpaRepository<ServiceMtCheckPoint,Long>
{

    @Query(value = "{call sp_mt_service_getChecklist(:transType,:chassis,:model)}",nativeQuery = true)
    List<Map<String,Object>> getAllCheckpoint(String transType, String chassis, String model);


}

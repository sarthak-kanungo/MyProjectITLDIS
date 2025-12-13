package com.i4o.dms.kubota.service.psc.repository;

import com.i4o.dms.kubota.service.pdi.domain.ServicePdiChassisCheckpointInfo;
import com.i4o.dms.kubota.service.psc.domain.ServicePscChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PscServiceCheckpointRepo extends JpaRepository<ServicePscChassisCheckpointInfo,Object>  {
}

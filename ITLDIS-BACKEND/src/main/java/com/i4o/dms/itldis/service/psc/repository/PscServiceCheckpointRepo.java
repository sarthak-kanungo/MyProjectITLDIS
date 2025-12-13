package com.i4o.dms.itldis.service.psc.repository;

import com.i4o.dms.itldis.service.pdi.domain.ServicePdiChassisCheckpointInfo;
import com.i4o.dms.itldis.service.psc.domain.ServicePscChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PscServiceCheckpointRepo extends JpaRepository<ServicePscChassisCheckpointInfo,Object>  {
}

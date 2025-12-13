package com.i4o.dms.itldis.service.pdi.repository;

import com.i4o.dms.itldis.service.pdi.domain.ServicePdiChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCheckpointRepo extends JpaRepository<ServicePdiChassisCheckpointInfo,Object> {
}

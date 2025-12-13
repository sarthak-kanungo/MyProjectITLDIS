package com.i4o.dms.itldis.service.pdc.repository;

import com.i4o.dms.itldis.service.pdc.domain.ServicePdcChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCheckpointPdcRepo extends JpaRepository<ServicePdcChassisCheckpointInfo,Object> {
}

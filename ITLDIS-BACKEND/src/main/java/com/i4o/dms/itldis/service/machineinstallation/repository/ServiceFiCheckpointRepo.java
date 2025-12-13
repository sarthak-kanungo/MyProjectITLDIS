package com.i4o.dms.itldis.service.machineinstallation.repository;

import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceFiChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceFiCheckpointRepo extends JpaRepository<ServiceFiChassisCheckpointInfo,Object> {
}

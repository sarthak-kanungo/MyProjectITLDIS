package com.i4o.dms.itldis.service.machineinstallation.repository;

import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceDiChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDiCheckpointRepo extends JpaRepository<ServiceDiChassisCheckpointInfo,Object> {

}

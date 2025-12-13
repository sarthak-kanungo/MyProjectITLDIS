package com.i4o.dms.kubota.service.machineinstallation.repository;

import com.i4o.dms.kubota.service.machineinstallation.domain.ServiceDiChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDiCheckpointRepo extends JpaRepository<ServiceDiChassisCheckpointInfo,Object> {

}

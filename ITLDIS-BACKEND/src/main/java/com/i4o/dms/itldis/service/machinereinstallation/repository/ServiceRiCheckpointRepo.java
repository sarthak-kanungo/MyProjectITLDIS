package com.i4o.dms.itldis.service.machinereinstallation.repository;


import com.i4o.dms.itldis.service.machinereinstallation.domain.ServiceReinstallationChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRiCheckpointRepo extends JpaRepository<ServiceReinstallationChassisCheckpointInfo,Object> {
}

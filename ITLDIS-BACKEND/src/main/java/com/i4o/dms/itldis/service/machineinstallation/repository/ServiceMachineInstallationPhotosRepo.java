package com.i4o.dms.itldis.service.machineinstallation.repository;

import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceInstallationPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMachineInstallationPhotosRepo extends JpaRepository<ServiceInstallationPhotos,Long> {
}

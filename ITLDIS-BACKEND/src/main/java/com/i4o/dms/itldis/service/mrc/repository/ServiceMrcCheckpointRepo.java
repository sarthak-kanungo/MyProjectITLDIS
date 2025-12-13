package com.i4o.dms.itldis.service.mrc.repository;

import com.i4o.dms.itldis.service.mrc.domain.ServiceMrcChassisCheckpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMrcCheckpointRepo extends JpaRepository<ServiceMrcChassisCheckpointInfo,Object> {
}

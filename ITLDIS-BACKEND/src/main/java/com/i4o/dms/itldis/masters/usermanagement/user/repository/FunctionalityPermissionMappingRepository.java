package com.i4o.dms.itldis.masters.usermanagement.user.repository;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.FunctionalityPermissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionalityPermissionMappingRepository extends JpaRepository<FunctionalityPermissionMapping,Long> {
}

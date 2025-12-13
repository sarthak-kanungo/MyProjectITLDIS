package com.i4o.dms.itldis.masters.usermanagement.user.repository;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.UserFunctionalityPermissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFunctionalityPermissionMappingRepository extends JpaRepository<UserFunctionalityPermissionMapping,Long> {
}

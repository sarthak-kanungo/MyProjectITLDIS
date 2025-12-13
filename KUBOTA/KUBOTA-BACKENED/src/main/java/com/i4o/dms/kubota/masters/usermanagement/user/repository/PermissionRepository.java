package com.i4o.dms.kubota.masters.usermanagement.user.repository;

import com.i4o.dms.kubota.masters.usermanagement.user.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}

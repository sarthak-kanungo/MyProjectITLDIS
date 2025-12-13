package com.i4o.dms.kubota.masters.usermanagement.user.repository;

import com.i4o.dms.kubota.masters.usermanagement.user.domain.FunctionalityMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionalityRepository extends JpaRepository<FunctionalityMaster,Long> {
	
	
}

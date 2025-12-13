package com.i4o.dms.kubota.masters.kdm.csb.repository;

import com.i4o.dms.kubota.masters.kdm.csb.domain.Csb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsbRepository extends JpaRepository<Csb,Long> {
}

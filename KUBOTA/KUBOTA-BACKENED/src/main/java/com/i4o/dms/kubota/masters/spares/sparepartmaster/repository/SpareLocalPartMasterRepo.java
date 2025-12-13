package com.i4o.dms.kubota.masters.spares.sparepartmaster.repository;

import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SpareLocalPartMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpareLocalPartMasterRepo extends JpaRepository<SpareLocalPartMaster, Long> {

}

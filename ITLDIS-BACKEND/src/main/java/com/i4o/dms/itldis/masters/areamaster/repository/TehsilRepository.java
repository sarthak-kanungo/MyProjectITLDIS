package com.i4o.dms.itldis.masters.areamaster.repository;

import com.i4o.dms.itldis.masters.areamaster.model.State;
import com.i4o.dms.itldis.masters.areamaster.model.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TehsilRepository extends JpaRepository<Tehsil,Long> {
    Tehsil findByTehsil(String tehsil);
}

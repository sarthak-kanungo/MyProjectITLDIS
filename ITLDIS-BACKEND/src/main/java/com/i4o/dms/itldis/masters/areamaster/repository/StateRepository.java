package com.i4o.dms.itldis.masters.areamaster.repository;

import com.i4o.dms.itldis.masters.areamaster.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,Long> {
    State findByState(String state);
}

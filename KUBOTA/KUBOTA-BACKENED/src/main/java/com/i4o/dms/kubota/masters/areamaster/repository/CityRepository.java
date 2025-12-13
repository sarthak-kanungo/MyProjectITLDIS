package com.i4o.dms.kubota.masters.areamaster.repository;

import com.i4o.dms.kubota.masters.areamaster.model.City;
import com.i4o.dms.kubota.masters.areamaster.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
    City findByCity(String city);
}

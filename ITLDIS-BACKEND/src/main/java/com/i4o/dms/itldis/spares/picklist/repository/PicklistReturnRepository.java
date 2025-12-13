package com.i4o.dms.itldis.spares.picklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.picklist.domain.PickListReturn;

@Repository
public interface PicklistReturnRepository extends JpaRepository<PickListReturn, Long>{

}

package com.i4o.dms.itldis.salesandpresales.grn.repository;

import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineInventoryRepository extends JpaRepository<MachineInventory,Long> {


    //MachineInventory findByChassisNo(String chassisNo);

}

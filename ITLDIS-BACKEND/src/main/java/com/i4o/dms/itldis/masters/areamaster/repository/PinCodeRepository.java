package com.i4o.dms.itldis.masters.areamaster.repository;

import com.i4o.dms.itldis.masters.areamaster.model.PinCode;
import com.i4o.dms.itldis.masters.areamaster.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinCodeRepository extends JpaRepository<PinCode,Long> {
    PinCode findByPinCode(Integer pincode);
}

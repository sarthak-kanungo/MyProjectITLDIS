package com.i4o.dms.itldis.service.jobcard.repository;

import com.i4o.dms.itldis.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.itldis.service.jobcard.domain.OutsideJobCharge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutsideChargeRepo extends JpaRepository<OutsideJobCharge,Long> {
}

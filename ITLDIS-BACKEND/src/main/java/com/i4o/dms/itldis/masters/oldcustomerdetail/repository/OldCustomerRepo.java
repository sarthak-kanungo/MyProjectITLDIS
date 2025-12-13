package com.i4o.dms.itldis.masters.oldcustomerdetail.repository;

import com.i4o.dms.itldis.masters.oldcustomerdetail.domain.OldCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldCustomerRepo extends JpaRepository<OldCustomer,Long>
{

}

package com.i4o.dms.kubota.masters.oldcustomerdetail.repository;

import com.i4o.dms.kubota.masters.oldcustomerdetail.domain.OldCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldCustomerRepo extends JpaRepository<OldCustomer,Long>
{

}

package com.i4o.dms.kubota.masters.dealermaster.customermaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMasterRequestApproval;

public interface CustomerMasterChangeRequestApprovalRepo extends JpaRepository<CustomerMasterRequestApproval, Long>{

}

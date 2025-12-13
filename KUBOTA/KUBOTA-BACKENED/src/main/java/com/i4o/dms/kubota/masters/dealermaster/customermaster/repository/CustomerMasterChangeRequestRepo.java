package com.i4o.dms.kubota.masters.dealermaster.customermaster.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMasterRequest;

public interface CustomerMasterChangeRequestRepo extends JpaRepository<CustomerMasterRequest, Long> {

	CustomerMasterRequest findByCustomerId(Long customerId);
	
	@Query(value="{call SP_Customer_ChangeRequestApproval(:changeRequestId, :remarks, :approvalType, :usercode, :hoUserId)}", nativeQuery=true)
	String changeRequestApproval(@Param("changeRequestId") Long changeRequestId, @Param("remarks") String remarks, @Param("approvalType")String approvalType, 
			@Param("usercode")String usercode, @Param("hoUserId") Long hoUserId);
	
    @Query(value = "{call sp_customer_request_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getCustomerChangeRequestApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    /**
     * @author suraj.gaur
     */
    @Query(value = "select top(1) * from SA_CUST_REQ_HDR where customer_id=:customerId", nativeQuery = true)
    Optional<CustomerMasterRequest> getByCustomerId(Long customerId);
}

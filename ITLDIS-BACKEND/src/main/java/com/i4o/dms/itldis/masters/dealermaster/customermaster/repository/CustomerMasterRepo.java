package com.i4o.dms.itldis.masters.dealermaster.customermaster.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.dto.CustomerMasterSearchResponseDto;

public interface CustomerMasterRepo extends JpaRepository<CustomerMaster, Long> {


    CustomerMaster findByMobileNumber(@Param("mobile") String mobile);

    CustomerMaster findByCustomerCode(@Param("customerCode") String customerCode);
    
    @Query(value="select customer_code from sa_customer_hdr where id=:customerId", nativeQuery = true)
    String getCustomerCodeById(@Param("customerId") Long customerId);
    
    @Query(value="select id from sa_customer_hdr where customer_code=:customerCode", nativeQuery = true)
    Long getCustomerIdByCode(@Param("customerCode") String customerCode);
    
    @Query(value = "{call sp_searchCustomer(:customerCode, :mobileNo, :page, :size, :usercode)}", nativeQuery = true)
    List<CustomerMasterSearchResponseDto> getCustomerMasterSearch(@Param("customerCode") String customerCode, 
    		@Param("mobileNo") String mobileNo, 
    		@Param("page") Integer page, 
    		@Param("size") Integer size, 
    		@Param("usercode") String usercode);
    
    @Query(value = "{call sp_searchCustomer_approval(:page, :size, :usercode)}", nativeQuery = true)
    List<CustomerMasterSearchResponseDto> getCustomerMasterApprovalSearch(@Param("page") Integer page, 
    		@Param("size") Integer size, 
    		@Param("usercode") String usercode);
    
    @Query(value = "{call sp_searchByCustomerCodeOrMobileNo(:customerCode,:mobileNo)}", nativeQuery = true)
    List<CustomerMaster> sp_searchByCustomerCodeOrMobileNo(@Param("customerCode") String customerCode, @Param("mobileNo") String mobileNo);

    @Query(value = "{call sp_getByCustomerCode(:customerCode)}", nativeQuery = true)
    Optional<CustomerMaster> getByCustomerCode(@Param("customerCode") String customerCode);

    @Query(value = "{call sp_sales_master_getDetailsBychassisNo(:chassisNo)}", nativeQuery = true)
    Map<String, Object> getByDetailsByChassisNo(@Param("chassisNo") String chassisNo);

    @Query(value = "{call sp_customer_code_auto(:customerCode,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> findByCustomerCodeContaining(@Param("customerCode") String customerCode, @Param("userCode")String userCode);

    @Query(value = "{call sp_sales_master_chassisnoauto(:chassisNo)}", nativeQuery = true)
    List<Map<String, Object>> findByChassisNumberContaining(@Param("chassisNo") String chassisNo);

    @Query(value = "{call sp_Spare_getMobileNumber(:char,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> autocompleteMobileNumber(@Param("char") String customerName, @Param("userCode") String userCode);

    @Query(value = "{call sp_Cust_ValidateMobileNumber(:mobileNumber,:customerCode,:userCode)}", nativeQuery = true)
    String validateMobileNumber(@Param("mobileNumber") String mobileNumber,@Param("customerCode") String customerCode, @Param("userCode")String userCode);

    @Query(value = "{call sp_customer_master_get_customer_details(:customerCode)}", nativeQuery = true)
    Map<String, Object> getcustomerDetails(@Param("customerCode") String customerCode);


    @Query(value = "{call sp_customer_master_get_customer_details_for_payment_receipt(:customerCode)}", nativeQuery = true)
    Map<String, Object> getcustomerDetailsForPaymentReceipt(@Param("customerCode") String customerCode);

    @Query(value = "{call sp_customer_master_get_vehicle_details(:customerCode)}", nativeQuery = true)
    List<Map<String, Object>> getVehicleDetails(@Param("customerCode")String customerCode);
}


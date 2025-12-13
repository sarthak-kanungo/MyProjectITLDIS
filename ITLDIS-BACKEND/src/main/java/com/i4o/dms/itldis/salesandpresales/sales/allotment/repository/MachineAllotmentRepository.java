package com.i4o.dms.itldis.salesandpresales.sales.allotment.repository;

import com.i4o.dms.itldis.salesandpresales.sales.allotment.domain.MachineAllotment;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.domain.MachineAllotmentDetail;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.dto.AllotmentDto;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.dto.AllotmentSearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.dto.CustomerDetail;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.dto.MachineDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineAllotmentRepository extends JpaRepository<MachineAllotment, Long> {

    @Query(value = "{call sp_getAllotmentCode(:enquiryNumber)}", nativeQuery = true)
    Map<String, Object> getEnquiryInAllotment(String enquiryNumber);


    @Query(value = "{call sp_sales_allotment_get_machine_by_chassis_no(:chassisNo, :branchId)}", nativeQuery = true)
    Map<String, Object> getMachineByChassisNumber(@Param("chassisNo") String chassisNo, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_allotment_search_chassis_no(:chassisNo,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> searchMachineChassisNumber(@Param("chassisNo") String chassisNo, @Param("usercode")String usercode);

   /* @Query(value = "{call sp_sales_allotment_search_implements_chassis_no(:chassisNo,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchImplementsMachineChassisNumber(@Param("chassisNo") String chassisNo,
                                                                   @Param("dealerId") Long dealerId);*/

    @Query(value = "{call sp_search_by_allotment_number(:allotmentNumber,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> searchByAllotmentNumber(@Param("allotmentNumber") String allotmentNumber,
                                                      @Param("userCode") String userCode);

    AllotmentDto findByAllotmentNumber(String allotmentNumber);/*

    @Query(value = "{call sp_sales_allotment_auto_enquiry_number(:searchString,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchByEnquiryNumberMobileNameTehsil(@Param("searchString") String searchString,
                                                                    @Param("dealerId") Long dealerId);*/

    @Query(value = "{call sp_sales_allotment_get_customer_by_enquiry(:enquiryId,:branchId)}", nativeQuery = true)
    CustomerDetail getCustomerByEnquiry(@Param("enquiryId") Long enquiryId,@Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_allotment_get_machine_details_from_enquiry(:enquiryId,:branchId)}", nativeQuery = true)
    List<MachineDetails> getMachineDetailsFromEnquiry(@Param("enquiryId") Long enquiryId,@Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_allotment_search_machine_allotment(:allotmentNumber,:enquiryNumber,:fromDate,:toDate," +
            ":product,:series,:model,:subModel,:variant,:itemNo,:chassisNo,:engineNo,:dealerId,:dealerEmployeeId,:managementAccess,:hoUserId,:page,:size,:usercode,:includeInactive,:orgHierId)}",
            nativeQuery = true)
    List<AllotmentSearchResponseDto> searchMachineAllotment(@Param("allotmentNumber") String allotmentNumber,
                                                            @Param("enquiryNumber") String enquiryNumber,
                                                            @Param("fromDate") String fromDate, @Param("toDate") String toDate,
                                                            @Param("product") String product,
                                                            @Param("series") String series, @Param("model") String model,
                                                            @Param("subModel") String subModel, @Param("variant") String variant,
                                                            @Param("itemNo") String itemNo, @Param("chassisNo") String chassisNo,
                                                            @Param("engineNo") String engineNo,
                                                            @Param("dealerId") Long dealerId,@Param("dealerEmployeeId") Long dealerEmployeeId,
                                                            @Param("managementAccess") Boolean managementAccess,
                                                            @Param("hoUserId") Long hoUserId,
                                                            @Param("page") Integer page,
                                                            @Param("size") Integer size,
                                                            @Param("usercode") String usercode,
                                                            @Param("includeInactive") Character includeInactive,
                                                            @Param("orgHierId") Long orgHierId);

   /* @Query(value = "{call sp_sales_allotment_search_machine_allotment_count(:allotmentNumber,:enquiryNumber,:fromDate,:toDate," +
            ":product,:series,:model,:subModel,:variant,:itemNo,:chassisNo,:engineNo,:dealerId,:dealerEmployeeId)}", nativeQuery = true)
    Long searchMachineAllotmentCount(@Param("allotmentNumber") String allotmentNumber,
                                        @Param("enquiryNumber") String enquiryNumber,
                                        @Param("fromDate") String fromDate, @Param("toDate") String toDate,
                                        @Param("product") String product,
                                        @Param("series") String series, @Param("model") String model,
                                        @Param("subModel") String subModel, @Param("variant") String variant,
                                        @Param("itemNo") String itemNo, @Param("chassisNo") String chassisNo,
                                        @Param("engineNo") String engineNo,
                                        @Param("dealerId") Long dealerId, @Param("dealerEmployeeId") Long dealerEmployeeId);*/

/*    @Query(value = "{call sp_sales_allotment_search_allotted_enquiry_number(:enquiryNumber,:dealerEmployeeId)}",nativeQuery = true)
    List<Map<String,Object>> searchByEnquiryNumber(@Param("enquiryNumber") String enquiryNumber,@Param("dealerEmployeeId") Long dealerEmployeeId);
*/
    @Query(value = "{call sp_sales_allotment_get_allotment_by_id(:allotmentId)}",nativeQuery = true)
    CustomerDetail getAllotmentById(@Param("allotmentId") Long allotmentId);

    @Query(value = "{call sp_sales_allotment_get_allotment_details_by_id(:allotmentId)}",nativeQuery = true)
    List<MachineDetails> getAllotmentDetailsById(@Param("allotmentId") Long allotmentId);

    @Query(value = "{call sp_sales_allotment_search_engine_no(:engineNumber,:userId)}",nativeQuery = true)
    List<Map<String,Object>> searchByEngineNumber(@Param("engineNumber") String engineNumber,@Param("userId") Long userId);

    @Query(value = "{call sp_sales_allotment_enquiry_info_by_mobile_number(:mobileNumber)}",nativeQuery = true)
    Map<String,Object> getCustomerByMobileNumber(@Param("mobileNumber") String mobileNumber);


    /*@Query(value = "{call sp_sales_allotment_auto_complete_item_no(:itemNo,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> autoCompleteItemNo(@Param("itemNo") String itemNo, @Param("dealerId")Long dealerId);*/


    @Query(value = "{call sp_sales_allotment_auto_complete_chassis_no(:machineMasterId,:chassisNo,:branchId)}",nativeQuery = true)
    List<Map<String,Object>> autoCompleteChassisNo(@Param("machineMasterId") Long machineMasterId,
                                                   @Param("chassisNo")String chassisNo,
                                                   @Param("branchId")Long branchId);

    MachineAllotment findByEnquiryId(@Param("id") Long id);
}




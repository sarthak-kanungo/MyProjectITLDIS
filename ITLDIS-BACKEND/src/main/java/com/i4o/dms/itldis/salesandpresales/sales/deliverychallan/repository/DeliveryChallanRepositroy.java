package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository;

import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeliveryChallanRepositroy extends JpaRepository<DeliveryChallan, Long> {

	@Query(value = "{call sp_sales_dc_validate_data(:dcType,:docId)}", nativeQuery = true)
	String validateDataBeforeSave(@Param("dcType")String dcType, @Param("docId")Long docId);
	
    @Query(value = "{call sp_sales_dc_getDcType_dropDown()}", nativeQuery = true)
    List<Map<String, Object>> getDcTypeList();

    @Query(value = "{call sp_sales_dc_getDcCancellationType_dropDown()}", nativeQuery = true)
    List<Map<String, Object>> getDcCancellationTypeList();

    @Query(value = "{call sp_sales_dc_getDcCancellationReason_List()}", nativeQuery = true)
    List<Map<String, Object>> getDcCancellationReasonList();

//    @Query(value = "{call sp_sales_dc_getDcCancellationReason_other_List()}", nativeQuery = true)
//    List<Map<String, Object>> getDcCancellationOtherReasonList();
    
    @Query(value = "{call sp_getReason()}", nativeQuery = true)
    List<Map<String, Object>> getDcCancellationOtherReasonList();

    @Query(value = "{call sp_sales_dc_getEnquiryDetails_By_enquiryNo(:enquiryNumber,:branchId)}", nativeQuery = true)
    EnquiryProspectDetailsResponse getEnquiryProspectDetails(@Param("enquiryNumber") String enquiryNumber, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_getAllotmentNumber_date_By_dcNumber(:dcId)}", nativeQuery = true)
    Map<String, Object> getAllotmentDetailsByDcId(@Param("dcId") Long dcId);

    @Query(value = "{call sp_sales_dc_get_machine_details(:enquiryNumber,:branchId)}", nativeQuery = true)
    List<MachineDetailsResponse> machineDetails(@Param("enquiryNumber") String enquiryNumber,@Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_get_deliverableChecklist(:enquiryNumber,:branchId)}", nativeQuery = true)
    List<DeliverableChecklistResponse> getDeliverableChecklist(@Param("enquiryNumber") String enquiryNumber,@Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_itemNo_autoComplete(:itemNumber)}", nativeQuery = true)
    List<Map<String, Object>> getItemNumberAutoComplete(@Param("itemNumber") String itemNumber);

    @Query(value = "{call sp_sales_dc_getitem_details_by_item_number(:itemNumber,:branchId)}", nativeQuery = true)
    Map<String, Object> getItemDetailsByItemNumber(@Param("itemNumber") String itemNumber,
                                                   @Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_dc_enquiryNumber_by_notDcCreated_autocomplete(:enquiryNumber,:dcType,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryNumberAutoComplete(@Param("enquiryNumber") String enquiryNumber,
                                                           @Param("dcType") String dcType,
                                                           @Param("branchId") Long branchId);

    //need to delete
    @Query(value = "{call sp_sales_dc_edit_response_details(:dcId)}", nativeQuery = true)
    Map<String, Object> editResponseDetails(@Param("dcId") String dcId);

    DeliveryChallanEditResponse findByDeliveryChallanId(@Param("dcId") Long dcId);

    /********************************************************************************************
     Search Form Methods
     ** ******************************************************************************************/

    @Query(value = "{call sp_sales_dc_deleiveryChallanNumber_autocomplete(:dcNumber,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getDeleiveryChallanNumberAutocomplete(@Param("dcNumber") String dcNumber, @Param("usercode") String usercode);

    @Query(value = "{call sp_sales_dc_chassisno_autocomplete(:chassisNo,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getDcChassiNumberAutocomplete(@Param("chassisNo") String chassisNo, @Param("usercode") String  usercode);

    @Query(value = "{call sp_sales_dc_customer_name_autocomplete(:customerName,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getDcCustomerNameAutoComplete(@Param("customerName") String customerName, @Param("usercode") String usercode);

    @Query(value = "{call sp_sales_dc_customer_mobileNumber_autocomplete(:customerMobileNumber,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getDcCustomerMobileNumberAutoComplete(@Param("customerMobileNumber") String customerMobileNumber, @Param("usercode") String usercode);

/*    @Query(value = "{call sp_sales_dc_enquiry_number_autocomplete(:enquiryNumber,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getDcEnquiryNumberAutoComplete(@Param("enquiryNumber") String enquiryNumber, @Param("branchId") Long branchId);
*/
    @Query(value = "{call sp_sales_dc_engine_number_autocomplete(:engineNumber,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getDcEngineNumberAutoComplete(@Param("engineNumber") String engineNumber, @Param("usercode") String usercode);

    @Query(value = "{call sp_sales_dc_getDcStatus_list()}", nativeQuery = true)
    List<Map<String, Object>> getDcStatus();

    @Query(value = "{call sp_sales_dc_search_delivery_challan(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:deliveryChallanNumber,:chassisNumber,:customerName,:customerMobileNumber," +
            ":dcFromDate,:dcToDate,:enquiryNumber,:enquiryType,:dcStatus,:product,:series,:model,:subModel,:varient,:itemNumber,:engineNumber,:page,:size,:usercode,:includeInactive,:orgHierId)}", nativeQuery = true)
    List<SearchDeliveryChallanResponse> searchDc(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("deliveryChallanNumber") String deliveryChallanNumber,
            @Param("chassisNumber") String chassisNumber,
            @Param("customerName") String customerName,
            @Param("customerMobileNumber") String customerMobileNumber,
            @Param("dcFromDate") String dcFromDate,
            @Param("dcToDate") String dcToDate,
            @Param("enquiryNumber") String enquiryNumber,
            @Param("enquiryType") String enquiryType,
            @Param("dcStatus") String dcStatus,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("varient") String varient,
            @Param("itemNumber") String itemNumber,
            @Param("engineNumber") String engineNumber,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("usercode") String usercode,
            @Param("includeInactive") Character includeInactive,
            @Param("orgHierId") Long orgHierId
    );

    /*@Query(value = "{call sp_sales_dc_search_delivery_challan_count(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:deliveryChallanNumber,:chassisNumber,:customerName,:customerMobileNumber," +
            ":dcFromDate,:dcToDate,:enquiryNumber,:enquiryType,:dcStatus,:product,:series,:model,:subModel,:varient,:itemNumber,:engineNumber,:page,:size)}", nativeQuery = true)
    Long searchDcCount(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("deliveryChallanNumber") String deliveryChallanNumber,
            @Param("chassisNumber") String chassisNumber,
            @Param("customerName") String customerName,
            @Param("customerMobileNumber") String customerMobileNumber,
            @Param("dcFromDate") String dcFromDate,
            @Param("dcToDate") String dcToDate,
            @Param("enquiryNumber") String enquiryNumber,
            @Param("enquiryType") String enquiryType,
            @Param("dcStatus") String dcStatus,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("varient") String varient,
            @Param("itemNumber") String itemNumber,
            @Param("engineNumber") String engineNumber,
            @Param("page") Integer page,
            @Param("size") Integer size
    );*/

    /**************************************************************************************
     * Dc for Dealer Transfer
     * ************************************************************************************
     */

    @Query(value = "{call sp_sales_dc_request_number_autocomplete(:requestNumber, :branchId)}", nativeQuery = true)
    List<Map<String, Object>> getDcRequestNumberAutoComplete(@Param("requestNumber") String requestNumber, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_dealer_details_by_request_number(:requestNumber)}", nativeQuery = true)
    Map<String, Object> getDealerDetailsByRequestNumber(@Param("requestNumber") String requestNumber);

    @Query(value = "{call sp_sales_dc_get_allotment_details_by_request_number(:requestNumber, :branchId)}", nativeQuery = true)
    Map<String, Object> getAllotmentDetailsByRequestNumber(@Param("requestNumber") String requestNumber, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_machine_details_by_request_number(:requestNumber, :branchId)}", nativeQuery = true)
    List<MachineDetailsResponse> getMachineDetailsByRequestNumber(@Param("requestNumber") String requestNumber, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_dc_allotment_no_autocomplete(:allotmentNumber,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> allotmentAutocomplete(@Param("allotmentNumber") String allotmentNumber,
                                                    @Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_dc_get_customer_details_by_allotment_Id(:allotmentId,:branchId)}", nativeQuery = true)
    Map<String, Object> allotmentCustomerDetails(@Param("allotmentId") Long allotmentId,
                                                 @Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_dc_machine_details_by_allotment_Id(:allotmentId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> allotmentMachineDetails(@Param("allotmentId") Long allotmentId,
                                                      @Param("branchId") Long branchId);
}

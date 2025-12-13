package com.i4o.dms.kubota.salesandpresales.enquiry.repository;

import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long>,ConnectionConfiguration {

    @Query(value = "{call sp_getPurposeOfPurchase()}", nativeQuery = true)
    List<Map<String, Object>> getPurposeOfPurchase();

    @Query(value = "{call sp_checkEnquiryForSubsidyPayment(:enquiryNo)}", nativeQuery = true)
    Integer getSubsidyCount(@Param("enquiryNo")String enquiryNo);
    
    @Query(value = "{call sp_sales_and_presales_getEnquiryType()}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryType();

    @Query(value = "{call sp_getFollowUpType()}", nativeQuery = true)
    List<Map<String, Object>> getFollowUpType();

    @Query(value = "{call sp_getProspectType()}", nativeQuery = true)
    List<Map<String, Object>> getProspectType();

    @Query(value = "{call sp_getSoilType()}", nativeQuery = true)
    List<Map<String, Object>> getSoilType();

    @Query(value = "{call sp_getMajorCropGrown()}", nativeQuery = true)
    List<Map<String, Object>> getMajorCropGrown();

    @Query(value = "{call sp_getCashLoan()}", nativeQuery = true)
    List<Map<String, Object>> getCashLoan();

    @Query(value = "{call sp_getFinanceStatus()}", nativeQuery = true)
    List<Map<String, Object>> getFinanceStatus();

    @Query(value = "{call sp_getOccupation()}", nativeQuery = true)
    List<Map<String, Object>> getOccupation();

    @Query(value = "{call sp_getGenerationActivityType()}", nativeQuery = true)
    List<Map<String, Object>> getGenerationActivityType();

    @Query(value = "{call sp_getConversionActivityType()}", nativeQuery = true)
    List<Map<String, Object>> getConversionActivityType();

    @Query(value = "{call sp_getBrands()}", nativeQuery = true)
    List<Map<String, Object>> getBrand();

    @Query(value = "{call sp_getRetailConversionActivity()}", nativeQuery = true)
    List<Map<String, Object>> getRetailConversionActivity();

    @Query(value = "{call sp_reopenEnquiry(:enquiryNo, :dealerId, :usercode)}", nativeQuery = true)
    String reopenEnquiry(@Param("enquiryNo") String enquiryNo,
            @Param("dealerId") Long dealerId,
            @Param("usercode") String usercode);
    
    @Query(value = "{call SP_getFinancier_list(:branchId, :dealerId, :searchValue, :usercode)}", nativeQuery = true)
    List<Map<String, Object>> getFinancier(@Param("branchId") Long branchId,
            @Param("dealerId") Long dealerId,
            @Param("searchValue") String searchValue,
            @Param("usercode") String usercode);
    
    @Query(value = "{call sp_getSearchEnquiry(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:enquiryNumber,:enquiryType,:model,:salesPerson,:enquiryDate,:enquiryFrom,:source,:enquiryStatus,:retailConversionActivity,:product,:series,:subModel,:variant,:itemNo,:finance,:autoClose,:subSidy,:exchange,:nextFollowUpFromDate,:nextFollowUpToDate,:tentativePurchaseFromDate,:tentativePurchaseToDate,:userCode,:stateName,:districtTehsilCityName,:includeInactive,:orgHierarchyId,:page,:size)}", nativeQuery = true)
    List<EnquirySearchResponseDto> getEnquirySearch(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long userId,
            @Param("enquiryNumber") String enquiryNumber,
            @Param("enquiryType") String enquiryType,
            @Param("model") String model,
            @Param("salesPerson") String salesPerson,
            @Param("enquiryDate") String enquiryDate,
            @Param("enquiryFrom") String enquiryFrom,
            @Param("source") String source,
            @Param("enquiryStatus") String enquiryStatus,
            @Param("retailConversionActivity") String retainConversionActivity,
            @Param("product") String product,
            @Param("series") String series,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("finance") String finance,
            @Param("autoClose") String autoClose,
            @Param("subSidy") String subSidy,
            @Param("exchange") String exchange,
            @Param("nextFollowUpFromDate") String nextFollowUpFromDate,
            @Param("nextFollowUpToDate") String nextFollowUpToDate,
            @Param("tentativePurchaseFromDate") String tentativePurchaseFromDate,
            @Param("tentativePurchaseToDate") String tentativePurchaseToDate,
            @Param("userCode") String userCode,
        	@Param("stateName") String stateName,
        	@Param("districtTehsilCityName") String districtTehsilCityName,
        	@Param("includeInactive") Character includeInactive,
        	@Param("orgHierarchyId") Long orgHierarchyId,
            @Param("page") Integer page,
            @Param("size") Integer size
    );
    @Query(value = "{call sp_getSearchEnquiry(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:enquiryNumber,:enquiryType,:model,:salesPerson,:enquiryDate,:enquiryFrom,:source,:enquiryStatus,:retailConversionActivity,:product,:series,:subModel,:variant,:itemNo,:finance,:autoClose,:subSidy,:exchange,:nextFollowUpFromDate,:nextFollowUpToDate,:tentativePurchaseFromDate,:tentativePurchaseToDate,:userCode,:stateName,:districtTehsilCityName,:includeInactive,:orgHierarchyId,:page,:size)}", nativeQuery = true)
    List<EnquiryPartialSearchResponseDto> getEnquiryPartialSearch(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long userId,
            @Param("enquiryNumber") String enquiryNumber,
            @Param("enquiryType") String enquiryType,
            @Param("model") String model,
            @Param("salesPerson") String salesPerson,
            @Param("enquiryDate") String enquiryDate,
            @Param("enquiryFrom") String enquiryFrom,
            @Param("source") String source,
            @Param("enquiryStatus") String enquiryStatus,
            @Param("retailConversionActivity") String retainConversionActivity,
            @Param("product") String product,
            @Param("series") String series,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("finance") String finance,
            @Param("autoClose") String autoClose,
            @Param("subSidy") String subSidy,
            @Param("exchange") String exchange,
            @Param("nextFollowUpFromDate") String nextFollowUpFromDate,
            @Param("nextFollowUpToDate") String nextFollowUpToDate,
            @Param("tentativePurchaseFromDate") String tentativePurchaseFromDate,
            @Param("tentativePurchaseToDate") String tentativePurchaseToDate,
            @Param("userCode") String userCode,
        	@Param("stateName") String stateName,
        	@Param("districtTehsilCityName") String districtTehsilCityName,
        	@Param("includeInactive") Character includeInactive,
        	@Param("orgHierarchyId") Long orgHierarchyId,
            @Param("page") Integer page,
            @Param("size") Integer size
    );

    @Query(value = "{call sp_getSearchEnquiryCount(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:enquiryNumber,:enquiryType,:model,:salesPerson,:enquiryDate,:enquiryFrom,:source,:enquiryStatus,:retailConversionActivity,:product,:series,:subModel,:variant,:itemNo,:finance,:autoClose,:subSidy,:exchange,:nextFollowUpFromDate,:nextFollowUpToDate,:tentativePurchaseFromDate,:tentativePurchaseToDate,:page,:size)}", nativeQuery = true)
    Long getEnquirySearchCount(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long userId,
            @Param("enquiryNumber") String enquiryNumber,
            @Param("enquiryType") String enquiryType,
            @Param("model") String model,
            @Param("salesPerson") String salesPerson,
            @Param("enquiryDate") String enquiryDate,
            @Param("enquiryFrom") String enquiryFrom,
            @Param("source") String source,
            @Param("enquiryStatus") String enquiryStatus,
            @Param("retailConversionActivity") String retainConversionActivity,
            @Param("product") String product,
            @Param("series") String series,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("finance") String finance,
            @Param("autoClose") String autoClose,
            @Param("subSidy") String subSidy,
            @Param("exchange") String exchange,
            @Param("nextFollowUpFromDate") String nextFollowUpFromDate,
            @Param("nextFollowUpToDate") String nextFollowUpToDate,
            @Param("tentativePurchaseFromDate") String tentativePurchaseFromDate,
            @Param("tentativePurchaseToDate") String tentativePurchaseToDate,
            @Param("page") Integer page,
            @Param("size") Integer size);


    @Query(value = "{call sp_getMobileNumber(:mobileNo,:dealerId,:userCode,:requestFrom)}", nativeQuery = true)
    List<Map<String, Object>> getMobileNumber(@Param("mobileNo") String mobileNo,
    		@Param("requestFrom")String requestFrom,
    		@Param("dealerId")Long dealerId, @Param("userCode") String userCode);

/*    @Query(value = "{call sp_getIdByProspectMobileNo(:mobileNumber)}", nativeQuery = true)
    Long getIdByProspectMobileNo(@Param("mobileNumber") String mobileNumber);
*/
    @Query(value = "{call sp_getIdByCustomerMobileNo(:mobileNumber)}", nativeQuery = true)
    Long getIdByCustomerMobileNo(@Param("mobileNumber") String mobileNumber);

/*    @Query(value = "{call sp_getIdByOldCustomerMobileNo(:mobileNumber)}", nativeQuery = true)
    Long getIdByOldCustomerMobileNo(@Param("mobileNumber") String mobileNumber);
*/
//    @Query(value = "{call sp_getIdByEnquiryNo(:enquiryNo)}", nativeQuery = true)
//    Long getIdByEnquiryNo(String enquiryNo);
    @Query(value = "{call sp_villageTehsilDistrictSearch(:searchValue,:userCode,:dealerId,:stateId)}", nativeQuery = true)
    List<Map<String,Object>> villageTehsilDistrictSearch(@Param("searchValue") String searchValue, @Param("userCode") String userCode, @Param("dealerId")Long dealerId, @Param("stateId")Long stateId);
    
    @Query(value = "{call sp_getStatesEnuiry(:cityId,:userCode,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getStatesEnuiry(@Param("cityId") Long cityId, @Param("userCode") String userCode, @Param("dealerId")Long dealerId);
    
    @Query(value = "{call sp_getEnquiryNo(:enquiryNo,:userId,:status)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryNo(@Param("enquiryNo") String enquiryNo, @Param("userId") Long userId, @Param("status")String status);

    @Query(value = "{call sp_getOldCustomer(:mobileNo)}", nativeQuery = true)
    EnquiryProspectDto getOldCustomer(String mobileNo);

    @Query(value = "{call sp_getCustomer(:mobileNo)}", nativeQuery = true)
    EnquiryProspectDto getCustomer(String mobileNo);

    @Query(value = "{call sp_getCurrentFollowUpDate(:enquiryNumber)}", nativeQuery = true)
    Date getCurrentFollowUpDate(@Param("enquiryNumber")String enquiryNumber);
    
    @Query(value = "{call sp_getEnquiryList(:fromDate,:toDate,:model,:userId)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryList(String fromDate, String toDate, String model, Long userId);

    @Query(value = "{call sp_getEnquiryListByCustomerNameAndMobileNo(:customerNameMobileNo,:userId)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryListByCustomerNameAndMobileNo(@Param("customerNameMobileNo") String customerNameMobileNo, @Param("userId") Long userId);

    @Query(value = "{call sp_getEnquiryByActivityNumber(:activityNumber)}", nativeQuery = true)
    List<EnquiryByActivityDto> getEnquiryByActivityNumber(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_isValidate(:enquiryNumber)}", nativeQuery = true)
    Map<String, Object> isValidate(String enquiryNumber);

    @Query(value = "{call sp_getEnquiryInAppByEnquiryNo(:enquiryNumber)}", nativeQuery = true)
    Map<String, Object> getEnquiryInAppByEnquiryNo(@Param("enquiryNumber") String enquiryNumber);

    EnquiryDto findByEnquiryNumber(String enquiryNumber);


    @Query(value = "{call sp_getEnquiryByNumber(:enquiryNumber)}", nativeQuery = true)
    Map<String, Object> getEnquiryByNumber(@Param("enquiryNumber") String enquiryNumber);

    @Query(value = "{call sp_setEnquiryType(:enquiryDate,:tentativePurchaseDate)}", nativeQuery = true)
    Map<String, Object> setEnquiryType(@Param("enquiryDate") String enquiryDate, @Param("tentativePurchaseDate") String tentativePurchaseDate);

    @Query(value = "{call sp_searchMarketingActivityNumber(:activityNumber,:activityPurpose,:source,:dealerId,:activityType,:enqnumber)}", nativeQuery = true)
    List<Map<String, Object>> searchMarketingActivityNumber(@Param("activityNumber") String activityNumber,
                                                            @Param("activityPurpose") String activityPurpose,
                                                            @Param("source") String source, 
                                                            @Param("dealerId") Long dealerId, 
                                                            @Param("activityType") String activityType,@Param("enqnumber") String enqnumber);


    @Query(value = "{call sp_salesandPreSales_enquiry_is_mobileNumberExist(:mobileNumber)}", nativeQuery = true)
    Map<String, Object> isMobileNumberExist(@Param("mobileNumber") String mobileNumber);

    @Query(value = "{call sp_salesAndPreSale_enquiry_getEnquiryNumberAfterEnquiryCreation(:branchId)}", nativeQuery = true)
    Map<String, Object> getEnquiryNumberAfterEnquiryCreation(Long branchId);

    Enquiry findByMobileNumberAndItemNoAndEnquiryStatus(@Param("mobileNumber") String mobileNumber, @Param("itemNo") String itemNo, @Param("enquiryStatus") String enquiryStatus);

    @Query(value = "{call sp_SA_getEnquiryForLOV(:search,:managementAccess,:dealerId,:branchId,:kaiEmployeeId,:dealerEmployeeId,:usercode,:tranName)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryNumberName(@Param("search") String search,
                                                   @Param("managementAccess") Boolean managementAccess,
                                                   @Param("dealerId") Long dealerId,
                                                   @Param("branchId") Long branchId,
                                                   @Param("kaiEmployeeId") Long kaiEmployeeId,
                                                   @Param("dealerEmployeeId") Long userId,
                                                   @Param("usercode") String usercode,
                                                   @Param("tranName") String tranName);

    @Query(value = "{call sp_getEnquiryStatus()}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryStatus();

    @Query(value = "{call sp_salesAndPreSales_getEnquiryNoByDealerEmployeeMasterId(:id)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryNoByDealerEmployeeMasterId(@Param("id") Long id);

    @Query(value = "{call sp_sales_and_presales_getItemNumberInEnquiry(:itemNo,:model)}", nativeQuery = true)
    List<Map<String,Object>> getItemNumberInEnquiry(@Param("itemNo") String itemNo, @Param("model") String model);

    @Query(value = "{call sp_sales_and_presales_getCustomerNameMobileModelCity(:search,:userId)}", nativeQuery = true)
    List<Object> getCustomerNameMobileModelCity(@Param("search") String search, @Param("userId") Long userId);

    @Query(value = "{call sp_sales_and_presales_update_prospect_code_in_enquiry(:id)}", nativeQuery = true)
    String  updateProspectCodeInEnquiry(@Param("id")Long id);

    @Query(value = "{call sp_sales_and_presales_check_item_model_in_enquiry(:model,:mobileNumber)}", nativeQuery = true)
    Integer  checkItemNumberModelInEnquiry(@Param("model")String model,@Param("mobileNumber") String mobileNumber);

    @Query(value = "{call sp_update_enquiry(:enquiryId,:prospectId)}", nativeQuery = true)
    String   updateEnquiry(@Param("enquiryId")Long enquiryId,@Param("prospectId") Long prospectId);

    @Query(value = "{call sp_sales_and_presales_drop_down_enquiry_status()}", nativeQuery = true)
    List<Map<String,Object>>   dropDownEnquiryStatus();

    @Query(value = "{call sp_sales_and_presales_drop_down_title()}", nativeQuery = true)
    List<Map<String,Object>>  dropDownTitle();
    
    @Query(value="select top 1 app_enquiry_flag from SA_ENQ_HDR where enquiry_number=:enqNo", nativeQuery=true)
    boolean getAppEnquiryFlag(@Param("enqNo") String enqNo);
    
    @Query(value = "sp_get_Preff_Lang",nativeQuery = true)
    List<Map<String,Object>> getLanguages();
    
    @Query(value="{call SP_Check_Function_Permission (:userid, :functionality)}", nativeQuery = true)
    Boolean getFunctionPermision(@Param("userid")Long userid, @Param("functionality")String functionality);
}

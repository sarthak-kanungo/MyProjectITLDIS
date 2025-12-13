package com.i4o.dms.kubota.salesandpresales.enquiry.repository;

import com.i4o.dms.kubota.salesandpresales.enquiry.domain.EnquiryFollowUp;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.PendingValidationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface EnquiryFollowUpRepo extends JpaRepository<EnquiryFollowUp, String> {
    @Query(value = "{call sp_getEnquiryFollowUp(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getEnquiryFollowUp(String enquiryNo);

    @Query(value = "{call sp_getResult()}", nativeQuery = true)
    List<Map<String, Object>> getResult();

    @Query(value = "{call sp_getLostDropReason(:result)}", nativeQuery = true)
    List<Map<String, Object>> getLostDropReason(String result);

    @Query(value = "{call sp_getReason()}", nativeQuery = true)
    List<Map<String, Object>> getReason();

    @Query(value = "{call sp_getEnquiryNo(:enquiryNo)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryNo(String enquiryNo);

    @Query(value = "{call sp_getIdEnquiryCode(:enquiryNo)}", nativeQuery = true)
    List<Map<String, Object>> getEnquiryCode(String enquiryNo);

    @Query(value = "{call sp_getTodayFollowUp(:dealerEmployeeId)}", nativeQuery = true)
    List<Map<String, Object>> getTodayFollowUp(@Param("dealerEmployeeId") Long dealerEmployeeId);

    @Query(value = "{call sp_getPendingFollowUp(:dealerEmployeeId)}", nativeQuery = true)
    List<Map<String, Object>> getPendingFollowUp(@Param("dealerEmployeeId") Long dealerEmployeeId);

    @Query(value = "{call sp_getPendingValidation(:dealerEmployeeId)}", nativeQuery = true)
    List<Map<String,Object>> getPendingValidation(@Param("dealerEmployeeId") Long dealerEmployeeId);

    @Query(value = "{call sp_getFollowUpHistory(:enquiryNumber)}", nativeQuery = true)
    List<Map<String, Object>> getFollowUpHistory( String enquiryNumber);

    @Query(value = "{call sp_salesAndPreSales_enquiryFollowUp_getFollowUpTimes(:enquiryNumber)}",nativeQuery = true)
    Map<String ,Object>  salesAndPreSales_enquiryFollowUp_getFollowUpTimes(String enquiryNumber);


}


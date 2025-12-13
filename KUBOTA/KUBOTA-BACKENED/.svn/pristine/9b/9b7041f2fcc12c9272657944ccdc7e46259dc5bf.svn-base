package com.i4o.dms.kubota.warranty.logsheet.repository;

import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheet;
import com.i4o.dms.kubota.warranty.logsheet.dto.LogsheetResponseDto;
import com.i4o.dms.kubota.warranty.logsheet.dto.WarrantyLogsheetViewDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
public interface WarrantyLogsheetRepo extends JpaRepository<WarrantyLogsheet,Long> {

    @Query(value = "{call sp_warranty_logsheet_drop_down_logsheet_type()}", nativeQuery = true)
    List<Map<String,Object>> dropDownLogsheetType();

    WarrantyLogsheetViewDto findByLogsheetNo(String logsheetNo);

    @Query(value = "{call sp_warranty_logsheet_search_logsheet_no(:logsheetNo)}", nativeQuery = true)
    List<Map<String,Object>> searchLoghseetNo(@RequestParam("loghseetNo")String logsheetNo);

    @Query(value = "{call sp_warranty_logsheet_search_customer_mobile_no(:mobileNo)}", nativeQuery = true)
    List<Map<String,Object>> searchCustomerMobileNo(@RequestParam("mobileNo")String mobileNo);

    @Modifying
    @Query(value = "update wa_logsheet set status='Closed', last_modified_by=:loginid, last_modified_date=getdate() where id=:id", nativeQuery = true)
    void closeLoghseet(@Param("loginid")Long loginid, @Param("id")Integer id);


    @Query(value = "{call sp_warranty_logsheet_search(:logNo,:logsheetType,:status,:jobCardNo,:chassisNo,:logsheetFromDate,:logsheetToDate,:model,:failureType,:mobileNo,:registrationNo,:jobCardFromDate,:JobCardToDate,:page,:size,:usercode)}", nativeQuery = true)
    List<LogsheetResponseDto> logsheetSearch(@RequestParam("logNo")String logNo,
                                       @RequestParam("logsheetType")String logsheetType,
                                       @RequestParam("status")String status,
                                       @RequestParam("jobCardNo")String jobCardNo,
                                       @RequestParam("chassisNo")String chassisNo,
                                       @RequestParam("logsheetFromDate")String logsheetFromDate,
                                       @RequestParam("logsheetToDate")String logsheetToDate,
                                       @RequestParam("model")String model,
                                       @RequestParam("failureType")String failureType,
                                       @RequestParam("mobileNo")String mobileNo,
                                       @RequestParam("registrationNo")String registrationNo,
                                       @RequestParam("jobCardFromDate")String jobCardFromDate,
                                       @RequestParam("JobCardToDate")String JobCardToDate,
                                       @RequestParam("page")Long page,
                                       @RequestParam("size")Long size,
                                       @RequestParam("usercode")String usercode);
    
    
    
//    @Query(value = "{call sp_warranty_Logsheet_Excel_Report(:logsheetNo,:logsheetType,:status,:jobCardNo,:chassisNo,:logsheetFromDate,:logsheetToDate,"
//            + ":model,:failureType,:mobileNo,:registrationNo,:jobCardFromDate,:jobCardToDate,:page,:size)}", nativeQuery = true)
//    List<LogsheetResponseDto> searchLogsheet(@Param("logsheetNo") String logsheetNo,
//                                             @Param("logsheetType") String logsheetType,
//                                             @Param("status") String status,
//                                             @Param("jobCardNo") String jobCardNo,
//                                             @Param("chassisNo") String chassisNo,
//                                             @Param("logsheetFromDate") String logsheetFromDate,
//                                             @Param("logsheetToDate") String logsheetToDate,
//                                             @Param("model") String model,
//                                             @Param("failureType") String failureType,
//                                             @Param("mobileNo") String mobileNo,
//                                             @Param("registrationNo") String registrationNo,
//                                             @Param("jobCardFromDate") String jobCardFromDate,
//                                             @Param("jobCardToDate") String jobCardToDate,
//                                             @Param("page") Long page,
//                                             @Param("size") Long size);


   


}

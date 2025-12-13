package com.i4o.dms.itldis.masters.salesandpresales.enquirysource.repository;

import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.dto.EnquirySourceMasterSearchResponse;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.dto.ActivityBudgetMasterSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EnquirySourceMasterRepo extends JpaRepository<EnquirySourceMaster, Long> {
    @Query(value = "{call sp_mt_sales_get_all_enquiry_source()}", nativeQuery = true)
    List<EnquirySourceMaster> getAllEnquirySource();

    //Stored Procedure Not Found
    @Query(value = "{call sp_searchByEnquirySource(:enquirySource)}", nativeQuery = true)
    List<EnquirySourceMaster> findByEnquirySourceContaining(@Param("enquirySource") String enquirySource);


    @Query(value = "{call sp_mt_sales_search_by_enquiry_source_and_active_status(:enquirySource,:activeStatus)}", nativeQuery = true)
    List<EnquirySourceMaster> findByEnquirySourceAndActiveStatus(String enquirySource, String activeStatus);

    @Query(value = "{call sp_mt_sales_get_source(:sourceName)}", nativeQuery = true)
    List<Map<String, Object>> getSourceName(@Param("sourceName") String sourceName );

    @Query(value = "{call sp_mt_sales_get_source_code_autocomplete(:sourceCode)}", nativeQuery = true)
    List<Map<String, Object>> getSourceCode(@Param("sourceCode") String sourceCode );

    @Query(value = "{call sp_getPurpose()}", nativeQuery = true)
    List<Map<String, Object>> getPurpose();

    @Query(value = "{call sp_getActivityCategory()}", nativeQuery = true)
    List<Map<String, Object>> getActivityCategory();
    
    @Query(value = "{call sp_getSource()}", nativeQuery = true)
    List<Map<String, Object>> getSourceDropDown();


    @Query(value = "{call sp_get_search_enquiry_source_master(:sourceCode,:sourceName,:purpose,:page,:size)}",nativeQuery = true)
    List<EnquirySourceMasterSearchResponse> searchEnquirySourceMaster(@Param("sourceCode") String sourceCode,
                                                                      @Param("sourceName") String sourceName,
                                                                      @Param("purpose")String purpose,
                                                                      @Param("page")Integer page,
                                                                      @Param("size")Integer size);

    @Query(value = "{call sp_get_search_enquiry_source_master_count(:sourceCode,:sourceName,:purpose,:page,:size)}",nativeQuery = true)
    Long searchEnquirySourceMasterCount(@Param("sourceCode") String sourceCode,
                                        @Param("sourceName") String sourceName,
                                        @Param("purpose")String purpose,
                                        @Param("page")Integer page,
                                        @Param("size")Integer size);

	EnquirySourceMaster findBySourceCode(String sourceCode);

}




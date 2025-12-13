package com.i4o.dms.itldis.salesandpresales.enquiry.repository;

import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryTransferHistory;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquiryToTransferDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.SearchEnquiryToTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EnquiryTransferHistoryRepo extends JpaRepository<EnquiryTransferHistory, Long> {
    @Query(value = "{call sp_getEnquiryToTransfer(:userId,:salesPerson,:enquiryNumber,:tehsil,:enquiryType,:autoClose)}", nativeQuery = true)
    List<SearchEnquiryToTransfer> getEnquiryToTransfer(Long userId,String salesPerson, String enquiryNumber, String tehsil, String enquiryType, String autoClose );

    @Query(value = "{call sp_getSalesPersonName(:dealerEmployeeId,:ids)}", nativeQuery = true)
    List<Map<String, Object>> getSalesPersonName(@Param("dealerEmployeeId") Long dealerEmployeeId,@Param("ids") String ids);
}

package com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.dto.TollFreeCallSearchResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.complaintResolution.dto.ComplaintResolutionResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.domain.TollFreeHdr;

@Transactional
public interface TollFreeRepo extends JpaRepository<TollFreeHdr, Long>{
	
	@Query(nativeQuery=true, value="{call sp_GetTollFreeCallHistory (:customerId, :vinId)}")
	List<Map<String, Object>> getCallHistory(@Param("customerId")Long customerId, @Param("vinId")Long vinId);

	@Query(nativeQuery=true, value="{call sp_toll_free_call_search (:dealerId, :fromDate, :toDate, :mobileNo, :page, :size, :UserCode)}")
	List<TollFreeCallSearchResponseDto> getCallSearch(@Param("dealerId")Long dealerId, @Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("mobileNo")String mobileNo,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("UserCode")String UserCode);
	
	
	@Query(nativeQuery=true, value="{call sp_complaint_resolution_search (:dealerId, :fromDate, :toDate, :status, :complaintType,:department, :page, :size, :UserCode)}")
	List<ComplaintResolutionResponseDto> getComplaintResoltionSearchSearch(@Param("dealerId")Long dealerId, @Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("status")String status,
			@Param("complaintType")String complaintType,
			@Param("department")String department,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("UserCode")String UserCode);
	
	@Query(nativeQuery=true, value="select u.employee_name+' / '+ d.designation honame from ADM_HO_USER(nolock) u inner join ADM_HO_MST_DESIGNATION(nolock) d on u.ho_designation_id = d.id where u.id=:userId")
	String getHoUserNameById(Long userId);
	
	@Query(nativeQuery=true, value="{call SP_GET_TSM_DETAILS(:pincode,:dealerId)}")
	Map<String, Object> getTsmDetails(@Param("pincode")String pincode,@Param("dealerId")Integer dealerId);
	
	@Modifying
	@Query(nativeQuery=true, value="{call sp_complaint_resolution_update (:complaintId, :complaintType, :reasonForDelayFrt, :reasonForDelayArt, :actionTaken,:isInvalid, :loginId)}")
	void updateComplaintResolutionDetails(@Param("complaintId") Long complaintId,
			@Param("complaintType") String complaintType,
			@Param("reasonForDelayFrt") String reasonForDelayFrt,
			@Param("reasonForDelayArt") String reasonForDelayArt,
			@Param("actionTaken") String actionTaken,
			@Param("isInvalid") Boolean isInvalid,
			@Param("loginId")Long loginId);
}

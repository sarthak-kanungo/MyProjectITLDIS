package com.i4o.dms.itldis.training.trainingProgrammeCalendar.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.training.trainingProgrammeCalendar.domain.TrainingProgramCalendarHeader;
import com.i4o.dms.itldis.training.trainingProgrammeCalendar.dto.TrainingProgCalendarSearchResponse;

@Transactional
public interface TrainingProgramCalendarRepo extends JpaRepository<TrainingProgramCalendarHeader, Long> {

	@Query(value = "{call sp_Get_Training_Mst_Location(:type)}", nativeQuery = true)
	List<Map<String, Object>> getProgramLocation(@Param("type") String type);

	@Query(value = "{call sp_Get_Training_Mst_Type(:departmentName,:type)}", nativeQuery = true)
	List<Map<String, Object>> getTrainingType(@Param("departmentName") String departmentName, @Param("type") String type);

	@Query(value = "{call sp_Get_Training_Mst_Module(:trainingTypeId,:type)}", nativeQuery = true)
	List<Map<String, Object>> getTrainingModule(@Param("trainingTypeId") Long trainingTypeId,
			@Param("type") String type);

	@Query(value = "{call sp_Get_Training_Mst_Module(:trainingTypeId,:type)}", nativeQuery = true)
	List<Map<String, Object>> getApplicableState(@Param("trainingTypeId") Long trainingTypeId,
			@Param("type") String type);

	@Query(value = "{call sp_Get_Dealers_By_States(:allStateName)}", nativeQuery = true)
	List<Map<String, Object>> getDealerName(@Param("allStateName") String allStateName);

	@Query(value = "{call sp_Search_Training_Prog_HDR(:userCode,:departmentName,:programNo,:trainingLocationId,:trainingModuleId,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	List<TrainingProgCalendarSearchResponse> tpcSearch(@Param("userCode") String userCode,@Param("departmentName") String departmentName,
			@Param("programNo") String programNo, @Param("trainingLocationId") Long trainingLocationId,
			@Param("trainingModuleId") Long trainingModuleId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("page") Integer page, @Param("size") Integer size);

//	TrainingProgramCalendarHeader findByProgramId(Long programId);
	
	@Query(value = "{call sp_get_Training_Prog_DTL(:programeId,:Header)}", nativeQuery = true)
	Map<String, Object> getHeaderDetailsByProgramId(@Param("programeId") Long programeId, @Param("Header") String Header);

	@Query(value = "{call sp_Training_PROG_No_Auto_Search(:programeNo, :userCode, :departmentName)}", nativeQuery = true)
	List<Map<String, Object>> getProgramNo(
			@Param("programeNo") String programeNo,
			@Param("userCode") String userCode,
			@Param("departmentName") String departmentName);

	@Query(value = "{call sp_Search_Nominee_Person_Details(:programeNo)}", nativeQuery = true)
	List<Map<String, Object>> getNomineeDetails(@Param("programeNo") String programeNo);

	@Modifying
	@Query(value = "delete from  TR_PROG_HOLIDAY_DTL where program_id=:programeId", nativeQuery = true)
	void deleteHolidayDates(@Param("programeId") Long  programeId);
	
	
	@Modifying
	@Query(value = "delete from  TR_PROG_DLR_DTL where program_id=:programeId", nativeQuery = true)
	void deleteDealerDetails(@Param("programeId") Long  programeId);
	
	@Modifying
	@Query(value = "update  TR_PROG_NOMIN_DTL set Nomination_Status=:nominationStatus, Attended_status=:attended where Program_Nomination_DTL_id=:programNominationDtlId", nativeQuery = true)
	void aproveNominies(@Param("nominationStatus") String  nominationStatus,
			@Param("attended")Character attended,
			@Param("programNominationDtlId") Long  programNominationDtlId);
	
	@Modifying
	@Query(value="update H set Status='Approved' from TR_PROG_NOMIN_HDR H inner join TR_PROG_NOMIN_DTL D on H.Program_Nomination_HDR_id = D.Program_Nomination_HDR_id where D.Program_Nomination_DTL_id=:id", nativeQuery=true)
	void updateNominieHdrStatus(@Param("id")Long id);
}

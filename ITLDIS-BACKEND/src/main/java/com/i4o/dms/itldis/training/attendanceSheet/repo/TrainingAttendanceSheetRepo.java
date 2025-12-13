package com.i4o.dms.itldis.training.attendanceSheet.repo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.connection.ConnectionConfiguration;
import com.i4o.dms.itldis.training.attendanceSheet.domain.NomineAttendanceSheet;
import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingAttendanceSheetSearchResponse;

@Transactional
public interface TrainingAttendanceSheetRepo  extends JpaRepository<NomineAttendanceSheet, Long>, ConnectionConfiguration{
	
	@Query(value = "{call sp_Training_PROG_No_For_Attendance_Auto_Search(:programeNo)}" ,  nativeQuery = true)
	List<Map<String, Object>> getProgramNoForNominee(@Param("programeNo") String programeNo);
	
	@Query(value = "{call sp_Get_PROG_Nominee_Person_DTL(:programId)}", nativeQuery = true)
	List<Map<String, Object>> getProgramAndNomineeDtl(@Param("programId") Long programId);
	
	@Query(value = "{call sp_Get_PROG_Nominee_Person_DTL_For_View(:programId)}", nativeQuery = true)
	List<Map<String, Object>> getProgramAndNomineeDtlView(@Param("programId") Long programId);
	
	@Query(value = "{call sp_Get_Training_PROG_Trainer(:departmentName)}" ,  nativeQuery = true)
	List<Map<String, Object>> getAttendanceTrainers(@Param("departmentName") String departmentName);
	
	@Modifying
	@Query(value = "update  TR_PROG_HDR set Trainer_1=:trainer1, Trainer_2=:trainer2 where program_id=:programeId", nativeQuery = true)
	void updateTrainerInTRProgHrd(@Param("trainer1") Long  trainer1,@Param("trainer2") Long  trainer2, @Param("programeId") Long  programeId);
	
	@Query(value = "{call sp_Search_Training_PROG_Nominee_Attendance(:userCode,:departmentName,:programNo,:trainingLocationId,:trainingModuleId,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	List<TrainingAttendanceSheetSearchResponse> attendaceSheetSearch(@Param("userCode") String userCode,@Param("departmentName") String departmentName,
			@Param("programNo") String programNo, @Param("trainingLocationId") Long trainingLocationId,@Param("trainingModuleId") Long trainingModuleId, 
			@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("page") Integer page, @Param("size") Integer size);
	
	@Query(value = "select Trainer_1 as trainer1, Trainer_2 as trainer2 from TR_PROG_HDR where  program_id=:programeId", nativeQuery = true)
	Map<String, Object> viewTrainers(@Param("programeId") Long  programeId);
	
//	@Query(value = "select format(Training_Date,'dd-MM-yyyy')  as trainingDate from TR_PROG_NOMIN_ATTENDANCE_DTL where Program_Nomination_DTL_id=:programNominationDtlId", nativeQuery = true)
//	List<String> getTrainingDates(@Param("programNominationDtlId") BigInteger  programNominationDtlId);
	
	@Query(value = "{call sp_Get_PROG_Attendance_Training_Date(:programNominationDtlId)}", nativeQuery = true)
	Object getTrainingDates(@Param("programNominationDtlId") BigInteger programNominationDtlId);
	
	List<NomineAttendanceSheet> findByProgramNominationDtlId(Long programNominationDtlId);
	
	@Modifying
	@Query(value = "{call SP_TR_DELETE_OLD_ATTENDANCE(:programId)}", nativeQuery = true)
	void deleteOldAttendance(@Param("programId") Long programId);
	
	@Modifying
	@Query(value = "{call SP_TR_UPDATE_INDEXES_FOR_EMPLOYEES(:programId, :nominationDtlId, :preTest, :postTest, :growthIndex, :avgGrowthIndex)}", nativeQuery = true)
	void updateIndexesValuesForEmployees(
			@Param("programId") Long programId,
			@Param("nominationDtlId") Long nominationDtlId,
			@Param("preTest") float preTest,
			@Param("postTest") float postTest,
			@Param("growthIndex") float growthIndex,
			@Param("avgGrowthIndex") float avgGrowthIndex);
	
	@Query(value = "{call SP_TR_GET_NOMINATION_EMP_INDEX(:programeId)}", nativeQuery = true)
	List<Map<String, Object>> viewNominationEmpIndex(@Param("programeId") Long  programeId);
	
	@Query(value = "select id, Document_Name, Document_Path from TR_PROG_NOMIN_DOC_DTL where Program_id = :programeId", nativeQuery = true)
	List<Map<String, Object>> viewAttendanceDoc(@Param("programeId") Long  programeId);

}

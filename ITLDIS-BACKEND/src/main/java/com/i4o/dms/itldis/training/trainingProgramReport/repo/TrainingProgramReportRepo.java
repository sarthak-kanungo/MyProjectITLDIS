package com.i4o.dms.itldis.training.trainingProgramReport.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.training.trainingProgramReport.domain.TrainingProgramReportHeader;
import com.i4o.dms.itldis.training.trainingProgramReport.dto.TrainingProgramReportSearchResponse;

public interface TrainingProgramReportRepo extends JpaRepository<TrainingProgramReportHeader, Long> {
	
	@Query(value = "{call sp_getOrgLevelHierForMaster(:userName,:orgLevel,:orghierId,:include)}", nativeQuery = true)
	List<Map<String, Object>> getZoneForTrainingReport(@Param("userName") String userName, @Param("orgLevel") Long orgLevel,@Param("orghierId") Long orghierId,  @Param("include") char include);
	
	
	@Query(value = "{call sp_Get_Training_Mst_Location(:type)}", nativeQuery = true)
	List<Map<String, Object>> getTprRegion(@Param("type") String type);
	
	
	
    @Query(value = "{call sp_training_PROG_Attendance_Report(:userCode,:stateId,:tsmNameID,:dealerNameId,:dealerDesigId,:empStatus,:trainingTypeId,:trainingModuleId,:fromDate,:toDate)}", nativeQuery = true)
    List<TrainingProgramReportSearchResponse> downloadTrainingReportExcel(@Param("userCode") String userCode,
    														   @Param("stateId") Long stateId,
    														   @Param("tsmNameID") Long tsmNameID,
    														   @Param("dealerNameId") Long dealerNameId,
    														   @Param("dealerDesigId") Long dealerDesigId,
    														   @Param("empStatus") char empStatus,
    														   @Param("trainingTypeId") Long trainingTypeId,
    														   @Param("trainingModuleId") Long trainingModuleId,
                                                               @Param("fromDate") String fromDate,
                                                               @Param("toDate") String toDate);

}

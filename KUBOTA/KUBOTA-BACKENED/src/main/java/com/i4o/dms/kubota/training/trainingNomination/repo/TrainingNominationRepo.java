package com.i4o.dms.kubota.training.trainingNomination.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.training.trainingNomination.domain.TrainingNominationHeader;
import com.i4o.dms.kubota.training.trainingNomination.dto.TrainingNominationSearchResponse;

@Transactional
public interface TrainingNominationRepo extends JpaRepository<TrainingNominationHeader, Long>{
	
	@Query(value = "{call sp_Get_PROG_HDR_For_Nominee(:userId,:programNo)}", nativeQuery = true)
	List<Map<String, Object>> getProgramNomineeProgDtl(@Param("userId") Long userId,@Param("programNo") String programNo);

	
	@Query(value = "{call sp_Dealer_MT_Employee_By_Designation(:designationId,:searchName,:trainingModule,:typeofTraining,:dealerId)}", nativeQuery = true)
	List<Map<String, Object>> getAutoEmpName(@Param("designationId") String designationId,
			@Param("searchName") String searchName,
			@Param("trainingModule") String trainingModule,
			@Param("typeofTraining") String typeofTraining,
			@Param("dealerId")Long dealerId);
	
	@Query(value = "{call sp_Get_Auto_Search_Training_PROG_Nominee_Number(:loginId,:searchName)}", nativeQuery = true)
	List<Map<String, Object>> getAutoNominationNo(@Param("loginId") Long loginId,@Param("searchName") String searchName);
	
	@Query(value = "{call sp_Get_Employee_Detail_For_Nomination(:programId,:employeeId)}", nativeQuery = true)
	Map<String, Object> getNomineeEmpDetails(@Param("programId") Long programId,@Param("employeeId") Long employeeId);
	
	@Query(value = "{call sp_search_training_Nominee_DTL(:loginId,:programId,:nomineeId,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	List<TrainingNominationSearchResponse> nomineeSearch(@Param("loginId") Long loginId,@Param("programId") Long programId, @Param("nomineeId") Long nomineeId,
			@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("page") Integer page, @Param("size") Integer size);
	
	
	TrainingNominationHeader findByNominationId(Long programId);
	
	@Modifying
	@Query(value="delete from TR_PROG_NOMIN_DTL where Program_Nomination_DTL_id in (:ids)", nativeQuery=true)
	void deleteDtls(@Param("ids") List<Long> ids);
	
	
	

	@Query(value = "select id, ISNULL(stuff(coalesce(' '+nullif(first_name, ''), '') + coalesce(' '+nullif(middle_name, ''), '') + coalesce(' '+nullif(Last_name, ''), ''),1,1,''),'') as employeeName,\r\n"
			+ "designation_id as designationId, employee_code as employeeCode  from ADM_DEALER_EMP  where id=:employeeId", nativeQuery = true)
	Map<String, Object> nomineeEmpDetails(@Param("employeeId") Long  employeeId);
	
	
	//Suraj--05/01/2023 START
	@Query(value = "{call SP_GET_PROGRAM_NOMINEE_HDR(:programNo)}", nativeQuery = true)
	Map<String, Object> getProgramNomineeProgHdr(@Param("programNo") String programNo);
	//Suraj--05/01/2023 END
	
}


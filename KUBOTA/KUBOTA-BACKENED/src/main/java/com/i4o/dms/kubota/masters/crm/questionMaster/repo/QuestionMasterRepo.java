package com.i4o.dms.kubota.masters.crm.questionMaster.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.crm.questionMaster.domain.QuestionMasterHeader;
import com.i4o.dms.kubota.masters.crm.questionMaster.dto.QuestionMasterSearchResponse;

public interface QuestionMasterRepo extends JpaRepository<QuestionMasterHeader, Long>{
	
	@Query(value = "{call sp_QA_Survey_type_List}",  nativeQuery = true)
	List<Map<String,Object>> getQuestionType();
	
	@Query(value = "{call sp_QA_Ques_Mst_Code_Auto(:questionCode)}",  nativeQuery = true)
	 List<Map<String, Object>> getAutoQuestionCode(@Param("questionCode") String questionCode);
	
	@Query(value = "{call sp_QA_Ques_SubQues_DTL_Search(:loginId,:questionId,:fromDate,:toDate,:page,:size)}",nativeQuery = true)
	List<QuestionMasterSearchResponse> searchQuestionMaster(@Param("loginId") Long loginId,@Param("questionId") Long questionId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("page") Integer page, @Param("size") Integer size);
	
	QuestionMasterHeader findByQuesId(Long questionId);

}

package com.i4o.dms.itldis.masters.crm.questionMaster.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.crm.questionMaster.domain.QuestionMainAnswer;
import com.i4o.dms.itldis.masters.crm.questionMaster.domain.QuestionMainSubAnswer;
import com.i4o.dms.itldis.masters.crm.questionMaster.domain.QuestionMasterHeader;
import com.i4o.dms.itldis.masters.crm.questionMaster.dto.QuestionMasterSearchDto;
import com.i4o.dms.itldis.masters.crm.questionMaster.dto.QuestionMasterSearchResponse;
import com.i4o.dms.itldis.masters.crm.questionMaster.repo.QuestionMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin( allowedHeaders = {"Origin","X-Requested-With","Content-Type","Accept","Authorization"}, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })

@RequestMapping("/api/master/crm-masters/question-master")
public class QuestionMasterController {
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
	private QuestionMasterRepo questionMasterRepo;
	
	
	@GetMapping(value = "/getQuestionType")
	ResponseEntity<ApiResponse<Object>> getSurveyType(){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> surveyType = questionMasterRepo.getQuestionType();
		apiResponse.setResult(surveyType);
		apiResponse.setMessage("Survet Type Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping(value ="saveQuestionMaster")
	public ResponseEntity<?> saveQuestionMaster(@RequestBody QuestionMasterHeader questionHeader){
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		try {
			questionHeader.setCreatedBy(userAuthentication.getLoginId());
			questionHeader.setCreatedDate(new Date());
			if(questionHeader.getMainAnswer()!=null) {
				questionHeader.getMainAnswer().forEach(ans->{
					ans.setCreatedBy(userAuthentication.getLoginId());
					ans.setCreatedDate(new Date());
					if(ans.getSubAnswerApplicable()=='Y') {
						ans.getSubAnswer().forEach(sub ->{
							sub.setCreatedBy(userAuthentication.getLoginId());
							sub.setCreatedDate(new Date());
							
						});
					}
				});
			}
			questionMasterRepo.save(questionHeader);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Question Master Save Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Question Master can't Save");
		}
		return ResponseEntity.ok(response);
	}
	
    @GetMapping("/getAutoQuestionCode")
    public ResponseEntity<?> getAutoQuestionCode(@RequestParam("questionCode") String questionCode) {
        ApiResponse<Object> response = new ApiResponse<>();
        List<Map<String, Object>> quesCode = questionMasterRepo.getAutoQuestionCode(questionCode);
        response.setMessage("Get Question Code Successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setResult(quesCode);
        return ResponseEntity.ok(response);

    }
	
	@PostMapping("/searchQuestionMaster")
	public ResponseEntity<?> searchQuestionMaster(@RequestBody QuestionMasterSearchDto searchQuestion) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<QuestionMasterSearchResponse> searchReasult = questionMasterRepo.searchQuestionMaster(1L,searchQuestion.getQuestionId(),
				searchQuestion.getFromDate(),searchQuestion.getToDate(),searchQuestion.getPage(),searchQuestion.getSize());
		Long count = 0l;
		if (searchReasult != null && searchReasult.size() > 0) {
			count = searchReasult.get(0).getRecordCount();
		}
		response.setMessage("Question Master get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(searchReasult);
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	 @GetMapping("/getViewEditData")
	    public ResponseEntity<?> questionMasterView(@RequestParam Long questionId) {
		 ApiResponse<Object> response = new ApiResponse<>();
		 QuestionMasterHeader view= questionMasterRepo.findByQuesId(questionId);
		 response.setResult(view);
		 response.setMessage("Get Question Master view Data");
		 response.setStatus(HttpStatus.OK.value());
	     return ResponseEntity.ok(response);

	 }
	 
	 @PostMapping(value = "/updateQuestionMaster")
		public ResponseEntity<?> updateQuestionMaster(@RequestBody QuestionMasterHeader questionHeader){
			ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
			try {
				QuestionMasterHeader questionUpdate = questionMasterRepo.getOne(questionHeader.getQuesId());
				questionUpdate.setSurveyTypeId(questionHeader.getSurveyTypeId());
				questionUpdate.setQuestionDesc(questionHeader.getQuestionDesc());
				questionUpdate.setIsactive(questionHeader.getIsactive());
				questionUpdate.setModifiedBy(userAuthentication.getLoginId());
				questionUpdate.setModifiedDate(new Date());
				questionUpdate.setMainAnswer(questionHeader.getMainAnswer());
				
				if(questionUpdate.getMainAnswer()!=null) {
					Iterator<QuestionMainAnswer> iter = (questionUpdate.getMainAnswer()).iterator();
					while (iter.hasNext()) {
						QuestionMainAnswer mainAns= iter.next();
						if(mainAns.getQuesDtlId() == null) {
							mainAns.setCreatedBy(userAuthentication.getLoginId());
							mainAns.setCreatedDate(new Date());
						}else {
							mainAns.setModifiedBy(userAuthentication.getLoginId());
							mainAns.setModifiedDate(new Date());
						}
						if(mainAns.getSubAnswerApplicable()=='Y') {
							Iterator<QuestionMainSubAnswer> iterSub = mainAns.getSubAnswer().iterator();
							while (iterSub.hasNext()) {
								QuestionMainSubAnswer subAns= iterSub.next();
								if(subAns.getQuesSubDtlId() == null) {
									subAns.setCreatedBy(userAuthentication.getLoginId());
									subAns.setCreatedDate(new Date());
								}else {
									subAns.setModifiedBy(userAuthentication.getLoginId());
									subAns.setModifiedDate(new Date());
									}
							}
						}
					}
					
				}
				questionMasterRepo.save(questionUpdate);
				response.setStatus(HttpStatus.OK.value());
				response.setMessage("Question Master update Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				response.setMessage("Question Master can't update");
			}
			return ResponseEntity.ok(response);
		}

}

package com.i4o.dms.itldis.crm.crmmodule.directsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyRecordings;

public interface SurveyRecordingRepo extends JpaRepository<SurveyRecordings, Long>{
	
	SurveyRecordings findBySurveyHdrId(Long surveyHdrId);

}

package com.i4o.dms.itldis.crm.crmmodule.directsurvey.response;


import java.util.List;

import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.CallAttempt;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.CurrentSurveyQuestions;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyComplaint;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyCropGrown;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyHeader;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyMajorImplements;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyOtherMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SurveyDto {
	private SurveyHeader surveyHeader;
	private List <SurveyMajorImplements> implementDetails;
	private List <SurveyCropGrown> crops;
	private List <SurveyOtherMachine> otherPurchaseDetails;
	private List <SurveyComplaint> complaint;
	private List <CallAttempt> callAttempt;
	private List <CurrentSurveyQuestions> currentSurveyQuesAns;

}

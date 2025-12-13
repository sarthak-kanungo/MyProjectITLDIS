package com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="QA_SURVEY_RECORDING_DTL")
public class SurveyRecordings {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long surveyRecordingId;
	  private Long surveyHdrId;
	  @NotNull(message = "Recording can't be null")
	  @Column(length = 300)
	  private String recording;


}

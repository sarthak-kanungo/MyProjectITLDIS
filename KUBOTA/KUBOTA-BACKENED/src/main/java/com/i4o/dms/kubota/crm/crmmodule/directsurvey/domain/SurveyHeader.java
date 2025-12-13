package com.i4o.dms.kubota.crm.crmmodule.directsurvey.domain;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Entity
@Table(name="QA_Survey_HDR")
public class SurveyHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long surveyHdrId; 
	private Long surveyRemdId;
	private Long surveyTypeId;
	private Long vinId;

	@JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfInstallation;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date warrantyValidTill;
	
	private String surveyNo;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date surveyDate;
	
	private String surveyStatus;
	private Long surveyDoneBy;
	private String surveyLocation;
	private String soldDealerName;
	private long customerMasterId;
	private String customerCode;
	private String customerName;
	private String customerMobileNumber;
	private String customerAlternateNo;
	private String customerAddress;
	private String country;
	private String state;
	private String district;
	private String tehsil;
	private String city;
	private String postOffice;
	private Integer pinCode;
	private String panNo;
	private String gstNo;
	private String preferedLanguage;
	private String satisfactionLevel;
	private String contactPersonName;
	private String contactPersonMobileNo;
	private Long contactPersonProfileId;
	private String contactPersonAge;
	private String ageOfMachineInMonth;
	private Integer hoursMeterReading;
	private String firstTimeBuyer;
	private String brandModel;
	private Long factorInfluencedId;
	private String refCustomerName1;
	private String refCustomerMobileNo1;
	private String refCustomerAddress1;
	private String refCustomerVillage1;
	private String refCustomerName2;
	private String refCustomerMobileNo2;
	private String refCustomerAddress2;
	private String refCustomerVillage2;
	private String additionalComments; 
	@Column(updatable=false)
    private Date createdDate = new Date();
	@Column(updatable=false)
	private Long createdBy;
    private Date modifiedDate;
	private Long modifiedBy;
	private Long soldToDealerId;
	
	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <SurveyMajorImplements> implementDetails;
	
	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <SurveyCropGrown> crops;
	
	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <SurveyOtherMachine> otherPurchaseDetails;
	
	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <SurveyComplaint> complaint;
	
//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List <CallAttempt> callAttempt;
	
	
	@OneToMany(mappedBy = "surveryHdr", cascade = CascadeType.ALL )
	@JsonManagedReference
	private List <CurrentSurveyQuestions> surveyQuestions;
	

	

}

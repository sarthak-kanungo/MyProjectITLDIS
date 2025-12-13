package com.i4o.dms.kubota.training.trainingProgrammeCalendar.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@ToString
@Getter
@Setter
@Entity
@Table(name="TR_PROG_HDR")
public class TrainingProgramCalendarHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "Program_id")
	private Long programId;
	
	@Column(name = "Program_number")
	private String programNumber;
	
	@NotNull(message = "ProgramDate is mandatory")
	@Column(name = "Program_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date programDate;
	
	@NotNull(message = "Program Location is mandatory")
	@Column(name = "Training_loc_id")
	private Long programLocationId;
	
	@NotNull(message = "Training Module is mandatory")
	@Column(name = "Training_Module_id")
	private Long trainingModuleId;
	
	@Column(name = "Location")
	private String location;
	
	@NotNull(message = "Start Date is mandatory")
	@Column(name = "Start_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	
	@NotNull(message = "End Date is mandatory")
	@Column(name = "End_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date endDate;
	
	@NotNull(message = "Last date Of Nominees is mandatory")
	@Column(name = "Last_Nomination_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date lastNominationDate;
	
	@NotNull(message = "Max No Of Nominees is mandatory")
	@Column(name = "Max_No_Of_Nominees")
	private Integer maxNoOfNominees;
	
	@NotNull(message = "No Of Nominees is mandatory")
	@Column(name = "No_Of_Allowed_Nominees")
	private Integer noNominees;
	
//	private Integer noTrainees;
	
	@Column(name = "Trainer_1")
	private String trainer1;
	
	@Column(name = "Trainer_2")
	private String trainer2;
	
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "Active")
	private char active;
	
	@Column(name = "Created_by")
	private Long createdBy;
	
	@Column(name = "Created_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	
	@Column(name = "Modified_by")
	private Long modifiedBy;
	
	@Column(name = "Modified_Date")
	private Date modifiedDate;
	
	
	
	@OneToMany(mappedBy = "tcpHeader", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TrainingProgramCalendarDealerDetails> tpcDealerDetails;
	
	@OneToMany(mappedBy = "tcpHeader", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TrainingProgramCalendarHolidaysDetails>tpcHolidayDetails;

}

package com.i4o.dms.kubota.training.trainingProgrammeCalendar.domain;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name="TR_PROG_DLR_DTL")
public class TrainingProgramCalendarDealerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long programDlrDtlId;
	//private Long programId;
	private Long dealerId;
	private char active;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Program_id")
	private TrainingProgramCalendarHeader tcpHeader;
	

}

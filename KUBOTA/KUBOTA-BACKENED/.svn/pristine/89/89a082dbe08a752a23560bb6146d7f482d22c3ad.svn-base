package com.i4o.dms.kubota.training.attendanceSheet.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TR_PROG_NOMIN_DOC_DTL")
public class TrainingAttendanceSheetDoc {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
//	  private Long programNominationHdrId; 	//programNominationHdrId
	  private Long ProgramId;
	  private String documentName;
	  private String documentPath;
	  @JsonFormat(pattern = "dd-MM-yyyy")
	  private Date createdDate;

}

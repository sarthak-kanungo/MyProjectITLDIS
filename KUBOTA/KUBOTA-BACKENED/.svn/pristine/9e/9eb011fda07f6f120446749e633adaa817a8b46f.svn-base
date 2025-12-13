package com.i4o.dms.kubota.training.attendanceSheet.domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="TR_PROG_NOMIN_ATTENDANCE_DTL")
public class NomineAttendanceSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Nominees_Attendance_DTL_id")
	private Long nomineeAttendanceDtlId;
	
	@Column(name = "Program_Nomination_DTL_id")
	private Long programNominationDtlId;
	
	@Column(name = "Training_Date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date trainingDate;
	
	@Column(name = "Attendance")
	private char attendance;
}

package com.i4o.dms.itldis.training.trainingProgrammeCalendar.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name="TR_PROG_HOLIDAY_DTL")
public class TrainingProgramCalendarHolidaysDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long programHolidDtlId;
	//private Long programId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date holidayDate;
	private char Active;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Program_id")
	private TrainingProgramCalendarHeader tcpHeader;

}

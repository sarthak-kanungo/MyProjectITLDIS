package com.i4o.dms.kubota.training.trainingNomination.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@Entity
@Table(name="TR_PROG_NOMIN_DTL")
public class TrainingNominationDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "Program_Nomination_DTL_id")
	private Long nominationDtlId;
	
	@Column(name = "employee_id", updatable=false)
	private Long employeeId;
	
	@Column(name = "Nomination_Status")
	private String status;
	
	@Column(name = "Attended_Status")
	private char attended;
	
	@JsonProperty("tShirtSize")
	@Column(name = "T_Shirt_Size", updatable=false)
	private String tShirtSize;
	
	@Column(name = "Active")
	private char active;
	
	@Column(name = "pre_test")
	private float preTest;
	
	@Column(name = "post_test")
	private float postTest;
	
	@Column(name = "growth_index")
	private float growthIndex;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Program_Nomination_HDR_id")
	private TrainingNominationHeader tnHeader;
	
	@Column(name = "Created_by", updatable=false)
	private Long createdBy;
	
	@Column(name = "Created_Date", updatable=false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	
	@Column(name = "Modified_by")
	private Long modifiedBy;
	
	@Column(name = "Modified_Date")
	private Date modifiedDate;
	
	@Transient
	private BigInteger designationId;
	
	@Transient
	private String employeeName;
	
	@Transient
	private String employeeCode;
	
}

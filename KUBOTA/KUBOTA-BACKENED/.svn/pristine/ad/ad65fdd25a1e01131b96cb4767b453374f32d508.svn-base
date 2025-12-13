package com.i4o.dms.kubota.training.trainingNomination.domain;


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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="TR_PROG_NOMIN_HDR")
public class TrainingNominationHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "Program_Nomination_HDR_id")
	private Long nominationId;
	
	@Column(name = "Nomination_Number", updatable=false)
	private String nominationNumber;
	
	@Column(name = "Nomination_date", updatable=false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date nominationDate;
	
	@Column(name = "Program_id", updatable=false)
	private Long programId;
	
	@Column(name = "Dealer_id", updatable=false)
	private long dealerId;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "Created_by", updatable=false)
	private Long createdBy;
	
	@Column(name = "Created_Date", updatable=false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	
	@Column(name = "Modified_by")
	private Long modifiedBy;
	
	@Column(name = "Modified_Date")
	private Date modifiedDate;
	
	
	@OneToMany(mappedBy = "tnHeader", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TrainingNominationDetails> nominationDetails;

}

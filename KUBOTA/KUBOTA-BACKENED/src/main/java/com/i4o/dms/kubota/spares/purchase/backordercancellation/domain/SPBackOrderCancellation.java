package com.i4o.dms.kubota.spares.purchase.backordercancellation.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BACK_ORDER_CANCELLATION")
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class SPBackOrderCancellation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bocNo = "DRA/" + ThreadLocalRandom.current().nextInt(1000) + "/" + System.currentTimeMillis();
	
	@Column(columnDefinition = "Default draftFlag value as 'false'")
    private Boolean draftFlag = false;
	
	@Column(columnDefinition = "It accept 'Draft' and 'Submitted' as values")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "dealer_id", referencedColumnName = "id")
	private DealerMaster dealerMaster;
	
	@ManyToOne
	@JoinColumn(name = "req_given_by_id")
	private DealerEmployeeMaster employeeMaster;
	
	@OneToMany(mappedBy = "orderCancellation", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SPBackOrderCancellationDtl> cancellationDtls;
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
	
}

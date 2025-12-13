package com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Setter
@Getter
@Entity
@Table(name = "incentive_scheme_master")
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class IncentiveSchemeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("schemeNo")
    private String schemeNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    
    private String schemeType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date schemeDate=new Date();
    
    @JsonProperty("referenceSchemeNo")
    private String referenceSchemeNumber;
    		
    private String status = "ACTIVE";

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date validTo;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date validFrom;

    @JsonProperty("maxQty")
    private Boolean maximumQuantity = false;
    
    @JsonProperty("claimAttachReq")
    private Boolean claimAttachmentRequired = false;
    
    @JsonProperty("exceedQty")
    private Boolean incentiveAllowedForExceededQuantity=false;

    private String sZone;
    private String sRegion;
    private String sProduct;
    private String sSeries;
    private String sModel;
    private String sSubModel;
    private String sVariant;
    private String sItem;
    
    @ManyToOne
    @JoinColumn(name = "activity_proposal_id")
    private MarketingActivityProposal activityProposal;
    
    @JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="incentiveSchemeMaster")
	private List<IncentiveSchemeMasterDetail> incentiveSchemeDetails; 
    
    @Column(updatable = false)
    private Long createdBy;
    @Column(updatable = false)
    private Date createdDate;
    
    private Long lastModifiedBy;
    private Date lastModifiedDate;
    
    @Transient
    private List<String> product;
    
    @Transient
    private List<String> series;
    
    @Transient
    private List<String> model;
    
    @Transient
    private List<String> submodels;
    
    @Transient
    private List<String> variant;
    
    @Transient
    private List<String> itemNo;
    
    @Transient
    private List<String> zones;
    
    @Transient
    private List<String> regions;  
    
    @Transient
    private IncentiveSchemeMasterAttachment schemeAttachment;
	
}


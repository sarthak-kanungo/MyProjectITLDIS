package com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WARRANTY_RETROFITMENT_CAMPAIGN")
public class WarrantyRetrofitmentCampaign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 21)
	private String retrofitmentNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
    private Date retrofitmentDate;
    
    @NotNull(message = "status can't be null")
    private String status = "Open";
    
    @NotNull(message = "campaign name can't be null")
    private String campaignName;

    @NotNull(message = "start date can't be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @NotNull(message = "end date  can't be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    
    private String dataFilePath;
    
    @Transient
	Set<WarrantyRetrofitmentChassisInfo> warrantyRetroFitmentChassisInfo;

    @OneToMany(mappedBy = "warrantyRetrofitmentCampaign",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyRetrofitmentCampaignItem> warrantyRetrofitmentCampaignItemList;
    
    @OneToMany(mappedBy = "warrantyRetrofitmentCampaign",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyRetrofitmentJcLabourChargeInfo> warrantyRetrofitmentJcLabourChargeInfoList;
    
    @OneToMany(mappedBy = "warrantyRetrofitmentCampaign",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyRetrofitmentCampaignPhoto> warrantyRetrofitmentCampaignPhoto;
    
    @Column(updatable=false)
    private Long createdBy;
    
    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
    
}

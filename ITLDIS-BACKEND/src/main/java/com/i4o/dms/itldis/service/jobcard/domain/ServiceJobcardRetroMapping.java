package com.i4o.dms.itldis.service.jobcard.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineVinMaster;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SV_JC_RETRO_MAPPING")
public class ServiceJobcardRetroMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JsonBackReference
    @JoinColumn(name = "job_card_id", referencedColumnName = "id")
    private ServiceJobCard serviceJobCard;
	
	@ManyToOne
    @JoinColumn(name = "retrofitment_id", referencedColumnName = "id")
    private WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign;	//Master table for service Jobcard-Retro mapping
	
	@ManyToOne
    @JoinColumn(name = "vin_id")
    private MachineVinMaster machineInventory;	//Master table for service Jobcard-Retro mapping
	
	private Date retroDoneOn = new Date();
	
	@Column(updatable=false)
    private Long createdBy;
    
	@Column(updatable=false)
    private Date createdDate = new Date();

}

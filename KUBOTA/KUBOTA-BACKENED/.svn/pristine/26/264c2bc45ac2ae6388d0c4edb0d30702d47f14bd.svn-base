package com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.dbentities.service.domain.ServiceMtFrsCode;
import com.i4o.dms.kubota.masters.dbentities.service.domain.ServiceMtJobcode;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wa_retrofitment_jc_labour_charge_info")
public class WarrantyRetrofitmentJcLabourChargeInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "wa_retrofitment_id",  referencedColumnName = "id")
    private WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign;

    @ManyToOne
    @JoinColumn(name = "sv_mt_jobcode_id",  referencedColumnName = "id")
    private ServiceMtFrsCode serviceMtJobcode;
//    private ServiceMtJobcode serviceMtJobcode;

    private Double claimAmount;
    
    private long hours;

}

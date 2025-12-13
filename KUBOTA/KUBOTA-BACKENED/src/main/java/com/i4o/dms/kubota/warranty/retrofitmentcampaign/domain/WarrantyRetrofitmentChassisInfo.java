package com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "wa_retrofitment_chassis_info")
public class WarrantyRetrofitmentChassisInfo {

    @EmbeddedId
    private  WarrantyRetrofitmentCampaignChassisId warrantyRetrofitmentCampaignChassisId;


}

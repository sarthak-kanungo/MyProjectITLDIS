package com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "wa_retrofitment_campaign_photo")
public class WarrantyRetrofitmentCampaignPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonBackReference
    private WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @NotNull
    @Column(length=50)
    private String fileType;

    @Override
	public String toString() {
		return "WarrantyRetrofitmentCampaignPhoto [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType
				+ "]";
	}
    
}

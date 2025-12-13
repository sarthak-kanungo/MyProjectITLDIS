package com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wa_retrofitment_campaign_item")
public class WarrantyRetrofitmentCampaignItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;

    private Integer quantity = 0;

}

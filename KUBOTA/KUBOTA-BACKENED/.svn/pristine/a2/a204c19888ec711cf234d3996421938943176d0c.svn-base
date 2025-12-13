package com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain;

import com.i4o.dms.kubota.masters.service.pdc.domain.ServiceMtPdcAggregate;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.domain.MachineAllotment;
import com.i4o.dms.kubota.warranty.logsheet.dto.WarrantyLogsheetViewDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class WarrantyRetrofitmentCampaignChassisId implements Serializable {

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "retrofitment_id")
    private WarrantyRetrofitmentCampaign warrantyRetroFitmentCampaign;

    @JoinColumn(name = "chassis_no")
    private String chassisNo;

    public WarrantyRetrofitmentCampaignChassisId(WarrantyRetrofitmentCampaign warrantyRetroFitmentCampaign, String chassisNo)
    {
       this.warrantyRetroFitmentCampaign = warrantyRetroFitmentCampaign;
       this.chassisNo = chassisNo;
    }



}

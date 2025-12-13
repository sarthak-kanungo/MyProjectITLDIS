package com.i4o.dms.itldis.service.jobcard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.masters.dbentities.service.domain.ServiceMtJobcode;
import com.i4o.dms.itldis.masters.spares.SpareMtPartCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "sv_jc_outside_charge_info")
public class OutsideJobCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("jobcodeId")
    private  Long id;

    @ManyToOne
    @JsonBackReference("outsideJobCharge")
    @JoinColumn(name = "service_Jobcard_id")
    private ServiceJobCard serviceJobCard;

    @ManyToOne
    @JoinColumn(name = "service_mt_jobcode_id")
    private ServiceMtJobcode serviceMtJobcode;

    private Double amount;

    @Column(updatable=false)
    private Double pcrAmount;
    @Transient
    private Double approvedAmount;
    @Transient
    private Double goodwillAmount;
    @Transient
    private Double goodwillApprovedAmount;
    @Transient
    private Double claimApprovedAmount;
    @Transient
    private String inspectionRemark;
    @Transient
    private Boolean claimable;
    

    @ManyToOne
    @JoinColumn(name = "labour_charge_category_id")
    private SpareMtPartCategory category;

    @Column(columnDefinition ="boolean default false",name ="delete_flag")
    private Boolean isSelected=false;



//    @ManyToOne
//    @JsonBackReference(value = "outsideCharge")
//    private KaiInspectionSheet kaiInspectionSheet;

}

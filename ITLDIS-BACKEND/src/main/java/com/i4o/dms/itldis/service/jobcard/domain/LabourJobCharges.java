package com.i4o.dms.itldis.service.jobcard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.masters.dbentities.service.domain.ServiceMtFrsCode;
import com.i4o.dms.itldis.masters.spares.SpareMtPartCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Entity
@Setter
@Table(name = "sv_jc_labour_charge_info")
public class LabourJobCharges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("labourChargeId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "frs_code_id")
    private ServiceMtFrsCode serviceMtFrsCode;

    private Double hour;

    @ManyToOne
    @JoinColumn(name = "service_jobcard_id")
    @JsonBackReference("labourCharge")
    private ServiceJobCard serviceJobCard;

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
    @JoinColumn(name = "os_labour_charge_category_id")
    private SpareMtPartCategory category;

    @Column(columnDefinition ="boolean default false",name ="delete_flag")
    private Boolean isSelected=false;





}

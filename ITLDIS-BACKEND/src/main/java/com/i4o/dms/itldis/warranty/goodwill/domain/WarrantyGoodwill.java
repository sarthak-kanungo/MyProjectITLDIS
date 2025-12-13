package com.i4o.dms.itldis.warranty.goodwill.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.itldis.service.jobcard.domain.OutsideJobCharge;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcr;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Getter
@Setter
@Entity
@Table(name="WA_GOODWILL")
public class WarrantyGoodwill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String goodwillNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable = false)
    private Date goodwillDate = new Date();

    private Boolean draftFlag;

    private String status;

    private String goodwillType;

    private String goodwillReason;

    private Double budgetConsumed;
    
    private String dealerRemark;

    private String kaiRemark;

    @ManyToOne
    @JoinColumn(name = "warranty_pcr_id")
    private WarrantyPcr warrantyPcr;

    @Column(updatable = false)
    private Long branchId;
    @Column(updatable = false)
    private Long createdBy;
    @Column(updatable = false)
    private Date createdOn = new Date();

    private Long lastModifiedBy;

    private Date lastModifiedDate;
    @Transient
    String wcrNo;
    @Transient
    private List<SparePartRequisitionItem> sparePartRequisitionItem;
    @Transient
    private List<OutsideJobCharge> outsideJobCharge;
    @Transient
    private List<LabourJobCharges> labourCharge;

    @OneToMany(mappedBy = "warrantyGoodwill",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarrantyGoodwillPhoto> warrantyGoodwillPhoto;

    @Transient
    private String mobileNumber;
    @Transient
    private String emailId;
    @Transient
    private String dealerCode;
    @Transient
    private String createWcr;
}

package com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.dbentities.warranty.WarrantyMtFailureMode;
import com.i4o.dms.itldis.masters.dbentities.warranty.WarrantyMtFailureUnit;
import com.i4o.dms.itldis.masters.dbentities.warranty.WarrantyMtTypeOfUse;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.itldis.service.jobcard.domain.OutsideJobCharge;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.domain.WarrantyWcr;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="WA_KAI_INSPECTION_SHEET")
public class KaiInspectionSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 21,unique = true, updatable=false)
    private String inspectionNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    private Date inspectionDate;

    @ManyToOne
    @JoinColumn(name = "failure_mode_id")
    private WarrantyMtFailureMode failureMode;

    @ManyToOne
    @JoinColumn(name = "failure_unit_id")
    private WarrantyMtFailureUnit failureUnit;

    @ManyToOne
    @JoinColumn(name = "type_of_use_id")
    private WarrantyMtTypeOfUse typeOfUse;

    private String symptom;

    private String defect;

    private String remedy;

    private String claimFinalRemark;

    @ManyToOne
    @JoinColumn(name = "warranty_wcr_id")
    private WarrantyWcr warrantyWcr;

    @Transient
    private String actionType;
    
    @Transient
    private List<LabourJobCharges>labourJobCharge;

    @Transient
    private List<OutsideJobCharge>outsideJobCharge;

    @Transient
    private List<SparePartRequisitionItem> sparePartRequisitionItem;


    @OneToMany(mappedBy = "kaiInspectionSheet",cascade = CascadeType.ALL)
    @JsonManagedReference("kaiInspectionPhoto")
    private List<KaiInspectionSheetPhoto> kaiInspectionSheetPhoto;
    
    @Column(updatable=false)
    private Long inspectionBy;
    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdOn = new Date();
    
    private Long modifiedBy;

    private Date modifiedOn;
}

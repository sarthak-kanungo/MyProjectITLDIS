package com.i4o.dms.itldis.spares.partrequisition.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.SpareMtPartCategory;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.masters.warranty.domain.WarrantyMtPartFailureCode;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SP_PART_REQUISITION_ITEM")
public class SparePartRequisitionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    private Long reqQuantity;

    private String  uom;

    private Double priceUnit;

    private Double amount;

    @ManyToOne
    @JoinColumn(name="category_id")
    private SpareMtPartCategory category;

    @Column(updatable=false)
    private Integer utilisedQuantity=0;

    @Column(name = "pcr_approved_quantity")
    private Integer approvedQuantity;

    @Column(updatable=false)
    private Integer pcrQuantity;

    @Transient
    private Integer gwClaimApprovedQuantity=0;
    @Transient
    private Double gwClaimApprovedAmount;
    @Transient
    private Integer claimApprovedQuantity=0;
    @Transient
    private Double claimApprovedAmount=0d;
    @Transient
    private Integer gwClaimQuantity;
    @Transient
    private String priceType;

    private Double gstPercent;

    private Double gstAmount;//amount*gstPercent/100

    private Double totalAmount;//amount+gstAmount

    private String hsnCode;

    @ManyToOne
    @JsonBackReference("sparePartRequisitionItem")
    private ServiceJobCard serviceJobCard;

    @ManyToOne
    @JsonBackReference("sparePartRequisition")
    private SparePartRequisition sparePartRequisition;

    @ManyToOne
    @JoinColumn(name = "failure_code_id")
    private WarrantyMtPartFailureCode warrantyMtPartFailureCode;
    @Transient
    private String inspectionRemark;
    @Transient
    private Boolean claimable;
    @Transient
    private Boolean keyPartNumber;

    private Boolean qtyUpdateFlag=false;

}

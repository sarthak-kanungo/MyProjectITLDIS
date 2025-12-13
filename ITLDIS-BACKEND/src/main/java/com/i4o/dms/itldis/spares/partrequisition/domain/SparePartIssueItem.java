package com.i4o.dms.itldis.spares.partrequisition.domain;

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
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.StoreMaster;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="SP_PART_ISSUE_ITEM")
public class SparePartIssueItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reqQuantity;

    private String remark;

    private Integer returnQuantity = 0;

    private Integer issuedQuantity = 0;

    private Integer allocatedQuantity = 0;

    private Boolean invoicedFlag = false;

    private Double unitPrice;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private SpareMtPartCategory category;
    
    @Transient
    private Double mrp;
    @Transient
    private Double spegst;
    @Transient
    private Double spmgst;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocationMaster;

    @ManyToOne
    @JoinColumn(name = "spare_part_issue_id")
    @JsonBackReference
    private SparePartIssue sparePartIssue;

    @ManyToOne
    @JoinColumn(name = "advanced_spare_part_issue_id")
    private SparePartIssue advancedSparePartIssue;
}

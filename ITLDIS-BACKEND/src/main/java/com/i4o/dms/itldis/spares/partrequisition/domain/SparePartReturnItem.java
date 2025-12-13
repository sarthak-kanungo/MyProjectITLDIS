package com.i4o.dms.itldis.spares.partrequisition.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.StoreMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="SP_PART_RETURN_ITEM")
public class SparePartReturnItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String remark;

    private Integer returnQuantity;

    @ManyToOne
    @JoinColumn(name = "spare_part_issue_item_id")
    private SparePartIssueItem sparePartIssue;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocationMaster;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    @ManyToOne
    @JoinColumn(name = "spare_part_return_id")
    @JsonBackReference
    private SparePartReturn sparePartReturn;
    
    
    @Transient
    private Double mrp;
    @Transient
    private Double spegst;
    @Transient
    private Double spmgst;
    @Transient
    private Double unitPrice;
}

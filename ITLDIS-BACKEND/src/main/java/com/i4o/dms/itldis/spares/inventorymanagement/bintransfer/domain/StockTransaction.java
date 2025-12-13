package com.i4o.dms.itldis.spares.inventorymanagement.bintransfer.domain;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_no",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    private Integer totalQty;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
}

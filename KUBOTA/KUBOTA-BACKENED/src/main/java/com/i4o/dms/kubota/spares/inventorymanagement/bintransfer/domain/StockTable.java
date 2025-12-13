package com.i4o.dms.kubota.spares.inventorymanagement.bintransfer.domain;

import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.StoreMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class StockTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_no", referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    //private Branch id

    //partBranch id

    private Integer invoiceQuantity;

    private Double unitPrice;

    private Double spmgst;

    private Double spegst;

    private Double spmrp;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocationMaster;

    private Integer inQty;

    private Date stkInDate;

    private Integer outQty;

    private Date stkOutDate;

    private Integer allotmentQty;

    private Date stkAllotmentDate;

    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
}
